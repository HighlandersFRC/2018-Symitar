package org.usfirst.frc.team4499.robot.autocommands;

import java.text.FieldPosition;

import javax.lang.model.element.Element;
import javax.naming.InsufficientResourcesException;

import org.usfirst.frc.team4499.robot.OI;
import org.usfirst.frc.team4499.robot.RobotConfig;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoPicker extends Command {
	private String fieldPos;
	DoNothing doNothing= new DoNothing();
	BasicAuto basicAuto = new BasicAuto();
    DriveForward driveForward = new DriveForward();
    LeftTurnAuto leftTurnAuto = new LeftTurnAuto();
    RightTurnAuto rightTurnAuto = new RightTurnAuto();
    public AutoPicker() {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(OI.switchTwo.get()) {
    		doNothing.start();
    	}
    	else if(OI.dialOne.get()) {
    		driveForward.start();
    		return;
    	}
    	else if(OI.dialTwo.get()) {
    		if(RobotConfig.fieldPositions.charAt(0)== RobotConfig.robotStartPosition) {
    			basicAuto.start();
    		}
    		else {
    			driveForward.start();
    		}
    		return;  		
    	}   	
    /*	else if(fieldPos.isEmpty()) {
    		doNothing.start();	
    	return;
    	}
    	for(int i = 0; i<fieldPos.length()-1;i++) {
    		if(fieldPos.charAt(i)!='R'||fieldPos.charAt(i)!='L') {
    			doNothing.start();
    			return;
    		}
    	}
    	if(fieldPos.charAt(0)==RobotConfig.robotStartPosition) {
    		basicAuto.start();
    		return;
    	}
    	else {
    		driveForward.start();
    	}
    	
    	fieldPos = RobotConfig.fieldPositions;
    	*/
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
