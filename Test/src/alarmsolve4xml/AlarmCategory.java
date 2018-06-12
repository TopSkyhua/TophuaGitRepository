/**
 * 
 */
package alarmsolve4xml;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Description 
 * @author lijiaxi 
 * @date 2017-8-24
 */
public class AlarmCategory {
	private String name;
	private Collection<Alarm> alarms;
	
	@XmlAttribute(name = "category")
	public String getName() {
		return name;
	}
	
	@XmlElement(name = "alarm")
	public Collection<Alarm> getAlarms() {
		return alarms;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAlarms(Collection<Alarm> alarms) {
		this.alarms = alarms;
	}
	
}
