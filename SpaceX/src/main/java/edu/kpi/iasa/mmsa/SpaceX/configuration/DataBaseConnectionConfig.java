package edu.kpi.iasa.mmsa.SpaceX.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;

@Configuration
public class DataBaseConnectionConfig {
    @Resource
    private Environment environment;

    @Bean
    public StringRedisTemplate redisTemplate(){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.afterPropertiesSet();
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(jedisConnectionFactory);
        return stringRedisTemplate;
    }
    @Bean
    public DriverManagerDataSource dataSource(){
        String mySqlUsername = redisTemplate().opsForValue().get(environment.getProperty("spring.datasource.username"));
        String mySqlPassword = redisTemplate().opsForValue().get(environment.getProperty("spring.datasource.password"));

        DriverManagerDataSource mySqlInstance = new DriverManagerDataSource();
        mySqlInstance.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        mySqlInstance.setUrl(environment.getProperty("spring.datasource.url"));
        mySqlInstance.setUsername(mySqlUsername);
        mySqlInstance.setPassword(mySqlPassword);
        return mySqlInstance;
    }
}
