package com.cxy.junit;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

import com.cxy.util.sign.SignUtil;
import com.cxy.util.string.HumpOrUnderlineChange;

public class JunitTest {

    @Test
    public void test() {
	// String str = "goods_id_";
	// System.out.println(HumpOrUnderlineChange.changeString(str, 1));

	int a = 1000000;
	for (int i = 1; i < a; i++) {
	    // System.out.println(i);
	    if ((i % 1 == 0) && (i % 2 == 1) && (i % 3 == 0) && (i % 4 == 1)
		    && (i % 5 == 4) && (i % 6 == 3) && (i % 7 == 0)
		    && (i % 8 == 1) && (i % 9 == 0) && (i % 10 == 1)) {
		System.out.println(i);
	    }
	}
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
	// List<String> list = new ArrayList<String>();
	// list.add("a");
	// list.add("b");
	// list.add("c");
	// list.add("d");
	// Iterator it = list.iterator();
	// while (it.hasNext()) {
	// String str = (String) it.next();
	// list.remove("a");
	// }
	for (int i = 0; i < 1000000; i++) {
	    for (int j = 0; j < 1000000; j++) {
		if (i + j == 800) {
		    System.out.println("i:" + i + ",j:" + j);
		    for (int u = 0; u < 1000000; u++) {
			if (i + u == 1300) {
			    System.out
				    .println("i:" + i + ",j:" + j + ",u:" + u);
			    for (int k = 0; k < 1000000; k++) {
				System.out.println("i:" + i + ",j:" + j + ",u:"
					+ u);
				if ((i + j == 800) && (u - k == 600)
					&& (i + u == 1300) && (j + k == 800)) {
				    System.err.println("i:" + i + ",j:" + j
					    + ",u:" + u + ",k:" + k);
				    break;
				}
			    }
			}
		    }
		}
	    }
	}
    }

    @Test
    public void test3() {
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
	// int i = 0;
	// int t;
	// for (t = 0; t <= 5; t++) {
	// i = i++;
	// System.out.println(i);
	// }
	// System.out.println(i);
	// int i = 010;
	// int j = i << 2;
	// int k = j >> 1;
	// System.out.println(i);
	// System.out.println(j);
	// System.out.println(k);
	// System.out.printf("j=%s,k=%s", j, k);
	// List<String> a = new ArrayList<String>();
	// a.add("tttt");
	// Class c = a.getClass();
	// try {
	// Method m = c.getMethod("add", Object.class);
	// m.invoke(a, 100);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// System.out.println(a);
	float f = 10000;
	System.out.printf("f=%9.2f", f);
    }

    @Test
    public void test5() {
	Map<String, Object> m = new HashMap<>();
	m.put("timeStamp", "1510544579872");
	m.put("priority", "3");
	m.put("customerCode", "10000");
	m.put("deviceCode", "10018");
	System.out.println("111");
	// System.out.println(SignUtil.getSign(m,
	// "e40401bedd704ad0bad9116db6b21eaa", (short) 0, true));
    }

}
