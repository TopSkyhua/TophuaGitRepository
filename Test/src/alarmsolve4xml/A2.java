/**
 * 
 */
package alarmsolve4xml;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;

/**
 * Description 
 * @author lijiaxi 
 * @date 2017-8-28
 */
public class A2 {
	private Collection<A3> a3s;
	
	@XmlElement(name="a3")
	public Collection<A3> getA3s() {
		return a3s;
	}

	public void setA3s(Collection<A3> a3s) {
		this.a3s = a3s;
	}
	
}
