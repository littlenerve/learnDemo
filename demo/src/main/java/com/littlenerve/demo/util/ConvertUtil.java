package com.littlenerve.demo.util;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class ConvertUtil {
	private ConvertUtil() {
	}

	/**
	 * 将List转为Map
	 *
	 * @param list         原数据
	 * @param keyExtractor Key的抽取规则
	 * @param <K>          Key
	 * @param <V>          Value
	 * @return
	 */
	public static <K, V> Map<K, V> listToMap(List<V> list, Function<V, K> keyExtractor) {
		if (CollectionUtils.isEmpty(list)) {
			return new HashMap<>();
		}
		Map<K, V> map = new HashMap<>(list.size());
		for (V element : list) {
			K key = keyExtractor.apply(element);
			if (key == null) {
				continue;
			}
			map.put(key, element);
		}
		return map;
	}

	/**
	 * 将List转为Map，可以指定过滤规则
	 *
	 * @param list         原数据
	 * @param keyExtractor Key的抽取规则
	 * @param predicate    过滤规则
	 * @param <K>          Key
	 * @param <V>          Value
	 * @return
	 */
	public static <K, V> Map<K, V> listToMap(List<V> list, Function<V, K> keyExtractor, Predicate<V> predicate) {
		if (CollectionUtils.isEmpty(list)) {
			return new HashMap<>();
		}
		Map<K, V> map = new HashMap<>(list.size());
		for (V element : list) {
			K key = keyExtractor.apply(element);
			if (key == null || !predicate.test(element)) {
				continue;
			}
			map.put(key, element);
		}
		return map;
	}

	/**
	 * 将List映射为List，比如List<Person> personList转为List<String> nameList
	 *
	 * @param originList 原数据
	 * @param mapper     映射规则
	 * @param <T>        原数据的元素类型
	 * @param <R>        新数据的元素类型
	 * @return
	 */
	public static <T, R> List<R> resultToList(List<T> originList, Function<T, R> mapper) {
		if (CollectionUtils.isEmpty(originList)) {
			return new ArrayList<>();
		}
		List<R> newList = new ArrayList<>(originList.size());
		for (T originElement : originList) {
			R newElement = mapper.apply(originElement);
			if (newElement == null) {
				continue;
			}
			newList.add(newElement);
		}
		return newList;
	}

	/**
	 * 将List映射为List，比如List<Person> personList转为List<String> nameList
	 * 可以指定过滤规则
	 *
	 * @param originList 原数据
	 * @param mapper     映射规则
	 * @param predicate  过滤规则
	 * @param <T>        原数据的元素类型
	 * @param <R>        新数据的元素类型
	 * @return
	 */
	public static <T, R> List<R> resultToList(List<T> originList, Function<T, R> mapper, Predicate<T> predicate) {
		if (CollectionUtils.isEmpty(originList)) {
			return new ArrayList<>();
		}
		List<R> newList = new ArrayList<>(originList.size());
		for (T originElement : originList) {
			R newElement = mapper.apply(originElement);
			if (newElement == null || !predicate.test(originElement)) {
				continue;
			}
			newList.add(newElement);
		}
		return newList;
	}

	/**
	 * 将List转为Map，同时支持自定义key和value
	 *
	 * @param list           原数据
	 * @param keyExtractor   Key的抽取规则
	 * @param valueExtractor Value的抽取规则
	 * @param <K>            Key
	 * @param <V>            Value
	 * @param <R>            NewValue
	 * @return
	 */
	/*public static <K, V, R> Map<K, R> listToMap(List<V> list, Function<V, K> keyExtractor, Function<V, R> valueExtractor) {
		if (CollectionUtils.isEmpty(list)) {
			return new HashMap<>();
		}
		Map<K, R> map = new HashMap<>(list.size());
		for (V element : list) {
			K key = keyExtractor.apply(element);
			if (key == null) {
				continue;
			}
			// 改进：和key一样，对value使用valueExtractor进行抽取
			map.put(key, valueExtractor.apply(element));
		}
		return map;
	}*/
}
