import java.io.IOException;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Gesture;


public class RcxController {
	public static Controller controller;
	public static LeapListener listener;
	
	public static void main(String[] args) {
		controller = new Controller();
		listener = new LeapListener();
		
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.addListener(listener);
		
		try {
			ListenToMe voice = new ListenToMe();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true){}
	}
}
