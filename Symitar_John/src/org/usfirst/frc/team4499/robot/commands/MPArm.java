package org.usfirst.frc.team4499.robot.commands;

import org.usfirst.frc.team4499.robot.RobotConfig;
import org.usfirst.frc.team4499.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MPArm extends Command {
	private double endpoint;
	private double currentAngle;
	private double startingAngle;
	private int run;
	private int angleTolerance;
	private double crateMultiplier;

    public MPArm(double angle, int tolerance) {
    	endpoint= angle;
    	angleTolerance= tolerance;
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startingAngle=-(RobotMap.armMaster.getSensorCollection().getQuadraturePosition()/2048.0)*180;
        run=0;    	
    	RobotMap.brake.set(RobotMap.releaseBrake);
    	if(RobotMap.armMaster.getSensorCollection().isRevLimitSwitchClosed()) {
    	    	RobotMap.armMaster.getSensorCollection().setQuadraturePosition(RobotConfig.armMaxEncoderTicks, RobotConfig.timeOut);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    currentAngle=-(RobotMap.armMaster.getSensorCollection().getQuadraturePosition()/2048.0)*180;
    if(RobotMap.intakeLeft.getSensorCollection().isFwdLimitSwitchClosed()) {
    	crateMultiplier = 1.25;
    
    }
    else {
    	crateMultiplier= 1;
    }
    if(startingAngle<endpoint) {
    if(currentAngle + angleTolerance<endpoint) {
    	RobotMap.armMaster.set(ControlMode.PercentOutput, -0.180  + 0.7*(-0.162*Math.cos((-currentAngle*Math.PI)/180)));
    
    	
    }
    else {

    	run++;
    	RobotMap.brake.set(RobotMap.setBrake);
    	RobotMap.armMaster.set(ControlMode.PercentOutput, 0);
    }
    }
    else if(startingAngle>endpoint) {
    	if(currentAngle-angleTolerance>endpoint) {
        	RobotMap.armMaster.set(ControlMode.PercentOutput, +0.180  +0.7*(-0.162*Math.cos((-currentAngle*Math.PI)/180)));
        	
        }
        else {
         	run++;
        	RobotMap.brake.set(RobotMap.setBrake);
        	RobotMap.armMaster.set(ControlMode.PercentOutput, 0);
        }
    	
    }
    }
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(RobotMap.armMaster.getMotorOutputPercent()==0 && run!=0) {
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	RobotMap.brake.set(RobotMap.setBrake);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
