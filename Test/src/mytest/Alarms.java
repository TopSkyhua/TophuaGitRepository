package mytest;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;

public class Alarms {

	private Collection<AlarmCategory> AlarmCategorys;

	@XmlElement(name = "alarm-list")
	public Collection<AlarmCategory> getAlarmCategorys() {
		return AlarmCategorys;
	}

	public void setAlarmCategorys(Collection<AlarmCategory> alarmCategorys) {
		AlarmCategorys = alarmCategorys;
	}

}
