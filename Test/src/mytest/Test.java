package mytest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.Collection;

import javax.swing.border.EmptyBorder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Description
 * 
 * @author litianhua
 * @date 2017年8月29日下午4:36:13
 */

public class Test {

	public String importFile(String file) {
		String result = null;
		InputStream is = null;
		Reader reader = null;
		File f = new File(file);
		try {
			is = new FileInputStream(f);
			reader = new InputStreamReader(is, "UTF8");
			char[] buff = new char[1024];
			StringBuilder sb = new StringBuilder();
			int size = 0;
			while ((size = reader.read(buff)) > 0) {
				sb.append(buff, 0, size);
			}
			result = sb.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void parseXml(String xmlContent) {
		try {
			JAXBContext jxbcontext = JAXBContext.newInstance(DeviceModul.class);
			Unmarshaller umUnmarshaller = jxbcontext.createUnmarshaller();
			StringReader reader = new StringReader(xmlContent);
			DeviceModul dm = (DeviceModul) umUnmarshaller.unmarshal(reader);
			String deviceName = dm.getName();
			int deviceCode = dm.getCode();
			Collection<Alarms> alarms = dm.getAlarms();
			for (Alarms alarm : alarms) {
				Collection<AlarmCategory> alarmCategorys = alarm.getAlarmCategorys();
				for (AlarmCategory alarmCategory : alarmCategorys) {
					Collection<Alarm> alarm2 = alarmCategory.getAlarms();
					for (Alarm alarm3 : alarm2) {
						System.out.println("deviceName:" + deviceName + " deviceCode:" + deviceCode
								+ " " + "alarmName:" + alarm3.getName() + "    alarmCode:"
								+ alarm3.getCode() + " " + "  alarmLevel:" + alarm3.getLevel()
								+ "");
					}

				}
			}

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void parseXml1(String filePath) {
		try {
			File file = new File(filePath);
			JAXBContext jxbcontext = JAXBContext.newInstance(DeviceModul.class);
			Unmarshaller umUnmarshaller = jxbcontext.createUnmarshaller();
			DeviceModul dm = (DeviceModul) umUnmarshaller.unmarshal(file);
			String deviceName = dm.getName();
			int deviceCode = dm.getCode();
			Collection<Alarms> alarms = dm.getAlarms();
			for (Alarms alarm : alarms) {
				Collection<AlarmCategory> alarmCategorys = alarm.getAlarmCategorys();
				for (AlarmCategory alarmCategory : alarmCategorys) {
					Collection<Alarm> alarm2 = alarmCategory.getAlarms();
					for (Alarm alarm3 : alarm2) {
						System.out.println("deviceName:" + deviceName + " deviceCode:" + deviceCode
								+ " " + "alarmName:" + alarm3.getName() + "    alarmCode:"
								+ alarm3.getCode() + " " + "  alarmLevel:" + alarm3.getLevel()
								+ "");
					}

				}
			}

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void parseXml2(String filePath) {
		try {
			InputStream is = new FileInputStream(filePath);
			JAXBContext jxbcontext = JAXBContext.newInstance(DeviceModul.class);
			Unmarshaller umUnmarshaller = jxbcontext.createUnmarshaller();
			DeviceModul dm = (DeviceModul) umUnmarshaller.unmarshal(is);
			String deviceName = dm.getName();
			int deviceCode = dm.getCode();
			Collection<Alarms> alarms = dm.getAlarms();
			for (Alarms alarm : alarms) {
				Collection<AlarmCategory> alarmCategorys = alarm.getAlarmCategorys();
				for (AlarmCategory alarmCategory : alarmCategorys) {
					Collection<Alarm> alarm2 = alarmCategory.getAlarms();
					for (Alarm alarm3 : alarm2) {
						System.out.println("deviceName:" + deviceName + " deviceCode:" + deviceCode
								+ " " + "alarmName:" + alarm3.getName() + "　　　alarmCode:"
								+ alarm3.getCode() + " " + "  alarmLevel:" + alarm3.getLevel()
								+ "");
					}

				}
			}

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String filePath = "D://Documents//桌面//test2.xml";
		Test test = new Test();
//		String xmlContent = test.importFile(filePath);
//		test.parseXml(xmlContent);
		
		test.parseXml2(filePath);
	}

}
