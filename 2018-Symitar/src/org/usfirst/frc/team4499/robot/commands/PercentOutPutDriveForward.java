package org.usfirst.frc.team4499.robot.commands;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj.command.Command;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import org.usfirst.frc.team4499.robot.RobotMap;

import org.usfirst.frc.team4499.robot.tools.PID;
import org.usfirst.frc.team4499.robot.Robot;





import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class PercentOutPutDriveForward extends Command {
	
	private double startTime;
    public PercentOutPutDriveForward() {
    	
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = Timer.getFPGATimestamp();
        RobotMap.motorLeftTwo.configVoltageCompSaturation(RobotMap.voltageControlMax, 10);
        RobotMap.motorLeftTwo.enableVoltageCompensation(true); 
        RobotMap.motorLeftTwo.configVoltageMeasurementFilter(32, 10);
        
        RobotMap.motorRightTwo.configVoltageCompSaturation(RobotMap.voltageControlMax, 10);
        RobotMap.motorRightTwo.enableVoltageCompensation(true); 
        RobotMap.motorRightTwo.configVoltageMeasurementFilter(32, 10);
        
        RobotMap.motorLeftOne.configVoltageCompSaturation(RobotMap.voltageControlMax, 10);
        RobotMap.motorLeftOne.enableVoltageCompensation(true); 
        RobotMap.motorLeftOne.configVoltageMeasurementFilter(32, 10);
        
        RobotMap.motorRightOne.configVoltageCompSaturation(RobotMap.voltageControlMax, 10);
        RobotMap.motorRightOne.enableVoltageCompensation(true);
        RobotMap.motorRightOne.configVoltageMeasurementFilter(32, 10);
    }

    // Called repeatedly when this Command is scheduled to run
    //to find the fvalue, use Self test to find the Percent Output
    //then, do ([PercentOutput] *1023)/Native units per 100ms;
    //find this on https://github.com/CrossTheRoadElec/Phoenix-Documentation/blob/master/README.md
    protected void execute() {
    	RobotMap.motorLeftOne.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,-0.5);
    	RobotMap.motorRightOne.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,0.5);
    	RobotMap.motorLeftTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,-0.5);
    	RobotMap.motorRightTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Timer.getFPGATimestamp()-startTime > 5) {
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.motorLeftTwo.enableVoltageCompensation(false);
        RobotMap.motorRightTwo.enableVoltageCompensation(false);
        RobotMap.motorLeftOne.enableVoltageCompensation(false);
        RobotMap.motorRightOne.enableVoltageCompensation(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	RobotMap.motorLeftTwo.enableVoltageCompensation(false);
        RobotMap.motorRightTwo.enableVoltageCompensation(false);
        RobotMap.motorLeftOne.enableVoltageCompensation(false);
        RobotMap.motorRightOne.enableVoltageCompensation(false);
    }
}
