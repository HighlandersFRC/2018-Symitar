


package org.usfirst.frc.team4499.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick joyStickOne = new Joystick(0);
	public static JoystickButton intake = new JoystickButton(joyStickOne,5);
	public static JoystickButton outake = new JoystickButton(joyStickOne,6);
	public static JoystickButton pistonin = new JoystickButton(joyStickOne,1);
	public static JoystickButton pistonout = new JoystickButton(joyStickOne,4);
	public static Joystick joystickTwo = new Joystick(1);
	

	
	
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);
	
}
