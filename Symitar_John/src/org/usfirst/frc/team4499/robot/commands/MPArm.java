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
	private int run;

    public MPArm() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize(double angle) {
        run=0;
    	endpoint= angle;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    currentAngle=-(RobotMap.armMaster.getSensorCollection().getQuadraturePosition()/2048.0)*180;
    if(RobotMap.armMaster.getSensorCollection().isRevLimitSwitchClosed()) {
    	RobotMap.armMaster.getSensorCollection().setQuadraturePosition(RobotConfig.armMaxEncoderTicks, RobotConfig.timeOut);
    }
    if(currentAngle<endpoint) {
    	RobotMap.armMaster.set(ControlMode.PercentOutput, -0.085 + -0.18*(Math.cos(currentAngle*Math.PI)/180));
    }
    else {
    	run++;
    	RobotMap.brake.set(RobotMap.setBrake);
    	RobotMap.armMaster.set(ControlMode.PercentOutput, 0);
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
