/**
 * 
 */
package alarmsolve4xml;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Description 
 * @author lijiaxi 
 * @date 2017-8-28
 */
public class A3 {
	private String name ;
	
	@XmlAttribute(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
