package com.littlenerve.demo.stream;

/**
 * Predicate接口的实现类，泛型规定只处理Person
 */
public class PredicateImpl implements Predicate<Person> {

    /**
     * 判断逻辑是：传入的person是否age>18，是就返回true
     *
     * @param person
     * @return
     */
    @Override
    public boolean test(Person person) {
        return person.getAge() > 18;
    }
}
