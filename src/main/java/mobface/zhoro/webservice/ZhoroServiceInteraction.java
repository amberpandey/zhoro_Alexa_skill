package mobface.zhoro.webservice;

import java.net.MalformedURLException;
import java.net.URL;

import mobface.zhoro.constants.AppConstants;
import mobface.zhoro.utils.DateUtils;

public class ZhoroServiceInteraction {
	
	ZhoroServiceRequestor request = new ZhoroServiceRequestor();
	DateUtils date = new DateUtils();

	/**
	 * This method returns the todays prediction of given zodiac/(sun sign)
	 * 
	 * @param zodiac
	 * @return
	 */
	public String getTodaysPrediction(String zodiac) {
		String prediction = "Unable to contact server please try after some time";
		try {
			URL url = new URL(AppConstants.DailyURL + zodiac +"&hday="+date.getTodaysDate()+"&hmonth="+(date.getMonth()+1)+"&hyear="+date.getYear());
			prediction = request.ReqestServiceForPrediction(url, false);
			prediction=prediction.replace("Todays ,", "");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prediction;
	}

	/**
	 * This method returns the yesterdays prediction of given zodiac/(sun sign)
	 * 
	 * @param zodiac
	 * @return
	 */
	public String getYesterdaysPrediction(String zodiac) {
		String prediction = "Unable to contact server please try after some time";
		try {
			URL url = new URL(AppConstants.DailyURL + zodiac +"&hday="+date.getyesterdaysDate()+"&hmonth="+(date.getMonth()+1)+"&hyear="+date.getYear());
			prediction = request.ReqestServiceForPrediction(url, false);
			prediction=prediction.replace("Todays ,", "");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prediction;
	}

	/**
	 * This method returns the tomorrows prediction of given zodiac/(sun sign)
	 * 
	 * @param zodiac
	 * @return
	 */
	public String getTomorrowsPrediction(String zodiac) {
		String prediction = "Unable to contact server please try after some time";
		try {
			URL url = new URL(AppConstants.DailyURL + zodiac +"&hday="+date.gettomorrowssDate()+"&hmonth="+(date.gettomorrowssMonth()+1)+"&hyear="+date.gettomorrowssYear());
			prediction = request.ReqestServiceForPrediction(url, false);
			prediction=prediction.replace("Todays ,", "");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prediction;
	}

	/**
	 * This method returns this months prediction of given zodiac/(sun sign)
	 * 
	 * @param zodiac
	 * @return
	 */
	public String getMonthsPrediction(String zodiac) {
		String prediction = "Unable to contact server please try after some time";
		try {
			URL url = new URL(AppConstants.MonthURL +zodiac+"&hmonth="+date.getMonth()+"&hyear=" + date.getYear());
			prediction = request.ReqestServiceForPrediction(url, true);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prediction;
	}

	/**
	 * This method returns this weeks prediction of given zodiac/(sun sign)
	 * 
	 * @param zodiac
	 * @return
	 */
	public String getWeeksPrediction(String zodiac) {
		String prediction = "Unable to contact server please try after some time";
		try {
			URL url = new URL(AppConstants.WeekURL +zodiac+"&hday="+date.getTodaysDate()+"&hmonth="+date.getMonth()+"&hyear=" + date.getYear());
			prediction = request.ReqestServiceForPrediction(url, true);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prediction;

	}

	/**
	 * This method returns this years prediction of given zodiac/(sun sign)
	 * 
	 * @param zodiac
	 * @return
	 */
	public String getYearsPrediction(String zodiac) {
		String prediction = "Unable to contact server please try after some time";
		try {
			URL url = new URL(AppConstants.YearURL + zodiac + "&hyear=" + date.getYear());
			prediction = request.ReqestServiceForPrediction(url, true);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prediction;
	}

}
