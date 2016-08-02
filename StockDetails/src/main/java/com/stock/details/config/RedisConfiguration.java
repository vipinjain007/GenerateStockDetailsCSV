package com.stock.details.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.support.collections.DefaultRedisMap;

@Configuration
@PropertySource("classpath:application.properties")
public class RedisConfiguration {

	@Value("${spring.redis.host}")
	private String REDIS_HOSTNAME;

	@Value("${spring.redis.port}")
	private int REDIS_PORT;

	@Value("${spring.redis.password}")
	private String REDIS_PASSWORD;

	

	@Value("${redisDatabase.cacheMap}")
	private String REDIS_CACHE_MAP;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(REDIS_HOSTNAME);
		factory.setPort(REDIS_PORT);
		// factory.setPassword(REDIS_PASSWORD);
		factory.setUsePool(true);
		factory.afterPropertiesSet();
		return factory;
	}

	@Bean
	RedisTemplate<?, ?> redisTemplate() {
		RedisTemplate<?, ?> redisTemplate = new RedisTemplate<Object, Object>();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}

	
	@Bean
	@SuppressWarnings("unchecked")
	DefaultRedisMap<String, String> cacheMap() {
		return new DefaultRedisMap<String, String>(REDIS_CACHE_MAP, (RedisOperations<String, ?>) redisTemplate());
	}



	@Bean
	RedisCacheManager cacheManager() {
		return new RedisCacheManager(redisTemplate());
	}
}