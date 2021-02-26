package com.littlenerve.demo.lambda;

public class ByAir implements MyRunnable {
    @Override
    public void run() {
        System.out.println("在某App上订了飞机票");
        System.out.println("坐飞机...");
    }
}
