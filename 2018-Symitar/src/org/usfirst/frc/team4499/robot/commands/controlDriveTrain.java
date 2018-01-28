
package org.usfirst.frc.team4499.robot.commands;



import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4499.robot.RobotMap;
import org.usfirst.frc.team4499.robot.OI;
import org.usfirst.frc.team4499.robot.commands.ExampleCommand;
import org.usfirst.frc.team4499.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team4499.robot.RobotMap;
import org.usfirst.frc.team4499.robot.AutoCommands.navxTurn;
import org.usfirst.frc.team4499.robot.AutoCommands.motionMagicDriveForward;
import org.usfirst.frc.team4499.robot.commands.PercentOutPutDriveForward;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;



import org.usfirst.frc.team4499.robot.AutoCommands.CenterAutoLeft;
/**
 *
 */
public class controlDriveTrain extends Command {

	private static int CURRENT_LIMIT;
	private static final int MAX_CURRENT_OFF_COUNT = 3;
	private TalonSRX motorRightOne;
	private TalonSRX motorRightTwo;
	private TalonSRX motorLeftOne;
	private TalonSRX motorLeftTwo;
	private int currentOffCount;
	private int passnum = 0;
	private double minCurrentScaler= 1;
	
    public controlDriveTrain(TalonSRX l1, TalonSRX l2, TalonSRX r1, TalonSRX r2, int maxCurrent) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	CURRENT_LIMIT = maxCurrent;
    	
        motorRightOne = r1;
    	motorRightTwo = r2;
    	motorLeftOne = l1;
    	motorLeftTwo = l2;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.motorLeftOne.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    	RobotMap.motorLeftTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    	RobotMap.motorRightOne.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    	RobotMap.motorRightTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    if(OI.disableStallProtection.get()) {   	
    	setMotorsNoCurrentProtection();	
    }
    else{
    	setMotorsCurrentProtection();
    		
    }
    		
    }
    
    private void setMotorsCurrentProtection() {
    
    	
    	//System.out.println("enabled Brownout protection");
    	if (calcCurrentPercent(motorLeftOne) < minCurrentScaler) {
    		minCurrentScaler = calcCurrentPercent(motorLeftOne);
    	}
    	if (calcCurrentPercent(motorLeftTwo) < minCurrentScaler) {
    		minCurrentScaler = calcCurrentPercent(motorLeftTwo);
    	}
    	if (calcCurrentPercent(motorRightOne) < minCurrentScaler) {
    		minCurrentScaler = calcCurrentPercent(motorRightOne);
    	}
    	if (calcCurrentPercent(motorRightTwo) < minCurrentScaler) {
    		minCurrentScaler = calcCurrentPercent(motorRightTwo);
    	}
    	System.out.println("Current protection scaled to " + minCurrentScaler);
    	
    	limitMotorPower(motorLeftOne, 0.5*OI.joyStickOne.getRawAxis(1) * Math.abs(OI.joyStickOne.getRawAxis(1)), minCurrentScaler);
    	limitMotorPower(motorLeftTwo, 0.5*OI.joyStickOne.getRawAxis(1) * Math.abs(OI.joyStickOne.getRawAxis(1)), minCurrentScaler);
    	limitMotorPower(motorRightOne,0.5* OI.joyStickOne.getRawAxis(5) * Math.abs(OI.joyStickOne.getRawAxis(5)), minCurrentScaler);
    	limitMotorPower(motorRightTwo, 0.5*OI.joyStickOne.getRawAxis(5) * Math.abs(OI.joyStickOne.getRawAxis(5)), minCurrentScaler);
    }

	private void setMotorsNoCurrentProtection() {
	//	System.out.println("disabled Brownout protection");
		if (Math.abs(OI.joyStickOne.getRawAxis(1)) > 0.2) {
    		
			motorLeftOne.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,OI.joyStickOne.getRawAxis(1)); // Up on joystick returns lower
			motorLeftTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,OI.joyStickOne.getRawAxis(1)); // Up on joystick returns lower

    	} else {
    		motorLeftOne.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,0); 
    		motorLeftTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,0);
    	}
    		
    	if (Math.abs(OI.joyStickOne.getRawAxis(5)) > 0.2) {
    		motorRightOne.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,OI.joyStickOne.getRawAxis(5));
    		motorRightTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,OI.joyStickOne.getRawAxis(5));
    		
    	} else {
    		motorRightOne.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,0);
    		motorRightTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,0);
    	}
	}
    
	private void limitMotorPower(TalonSRX driveMotor, double setPower, double currentScale) {
		if (Math.abs(setPower) < 0.2) {
			setPower = 0;
		}
		driveMotor.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,setPower * currentScale);
	}
    public void setMaxCurrent(int currentmax) {
    	CURRENT_LIMIT = currentmax;
    }
	private double calcCurrentPercent(TalonSRX driveMotor) {
		if (driveMotor.getOutputCurrent() > CURRENT_LIMIT) {
			return CURRENT_LIMIT / driveMotor.getOutputCurrent();
		}
		return 1;
	}

	private boolean shouldTurnMotorOff(TalonSRX driveMotor) {
		return driveMotor.getOutputCurrent() > CURRENT_LIMIT || currentOffCount < MAX_CURRENT_OFF_COUNT;
	}

    
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	motorLeftOne.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,0);
    	motorLeftTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,0);
    	motorRightOne.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,0);
    	motorRightTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	motorLeftOne.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,0);
    	motorLeftTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,0);
    	motorRightOne.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,0);
    	motorRightTwo.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,0);
    }
}
