package org.usfirst.frc.team4499.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4499.robot.OI;
import org.usfirst.frc.team4499.robot.RobotConfig;
import org.usfirst.frc.team4499.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
/**
 * This class will manage all aspects of the drive train during teleop
 */
public class TeleopDrive extends Command {
	
    public TeleopDrive() {
    	System.out.println("Teleop Drive Initialized");

    }

    protected void initialize() {
    

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(Math.abs(OI.joyStickOne.getRawAxis(5)) > 0.15) {
    		RobotMap.rightDriveLead.set(ControlMode.PercentOutput,OI.joyStickOne.getRawAxis(5));
    	}
    	else {
    		RobotMap.rightDriveLead.set(ControlMode.PercentOutput,0);
    	}
    	
    	if(Math.abs(OI.joyStickOne.getRawAxis(1))>0.15) {
    		RobotMap.leftDriveLead.set(ControlMode.PercentOutput, OI.joyStickOne.getRawAxis(1));
    	}
    	else {
    		RobotMap.leftDriveLead.set(ControlMode.PercentOutput,0);
    	}
    	
    	if(OI.shiftUp.get()) {
    		RobotMap.shifters.set(RobotMap.highGear);
    	}
    	else if(OI.shiftDown.get()) {
    		RobotMap.shifters.set(RobotMap.lowGear);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.rightDriveLead.set(ControlMode.PercentOutput,0);
    	RobotMap.leftDriveLead.set(ControlMode.PercentOutput, 0);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
