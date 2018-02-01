/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4499.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;




import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.hal.PDPJNI;
import org.usfirst.frc.team4499.robot.RobotMap;
import org.usfirst.frc.team4499.robot.OI;
import org.usfirst.frc.team4499.robot.commands.ExampleCommand;
import org.usfirst.frc.team4499.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team4499.robot.RobotMap;
import org.usfirst.frc.team4499.robot.AutoCommands.navxTurn;
import org.usfirst.frc.team4499.robot.AutoCommands.motionMagicDriveForward;
import org.usfirst.frc.team4499.robot.commands.PercentOutPutDriveForward;
import org.usfirst.frc.team4499.robot.commands.controlDriveTrain;



import org.usfirst.frc.team4499.robot.AutoCommands.CenterAutoLeft;
import com.ctre.phoenix.ILoopable;
import org.usfirst.frc.team4499.robot.Schedulers;


// Give me something cause why not
//Too broke


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	
	public static final ExampleSubsystem kExampleSubsystem
			= new ExampleSubsystem();
	public static OI m_oi;
	private CenterAutoLeft Auto = new CenterAutoLeft();
	public static boolean startedCommand;
	private motionMagicDriveForward drive;
	private PercentOutPutDriveForward drive2;
	private navxTurn turn;
	private controlDriveTrain control;
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
    	 control = new controlDriveTrain(RobotMap.motorLeftOne, RobotMap.motorLeftTwo, RobotMap.motorRightOne, RobotMap.motorRightTwo,38);

		//turn = new navxTurn(RobotMap.navx.getAngle()+ 45);
		drive = new motionMagicDriveForward(110, RobotMap.navx.getAngle(),300, 150);
		drive2 = new PercentOutPutDriveForward();

		m_oi = new OI();
		m_chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
	}

	
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
    	

		
		m_autonomousCommand = m_chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
            drive.start();
            RobotMap.motorLeftTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
        	RobotMap.motorRightTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
        	RobotMap.motorLeftOne.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
        	RobotMap.motorRightTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
            
			m_autonomousCommand.start();
		}
	}

	
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {

		RobotMap.motorLeftOne.setInverted(true);
		RobotMap.motorLeftTwo.setInverted(true);
		control.start();
		RobotMap.motorLeftOne.configOpenloopRamp(0.3,0);
		RobotMap.motorRightOne.configOpenloopRamp(0.3,0);
		RobotMap.motorLeftTwo.configOpenloopRamp(0.3,0);
		RobotMap.motorRightTwo.configOpenloopRamp(0.3,0);

	 	//************************
		//FOR LED LIGHTS
		for (ILoopable loop:org.usfirst.frc.team4499.robot.Tasks.FullList)
		{
				org.usfirst.frc.team4499.robot.Schedulers.PeriodicTasks.add(loop);
		}
		//************************
			
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	
	@Override
	public void teleopPeriodic() {

    System.out.println(RobotMap.pdp.getVoltage() + "pdpvoltage");
	System.out.println(RobotMap.motorLeftTwo.getOutputCurrent() );
	SmartDashboard.putBoolean( "IMU_Connected", RobotMap.navx.isConnected());
    System.out.println(RobotMap.navx.getAngle()+ " navx Angle X");

		//************************
				//FOR LED LIGHTS
		org.usfirst.frc.team4499.robot.Schedulers.PeriodicTasks.process();
		
		//************************
	
			
		
		Scheduler.getInstance().run();
	}


	@Override
	public void testPeriodic() {
	}
}
