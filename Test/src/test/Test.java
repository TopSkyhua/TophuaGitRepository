package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author litianhua
 * @date 2017年10月10日
 */
public class Test {

	/**
     * 根据年 月 获取对应的月份 天数
     */
    public static int getDaysByYearMonth(Date date) {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	System.out.println(df.format(date));
        Calendar a = Calendar.getInstance();
        a.setTime(date);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        System.out.println(df.format(a.getTime()));
        return maxDate;
    }
    
	public Integer getResult(Integer a) {
		return a;
	}

	public static void main(String[] args) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		try {
			System.out.println(getDaysByYearMonth(df.parse("2018-04")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
