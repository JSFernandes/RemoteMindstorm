import java.io.IOException;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class ListenToMe {
	public ListenToMe() throws IOException{
		ConfigurationManager cm = new ConfigurationManager(ListenToMe.class.getResource("myconfig.xml"));
		Recognizer recognizer = (Recognizer)cm.lookup("recognizer");
		recognizer.allocate();
		Microphone microphone = (Microphone)cm.lookup("microphone");
		if(!microphone.startRecording()){
			System.out.println("Cannot start microphone.");
			recognizer.deallocate();
			System.exit(1);
		}
		//System.out.println("Say: (Good morning | Hello | Hi | Welcome) ( Osama | Paul | Philip | Rita | Will )");
		do {
			System.out.println("Start speaking. Press Ctrl-C to quit.\n");
			Result result = recognizer.recognize();
			if(result != null) {
				String resultText = result.getBestFinalResultNoFiller();
				System.out.println((new StringBuilder()).append("You said: ").append(resultText).append('\n').toString());
				if(resultText.equals("play sound"))
					Runtime.getRuntime().exec(new String[]{"bash","-c","nqc -Susb:/dev/usb/legousbtower0 -raw 5902"});

			} else {
				System.out.println("I can't hear what you said.\n");
			}
		} while(true);
	}

}
