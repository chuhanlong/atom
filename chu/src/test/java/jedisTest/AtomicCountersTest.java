package jedisTest;


import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chu.redisTest.ApplicationConfig;

/**
 * @author Jon Brisbin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class AtomicCountersTest {

    @Autowired
    RedisConnectionFactory connectionFactory;

    @Test
    public void testAtomicCounters() {
        RedisAtomicLong counter = new RedisAtomicLong("spring-data-book:counter-test:hits", connectionFactory, 0);
        Long l = counter.incrementAndGet();
        Assert.assertTrue(l > 0);
    }

}
