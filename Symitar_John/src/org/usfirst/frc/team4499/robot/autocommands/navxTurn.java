package org.usfirst.frc.team4499.robot.autocommands;

import org.usfirst.frc.team4499.robot.OI;

import org.usfirst.frc.team4499.robot.RobotMap;
//import org.usfirt.frc.team4499.robot.tools.PID;


import org.usfirst.frc.team4499.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4499.robot.tools.PID;


/**
 *
*/ 
public class navxTurn extends Command {
	private double speed = 0;
	private double time;
	private double desiredAngle;
	private double kp = 0.075;
	private double ki = 0.000250;
	private double kd = 0;
	private PID orientation; 
	private double startTime;
	private int zeroed;
    private float turnPower;

	private boolean across = false;
	

    public navxTurn( double angle, float Power) {
        desiredAngle= angle;
        orientation = new PID(kp,ki,kd);
    	orientation.setContinuous(true);
    	orientation.setMaxOutput(Power);
    	orientation.setMinOutput(-Power);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
       orientation.setSetPoint(RobotMap.navx.getAngle() + desiredAngle);
       startTime = Timer.getFPGATimestamp();
    }

    // Csalled repeatedly when this Command is scheduled to run
    protected void execute() {
  
 
    	orientation.updatePID(RobotMap.navx.getAngle());
    	RobotMap.leftDriveLead.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,-orientation.getResult());
    	RobotMap.rightDriveLead.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, orientation.getResult());
    	

   }


    

    // Make this return true whcccen this Command no longer needs to run execute()
    protected boolean isFinished() {
    
    	if(Math.abs(RobotMap.navx.getAngle() - this.desiredAngle) <=0.5) {
   
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	this.end();
    
    	RobotMap.leftDriveLead.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    	RobotMap.rightDriveLead.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    	

  	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

//Accretion Navx Turn, Comment out for accretion
/*
package org.usfirst.frc.team4499.robot.AutoCommands;

import org.usfirst.frc.team4499.robot.OI;

import org.usfirst.frc.team4499.robot.RobotMap;
//import org.usfirt.frc.team4499.robot.tools.PID;


import org.usfirst.frc.team4499.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4499.robot.tools.PID;


/**
 *

public class navxTurn extends Command {
	private double speed = 0;
	private double time;
	private double desiredAngle;
	private double kp = 0.075;
	private double ki = 0.000250;
	private double kd = 0;
	private PID orientation; 
	private double startTime;
	private int zeroed;
    private float turnPower;

	private boolean across = false;
	

    public navxTurn( double angle, float Power) {
    	turnPower= Power;
    	this.time = time;
        desiredAngle= angle;
    	this.speed = speed;
    
        orientation = new PID(kp,ki,kd);
    	orientation.setContinuous(true);
    	orientation.setMaxInput(360);
    	orientation.setMinInput(0);
    	orientation.setMaxOutput(0.5);
    	orientation.setMinOutput(-0.5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    
    	
    	RobotMap.motorLeftTwo.setSelectedSensorPosition(0,0,0);
		RobotMap.motorRightTwo.setSelectedSensorPosition(0,0,0);
    	
    orientation.setSetPoint(desiredAngle);
    	startTime = Timer.getFPGATimestamp();
    }

    // Csalled repeatedly when this Command is scheduled to run
    protected void execute() {
  
 
    orientation.updatePID(RobotMap.navx.getAngle());
    	RobotMap.motorLeftOne.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, turnPower*(orientation.getResult() - speed));
    	RobotMap.motorLeftTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, turnPower*(orientation.getResult() - speed));
    	
    	RobotMap.motorRightOne.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, turnPower*(orientation.getResult() + speed));
    	RobotMap.motorRightTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, turnPower*(orientation.getResult() + speed));
    	System.out.println(RobotMap.navx.getAngle()-desiredAngle);
   }


    

    // Make this return true whcccen this Command no longer needs to run execute()
    protected boolean isFinished() {
    
    	if(Math.abs(RobotMap.navx.getAngle() - this.desiredAngle) <=0.5) {
   
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    
    	RobotMap.motorLeftOne.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    	RobotMap.motorLeftTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    	
    	RobotMap.motorRightOne.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    	RobotMap.motorRightTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
  	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}*/