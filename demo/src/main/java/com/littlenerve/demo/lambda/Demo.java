package com.littlenerve.demo.lambda;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/**
 * lambda表达式作用：让代码更简洁，更优雅，优化程序 可以视作函数式编程的子集
 * 本质：Lambda表达式，其实是一段可传递的代码。它的本质是以类的身份，干方法的活。（将方法参数化）
 * Lambda表达式 = 形参列表 + 方法体
 * 接口要想接收Lambda表达式，必须是一个函数式接口。所谓函数式接口，最核心的特征是：有且只有一个抽象方法。
 * @functionalinterface注解 检测接口内是否有唯一的抽象方法
 */
public class Demo {
    public static void main(String[] args) {
        method1();
        method2();
        method3();

        String str1 = "abc";
        String str2 = "abcd";

//        Lambda表达式 = 形参列表 + 方法体
        // 上面推导得出Lambda表达式与匿名类对象等价，所以我们可以把Lambda表达式赋值给Comparator接口
        Comparator<String> comparator = (String s1, String s2) -> {
            return s1.length() - s2.length();
        };
        // 调用
        int k = compareString(str1, str2, comparator);

        // 改进一下，跳过赋值这一步，直接把整个Lambda传给compareString()方法：
        compareString(str1, str2, (String s1, String s2) -> {
            return s1.length() - s2.length();
        });

        // 上面的代码虽然能运行，但是idea一直显示灰色，说有更优雅的写法。好吧，我改改。
        int x = compareString(str1, str2, (s1, s2) -> s1.length() - s2.length());

        // 不对，还是不够精简，再改改（方法引用）：
        x = compareString(str1, str2, Comparator.comparingInt(String::length));

        // 完美。
    }

    //使用lambda表达式
    private static void method3() {
        new MyThread(() -> {
            System.out.println("不用买票");
            System.out.println("骑电瓶车...");
        }).start();
    }

    //使用匿名内部类优化 避免类爆炸
    private static void method2() {
        new MyThread(new MyRunnable() {
            @Override
            public void run() {
                System.out.println("不用买票");
                System.out.println("骑电瓶车...");
            }
        }).start();
    }

    private static void method1() {
        ByTrain byTrain = new ByTrain();
        new MyThread(byTrain).start();

        ByAir byAir = new ByAir();
        new MyThread(byAir).start();
    }

    public static int compareString(String str1, String str2, Comparator<String> comparator) {
        return comparator.compare(str1, str2);
    }

    @Test
    public void testClosure() throws InterruptedException {
        // 在匿名内部类的外面定义一个String变量
        //反编译以后发现 str实际是final的
        String str = "hello";
        // 构造一个匿名内部类对象
        //使用闭包机制，使得匿名内部类可以读取到外部的变量
        new Thread() {
            @Override
            public void run() {
                System.out.println(str);
            }
        }.start();

        TimeUnit.SECONDS.sleep(1);
    }


}
