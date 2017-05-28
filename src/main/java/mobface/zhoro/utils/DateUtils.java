package mobface.zhoro.utils;

import java.util.Calendar;

public class DateUtils {
	Calendar date = Calendar.getInstance();

	/**
	 * 
	 * @return
	 */
	public  int getTodaysDate() {
		return date.get(Calendar.DATE);
	}

	/**
	 * 
	 * @return
	 */
	public  int getyesterdaysDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return 	cal.get(Calendar.DATE);
	}
	/**
	 * 
	 * @return
	 */
	public  int getyesterdaysMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return 	cal.get(Calendar.MONTH);
	}
	/**
	 * 
	 * @return
	 */
	public  int getyesterdaysYear() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return 	cal.get(Calendar.YEAR);
	}
	/**
	 * 
	 * @return
	 */
	public  int gettomorrowssMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, +1);
		return 	cal.get(Calendar.MONTH);
	}
	/**
	 * 
	 * @return
	 */
	public  int gettomorrowssYear() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, +1);
		return 	cal.get(Calendar.YEAR);
	}
	/**
	 * 
	 * @return
	 */
	public  int gettomorrowssDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, +1);
		return 	cal.get(Calendar.DATE);
	}

	/**
	 * 
	 * @return
	 */
	public  int getYear() {

		return date.get(Calendar.YEAR);
	}

	/**
	 * 
	 * @return
	 */
	public  int getMonth() {
		return date.get(Calendar.MONTH);
	}

	/**
	 * 
	 * @return
	 */
	public  String getWeek() {
		return null;
	}

}
