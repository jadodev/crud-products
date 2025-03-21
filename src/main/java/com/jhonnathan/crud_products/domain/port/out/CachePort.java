package com.jhonnathan.crud_products.domain.port.out;


import java.util.concurrent.TimeUnit;

public interface CachePort {
    void guardarEnCache(String clave, Object valor, long duracion, TimeUnit unidadTiempo);
    Object obtenerDeCache(String clave);
    void eliminarDeCache(String clave);
}