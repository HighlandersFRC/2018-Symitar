package org.usfirst.frc.team4499.robot.commands;

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
public class motionMagicDriveForward extends Command {
	private PID angleorientation;
	private float nativeUnitsperCycleLeft;
	private float nativeUnitsPerCycleRight;

	private float fGainLeft;
	private float fGainRight;
	private float motionMagicEndPoint;
	private double starttime;
	private double cruiseVelocityLeft;
	private double cruiseVelocityRight;
	private int AccelerationLeft;
	private int AccelerationRight;
	private double initCruiseVelocityLeft = cruiseVelocityLeft;
	private double initCruiseVelocityRight = cruiseVelocityRight;
	private double startAngle;
	private double desiredAngle;
    public motionMagicDriveForward(float distance, double angle, double cruiseVelocity, int acceleration) {
    cruiseVelocityLeft = cruiseVelocity;
    cruiseVelocityRight = cruiseVelocity;
    initCruiseVelocityLeft = cruiseVelocityLeft;
    initCruiseVelocityRight = cruiseVelocityRight;
    AccelerationLeft= acceleration;
    AccelerationRight= acceleration;
    this.motionMagicEndPoint= (distance*4096.0f*2.5f/(((float)Math.PI * 6.5f)));
    this.nativeUnitsperCycleLeft = (RobotMap.maxLeftRPM) * (1.0f / 60.0f) * (1.0f/10.0f) * (4096.0f) * (1.0f/(1.0f));
    	
    this.nativeUnitsPerCycleRight = (RobotMap.maxRightRPM) * (1.0f / 60.0f) * (1.0f/10.0f) * (4096.0f) * (1.0f/1.0f);
    fGainLeft =(1.0f* 1023.0f)/this.nativeUnitsperCycleLeft;
    fGainRight = (1.0f* 1023.0f)/this.nativeUnitsPerCycleRight;
        
    desiredAngle = angle;
    
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
   // Robot.startedCommand= true;
    starttime = Timer.getFPGATimestamp();
    
   
    RobotMap.motorLeftTwo.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
	RobotMap.motorRightTwo.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
	RobotMap.motorLeftTwo.setSelectedSensorPosition(0,0,0);
	RobotMap.motorRightTwo.setSelectedSensorPosition(0,0,0);

	
	RobotMap.motorRightTwo.setInverted(true);
	RobotMap.motorLeftTwo.setInverted(true);
	angleorientation = new PID(0, 0, 0);
    angleorientation.setContinuous(true);
    
 	angleorientation.setPID(5.5, 1.2, 500.6);
  	angleorientation.setSetPoint(RobotMap.navx.getAngle());
  	RobotMap.motorRightOne.getSensorCollection().setQuadraturePosition(0, 0);
    RobotMap.motorLeftOne.getSensorCollection().setQuadraturePosition(0, 0);
    RobotMap.motorRightTwo.getSensorCollection().setQuadraturePosition(0, 0);
    RobotMap.motorLeftTwo.getSensorCollection().setQuadraturePosition(0, 0);
	RobotMap.motorLeftTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.MotionMagic,this.motionMagicEndPoint);		
	RobotMap.motorLeftOne.set(com.ctre.phoenix.motorcontrol.ControlMode.Follower, 4);	
	RobotMap.motorRightTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.MotionMagic,-this.motionMagicEndPoint);	
	RobotMap.motorRightOne.set(com.ctre.phoenix.motorcontrol.ControlMode.Follower, 3);	
	RobotMap.motorLeftTwo.configPeakOutputForward(0.9, 0);
	RobotMap.motorLeftTwo.configNominalOutputForward(0, 0);
	RobotMap.motorRightTwo.configPeakOutputForward(0.9, 0);
	RobotMap.motorRightTwo.configNominalOutputForward(0, 0);
	RobotMap.motorLeftTwo.configPeakOutputReverse(-0.9, 0);
	RobotMap.motorLeftTwo.configNominalOutputReverse(0, 0);
	RobotMap.motorRightTwo.configPeakOutputReverse(-0.9, 0);
	RobotMap.motorRightTwo.configNominalOutputReverse(0, 0);
	//setting pid value for both sides
    RobotMap.motorLeftTwo.config_kP(0, 0.00008, 0);
	RobotMap.motorLeftTwo.config_kI(0, 0.000000008, 0);	
	RobotMap.motorLeftTwo.config_IntegralZone(0, 0, 0);
	RobotMap.motorLeftTwo.config_kD(0, 0.14, 0);
	RobotMap.motorLeftTwo.config_kF(0, this.fGainLeft, 0);//0.3625884);
	RobotMap.motorLeftTwo.configAllowableClosedloopError(0, 300, 0);//300);
	RobotMap.motorRightTwo.config_kP(0, 0.000023, 0);
    RobotMap.motorRightTwo.config_kI(0, 0.000000004, 0);
	RobotMap.motorRightTwo.config_IntegralZone(0, 0, 0);
	RobotMap.motorRightTwo.config_kD(0, 0.14, 0);
	RobotMap.motorRightTwo.config_kF(0, this.fGainRight- 0.000, 0);//0.3625884);
	RobotMap.motorRightTwo.configAllowableClosedloopError(0, 300, 0);
    
    RobotMap.motorLeftTwo.configMotionCruiseVelocity((int)(this.cruiseVelocityLeft*4096)/600, 0);
    RobotMap.motorRightTwo.configMotionCruiseVelocity((int)(this.cruiseVelocityRight*4096)/600, 0);

	//setting Acceleration and velocity for the right
	RobotMap.motorRightTwo.configMotionAcceleration((AccelerationRight*4096)/600, 0);
	RobotMap.motorLeftTwo.configMotionAcceleration((AccelerationLeft*4096)/600, 0);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    System.out.println(RobotMap.motorRightTwo.getSelectedSensorPosition(0));
    System.out.println(RobotMap.motorLeftTwo.getSelectedSensorPosition(0));

    System.out.println(RobotMap.navx.getAngle());
    SmartDashboard.putNumber("AngleResult", this.angleorientation.getResult());
    //SmartDashboard.putNumber("AngleError", RobotMap.navx.getAngle()-desiredAngle);
    SmartDashboard.putNumber("Right Error", RobotMap.motorRightTwo.getClosedLoopError(0));
    	
    angleorientation.updatePID(RobotMap.navx.getAngle());
    if(this.motionMagicEndPoint > 0){
        cruiseVelocityLeft = (float) (this.initCruiseVelocityLeft+ angleorientation.getResult());
        cruiseVelocityRight = (float) (this.initCruiseVelocityRight - angleorientation.getResult());
    	        }
    if(this.motionMagicEndPoint <= 0){
    	cruiseVelocityLeft = (float) (this.initCruiseVelocityLeft- angleorientation.getResult());
    	cruiseVelocityRight = (float) (this.initCruiseVelocityRight + angleorientation.getResult());
    	       }
    RobotMap.motorLeftTwo.configMotionCruiseVelocity((int)(this.cruiseVelocityLeft*4096)/600, 0);
    RobotMap.motorRightTwo.configMotionCruiseVelocity((int)(this.cruiseVelocityRight*4096)/600, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
     if(this.motionMagicEndPoint >0) {
    	 if(Math.abs(RobotMap.motorLeftTwo.getMotorOutputPercent() )==0.0 && Math.abs(RobotMap.motorRightTwo.getMotorOutputPercent() )== 0.00&& Timer.getFPGATimestamp()-starttime > 10) {
    		 	return true;
    	 }
     }

        return false;
    
    }

    // Called once after isFinished returns true
    protected void end() {
    RobotMap.motorRightTwo.setInverted(false);
    RobotMap.motorLeftTwo.setInverted(false);
    angleorientation.setContinuous(false);
    RobotMap.motorLeftTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    RobotMap.motorRightTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    RobotMap.motorLeftOne.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    RobotMap.motorRightTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    System.out.println(this.startAngle- RobotMap.navx.getAngle());

    }
    

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	angleorientation.setContinuous(false);

    	RobotMap.motorRightTwo.setInverted(false);
    	RobotMap.motorLeftTwo.setInverted(false);
    	RobotMap.motorLeftTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    	RobotMap.motorRightTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    	RobotMap.motorLeftOne.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    	RobotMap.motorRightTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    	System.out.println(this.startAngle- RobotMap.navx.getAngle());
    }
}
