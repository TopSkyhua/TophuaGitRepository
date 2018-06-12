/**
 * 
 */
package alarmsolve4xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Collection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * Description 
 * @author lijiaxi 
 * @date 2017-8-25
 */
public class TestImportor {
	//private Alarm alarm = new Alarm();
	
	public boolean parseXml(String content){
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Alarms.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(content);
			Alarms dm = (Alarms) unmarshaller.unmarshal(reader);
			System.out.println(dm.getAlarmCategories().size());
			Integer modelCode = dm.getModelCode();
			//DeviceModel deviceModel = new DeviceModelDao().getByCode(modelCode);
			
			Collection<AlarmCategory> alarmCategories = dm.getAlarmCategories();
			for (AlarmCategory alarmCategory : alarmCategories) {
				//System.out.println(alarmCategory.getName());
				String categoryName = alarmCategory.getName();
				System.out.println("Call spAddAlarmCategory("+modelCode+",'"+categoryName+"');");
				Collection<Alarm> alarms = alarmCategory.getAlarms();
				for (Alarm alarm : alarms) {
					Integer alarmLevel = alarm.getLevel();
					Integer alarmCode = alarm.getCode();
					String alarmContent = alarm.getName();
					System.out.println("Call `spAddAlarm`("+modelCode+"," +
							"'"+categoryName+"',"+alarmLevel+","+
							alarmCode+",'"+alarmContent+"');");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public String importFile(String pathName){
		String result = null;
		File file = new File(pathName);
		InputStream is = null;
		InputStreamReader reader = null;
		try {
			is = new FileInputStream(file);
			//isReader = new InputStreamReader(is, "UTF8");
			reader = new InputStreamReader(is,"UTF8");
			StringBuilder sb = new StringBuilder();
			char[] buffer = new char[1024];
			int size = 0;
			while((size = reader.read(buffer)) >0){
				sb.append(buffer, 0, size);
			}
			result = sb.toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		TestImportor importor = new TestImportor();
		String pathName = "D://Documents//桌面//test.xml";
		String content = importor.importFile(pathName);
//		System.out.println(content);
		importor.parseXml(content);
	}
}

//
//<?xml version="1.0" encoding="UTF-8"?>
//<alarms>
//	<alarm-list category="PV侧告警">
//		<alarm  code="273" name="第 1 路组串输入电压高"  level="3"/>
//		<alarm  code="274" name="第 2 路组串输入电压高" level="3"/>
//		<alarm  code="" name="第 1 路组串输入电压低" level="3"/>
//		<alarm  code="" name="第 2 路组串输入电压低" level="3"/>
//		<alarm  code="257" name="第 1 路组串输入极性反接" level="3"/>
//		<alarm  code="258" name="第 2 路组串输入极性反接" level="3"/>
//		<alarm  code="3597" name="ISO 绝缘阻抗异常" level="3"/>
//	</alarm-list>
//</alarms>
