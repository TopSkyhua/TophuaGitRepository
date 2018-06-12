package test;

import java.util.Scanner;

/**
 * @author litianhua 
 * @date 2017年11月1日
 */
public class OutPutTest {

	
	
	public void getFlow() {
		while (true) {
			Scanner in = new Scanner(System.in);
			String s = in.nextLine();
			String[] arr = s.split("\\+");
			String data = "2017/9/";
			String data1 = "2017/10/";
			int sum = 0;
			for (int i = 0; i < arr.length; i++) {
				if (16 + i <= 30) {
					System.out.println(data + String.valueOf(16 + i) + "  ：" + arr[i]);
				} else {
					System.out.println(data1 + String.valueOf(16 + i - 30) + "  ：" + arr[i]);
				}
				sum += Integer.parseInt(arr[i]);
			}
			// System.out.println("个数：" + arr.length);
			System.out.println("签到验证总次数：" + sum);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
	}

}
