package org.usfirst.frc.team4499.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command allows for pistons to be controlled generically from within a command group.
 * This makes it easier to sequence piston operations in a command
 */
public class SetPiston extends Command {
	DoubleSolenoid piston;
	DoubleSolenoid.Value position;

    public SetPiston(DoubleSolenoid piston, DoubleSolenoid.Value position) {
    	this.piston = piston;
    	this.position = position;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	piston.set(position);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
