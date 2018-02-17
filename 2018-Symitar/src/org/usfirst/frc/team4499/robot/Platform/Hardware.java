package org.usfirst.frc.team4499.robot;
import com.ctre.phoenix.CANifier;
import edu.wpi.first.wpilibj.Joystick;

public class Hardware {
	public static CANifier canifier = new CANifier(10);
	public static Joystick gamepad = new Joystick(0);
}
