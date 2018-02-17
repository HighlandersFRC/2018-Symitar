package org.usfirst.frc.team4499.robot;
import com.ctre.phoenix.ILoopable;
import org.usfirst.frc.team4499.robot.Tasks;
import org.usfirst.frc.team4499.robot.TaskHSV;
import org.usfirst.frc.team4499.robot.Tasks;

public class TaskAnimatedLEDStrip implements ILoopable {
	private float _hue;

	public void onStart() {
		
	}
	public void onStop() {
		
	}
	public boolean isDone(){
		return false;
	}
	public void onLoop(){
		 /* Ramp through the outer rim of the HSV color wheel */
        _hue += 1;
        System.out.println("Animated Loop " + _hue);
        if (_hue >= 360) { _hue = 0; }
        /* Update LEDStrip/HSV target */
        org.usfirst.frc.team4499.robot.Tasks.taskHSV_ControlLedStrip.Hue = _hue;
        org.usfirst.frc.team4499.robot.Tasks.taskHSV_ControlLedStrip.Saturation = 1.0f; 	/* Outer rim of HSV color wheel */
        org.usfirst.frc.team4499.robot.Tasks.taskHSV_ControlLedStrip.Value = 0.05f; 		/* Hard-code the brightness */
	}
	
	public String toString(){
		return "AnimatedLEDStrip:" + _hue;
	}

}
