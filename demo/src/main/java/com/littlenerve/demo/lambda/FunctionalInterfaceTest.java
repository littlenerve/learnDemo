package com.littlenerve.demo.lambda;

public class FunctionalInterfaceTest {

    // 1.写了一个方法，参数是函数式接口，你可以传递Runnable的实现，也可以使用Lambda或方法引用
    public static void execute(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 2.传入匿名对象
        execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名对象");
            }
        });

        // 3.使用Lambda，()表示Runnable#run()的参数，println()是Runnable#run()的方法体
        execute(() -> System.out.println("使用lambda"));

        // 5.因为wrapPrintln和上面的println做的是同样的事，可以替换
        UserService userService = new UserService();
        execute(() -> userService.wrapPrintln());
        
        // 6.IDEA提示上面的代码可以优化成 方法引用
        execute(userService::wrapPrintln);
        
        // 8.你会发现上面的写法仍是对的，因为“仅有一个抽象方法”是对Runnable的约束，不要搞混
    }

    // 4.我们试着把println()移到wrapPrintln中
    static class UserService {
        public void wrapPrintln() {
            System.out.println("包装后的println");
        }

        // 7.给UserService新增一个方法
        public void anotherMethod() {
            System.out.println("另一个方法，不影响execute使用wrapPrintln");
        }
    }
}
