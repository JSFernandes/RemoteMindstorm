import com.leapmotion.leap.CircleGesture;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Listener;

public class LeapListener extends Listener {
	
	public void onFrame(Controller controller) {
		try {
			String message = null;
			if(controller.frame().fingers().count() == 5) {
				float hand_z = controller.frame().hands().get(0).palmPosition().getZ();
				if(hand_z < -20) {
					message = "1";
					System.out.println("going ahead");
				}
				else if(hand_z > 50) {
					message = "2";
					System.out.println("going back!");
				}
				else {
					message = "5";
					System.out.println("stahp");
				}
			}
			
			Gesture gesture = controller.frame().gestures().get(0);
			CircleGesture circle = new CircleGesture(gesture);
			if(circle.isValid()) {
				if (circle.pointable().direction().angleTo(circle.normal()) <= Math.PI/2) {
					message = "3";
	                System.out.println("clockwise");
				}
				else
				{
					message = "4";
					System.out.println("counterclockwise");
				}
			}
			
			if(message != null) {
				Runtime.getRuntime().exec(new String[]{"bash","-c","nqc -Susb:/dev/usb/legousbtower0 -msg " + message});
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return;
		}
	}
}
