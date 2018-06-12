package mytest;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="device-model")
public class DeviceModul {

	private String name;
	private Integer code;
	private String deviceType;
	private Collection<Alarms> alarms;

	@XmlAttribute(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlAttribute(name = "code")
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	
	@XmlAttribute(name = "deviceType")
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}	
	
	@XmlElement(name = "alarms")
	public Collection<Alarms> getAlarms() {
		return alarms;
	}

	public void setAlarms(Collection<Alarms> alarms) {
		this.alarms = alarms;
	}

}
