/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4499.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	//Setup Controllers
	public static Joystick joyStickOne = new Joystick(0);
	public static Joystick joyStickTwo = new Joystick(1);
	
	//Setup Buttons for Driver
	public static JoystickButton shiftDown = new JoystickButton(joyStickOne,5);
	public static JoystickButton shiftUp = new JoystickButton(joyStickOne,6);
	
	//Setup Buttons for Copilot, TODO change to controller two 
	public static JoystickButton armUp = new JoystickButton(joyStickOne,4);
	public static JoystickButton armDown = new JoystickButton(joyStickOne,1);
	public static JoystickButton closeIntake = new JoystickButton(joyStickOne, 2);
	public static JoystickButton openIntake = new JoystickButton(joyStickOne, 3);
	
	
}
