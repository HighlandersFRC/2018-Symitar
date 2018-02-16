package org.usfirst.frc.team4499.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.CameraServer;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4499.robot.autocommands.navxTurn;
import org.usfirst.frc.team4499.robot.autocommands.motionMagicDriveForward;

import org.usfirst.frc.team4499.robot.OI;
import org.usfirst.frc.team4499.robot.RobotMap;

import org.usfirst.frc.team4499.robot.commands.MoveArm;
import org.usfirst.frc.team4499.robot.commands.TeleopDrive;
import org.usfirst.frc.team4499.robot.subsystems.ExampleSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 *
 */
public class TeleopGrabber extends Command {

    public TeleopGrabber() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.closeIntake.get()) {
    		RobotMap.intake.set(RobotMap.closeIntake);
    		
    	}
    	else if(OI.openIntake.get()) {
    		RobotMap.intake.set(RobotMap.openIntake);
    	}
    	if(OI.joyStickTwo.getRawAxis(2)>0.15) {
    		RobotMap.intakeLeft.set(ControlMode.PercentOutput, OI.joyStickTwo.getRawAxis(2));
    		RobotMap.intakeRight.set(ControlMode.PercentOutput, OI.joyStickTwo.getRawAxis(2));
    	}
    	if(OI.joyStickTwo.getRawAxis(3)>0.15) {
        	RobotMap.intakeLeft.set(ControlMode.PercentOutput, -OI.joyStickTwo.getRawAxis(3));
        	RobotMap.intakeRight.set(ControlMode.PercentOutput, -OI.joyStickTwo.getRawAxis(3));

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
