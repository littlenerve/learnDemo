package com.littlenerve.demo.stream;

/**
 * 定义一个Function接口
 * 从接口看Function<E, R>中，E(Enter)表示入参类型，R(Return)表示返回值类型
 * 
 * @param <E> 入参类型
 * @param <R> 返回值类型
 */
@FunctionalInterface
public interface Function<E, R> {
    /**
     * 定义一个apply()方法，接收一个E返回一个R。也就是把E映射成R
     *
     * @param e
     * @return
     */
    R apply(E e);
}
