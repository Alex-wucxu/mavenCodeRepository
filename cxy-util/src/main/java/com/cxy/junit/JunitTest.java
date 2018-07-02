package com.cxy.junit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.cxy.util.arithmetic.NumberEncrypt;
import com.cxy.util.string.HumpOrUnderlineChange;

public class JunitTest {

	@Test
	public void test() {
		String str = "goods_id_";
		System.out.println(HumpOrUnderlineChange.changeString(str, 1));
	}

	@Test
	public void test1() {
		String str = "ABC137GMNQQ2049PN5FFF";
		char[] chars = str.toCharArray();
		Arrays.sort(chars);
		for (int i = 0; i < 8; i++) {
			System.out.print(chars[i]);
		}
	}

	@Test
	public void test2() {
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		Iterator it = list.iterator();
		while (it.hasNext()) {
			String str = (String) it.next();
			list.remove("a");
		}
	}
	
	@Test
	public void test3(){
		Random rand = new Random(47);
		int i, j, k;
		j = rand.nextInt(100) + 1;
		System.out.println("j : " + j);
		k = rand.nextInt(100) + 1;
		System.out.println("k : " + k);
		i = j + k;
		System.out.println("j + k : " + i);
		i = j - k;
		System.out.println("j - k : " + i);
		i = k / j;
		System.out.println("k / j : " + i);
		i = k * j;
		System.out.println("k * j : " + i);
		i = k % j;
		System.out.println("k % j : " + i);
		j %= k;
		System.out.println("j %= k :" + j);
		float u, v, w;
		v = rand.nextFloat();
		System.out.println("v : " + v);
		w = rand.nextFloat();
		System.out.println("w : " + w);
		u = v + w;
		System.out.println("v + w : " + u);
		u = v - w;
		System.out.println("v - w : " + u);
		u = v * w;
		System.out.println("v * w : " + u);
		u = v / w;
		System.out.println("v / w : " + u);
		u += v;
		System.out.println("u += v : " + u);
		u -= v;
		System.out.println("u -= v : " + u);
		u *= v;
		System.out.println("u *= v : " + u);
		u /= v;
		System.out.println("u /= v : " + u);
	}

	@Test
	public void test4() {
		//总经验
		int total = 0;
		//总个数
		int totalNum = 200;
		//该阶段之前总经验
		int small = 0;
		//每5颗总经验
		int row = 0;
		for (int j = 1; j <= totalNum; j++) {
			//单颗
			int dan = 1000;
			if (j <= 100){
				if (j == 51){
					System.out.println("1-50颗经验：" + total);
					System.out.println("");
					small = total;
				}
				//单颗计算
				dan = dan + (j - 1) * 15;
			} else if (j <= 150){
				if(j == 101){
					System.out.println("51-100颗经验：" + (total - small));
					System.out.println("");
					small = total;
				}
				//单颗计算
				dan = dan + 99 * 15 + (j - 100) * 80;
			} else if (j <= 200){
				if(j == 151){
					System.out.println("101-150颗经验：" + (total - small));
					System.out.println("");
					small = total;
				}
				//单颗计算
				dan = dan + 99 * 15 + 50 * 80 + (j - 150) * 120;
			}
			//总经验计算
			total = total + dan;
			String num = "";
			if(j < 10) {
				num = "第0" + String.valueOf(j);
			} else {
				num = "第" + j + "";
			}
			System.out.print(num + "颗，经验是：" + dan);
			System.out.print("   ");
			row = row + dan;
			if (j % 5 == 0){
				System.out.print("第" + (j - 4) +"-" + j + "颗消耗：" + row);
				System.out.println("");
				row = 0;
			}
		}
		System.out.println("151-200颗经验：" + (total - small));
		System.out.println(totalNum + "颗总经验：" + total);
	}

	@Test
	public void test5() {
		System.out.println(NumberEncrypt.randomNum(5554));
	}

}
