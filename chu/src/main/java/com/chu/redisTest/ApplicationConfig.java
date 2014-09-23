package com.chu.redisTest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.OxmSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.chu.dao.domain.User;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;

/**
 * @author Jon Brisbin
 */
@Configuration
public abstract class ApplicationConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory cf = new JedisConnectionFactory();
        cf.setHostName("10.1.77.17");
        cf.setPort(6379);
        cf.afterPropertiesSet();
        return cf;
    }

    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate rt = new RedisTemplate();
        rt.setConnectionFactory(redisConnectionFactory());
        return rt;
    }

    private static Map<Class, JAXBContext> jaxbContextHashMap = new ConcurrentHashMap<Class, JAXBContext>();

    @Bean
    public OxmSerializer oxmSerializer() throws Throwable{
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        Map<String,Object> properties = new HashMap<String, Object>();//创建映射，用于设置Marshaller属性
        properties.put(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);   //放置xml自动缩进属性
        properties.put(Marshaller.JAXB_ENCODING,"utf-8");   //放置xml自动缩进属性
        jaxb2Marshaller.setClassesToBeBound(User.class);//映射的xml类放入JAXB环境中
        jaxb2Marshaller.setMarshallerProperties(properties);//设置Marshaller属性
        return  new OxmSerializer(jaxb2Marshaller,jaxb2Marshaller);
    }

    public static enum StringSerializer implements RedisSerializer<String> {
        INSTANCE;

        public byte[] serialize(String s) throws org.springframework.data.redis.serializer.SerializationException {
            return (null != s ? s.getBytes() : new byte[0]);
        }

        public String deserialize(byte[] bytes) throws SerializationException {
            if (bytes.length > 0) {
                return new String(bytes);
            } else {
                return null;
            }
        }

//		public byte[] serialize(String t)
//				throws org.springframework.data.redis.serializer.SerializationException {
//			// TODO Auto-generated method stub
//			return null;
//		}
    }

    public static enum LongSerializer implements RedisSerializer<Long> {
        INSTANCE;

//        @Override
        public byte[] serialize(Long aLong) throws SerializationException {
            if (null != aLong) {
                return aLong.toString().getBytes();
            } else {
                return new byte[0];
            }
        }

//        @Override
        public Long deserialize(byte[] bytes) throws SerializationException {
            if (bytes.length > 0) {
                return Long.parseLong(new String(bytes));
            } else {
                return null;
            }
        }
    }


    public static enum IntSerializer implements RedisSerializer<Integer> {
        INSTANCE;

//        @Override
        public byte[] serialize(Integer i) throws SerializationException {
            if (null != i) {
                return i.toString().getBytes();
            } else {
                return new byte[0];
            }
        }

//        @Override
        public Integer deserialize(byte[] bytes) throws SerializationException {
            if (bytes.length > 0) {
                return Integer.parseInt(new String(bytes));
            } else {
                return null;
            }
        }
    }

}
