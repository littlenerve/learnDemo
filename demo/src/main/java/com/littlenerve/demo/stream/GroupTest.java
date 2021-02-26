package com.littlenerve.demo.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *要求分组统计出各个城市的年龄总和，返回格式为 Map<String, Integer>
 */
public class GroupTest {
	private static List<People> list;

	static {
		list = new ArrayList<>();
		list.add(new People("i", 18, "杭州", 999.9));
		list.add(new People("am", 19, "温州", 777.7));
		list.add(new People("iron", 21, "杭州", 888.8));
		list.add(new People("man", 17, "宁波", 888.8));
	}

	public static void main(String[] args) {
		Map<String, Integer> collect = list.stream().collect(Collectors.groupingBy(people -> people.getAddress(), Collectors.summingInt(People::getAge)));
		System.out.println(collect);
	}


}
@Data
@AllArgsConstructor
@NoArgsConstructor
class People{
	String name;
	int age;
	String address;
	double salary;
}
