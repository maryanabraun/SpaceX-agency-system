package edu.kpi.iasa.mmsa.SpaceX.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final StringRedisTemplate redisTemplate;

    public String save(String value, String key) {
        (redisTemplate.getConnectionFactory()).getConnection().set(key.getBytes(),
                value.getBytes());
        return key;
    }

    public String get(String key){
        return new String(redisTemplate.getConnectionFactory().getConnection().get(key.getBytes()),
                StandardCharsets.UTF_8);
    }
}
