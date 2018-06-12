/**
 * 
 */
package alarmsolve4xml;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Description 
 * @author lijiaxi 
 * @date 2017-8-24
 */
public class Alarm {
	private String name ;
	private int code;
	private int level;
	
	@XmlAttribute(name = "name")
	public String getName() {
		return name;
	}
	
	@XmlAttribute(name = "code")
	public int getCode() {
		return code;
	}
	
	@XmlAttribute(name = "level")
	public int getLevel() {
		return level;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
}
