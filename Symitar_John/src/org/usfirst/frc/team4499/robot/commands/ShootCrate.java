package org.usfirst.frc.team4499.robot.commands;

import org.usfirst.frc.team4499.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootCrate extends Command {
	private double startTime;

    public ShootCrate() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime= Timer.getFPGATimestamp();
    	RobotMap.armMaster.set(ControlMode.PercentOutput, 0);
    	RobotMap.brake.set(RobotMap.setBrake);
    	RobotMap.intake.set(RobotMap.closeIntake);
    	RobotMap.intakeLeft.set(ControlMode.PercentOutput, 1);
    	RobotMap.intakeRight.set(ControlMode.PercentOutput, 1);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Math.abs(startTime-Timer.getFPGATimestamp())>0.75) {
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.intakeLeft.set(ControlMode.PercentOutput, 0);
    	RobotMap.intakeRight.set(ControlMode.PercentOutput, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
