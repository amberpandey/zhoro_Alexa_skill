package mobface.zhoro.webservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class MockRunner {
    private static final Logger log = LoggerFactory.getLogger(MockRunner.class);
	public static void main(String args[]){
		log.debug("inside MAin");
		ZhoroServiceInteraction http=new ZhoroServiceInteraction();
		try {
			//System.out.println(http.getYearsPrediction("Cancer"));
			System.out.println("TODAY"+http.getTodaysPrediction("Cancer"));
			System.out.println("Yesterdays"+http.getYesterdaysPrediction("Cancer"));
			System.out.println("Tomorrows"+http.getTomorrowsPrediction("Cancer"));
			/*System.out.println("Monthly"+http.getMonthsPrediction("Cancer"));*/
			//System.out.println("Weekly"+http.getWeeksPrediction("Cancer"));
//			http.login("qaygeoffrey.digitallife6432", "DL123att");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @return
	 */
	public static boolean switchOn(){
		return true;
	}
	/**
	 * 
	 * @return
	 */
	public static boolean switchOff(){
		return true;
	}
}
