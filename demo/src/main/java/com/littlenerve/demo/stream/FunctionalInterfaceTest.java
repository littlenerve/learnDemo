package com.littlenerve.demo.stream;

/**
 * 消费型接口 Consumer<T>    void accept(T t)
 * 供给型接口 Supplier<T>       T get()
 * 函数型接口 Function<T, R>   R apply(T t)
 * 断定型接口 Predicate<T>     boolean test(T t)
 *
 * jdk8接口出现静态方法和默认方法原因：collection增加stream方法，拓展已有的接口，同时保证向前兼容
 */
public class FunctionalInterfaceTest {
    /**
     * 方法重载
     *
     * @param param1
     */
    public static void lambdaMethod(Param1 param1) {
        param1.print();
    }

    /**
     * 方法重载
     *
     * @param param2
     */
    public static void lambdaMethod(Param2 param2) {
        param2.print();
    }

    /**
     * 给函数式接口赋值的格式：
     * 函数式接口 = 入参 -> 出参/制造出参的语句
     *
     * @param args
     */
    public static void main(String[] args) {
        FunctionalInterface1 interface1 = str -> System.out.println(str);
        FunctionalInterface2 interface2 = () -> {
            return "abc";
        };
        //FunctionalInterface2 interface2 = () -> "abc";
        FunctionalInterface3 interface3 = str -> Integer.parseInt(str);
        FunctionalInterface4 interface4 = str -> str.length() > 3;

        // Ambiguous method call
        //由于两个函数式接口的出入参形式一直，所以lambda表达式无法区分，编译不通过
       // lambdaMethod(() -> System.out.println("test"));

    }


    /**
     * 消费型，吃葡萄不吐葡萄皮：有入参，无返回值
     * (T t) -> {}
     */
    interface FunctionalInterface1 {
        void method(String str);
    }

    /**
     * 供给型，无中生有：没有入参，却有返回值
     * () -> T t
     */
    interface FunctionalInterface2 {

        String method();
    }

    /**
     * 映射型，转换器：把T转成R返回
     * T t -> R r
     */
    interface FunctionalInterface3 {
        int method(String str);
    }

    /**
     * 特殊的映射型：把T转为boolean
     * T t -> boolean
     */
    interface FunctionalInterface4 {
        boolean method(String str);
    }
    /**
     * 函数式接口1
     */
    interface Param1 {
        void print();
    }

    /**
     * 函数式接口2
     */
    interface Param2 {
        void print();
    }

}
