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
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4499.robot.RobotMap;
import org.usfirst.frc.team4499.robot.OI;
import org.usfirst.frc.team4499.robot.commands.ExampleCommand;
import org.usfirst.frc.team4499.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team4499.robot.RobotMap;
import org.usfirst.frc.team4499.robot.commands.motionMagicDriveForward;

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
	public static OI m_oi;hlk
	
	public static boolean startedCommand;
	private static motionMagicDriveForward drive;
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		drive = new motionMagicDriveForward(100000, RobotMap.navx.getAngle());

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
		drive.start();
		m_autonomousCommand = m_chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
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
	//	RobotMap.componentmotorLeftThree.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
	
		if(!this.startedCommand) {
			RobotMap.motorLeftOne.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, OI.joyStickOne.getRawAxis(1));
			RobotMap.motorRightOne.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, -OI.joyStickOne.getRawAxis(5));
			RobotMap.motorLeftTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, OI.joyStickOne.getRawAxis(1));
			RobotMap.motorRightTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, -OI.joyStickOne.getRawAxis(5));
					
		
			if(OI.outake.get())
				
			{
				if(OI.pistonin.get()){
					RobotMap.Solenoid1.set(DoubleSolenoid.Value.kForward);
				}
				
				else{
					RobotMap.Solenoid1.set(DoubleSolenoid.Value.kReverse);
				}
			
				System.out.println("outtaking");
				RobotMap.componentmotorLeftOne.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, -0.3);
				RobotMap.componentmotorLeftTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0.3);
                
			}
			else if(OI.intake.get())
			{
				if(OI.pistonin.get()){
					RobotMap.Solenoid1.set(DoubleSolenoid.Value.kForward);
				}
			
				else{
					RobotMap.Solenoid1.set(DoubleSolenoid.Value.kReverse);
				}
				System.out.println("intaking");
				RobotMap.componentmotorLeftOne.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 1.0);
				RobotMap.componentmotorLeftTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, -1.0);	
				System.out.println(RobotMap.componentmotorLeftOne.getMotorOutputPercent() + "left");
				System.out.println(RobotMap.componentmotorLeftTwo.getMotorOutputPercent() + "Right");

			}	
			else{
				if(OI.pistonin.get()){
					RobotMap.Solenoid1.set(DoubleSolenoid.Value.kForward);
				}
			
				else{
					RobotMap.Solenoid1.set(DoubleSolenoid.Value.kReverse);
				}
				RobotMap.componentmotorLeftOne.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
			    RobotMap.componentmotorLeftTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);	   
				
			}
		
		}
		
		
		Scheduler.getInstance().run();
	}


	@Override
	public void testPeriodic() {
	}
}
