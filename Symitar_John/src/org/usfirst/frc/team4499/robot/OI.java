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
	public static JoystickButton armForwardIntake = new JoystickButton(joyStickTwo,4);
	public static JoystickButton armReverseIntake = new JoystickButton(joyStickTwo,1);
	public static JoystickButton armForwardShoot = new JoystickButton(joyStickTwo, 2);
	public static JoystickButton armReverseShoot = new JoystickButton(joyStickTwo, 3);
	
	public static JoystickButton closeIntake = new JoystickButton(joyStickTwo, 6);
	public static JoystickButton openIntake = new JoystickButton(joyStickTwo, 5);
	//setting up dial inputs
	public static Joystick dial = new Joystick(2);
	public static JoystickButton dialOne = new JoystickButton(dial,1);
	public static JoystickButton dialTwo = new JoystickButton(dial,2);
	public static JoystickButton dialThree = new JoystickButton(dial,3);
	public static JoystickButton dialFour = new JoystickButton(dial,4);
	public static JoystickButton dialFive = new JoystickButton(dial,5);
	public static JoystickButton switchOne = new JoystickButton(dial, 6);
	public static JoystickButton switchTwo = new JoystickButton(dial, 7);
	public static JoystickButton switchThree = new JoystickButton(dial, 8);
	
	
	
}
