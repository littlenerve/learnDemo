package com.littlenerve.demo.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.littlenerve.demo.util.ConvertUtil.listToMap;
import static org.junit.jupiter.api.Assertions.*;

class ConvertUtilTest {
	private static List<Person> list;

	static {
		list = new ArrayList<>();
		list.add(new Person("i", 18, "杭州", 999.9));
		list.add(new Person("am", 19, "温州", 777.7));
		list.add(new Person("iron", 21, "杭州", 888.8));
		list.add(new Person("man", 17, "宁波", 888.8));
	}

	@Test
	void testListToMap() {
		Map<String, Person> nameToPersonMap = listToMap(list, Person::getName);
		System.out.println(nameToPersonMap);

		Map<String, Person> personGt18 = listToMap(list, Person::getName, person -> person.getAge() >= 18);
		System.out.println(personGt18);
	}

	@Test
	void resultToList() {
	}

	@Test
	void testResultToList() {
	}

	@Test
	void testListToMap1() {
	}
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	static class Person {
		private String name;
		private Integer age;
		private String address;
		private Double salary;
	}
}
