/**
 * 
 */
package mobface.zhoro;

import java.util.ArrayList;

import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;

import mobface.zhoro.utils.PreviousPrediction;
import mobface.zhoro.webservice.ZhoroServiceInteraction;

/**
 * This sample shows how to create a simple speechlet for handling speechlet requests.
 */
public class zHoroSpeechlet implements Speechlet {
	ArrayList<String> zodiacList=new ArrayList<>();
    private static final Logger log = LoggerFactory.getLogger(zHoroSpeechlet.class);

    @Override
    public void onSessionStarted(final SessionStartedRequest request, final Session session)
            throws SpeechletException {
        log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        // any initialization logic goes here
    }

    @Override
    public SpeechletResponse onLaunch(final LaunchRequest request, final Session session)
            throws SpeechletException {
        log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        return getWelcomeResponse();
    }

    @Override
    public SpeechletResponse onIntent(final IntentRequest request, final Session session)
            throws SpeechletException {
        log.info("onIntent requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        if ("WeekIntent".equals(intentName)) {
            return getHelloResponse("WEEK", WordUtils.capitalizeFully(intent.getSlot("Zodiac").getValue()));
        } else if ("MonthIntent".equals(intentName)) {
            return getHelloResponse("MONTH", WordUtils.capitalizeFully(intent.getSlot("Zodiac").getValue()));
        }else if ("YearIntent".equals(intentName)) {
            return getHelloResponse("YEAR", WordUtils.capitalizeFully(intent.getSlot("Zodiac").getValue()));
        }else if ("DailyIntent".equals(intentName)) {
            return getHelloResponse("TODAY", WordUtils.capitalizeFully(intent.getSlot("Zodiac").getValue()));
        }else if ("YesterdayIntent".equals(intentName)) {
            return getHelloResponse("YESTERDAY", WordUtils.capitalizeFully(intent.getSlot("Zodiac").getValue()));
        }else if ("TomorrowIntent".equals(intentName)) {
            return getHelloResponse("TOMORROW", WordUtils.capitalizeFully(intent.getSlot("Zodiac").getValue()));
        }else if ("AMAZON.HelpIntent".equals(intentName)) {
            return getHelpResponse();
        }else if ("AMAZON.StopIntent".equals(intentName)) {
            PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
            outputSpeech.setText("Goodbye, Hope you like zHoro Skill on Alexa");

            return SpeechletResponse.newTellResponse(outputSpeech);
        } else if ("AMAZON.CancelIntent".equals(intentName)) {
            PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
            outputSpeech.setText("Goodbye, Hope you like zHoro Skill on Alexa");

            return SpeechletResponse.newTellResponse(outputSpeech);
        } 
        else if ("AMAZON.RepeatIntent".equals(intentName)) {
        	 return getRepeatResponse();
        }else {
            throw new SpeechletException("Invalid Intent");
        }
    }

    @Override
    public void onSessionEnded(final SessionEndedRequest request, final Session session)
            throws SpeechletException {
        log.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        // any cleanup logic goes here
    }

    /**
     * Creates and returns a {@code SpeechletResponse} with a welcome message.
     *
     * @return SpeechletResponse spoken and visual response for the given intent
     */
    private SpeechletResponse getWelcomeResponse() {
        String speechText = "Welcome to the Z Horo, you can get your predictions or horoscope just by saying simple command , for example you can say, Get this week horscope for gemini , to get the current week horoscope of Gemini sun sign ";

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("zHoro");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }

    /**
     * Creates a {@code SpeechletResponse} for the hello intent.
     *
     * @return SpeechletResponse spoken and visual response for the given intent
     */
    private SpeechletResponse getHelloResponse(String timePeriod, String zodiac) {
    	String response="Unable to process your request at this time";
    	String title="";
    	String reprompttext="";
    	ZhoroServiceInteraction zhoro = new ZhoroServiceInteraction();
    	
    	switch(timePeriod){
    	case "WEEK":
    		response="This Week Prediction for "+zodiac+" is,  "+zhoro.getWeeksPrediction(zodiac)+". z Horo can also tell  daily yearly or monthly predictions. Thanks for trying z horo, good bye";
    		title="This Week Prediction for "+zodiac+" is";
    		reprompttext ="zHoro can also tell prediction for month and year. go ahead and ask for your monthly and yearly prediction";
    		break;
    	case "MONTH":
    		response="This months Prediction for "+zodiac+" is,  "+zhoro.getMonthsPrediction(zodiac)+". z Horo can also tell  daily yearly or weekly predictions. Thanks for trying z horo, good bye";
    		title="This months Prediction for "+zodiac+" is";
    		reprompttext ="zHoro can also tell prediction for week and year. go ahead and ask for your weekly and yearly prediction";
    		break;
    	case "YEAR":
    		response="This year Prediction for "+zodiac+" is,  "+zhoro.getYearsPrediction(zodiac)+". z Horo can also tell  daily monthly or weekly predictions. Thanks for trying z horo, good bye";
    		title="This year Prediction for "+zodiac+" is";
    		reprompttext ="zHoro can also tell prediction for month and week. go ahead and ask for your monthly and weekly prediction";
    		reprompttext="";
    		break;
    	case "TODAY":
    		response="Today's Prediction for "+zodiac+" is,  "+zhoro.getTodaysPrediction(zodiac)+". z Horo can also tell  weekly monthly or yearly predictions. Thanks for trying z horo, good bye";
    		title="Today's Prediction for "+zodiac+" is";
    		break;
    	case "YESTERDAY":
    		response="Yesterday's Prediction for "+zodiac+" was,  "+zhoro.getYesterdaysPrediction(zodiac)+". z Horo can also tell  weekly monthly or yearly predictions. Thanks for trying z horo, good bye";
    		title="Yesterday's Prediction for "+zodiac+" was";
    		break;
      	case "TOMORROW":
    		response="Tomorrow's Prediction for "+zodiac+" is,  "+zhoro.getTomorrowsPrediction(zodiac)+". z Horo can also tell  weekly monthly or yearly predictions. Thanks for trying z horo, good bye";
    		title="Tomorrow's Prediction for "+zodiac+" is";
    		break;
    	default:
    			title="Failed to fetch your prediction";
    			response="Unable to process your request at this time";
    	break;
    	}
        String speechText = response;

        // Create the Simple card content.
        PreviousPrediction.PREDICTION=response;
        PreviousPrediction.REPROMPT=reprompttext;
        SimpleCard card = new SimpleCard();
        card.setTitle(title);
        card.setContent(response);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);
        // Create the plain text output.
        PlainTextOutputSpeech repromptspeech = new PlainTextOutputSpeech();
        repromptspeech.setText(reprompttext);

        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptspeech);
        return SpeechletResponse.newTellResponse(speech,card);
/*         SpeechletResponse.newAskResponse(speech,reprompt, card);*/
    }

    /**
     * Creates a {@code SpeechletResponse} for the help intent.
     *
     * @return SpeechletResponse spoken and visual response for the given intent
     */
    private SpeechletResponse getHelpResponse() {
        String speechText = "you can get your predictions or horoscope just by saying simple command , for example you can say, Get this week horscope for gemini , to get the current week horoscope of Gemini sun sign for quiting say cancel or stop";

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("zHoro");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);
        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }
    /**
     * Creates a {@code SpeechletResponse} for the help intent.
     *
     * @return SpeechletResponse spoken and visual response for the given intent
     */
    private SpeechletResponse getRepeatResponse() {
        String speechText = PreviousPrediction.PREDICTION;

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("zHoro");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(PreviousPrediction.PREDICTION);
        PlainTextOutputSpeech repromptspeech = new PlainTextOutputSpeech();
        repromptspeech.setText(PreviousPrediction.REPROMPT);
        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptspeech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);

        }
    
}
