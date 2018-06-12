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
 * @date 2017-8-24
 */
@XmlRootElement(name = "alarms")
public class Alarms {
	private Collection<AlarmCategory> alarmCategories;
	private Integer modelCode;
	
	@XmlAttribute(name="modelCode")
	public Integer getModelCode() {
		return modelCode;
	}

	public void setModelCode(Integer modelCode) {
		this.modelCode = modelCode;
	}

	@XmlElement(name = "alarm-list")
	public Collection<AlarmCategory> getAlarmCategories() {
		return alarmCategories;
	}

	public void setAlarmCategories(Collection<AlarmCategory> alarmCategories) {
		this.alarmCategories = alarmCategories;
	}
	
}
