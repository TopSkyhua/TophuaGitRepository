package com.lth.springmvc.testprogram;

import java.util.Random;

/**
 * @author litianhua
 * @date 2018年4月20日
 * 
 */
public class KeyCreate {

	public static String getRandomString(int length) {
		char[] baseChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
		String result = ""; 
		Random random = new Random();
		for(int i = 0; i < length; i++) { 
			int index = random.nextInt(baseChar.length);
			result += baseChar[index];
		}
		return result;
	}


	public static void main(String[] args) {
		System.out.println("AES秘钥：" + getRandomString(16));
		System.out.println("AES向量：" + getRandomString(16));
		System.out.println("Sig秘钥：" + getRandomString(16));
		System.out.println("密钥：" + getRandomString(16));
	}

}
