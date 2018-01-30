package org.usfirst.frc.team4499.robot.commands;
import  org.usfirst.frc.team4499.robot.RobotMap;

import org.usfirst.frc.team4499.robot.commands.controlDriveTrain;
import org.usfirst.frc.team4499.robot.commands.controlGrabber;
import org.usfirst.frc.team4499.robot.commands.controlArm;



import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.hal.PDPJNI;

/**
 *
 */
public class controlGeneral extends Command {
   private double maxVoltage;
   private controlDriveTrain driveControl;
   private controlArm armControl;
   private controlGrabber grabberControl;

   

    public controlGeneral() {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        driveControl =  new controlDriveTrain(RobotMap.motorLeftOne, RobotMap.motorLeftTwo,RobotMap.motorLeftThree, RobotMap.motorRightOne, RobotMap.motorRightTwo,RobotMap.motorRightThree,23);
    	grabberControl = new controlGrabber();
        armControl = new controlArm();
    	maxVoltage = RobotMap.pdp.getVoltage();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    if(RobotMap.pdp.getVoltage()< 9.0) {
    	RobotMap.compressor1.setClosedLoopControl(false);
    	
    }
    else if(RobotMap.pdp.getVoltage()< 8.0) {
    	//if possible lessen the amount of power availabe to the rs775s controlling the arm
    	//otherwise, potentially stop all controlof the arm
    	//lessen power draw of grabber
    }
    else if(RobotMap.pdp.getVoltage()< 7.0) {
    	driveControl.setMaxCurrent(15);
    	//along with this end control of the grabber
    	
    }
    else {
    	RobotMap.compressor1.setClosedLoopControl(true);
    	driveControl.start();
    	armControl.start();
    	grabberControl.start();
    	driveControl.setMaxCurrent(23);
    	
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
