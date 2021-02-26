package com.littlenerve.demo.stream;

import com.littlenerve.demo.stream.Person;
import com.littlenerve.demo.stream.Predicate;
import com.littlenerve.demo.stream.PredicateImpl;
import org.junit.jupiter.api.Test;

/**
 *
 */
public class MockStream {

    public static void main(String[] args) {

        Person bravo = new Person("bravo", 18);

        // 1.具体实现类，调用它的test()方法
        Predicate<Person> predicate1 = new PredicateImpl();
        // test()内部代码是：bravo.getAge() > 18
        myPrint(bravo, predicate1); // false

        // 2.匿名类，调用它的test()方法
        Predicate<Person> predicate2 = new Predicate<Person>() {
            @Override
            public boolean test(Person person) {
                return person.getAge() < 18;
            }
        };
        myPrint(bravo, predicate2); // false

        // 3.Lambda表达式，调用它的test()方法。
        // 按照Lambda表达式的标准，只要你是个函数式接口，那么就可以接收Lambda表达式
        Predicate<Person> predicate3 = person -> person.getAge() == 18;
        myPrint(bravo, predicate3); // true
    }

    @Test
    public void test(){
        Person bravo = new Person("bravo", 18);

        // 1.具体实现类，Function<Person, Integer>中，Person是入参类型，Integer是返回值类型
        Function<Person, Integer> function1 = new FunctionImpl();
        Integer age = function1.apply(bravo);
        myPrint(bravo, function1);

        // 2.匿名类
        Function<Person, Integer> function2 = new Function<Person, Integer>() {
            @Override
            public Integer apply(Person person) {
                return person.getAge();
            }
        };
        myPrint(bravo, function2);


        // 3.Lambda表达式 person（入参类型） ->  person.getAge()（返回值类型）
        Function<Person, Integer> function3 = person -> person.getAge();
        myPrint(bravo, function3);
    }

    /**
     * 不论具体实现类、匿名类还是Lambda表达式，其实它们都只做了两件事：
     *  * 1.让函数式接口占坑
     *  * 2.自己不慌不忙制定映射规则，规则可以被藏在具体类、匿名类中，或者Lambda表达式本身
     * @param person
     * @param filter
     */
    public static void myPrint(Person person, Predicate<Person> filter) {
        if (filter.test(person)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
    public static void myPrint(Person person, Function<Person, Integer> mapper) {
        System.out.println(mapper.apply(person));
    }
}

