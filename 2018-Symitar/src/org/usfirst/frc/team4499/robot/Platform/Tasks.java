package org.usfirst.frc.team4499.robot;

import com.ctre.phoenix.ILoopable;
import org.usfirst.frc.team4499.robot.TaskAnimatedLEDStrip;


public class Tasks {
	public static TaskAnimatedLEDStrip taskAnimatedLEDStrip = new TaskAnimatedLEDStrip();
	public static org.usfirst.frc.team4499.robot.TaskHSV taskHSV_ControlLedStrip = new org.usfirst.frc.team4499.robot.TaskHSV();
	public static org.usfirst.frc.team4499.robot.TaskMainLoop taskMainLoop = new org.usfirst.frc.team4499.robot.TaskMainLoop();
	
	public static ILoopable[] FullList ={
			taskAnimatedLEDStrip,taskMainLoop,taskHSV_ControlLedStrip,
	};
	
}
