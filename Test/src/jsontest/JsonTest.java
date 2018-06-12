package jsontest;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Description 
 * @author litianhua 
 * @date 2017年9月21日下午1:42:18
 */
@JsonSerialize
public class JsonTest {

	private String Name;
	private int Age;
	private String Sex; 
	
	public JsonTest getData(){
		JsonTest jt = new JsonTest();
		jt.Name = "hua";
		jt.Age = 10;
		jt.Sex = "男";
		return jt;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new JsonTest().getData());
	}

}
