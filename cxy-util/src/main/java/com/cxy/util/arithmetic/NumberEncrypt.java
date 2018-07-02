package com.cxy.util.arithmetic;

import java.util.Random;

public class NumberEncrypt {
	
	/**
	 * 
	 * @Title:        randomNum 
	 * @Description:  替换原数字
	 * @param:        @param num	原数字
	 * @param:        @return    
	 * @return:       Integer    	替换后数字
	 * @author        Alex
	 * @Date          2016年9月28日 上午11:32:21
	 */
	public static Integer randomNum(Integer num){
		String maxRandom = "";
		String minRandom = "";
		String resultNum = "";
		
		char[] nums = num.toString().toCharArray();
		
		for (int i = 0; i < nums.length; i++) {
			maxRandom += "9";
			if(i == 0){
				minRandom += "1";
			} else {
				minRandom += "0";
			}
		}
		
		Random random = new Random();
		Integer randomNumber = random.nextInt(Integer.parseInt(maxRandom));
		while(randomNumber < Integer.parseInt(minRandom)){
			randomNumber = random.nextInt(Integer.parseInt(maxRandom));
		}
		
		char[] randomNumbers = randomNumber.toString().toCharArray();
		
		for (int i = 0; i < nums.length; i++) {
			resultNum += " " + changeArithmetic((i % 2), Integer.parseInt(String.valueOf(nums[i])), Integer.parseInt(String.valueOf(randomNumbers[i])));
		}
		
		return Integer.parseInt(resultNum.replace(" ", ""));
	}
	
	/**
	 * 
	 * @Title:        changeArithmetic 
	 * @Description:  选择算法，将原数字和随机数运算
	 * @param:        @param change			算法0：加	1：乘  2：减  3除
	 * @param:        @param originalNum	原数字
	 * @param:        @param randomNum		随机数
	 * @param:        @return    
	 * @return:       int    
	 * @author        Alex
	 * @Date          2016年9月28日 上午11:45:15
	 */
	public static int changeArithmetic(int change, int originalNum, int randomNum){
		int resultNum = 0;
		
		switch (change) {
		case 0:
			resultNum = originalNum + randomNum;
			break;
		case 1:
			resultNum = originalNum * randomNum;
			break;
//		case 2:
//			
//			break;
//		case 3:
//			
//			break;
		}
		return resultNum;
	}

}
