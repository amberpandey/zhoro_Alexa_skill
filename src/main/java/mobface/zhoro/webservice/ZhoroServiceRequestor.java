package mobface.zhoro.webservice;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

public class ZhoroServiceRequestor {
/**
 * 
 * @param url
 * @param isYearlyPrediction
 * @return
 */
	public String ReqestServiceForPrediction(URL url,boolean isYearlyPrediction){
		String responseString="";
		try {
			URLConnection connection = url.openConnection();
			responseString = new BufferedReader(new InputStreamReader(connection.getInputStream())).lines()
					   .parallel().collect(Collectors.joining("\n"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseString = "Service Currently Unavailable!!!";
			e.printStackTrace();
		}
		return parseResponse(responseString,isYearlyPrediction);	
	}
/**
 * 
 * @param responseFromService
 * @param isYearlyPrediction
 * @return
 */
	private String parseResponse(String responseFromService, boolean isYearlyPrediction){
		String data ="";
		if (isYearlyPrediction){
		return responseFromService.split("<p align=\"justify\">")[1].split("</p>")[0].replace("<br>","  ");
		}else{
		try{
		data= responseFromService.split("<p align=\"justify\">")[1].split("</p>")[0];

		}catch(Exception e){
		data="Unable to contact server please try after some time";	
		}

		return  data;
/*		System.out.println(data);*/

		}

	}

}
