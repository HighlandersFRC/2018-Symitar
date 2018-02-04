package org.usfirst.frc.team4499.robot.commands;

import org.usfirst.frc.team4499.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.*;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class MMArm extends Command {
	
	public double endpoint;
	public double offset;
	
    public MMArm(double endpoint) {
    	System.out.println("Initialized Arm");
    	this.endpoint = endpoint;
        
    	//Setup encoder for first motor
		//RobotMap.motorOne.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    	RobotMap.armMaster.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 0);
    	RobotMap.armMaster.getSensorCollection().setQuadraturePosition(0, 0);
		RobotMap.armMaster.setSelectedSensorPosition(0, 0, 0);
		RobotMap.armMaster.getSensorCollection().setAnalogPosition(0, 0);
		RobotMap.armMaster.setSensorPhase(true);
		
		//Setup follower can Talon
		RobotMap.armFollower.set(com.ctre.phoenix.motorcontrol.ControlMode.Follower, RobotMap.armMasterID);
		RobotMap.armFollower.setInverted(true);
		
		//Setup motorOne Nominal Outputs
		RobotMap.armMaster.configNominalOutputForward(0, 10);
		RobotMap.armMaster.configNominalOutputReverse(0, 10);
		RobotMap.armMaster.configPeakOutputForward(1, 10);
		RobotMap.armMaster.configPeakOutputReverse(-1, 10);
		
		
		RobotMap.armMaster.configMotionCruiseVelocity((250*4096)/600, 0);
		RobotMap.armMaster.selectProfileSlot(0, 0);
		//Set these values on Talons once tuned using SilverLight
	    //RobotMap.motorOne.config_kP(0, 0.000014, 0);
    	//RobotMap.motorOne.config_kI(0, 0.000000008, 0);	
		//RobotMap.motorOne.config_kD(0, 0.14, 0);
		//RobotMap.motorOne.config_kF(0, this.fGainLeft+ 0.014, 0);//0.3625884);
		
		RobotMap.armMaster.configAllowableClosedloopError(0, 300, 0);//300);
		
		RobotMap.armMaster.configSetParameter(ParamEnum.eClearPositionOnLimitF, 1, 0, 0, 10);
		
		
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.armMaster.set(com.ctre.phoenix.motorcontrol.ControlMode.MotionMagic, endpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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
