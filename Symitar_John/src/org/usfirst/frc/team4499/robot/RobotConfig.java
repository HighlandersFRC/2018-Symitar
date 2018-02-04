package org.usfirst.frc.team4499.robot;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class RobotConfig {
	//This is a place to put all sorts of constants that will be used in other places of the robot configuration
	public static double gearRatio = 7.0;
    public static double encoderTicsPerShaftRotation = 4096;
    public static double encoderTicsPerWheelRotation = gearRatio * encoderTicsPerShaftRotation ;
    public static double wheelDiam = 6.0;
    public static double wheelCircum = Math.PI * wheelDiam;
    
	public static double voltageControlMax = 11.0;
	
	
	public static int driveMotorContinuousCurrent = 40;     //Amps
	public static int driveMotorPeakCurrent = 60;			//Amps
	public static int driveMotorPeakCurrentDuration = 100;  //Milliseconds
	public static boolean enableDriveCurrentLimit = true;
	
	public static int timeOut = 10; 						//Milliseconds
	
	//This will run when this object is created, and will handle configuring all sensors 
	public RobotConfig() {
		setStartingConfig();
		
	}
	public void setStartingConfig() {
		System.out.println("Initializing Talons");
		//Setup follower can talons
    	RobotMap.rightDriveFollowerOne.set(ControlMode.Follower, RobotMap.rightDriveLeadID);
    	RobotMap.rightDriveFollowerTwo.set(ControlMode.Follower, RobotMap.rightDriveLeadID);
    	RobotMap.leftDriveFollowerOne.set(ControlMode.Follower, RobotMap.leftDriveLeadID);
    	RobotMap.leftDriveFollowerTwo.set(ControlMode.Follower, RobotMap.leftDriveLeadID);
    	
    	//Invert the right hand side of the drive train
    	RobotMap.rightDriveLead.setInverted(true);
    	RobotMap.rightDriveFollowerOne.setInverted(true);
    	RobotMap.rightDriveFollowerTwo.setInverted(true);
    	
    	//TODO This particular motor runs backwards. If hardware changes this will need to be changed also.
    	RobotMap.leftDriveFollowerTwo.setInverted(true);
    	
    	//Setup and Enable current limiting for all drive motors
    	for(TalonSRX talon:RobotMap.driveMotors) {
    		talon.configContinuousCurrentLimit(RobotConfig.driveMotorContinuousCurrent, RobotConfig.timeOut);
    		talon.configPeakCurrentLimit(RobotConfig.driveMotorPeakCurrent, RobotConfig.timeOut);
    		talon.configPeakCurrentDuration(RobotConfig.driveMotorPeakCurrentDuration, RobotConfig.timeOut);
    		talon.enableCurrentLimit(RobotConfig.enableDriveCurrentLimit);
    	}
    	
    	RobotMap.armMaster.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 0);
    	RobotMap.armMaster.getSensorCollection().setQuadraturePosition(0, 0);
		RobotMap.armMaster.setSelectedSensorPosition(0, 0, 0);
		RobotMap.armMaster.getSensorCollection().setAnalogPosition(0, 0);
		RobotMap.armMaster.setSensorPhase(true);
		
		//Setup follower can Talon
		RobotMap.armFollower.set(ControlMode.Follower, RobotMap.armMasterID);
		RobotMap.armMaster.setInverted(false);
		RobotMap.armFollower.setInverted(false);
		
		//Setup armMaster Nominal Outputs
		RobotMap.armMaster.configNominalOutputForward(0, 10);
		RobotMap.armMaster.configNominalOutputReverse(0, 10);
		RobotMap.armMaster.configPeakOutputForward(1, 10);
		RobotMap.armMaster.configPeakOutputReverse(-1, 10);

		
		RobotMap.armMaster.configSetParameter(ParamEnum.eClearPositionOnLimitF, 1, 0, 0, 10);
		
	}
}
