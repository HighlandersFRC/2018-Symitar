package org.usfirst.frc.team4499.robot.commands;

import org.usfirst.frc.team4499.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This is a command group that properly sequences the actions needed to move the arm to a designated position
 */
public class MoveArm extends CommandGroup {
	private double position;
    public MoveArm(double position) {
    	
    	//release the brake
    	addSequential(new SetPiston(RobotMap.brake,RobotMap.releaseBrake));
    	
    	//give the brake some time to release
    	addSequential(new Wait(0.1));
    	
    	//move the arm 
    	addSequential(new MMArm(position));
    	
    	//reengage the brake
    	addSequential(new SetPiston(RobotMap.brake,RobotMap.setBrake));
 
    }
}
