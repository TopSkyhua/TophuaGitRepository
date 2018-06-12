/**
 * 
 */
package alarmsolve4xml;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Description 
 * @author lijiaxi 
 * @date 2017-8-28
 */
@XmlRootElement(name="a1")
public class A1 {
	private Collection<A2> a2s;
	private String name ;
	
	@XmlAttribute(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "a2")
	public Collection<A2> getA2s() {
		return a2s;
	}

	public void setA2s(Collection<A2> a2s) {
		this.a2s = a2s;
	}
	
}
