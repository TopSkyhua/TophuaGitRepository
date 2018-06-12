package mytest;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Description 
 * @author litianhua 
 * @date 2017年8月29日下午3:58:04
 */
public class AlarmCategory {
	
	private String category;
	private Collection<Alarm> alarms;
	
	@XmlAttribute(name = "category")
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	@XmlElement(name = "alarm")
	public Collection<Alarm> getAlarms() {
		return alarms;
	}
	public void setAlarms(Collection<Alarm> alarms) {
		this.alarms = alarms;
	}
	
	
}
