package org.usfirst.frc.team4499.robot.AutoCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import edu.wpi.first.wpilibj.command.WaitCommand;

import org.usfirst.frc.team4499.robot.AutoCommands.motionMagicDriveForward;
import org.usfirst.frc.team4499.robot.AutoCommands.navxTurn;
import org.usfirst.frc.team4499.robot.AutoCommands.Wait;

import org.usfirst.frc.team4499.robot.RobotMap;



/**
 *
 */
public class CenterAutoLeft extends CommandGroup {
	private double StartingAngle;

    public CenterAutoLeft() {
    	addSequential(new navxTurn( RobotMap.navx.getAngle() +30,0.8f));
    	/*addSequential(new WaitCommand(0.5));
    	addSequential(new navxTurn(RobotMap.navx.getAngle() + -30,0.8f));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new navxTurn(RobotMap.navx.getAngle(),0.8f));
    	addSequential(new WaitCommand(0.5)); 	
    	addSequential(new navxTurn(RobotMap.navx.getAngle() + 45,0.8f));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new navxTurn(RobotMap.navx.getAngle(),0.8f));  	
    	addSequential(new WaitCommand(0.5));
    	addSequential(new navxTurn(RobotMap.navx.getAngle() + -45,0.8f));
    	addSequential(new WaitCommand(0.5));*/
    	//addSequential(new navxTurn(RobotMap.navx.getAngle(),0.8f));  

    	//addSequential(new navxTurn(StartingAngle,0.5f));
    	

        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
