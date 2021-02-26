package com.littlenerve.demo.stream;

/**
 * Function接口的实现类，规定传入Person类型返回Integer类型
 */
public class FunctionImpl implements Function<Person, Integer> {

    /**
     * 传入person对象，返回age
     *
     * @param person
     * @return
     */
    @Override
    public Integer apply(Person person) {
        return person.getAge();
    }
}
