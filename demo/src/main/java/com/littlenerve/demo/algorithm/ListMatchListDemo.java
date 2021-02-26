package com.littlenerve.demo.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

/**
 * list与list匹配的算法优化
 */
public class ListMatchListDemo {
	public static void main(String[] args) {

		// 老公组
		List<Couple> husbands = new ArrayList<>();
		husbands.add(new Couple(1, "梁山伯"));
		husbands.add(new Couple(2, "牛郎"));
		husbands.add(new Couple(3, "干将"));
		husbands.add(new Couple(4, "工藤新一"));
		husbands.add(new Couple(5, "罗密欧"));

		// 老婆组
		List<Couple> wives = new ArrayList<>();
		wives.add(new Couple(1, "祝英台"));
		wives.add(new Couple(2, "织女"));
		wives.add(new Couple(3, "莫邪"));
		wives.add(new Couple(4, "毛利兰"));
		wives.add(new Couple(5, "朱丽叶"));

		// 用于计算循环次数
		int count = 0;

		//算法一
		 method1(husbands, wives, count);

		//算法二
		 method2(husbands, wives, count);

		//算法三
//		method3(husbands, wives, count);

		//算法四 它的精髓就是利用HashMap给其中一列数据加了“索引”，每个数据的“索引”（Map的key）是不同的，让数据差异化。 以空间换时间 增加的空间主要是map中的key
		method4(husbands, wives, count);
	}

	//推荐
	private static void method4(List<Couple> husbands, List<Couple> wives, int count) {
		// 给女嘉宾发牌子
		Map<Integer, Couple> wivesMap = new HashMap<>();
		for (Couple wife : wives) {
			// 女嘉宾现在不在List里了，而是去了wivesMap中，前面放了一块牌子：男嘉宾的号码
			wivesMap.put(wife.getFamilyId(), wife);
			count++;
		}

		// 男嘉宾上场
		for (Couple husband : husbands) {
			// 找到举着自己号码牌的女嘉宾
			Couple wife = wivesMap.get(husband.getFamilyId());
			System.out.println(husband.getUserName() + "爱" + wife.getUserName());
			count++;
		}

		System.out.println("----------------------");
		System.out.println("循环了：" + count + "次");
	}

	//如果数据离散性大，可能循环次数会很大 效率是波动的 可能存在并发问题
	private static void method3(List<Couple> husbands, List<Couple> wives, int count) {
		/*对于List来说，有两种方法可以避免并发修改异常：
			• 迭代器迭代元素，迭代器修改元素(ListIterator)*/
		ListIterator<Couple> iterator = wives.listIterator();
			for (Couple husband : husbands) {
        // 迭代器遍历
        while (iterator.hasNext()) {
            Couple wife = iterator.next();
            // 记录循环的次数
            count++;
            if (husband.getFamilyId().equals(wife.getFamilyId())) {
                System.out.println(husband.getUserName() + "爱" + wife.getUserName());
                // 迭代器删除
                iterator.remove();
                break;
            }
        }
    }
			/*• 集合遍历元素，集合修改元素(for)*/
		for (Couple husband : husbands) {
			//逆序
			for (int i = wives.size() - 1; i >= 0; i--) {
				// 记录循环的次数
				count++;
				if (husband.getFamilyId().equals(wives.get(i).getFamilyId())) {
					System.out.println(husband.getUserName() + "爱" + wives.get(i).getUserName());
					// 牵手成功，把女嘉宾从舞台请下来，同时换下一位男嘉宾上场
					wives.remove(i);
					// 试着把break去掉，不会抛并发修改异常
					break;
				}
			}
		}
		//正序
		/*for (Couple husband : husbands) {
			for (int i = 0; i < wives.size(); i++) {
				// 记录循环的次数
				count++;
				if (husband.getFamilyId().equals(wives.get(i).getFamilyId())) {
					System.out.println(husband.getUserName() + "爱" + wives.get(i).getUserName());
					// 牵手成功，把女嘉宾从舞台请下来，同时换下一位男嘉宾上场
					wives.remove(i);
					// 把i往回拨。你可以试试把i--注释掉，看看是不是少循环了几次（因为有元素被跳过了）
					i--;
					// break;
				}
			}
		}*/
		//foreach写法 底层为迭代器，此时应该使用迭代器删除元素
		/*for (Couple husband : husbands) {
			for (Couple wife : wives) {
				// 记录循环的次数
				count++;
				if (husband.getFamilyId().equals(wife.getFamilyId())) {
					System.out.println(husband.getUserName() + "爱" + wife.getUserName());
					// 牵手成功，把女嘉宾从舞台请下来，同时换下一位男嘉宾上场
					wives.remove(wife);
					break;
				}
			}
		}*/

		System.out.println("----------------------");
		System.out.println("循环了：" + count + "次");
		count = 0;
	}

	private static void method2(List<Couple> husbands, List<Couple> wives, int count) {
		for (Couple husband : husbands) {
			for (Couple wife : wives) {
				// 记录循环的次数
				count++;
				if (husband.getFamilyId().equals(wife.getFamilyId())) {
					System.out.println(husband.getUserName() + "爱" + wife.getUserName());
					// 牵手成功，换下一位男嘉宾
					break;
				}
			}
		}

		System.out.println("----------------------");
		System.out.println("循环了：" + count + "次");
		count = 0;

	}

	private static void method1(List<Couple> husbands, List<Couple> wives, int count) {
		for (Couple husband : husbands) {
			for (Couple wife : wives) {
				// 记录循环的次数
				count++;
				if (husband.getFamilyId().equals(wife.getFamilyId())) {
					System.out.println(husband.getUserName() + "爱" + wife.getUserName());
				}
			}
		}

		System.out.println("----------------------");
		System.out.println("循环了：" + count + "次");
		count = 0;

	}


@Data
@AllArgsConstructor
static class Couple{
	private Integer familyId;
	private String userName;
}
}
