package org.usfirst.frc.team4499.robot.commands;

import java.util.function.DoubleToLongFunction;

import org.usfirst.frc.team4499.robot.OI;
import org.usfirst.frc.team4499.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MMArm extends Command {
	public double endpoint;
	public double startTime;
    public MMArm(double endpoint) {
        this.endpoint = endpoint;
        RobotMap.motorTwo.set(ControlMode.Follower, 0);
        RobotMap.motorTwo.setInverted(true);
        
        //Setup Sensor
        RobotMap.motorOne.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        RobotMap.motorOne.setSensorPhase(true);
        RobotMap.motorOne.setSelectedSensorPosition(0, 0, 0);
        
        //Motor Configuration
        RobotMap.motorOne.configNominalOutputForward(0,0);
        RobotMap.motorOne.configNominalOutputReverse(0,0);
        RobotMap.motorOne.configPeakOutputForward(1,0);
        RobotMap.motorOne.configPeakOutputReverse(1, 0);
        
        RobotMap.motorOne.configMotionCruiseVelocity(200, 0);
        RobotMap.motorOne.configMotionAcceleration(400, 0); 
        
        
        

        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Staring Motion");
    	RobotMap.motorOne.set(ControlMode.MotionMagic, this.endpoint);
    	startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(RobotMap.motorOne.getClosedLoopError(0)*360 /4096 +  "    " +RobotMap.motorOne.getClosedLoopError(0));
    	
    	SmartDashboard.putNumber("Velocity",RobotMap.motorOne.getSelectedSensorVelocity(0));
    	SmartDashboard.putNumber("AccelerationRate", (Timer.getFPGATimestamp()-startTime)*200 );
    	//System.out.println(RobotMap.motorOne.getSelectedSensorPosition(0));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
        
        //TODO automatically return true when the Motion Magic Completes its control loop
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	//When this runs call another function that uses a PID + static offset to hold the arm in place
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	
    }
}
