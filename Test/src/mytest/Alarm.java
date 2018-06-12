package mytest;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Description 
 * @author litianhua 
 * @date 2017年8月29日下午4:27:02
 */
public class Alarm {
	private int code;
	private int level;
	private String name;
	
	@XmlAttribute(name = "code")
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	@XmlAttribute(name = "level")
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	@XmlAttribute(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
