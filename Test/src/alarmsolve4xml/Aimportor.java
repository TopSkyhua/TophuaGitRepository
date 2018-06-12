/**
 * 
 */
package alarmsolve4xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * Description 
 * @author lijiaxi 
 * @date 2017-8-28
 */
public class Aimportor {
	public boolean parseXml(String content){
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Alarm.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(content);
			A1 dm = (A1) unmarshaller.unmarshal(reader);
			System.out.println(dm.getA2s().size());
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
		Aimportor importor = new Aimportor();
		String pathName = "D://Documents//桌面//EA3-5KTLSI对比大类新告警协议最新版1.xml";
		String content = importor.importFile(pathName);
		importor.parseXml(content);
	}
}
