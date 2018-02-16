package org.usfirst.frc.team4499.robot.autocommands;

import java.text.FieldPosition;

import org.usfirst.frc.team4499.robot.RobotConfig;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoPicker extends Command {
	private String fieldPos;

    public AutoPicker() {
    	DoNothing doNothing= new DoNothing();
    	BasicAuto basicAuto = new BasicAuto();
   
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	fieldPos = RobotConfig.fieldPositions;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(fieldPos.isEmpty()) {
    		
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
