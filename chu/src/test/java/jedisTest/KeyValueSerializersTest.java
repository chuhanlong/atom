package jedisTest;



import java.io.Serializable;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.OxmSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chu.dao.domain.User;
import com.chu.redisTest.ApplicationConfig;

/**
 * @author Jon Brisbin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class KeyValueSerializersTest {

    @Autowired
    RedisConnectionFactory connectionFactory;

    @Autowired
    OxmSerializer oxmSerializer;

    @Test
    public void testStringLongSerializers() {
        RedisTemplate<String, Long> redis = new RedisTemplate<String, Long>();
        redis.setConnectionFactory(connectionFactory);
        redis.setKeySerializer(ApplicationConfig.StringSerializer.INSTANCE);
        redis.setValueSerializer(ApplicationConfig.LongSerializer.INSTANCE);
        redis.afterPropertiesSet();

        ValueOperations<String, Long> ops = redis.opsForValue();

        String key = "spring-data-book:counter-test:hits";

        ops.setIfAbsent(key, 1L);
        Long l = ops.increment(key, 1);

        Assert.assertTrue(l>0);
    }


    @Test
    public void testJdkSerialiable() {
        RedisTemplate<String, Serializable> redis = new RedisTemplate<String, Serializable>();
        redis.setConnectionFactory(connectionFactory);
        redis.setKeySerializer(ApplicationConfig.StringSerializer.INSTANCE);
        redis.setValueSerializer(new JdkSerializationRedisSerializer());
        redis.afterPropertiesSet();

        ValueOperations<String, Serializable> ops = redis.opsForValue();

        User user1 = new User();
        user1.setName("user1");
        user1.setId(1);

        String key1 = "users/user1";
        User user11 = null;

        long begin = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            ops.set(key1,user1);
            user11 = (User)ops.get(key1);
        }
        long time = System.currentTimeMillis() - begin;
        System.out.println("jdk time:"+time);
//        assertThat(user11.getUserName(),is("user1"));
        Assert.assertTrue(user11.getName().equals("user1"));
    }


    @Test
    public void testJacksonSerialiable() {
        RedisTemplate<String, Object> redis = new RedisTemplate<String, Object>();
        redis.setConnectionFactory(connectionFactory);
        redis.setKeySerializer(ApplicationConfig.StringSerializer.INSTANCE);
        redis.setValueSerializer(new JacksonJsonRedisSerializer<User>(User.class));
        redis.afterPropertiesSet();

        ValueOperations<String, Object> ops = redis.opsForValue();

        User user1 = new User();
        user1.setName("user1");
        user1.setId(1);

        User user11 = null;
        String key1 = "json/user1";

        long begin = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            ops.set(key1,user1);
            user11 = (User)ops.get(key1);
        }
        long time = System.currentTimeMillis() - begin;

        System.out.println("json time:"+time);
//        assertThat(user11.getUserName(),is("user1"));
        Assert.assertTrue(user11.getName().equals("user1"));
    }

    @Test
    public void testOxmSerialiable() throws Throwable{
        RedisTemplate<String, Object> redis = new RedisTemplate<String, Object>();
        redis.setConnectionFactory(connectionFactory);
        redis.setKeySerializer(ApplicationConfig.StringSerializer.INSTANCE);

        redis.setValueSerializer(oxmSerializer);
        redis.afterPropertiesSet();

        ValueOperations<String, Object> ops = redis.opsForValue();

        User user1 = new User();
        user1.setName("user1");
        user1.setId(1);


        User user11 = null;
        String key1 = "oxm/user1";

        long begin = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            ops.set(key1,user1);
            user11 = (User)ops.get(key1);
        }
        long time = System.currentTimeMillis() - begin;

        System.out.println("oxm time:"+time);
//        assertThat(user11.getUserName(),is("user1"));
        Assert.assertTrue(user11.getName().equals("user1"));
    }
}
