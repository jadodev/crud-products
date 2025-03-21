package com.jhonnathan.crud_products.infrastructure.redis;

import com.jhonnathan.crud_products.domain.port.out.CachePort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisAdapter implements CachePort {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisAdapter(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void guardarEnCache(String clave, Object valor, long duracion, TimeUnit unidadTiempo) {
        redisTemplate.opsForValue().set(clave, valor, duracion, unidadTiempo);
    }

    @Override
    public Object obtenerDeCache(String clave) {
        return redisTemplate.opsForValue().get(clave);
    }

    @Override
    public void eliminarDeCache(String clave) {
        redisTemplate.delete(clave);
    }
}