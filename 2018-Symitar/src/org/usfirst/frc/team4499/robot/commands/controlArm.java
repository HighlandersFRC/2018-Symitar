package org.usfirst.frc.team4499.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.hal.MatchInfoData;
import edu.wpi.first.wpilibj.hal.PDPJNI;
import org.usfirst.frc.team4499.robot.RobotMap;
import org.usfirst.frc.team4499.robot.OI;
import org.usfirst.frc.team4499.robot.commands.ExampleCommand;
import org.usfirst.frc.team4499.robot.subsystems.ExampleSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.usfirst.frc.team4499.robot.RobotMap;
import org.usfirst.frc.team4499.robot.AutoCommands.navxTurn;
import org.usfirst.frc.team4499.robot.AutoCommands.motionMagicDriveForward;
import org.usfirst.frc.team4499.robot.commands.PercentOutPutDriveForward;
import org.usfirst.frc.team4499.robot.commands.controlDriveTrain;
import org.usfirst.frc.team4499.robot.AutoCommands.CenterAutoLeft;



/**
 *
 */
public class controlArm extends Command {

    public controlArm() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.armFollower.set(com.ctre.phoenix.motorcontrol.ControlMode.Follower,5);
    	RobotMap.armMaster.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Math.abs(OI.joyStickOne.getRawAxis(3)) >0.2) {
    		RobotMap.armMaster.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, OI.joyStickOne.getRawAxis(3));
    	}
    	else if(Math.abs(OI.joyStickOne.getRawAxis(2)) >0.2) {
    		RobotMap.armMaster.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,-OI.joyStickOne.getRawAxis(2));

    	}
    	else {
    		RobotMap.armMaster.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput,0);
    		
    	}
    
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
