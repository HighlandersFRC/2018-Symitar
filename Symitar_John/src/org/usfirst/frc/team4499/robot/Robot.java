/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4499.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4499.robot.commands.MoveArm;
import org.usfirst.frc.team4499.robot.commands.TeleopDrive;
import org.usfirst.frc.team4499.robot.subsystems.ExampleSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class Robot extends TimedRobot {
	public static OI m_oi;
	public SerialPort cam2;
	public SerialPort cam1;
	public SerialPort cam;
	//public MoveArm arm = new MoveArm(0);
	public static final ExampleSubsystem kExampleSubsystem
	= new ExampleSubsystem();
	public RobotConfig config;

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		System.out.println("Test");
		
		 CameraServer server = CameraServer.getInstance();
	     server.startAutomaticCapture();
	        
	     cam= new SerialPort(115200, SerialPort.Port.kUSB);
	     cam1= new SerialPort(115200, SerialPort.Port.kUSB1);
	     cam2= new SerialPort(115200, SerialPort.Port.kUSB2);
	        
		m_oi = new OI();
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
		config = new RobotConfig();
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

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		TeleopDrive drive = new TeleopDrive();
		Scheduler.getInstance().add(drive);
		
		
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		//System.out.println("John's Code is Running");
		Scheduler.getInstance().run();
		/*
		if(OI.armUp.get() && !arm.isRunning()) {
			arm = new MoveArm(45);
			Scheduler.getInstance().add(arm);
		}
		else if(OI.armDown.get() && !arm.isRunning()) {
			arm= new MoveArm(0);
			Scheduler.getInstance().add(arm);
		}
		*/
		if(OI.armUp.get()) {
			RobotMap.armMaster.set(ControlMode.PercentOutput, 0.2);
			
		}
		else if(OI.armDown.get()) {
			RobotMap.armMaster.set(ControlMode.PercentOutput, -0.2);
		}
		else {
			RobotMap.armMaster.set(ControlMode.PercentOutput, 0);
		}
		
		System.out.println(RobotMap.armMaster.getSensorCollection().getQuadraturePosition());
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
