package jedisTest.caching;


import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import com.chu.redisTest.ApplicationConfig;
import com.chu.redisTest.ApplicationConfig.IntSerializer;

/**
 * @author Jon Brisbin
 */
@Configuration
@EnableCaching
public class CachingConfig extends ApplicationConfig {

  @SuppressWarnings({"unchecked"})
  @Bean public RedisCacheManager redisCacheManager() {
    RedisTemplate tmpl = new RedisTemplate();
    tmpl.setConnectionFactory( redisConnectionFactory() );
    tmpl.setKeySerializer( IntSerializer.INSTANCE );
    tmpl.setValueSerializer( new JdkSerializationRedisSerializer() );
    RedisCacheManager cacheMgr = new RedisCacheManager( tmpl );
    return cacheMgr;
  }

  @Bean public CacheableTest cacheableTest() {
    return new CacheableTest();
  }

}
