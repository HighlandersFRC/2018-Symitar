package org.usfirst.frc.team4499.robot.autocommands;

import edu.wpi.first.wpilibj.command.Command;



import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import org.usfirst.frc.team4499.robot.RobotMap;

import org.usfirst.frc.team4499.robot.tools.PID;
import org.usfirst.frc.team4499.robot.Robot;
import org.usfirst.frc.team4499.robot.RobotConfig;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class motionMagicDriveForward extends Command {
	private PID angleorientation;
	private float nativeUnitsperCycleLeft;
	private float nativeUnitsPerCycleRight;

	private float fGainLeft;
	private float fGainRight;
	private double motionMagicEndPoint;
	private double starttime;
	private double cruiseVelocityLeft;
	private double cruiseVelocityRight;
	private int AccelerationLeft;
	private int AccelerationRight;
	private double initCruiseVelocityLeft = cruiseVelocityLeft;
	private double initCruiseVelocityRight = cruiseVelocityRight;
	private double startAngle;
	private double desiredAngle;
	private double pGainLeft;
	private double pGainRight;
	private double endpoint;

	
    public motionMagicDriveForward(double distance, double angle, double cruiseVelocity, int acceleration) {
   
    cruiseVelocityLeft = cruiseVelocity;
    cruiseVelocityRight = cruiseVelocity;
    initCruiseVelocityLeft = cruiseVelocityLeft;
    initCruiseVelocityRight = cruiseVelocityRight;
    AccelerationLeft= acceleration;
    AccelerationRight= acceleration;
    
   endpoint = distance;
    //to find the fvalue, use Self test to find the Percent Output
    //then, do ([PercentOutput] *1023)/Native units per 100ms;
    //find this on https://github.com/CrossTheRoadElec/Phoenix-Documentation/blob/master/README.md
   
    fGainLeft = 0.132404f;// + 0.0127f;
    fGainRight = fGainLeft -0.0185f;
    pGainLeft = 0;
    pGainRight= 0;
        
    desiredAngle = angle;
    
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
   // Robot.startedCommand= true;
    RobotMap.leftDriveLead.setSelectedSensorPosition(0, 0, 0);
    RobotMap.rightDriveLead.setSelectedSensorPosition(0, 0, 0);
    this.motionMagicEndPoint= ((-endpoint/RobotConfig.wheelCircum)*RobotConfig.encoderTicsPerWheelRotation);
    
    RobotMap.shifters.set(RobotMap.lowGear);

    starttime = Timer.getFPGATimestamp();
  
	
	angleorientation = new PID(0, 0, 0);
    angleorientation.setContinuous(true);
    //comment this line to diable the navx
 	angleorientation.setPID(10.0, 0.8, 0);
  	angleorientation.setSetPoint(RobotMap.navx.getAngle());
  	angleorientation.setMaxOutput(1500.0);
	angleorientation.setMinOutput(-1500.0);
    
    RobotMap.leftDriveLead.set(com.ctre.phoenix.motorcontrol.ControlMode.MotionMagic,this.motionMagicEndPoint);		
	RobotMap.rightDriveLead.set(com.ctre.phoenix.motorcontrol.ControlMode.MotionMagic,this.motionMagicEndPoint);	

	
	
	RobotMap.leftDriveLead.configPeakOutputForward(0.9, 0);
	RobotMap.leftDriveLead.configNominalOutputForward(0, 0);
	RobotMap.rightDriveLead.configPeakOutputForward(0.9, 0);
	RobotMap.rightDriveLead.configNominalOutputForward(0, 0);

	
	//setting pid value for both sides
   // RobotMap.leftDriveLead.config_kP(0, 0.00045, 0);
  //	RobotMap.leftDriveLead.config_kI(0, 0.00000009, 0);	
  //	RobotMap.motorLeftTwo.config_IntegralZone(0, 0, 0);
  //	RobotMap.motorLeftTwo.config_kD(0, 0.14, 0);
  	RobotMap.leftDriveLead.config_kF(0, this.fGainLeft, 0);
  //	RobotMap.motorLeftTwo.configAllowableClosedloopError(0, 300, 0);//300);
  //    RobotMap.rightDriveLead.config_kP(0, 0.00045, 0);
 //     RobotMap.rightDriveLead.config_kI(0, 0.000000009, 0);
  //	RobotMap.rightDriveLead.config_IntegralZone(0, 0, 0);
  //	RobotMap.rightDriveLead.config_kD(0, 0.14, 0);
  	RobotMap.rightDriveLead.config_kF(0, this.fGainRight, 0);
  //	RobotMap.rightDriveLead.configAllowableClosedloopError(0, 300, 0);
	
    RobotMap.leftDriveLead.configMotionCruiseVelocity((int)(this.cruiseVelocityLeft*4096)/600, 0);
    RobotMap.rightDriveLead.configMotionCruiseVelocity((int)(this.cruiseVelocityRight*4096)/600, 0);

	//setting Acceleration and velocity for the right
	RobotMap.rightDriveLead.configMotionAcceleration((AccelerationRight*4096)/600, 0);
	RobotMap.leftDriveLead.configMotionAcceleration((AccelerationLeft*4096)/600, 0);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    /*System.out.println(Timer.getFPGATimestamp()-starttime + " time ");
    System.out.println((int)-RobotMap.rightDriveLead.getSelectedSensorPosition(0)+ " ticksRight " + (RobotMap.rightDriveLead.getSelectedSensorPosition(0)
    		/(RobotConfig.gearRatio * RobotConfig.encoderTicsPerShaftRotation)) * RobotConfig.wheelCircum + " in Right");

    System.out.println((int)-RobotMap.leftDriveLead.getSelectedSensorPosition(0) + " ticksLeft " + (RobotMap.leftDriveLead.getSelectedSensorPosition(0)
    	    /(RobotConfig.gearRatio * RobotConfig.encoderTicsPerShaftRotation)) * RobotConfig.wheelCircum+ " in Left");
    System.out.println(RobotMap.leftDriveLead.getSelectedSensorPosition(0) - this.motionMagicEndPoint + " Left ClosedLoop Error in ticks");
    System.out.println(RobotMap.rightDriveLead.getSelectedSensorPosition(0) - this.motionMagicEndPoint + " Right Closed Loop Error in ticks");
    System.out.println((((this.motionMagicEndPoint - RobotMap.leftDriveLead.getSelectedSensorPosition(0)) / (RobotConfig.gearRatio * RobotConfig.encoderTicsPerShaftRotation)) * RobotConfig.wheelCircum) + " Closed Loop error in inches Left");
    System.out.println((((this.motionMagicEndPoint - RobotMap.rightDriveLead.getSelectedSensorPosition(0)) / (RobotConfig.gearRatio * RobotConfig.encoderTicsPerShaftRotation)) * RobotConfig.wheelCircum) + " Closed Loop error in inches Right");*/

    this.angleorientation.updatePID(RobotMap.navx.getAngle());
    if(this.motionMagicEndPoint > 0){
        cruiseVelocityLeft = (float) (this.initCruiseVelocityLeft+ angleorientation.getResult());
        cruiseVelocityRight = (float) (this.initCruiseVelocityRight - angleorientation.getResult());
    	        }
    else{
    	cruiseVelocityLeft = (float) (this.initCruiseVelocityLeft- angleorientation.getResult());
    	cruiseVelocityRight = (float) (this.initCruiseVelocityRight + angleorientation.getResult());
    	       }
    RobotMap.leftDriveLead.configMotionCruiseVelocity((int)(this.cruiseVelocityLeft*4096)/600, 0);
    RobotMap.rightDriveLead.configMotionCruiseVelocity((int)(this.cruiseVelocityRight*4096)/600, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
     
    	 if(Math.abs(RobotMap.leftDriveLead.getMotorOutputPercent() )<=0.075 && Math.abs(RobotMap.rightDriveLead.getMotorOutputPercent()) <= 0.75&& Math.abs(Timer.getFPGATimestamp()-starttime)> 1) {
    		 	return true;
    	 }
     

        return false;
    
    }

    // Called once after isFinished returns true
    protected void end() {
    RobotMap.leftDriveLead.enableVoltageCompensation(false);
    RobotMap.rightDriveLead.enableVoltageCompensation(false);
    angleorientation.setContinuous(false);
    SmartDashboard.putNumber("Left Position", RobotMap.leftDriveLead.getSelectedSensorPosition(0));
    SmartDashboard.putNumber("Right Position", RobotMap.rightDriveLead.getSelectedSensorPosition(0));
    RobotMap.rightDriveLead.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    RobotMap.leftDriveLead.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);

    

    }
    

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    this.end();

    }
}
//Accretion Version Comment out for practice bot
/*
package org.usfirst.frc.team4499.robot.AutoCommands;

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
 
public class motionMagicDriveForward extends Command {
	private PID angleorientation;
	private float nativeUnitsperCycleLeft;
	private float nativeUnitsPerCycleRight;

	private float fGainLeft;
	private float fGainRight;
	private double motionMagicEndPoint;
	private double starttime;
	private double cruiseVelocityLeft;
	private double cruiseVelocityRight;
	private int AccelerationLeft;
	private int AccelerationRight;
	private double initCruiseVelocityLeft = cruiseVelocityLeft;
	private double initCruiseVelocityRight = cruiseVelocityRight;
	private double startAngle;
	private double desiredAngle;
	private double pGainLeft;
	private double pGainRight;

	
    public motionMagicDriveForward(double distance, double angle, double cruiseVelocity, int acceleration) {
   
    cruiseVelocityLeft = cruiseVelocity;
    cruiseVelocityRight = cruiseVelocity;
    initCruiseVelocityLeft = cruiseVelocityLeft;
    initCruiseVelocityRight = cruiseVelocityRight;
    AccelerationLeft= acceleration;
    AccelerationRight= acceleration;
    this.motionMagicEndPoint= ((distance/RobotMap.wheelCircum)*RobotMap.encoderTicsPerWheelRotation);
    this.nativeUnitsperCycleLeft = (RobotMap.maxLeftRPM) * (1.0f / 60.0f) * (1.0f/10.0f) * (4096.0f) * (1.0f/(1.0f));
    	
    this.nativeUnitsPerCycleRight = (RobotMap.maxRightRPM) * (1.0f / 60.0f) * (1.0f/10.0f) * (4096.0f) * (1.0f/1.0f);
    //to find the fvalue, use Self test to find the Percent Output
    //then, do ([PercentOutput] *1023)/Native units per 100ms;
    //find this on https://github.com/CrossTheRoadElec/Phoenix-Documentation/blob/master/README.md
    fGainLeft =0.31915f;//0.29627f;
    fGainRight =fGainLeft + 0.020f;//+ 0.008f;//0.28341f;
    pGainLeft = 0;
    pGainRight= 0;
        
    desiredAngle = angle;
    
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
   // Robot.startedCommand= true;
    starttime = Timer.getFPGATimestamp();
    RobotMap.motorLeftTwo.configVoltageCompSaturation(RobotMap.voltageControlMax, 10);
    RobotMap.motorLeftTwo.enableVoltageCompensation(true); 
    RobotMap.motorLeftTwo.configVoltageMeasurementFilter(32, 10);
    
    RobotMap.rightDriveLead.configVoltageCompSaturation(RobotMap.voltageControlMax, 10);
    RobotMap.rightDriveLead.enableVoltageCompensation(true); 
    RobotMap.rightDriveLead.configVoltageMeasurementFilter(32, 10);
    
    RobotMap.motorLeftOne.configVoltageCompSaturation(RobotMap.voltageControlMax, 10);
    RobotMap.motorLeftOne.enableVoltageCompensation(true); 
    RobotMap.motorLeftOne.configVoltageMeasurementFilter(32, 10);
    
    RobotMap.motorRightOne.configVoltageCompSaturation(RobotMap.voltageControlMax, 10);
    RobotMap.motorRightOne.enableVoltageCompensation(true);
    RobotMap.motorRightOne.configVoltageMeasurementFilter(32, 10);
	
    RobotMap.motorLeftTwo.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
	RobotMap.rightDriveLead.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
	RobotMap.motorLeftTwo.setSelectedSensorPosition(0,0,0);
	RobotMap.rightDriveLead.setSelectedSensorPosition(0,0,0);
	
  //  RobotMap.motorLeftTwo.setSensorPhase(true);
  //  RobotMap.rightDriveLead.setSensorPhase(true);
	
	RobotMap.rightDriveLead.setInverted(false);
	RobotMap.motorLeftTwo.setInverted(true);
	RobotMap.motorRightOne.setInverted(false);
	RobotMap.motorLeftOne.setInverted(true);
	angleorientation = new PID(0, 0, 0);
    angleorientation.setContinuous(true);
    //comment this line to diable the navx
 	angleorientation.setPID(9.5, 0.0, 0);
  	angleorientation.setSetPoint(RobotMap.navx.getAngle());
  	RobotMap.motorRightOne.getSensorCollection().setQuadraturePosition(0, 0);
    RobotMap.motorLeftOne.getSensorCollection().setQuadraturePosition(0, 0);
    RobotMap.rightDriveLead.getSensorCollection().setQuadraturePosition(0, 0);
    RobotMap.motorLeftTwo.getSensorCollection().setQuadraturePosition(0, 0);
    RobotMap.motorLeftTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.MotionMagic,this.motionMagicEndPoint);		
	RobotMap.motorLeftOne.set(com.ctre.phoenix.motorcontrol.ControlMode.Follower, RobotMap.leftTwoTalonID);	
	RobotMap.rightDriveLead.set(com.ctre.phoenix.motorcontrol.ControlMode.MotionMagic,this.motionMagicEndPoint);	
	RobotMap.motorRightOne.set(com.ctre.phoenix.motorcontrol.ControlMode.Follower, RobotMap.rightTwoTalonID);	
	RobotMap.motorLeftTwo.configPeakOutputForward(0.9, 0);
	RobotMap.motorLeftTwo.configNominalOutputForward(0, 0);
	RobotMap.rightDriveLead.configPeakOutputForward(0.9, 0);
	RobotMap.rightDriveLead.configNominalOutputForward(0, 0);
	RobotMap.motorLeftTwo.configPeakOutputReverse(-0.9, 0);
	RobotMap.motorLeftTwo.configNominalOutputReverse(0, 0);
	RobotMap.rightDriveLead.configPeakOutputReverse(-0.9, 0);
	RobotMap.rightDriveLead.configNominalOutputReverse(0, 0);
	//setting pid value for both sides
    RobotMap.motorLeftTwo.config_kP(0, 0.00045, 0);
  	RobotMap.motorLeftTwo.config_kI(0, 0.00000009, 0);	
  //	RobotMap.motorLeftTwo.config_IntegralZone(0, 0, 0);
  //	RobotMap.motorLeftTwo.config_kD(0, 0.14, 0);
  	RobotMap.motorLeftTwo.config_kF(0, this.fGainLeft, 0);//0.3625884);
  //	RobotMap.motorLeftTwo.configAllowableClosedloopError(0, 300, 0);//300);
  	RobotMap.rightDriveLead.config_kP(0, 0.00045, 0);
    RobotMap.rightDriveLead.config_kI(0, 0.000000009, 0);
  //	RobotMap.rightDriveLead.config_IntegralZone(0, 0, 0);
  //	RobotMap.rightDriveLead.config_kD(0, 0.14, 0);
  	RobotMap.rightDriveLead.config_kF(0, this.fGainRight- 0.000, 0);//0.3625884);
  //	RobotMap.rightDriveLead.configAllowableClosedloopError(0, 300, 0);
	
    RobotMap.motorLeftTwo.configMotionCruiseVelocity((int)(this.cruiseVelocityLeft*4096)/600, 0);
    RobotMap.rightDriveLead.configMotionCruiseVelocity((int)(this.cruiseVelocityRight*4096)/600, 0);

	//setting Acceleration and velocity for the right
	RobotMap.rightDriveLead.configMotionAcceleration((AccelerationRight*4096)/600, 0);
	RobotMap.motorLeftTwo.configMotionAcceleration((AccelerationLeft*4096)/600, 0);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    System.out.println(Timer.getFPGATimestamp()-starttime + " time ");
    System.out.println((int)RobotMap.rightDriveLead.getSelectedSensorPosition(0) + " ticksRight " + (RobotMap.rightDriveLead.getSelectedSensorPosition(0)
    /(RobotMap.gearRatio * RobotMap.encoderTicsPerShaftRotation)) * RobotMap.wheelCircum + " in Right");//+this.motionMagicEndPoint + " right Encoder");

    System.out.println((int)RobotMap.motorLeftTwo.getSelectedSensorPosition(0) + " ticksLeft " + (RobotMap.motorLeftTwo.getSelectedSensorPosition(0)
    	    /(RobotMap.gearRatio * RobotMap.encoderTicsPerShaftRotation)) * RobotMap.wheelCircum+ " in Left");
  //  System.out.println(RobotMap.navx.getAngle()+ " navx Output");
    System.out.println(RobotMap.motorLeftTwo.getSelectedSensorPosition(0) + this.motionMagicEndPoint + " Left ClosedLoop Error in ticks");
    System.out.println(RobotMap.rightDriveLead.getSelectedSensorPosition(0) + this.motionMagicEndPoint + " Right Closed Loop Error in ticks");
    System.out.println((((this.motionMagicEndPoint + RobotMap.motorLeftTwo.getSelectedSensorPosition(0)) / (RobotMap.gearRatio * RobotMap.encoderTicsPerShaftRotation)) * RobotMap.wheelCircum) + " Closed Loop error in inches Left");
    System.out.println((((this.motionMagicEndPoint + RobotMap.rightDriveLead.getSelectedSensorPosition(0)) / (RobotMap.gearRatio * RobotMap.encoderTicsPerShaftRotation)) * RobotMap.wheelCircum) + " Closed Loop error in inches Right");

   /* SmartDashboard.putNumber("AngleResult", this.angleorientation.getResult());
    SmartDashboard.putNumber("AngleError", RobotMap.navx.getAngle()-desiredAngle);
    SmartDashboard.putNumber("Right Error", RobotMap.rightDriveLead.getClosedLoopError(0));
    SmartDashboard.putNumber("LeftVelocity", (RobotMap.motorLeftTwo.getSelectedSensorVelocity(0)* 600)/4096);
    SmartDashboard.putNumber("Right Velocity",(RobotMap.rightDriveLead.getSelectedSensorVelocity(0)* 600)/4096);
    	
    angleorientation.updatePID(RobotMap.navx.getAngle());
    if(this.motionMagicEndPoint > 0){
        cruiseVelocityLeft = (float) (this.initCruiseVelocityLeft- angleorientation.getResult());
        cruiseVelocityRight = (float) (this.initCruiseVelocityRight + angleorientation.getResult());
    	        }
    else{
    	cruiseVelocityLeft = (float) (this.initCruiseVelocityLeft+ angleorientation.getResult());
    	cruiseVelocityRight = (float) (this.initCruiseVelocityRight - angleorientation.getResult());
    	       }
    RobotMap.motorLeftTwo.configMotionCruiseVelocity((int)(this.cruiseVelocityLeft*4096)/600, 0);
    RobotMap.rightDriveLead.configMotionCruiseVelocity((int)(this.cruiseVelocityRight*4096)/600, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
     if(this.motionMagicEndPoint >0) {
    	 if(Math.abs(RobotMap.motorLeftTwo.getMotorOutputPercent() )==0.0 && Math.abs(RobotMap.rightDriveLead.getMotorOutputPercent() )== 0.00&& Timer.getFPGATimestamp()-starttime > 10) {
    		 	return true;
    	 }
     }

        return false;
    
    }

    // Called once after isFinished returns true
    protected void end() {
    RobotMap.motorLeftTwo.enableVoltageCompensation(false);
    RobotMap.rightDriveLead.enableVoltageCompensation(false);
    RobotMap.motorLeftOne.enableVoltageCompensation(false);
    RobotMap.motorRightOne.enableVoltageCompensation(false);
    RobotMap.rightDriveLead.setInverted(false);
    RobotMap.motorLeftTwo.setInverted(false);
    RobotMap.motorRightOne.setInverted(false);
    RobotMap.motorLeftOne.setInverted(false);
    angleorientation.setContinuous(false);
    SmartDashboard.putNumber("Left Position", RobotMap.motorLeftTwo.getSelectedSensorPosition(0));
    SmartDashboard.putNumber("Right Position", RobotMap.motorLeftTwo.getSelectedSensorPosition(0));
    RobotMap.motorLeftTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    RobotMap.rightDriveLead.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    RobotMap.motorLeftOne.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    RobotMap.rightDriveLead.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    System.out.println(this.startAngle- RobotMap.navx.getAngle());

    }
    

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	angleorientation.setContinuous(false);

	    RobotMap.rightDriveLead.setInverted(false);
	    RobotMap.motorLeftTwo.setInverted(false);
	    RobotMap.motorRightOne.setInverted(false);
	    RobotMap.motorLeftOne.setInverted(false);
    	RobotMap.motorLeftTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    	RobotMap.rightDriveLead.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    	RobotMap.motorLeftOne.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    	RobotMap.rightDriveLead.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    	System.out.println(this.startAngle- RobotMap.navx.getAngle());
    }
}


*/
