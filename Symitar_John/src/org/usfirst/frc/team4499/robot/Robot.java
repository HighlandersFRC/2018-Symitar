/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4499.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4499.robot.autocommands.navxTurn;

import java.text.FieldPosition;

import org.usfirst.frc.team4499.robot.autocommands.AutoPicker;
import org.usfirst.frc.team4499.robot.autocommands.motionMagicDriveForward;

import org.usfirst.frc.team4499.robot.commands.MoveArm;
import org.usfirst.frc.team4499.robot.commands.MPArm;
import org.usfirst.frc.team4499.robot.commands.TeleopDrive;
import org.usfirst.frc.team4499.robot.commands.TeleopGrabber;
import org.usfirst.frc.team4499.robot.commands.TeleopArm;
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
	public motionMagicDriveForward motionMagicDriveForward;
    public navxTurn turn;
    public AutoPicker auto;
   
    public MPArm mpArm;
    
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		auto = new AutoPicker();
		System.out.println("Test");
		motionMagicDriveForward = new motionMagicDriveForward(100, RobotMap.navx.getAngle(), 1100, 1500);
	    turn = new navxTurn(45,  0.75f);
	
	
	    UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(0);
	    UsbCamera camera2 = CameraServer.getInstance().startAutomaticCapture(1);
	
	
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
        config.autoConfig();
        auto.start();
        RobotConfig.fieldPositions = DriverStation.getInstance().getGameSpecificMessage();	
        if(OI.switchOne.get()) {
        	RobotConfig.robotStartPosition ='L';
        }
        else if(OI.switchThree.get()) {
        	RobotConfig.robotStartPosition = 'R';
        }
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
		config.teleopConfig();
		TeleopDrive drive = new TeleopDrive();
		TeleopArm arm = new TeleopArm();
		TeleopGrabber grabber = new TeleopGrabber();
		Scheduler.getInstance().add(drive);
		Scheduler.getInstance().add(grabber);
		Scheduler.getInstance().add(arm);
		
		
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
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
