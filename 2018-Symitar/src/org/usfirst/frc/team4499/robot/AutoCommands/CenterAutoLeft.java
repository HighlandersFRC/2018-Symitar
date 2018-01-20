package org.usfirst.frc.team4499.robot.AutoCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team4499.robot.commands.motionMagicDriveForward;
import org.usfirst.frc.team4499.robot.commands.navxTurn;
import org.usfirst.frc.team4499.robot.RobotMap;



/**
 *
 */
public class CenterAutoLeft extends CommandGroup {
	private double StartingAngle;

    public CenterAutoLeft() {
        StartingAngle = RobotMap.navx.getAngle();
    	addSequential(new motionMagicDriveForward(20.0f, StartingAngle,100,80));
    	addSequential(new navxTurn(StartingAngle + 30,0.9f));
    	addSequential(new motionMagicDriveForward(108.0f, StartingAngle + 30,300, 150));
    	addSequential(new navxTurn(StartingAngle,0.5f));
    	addSequential(new motionMagicDriveForward(40.0f, StartingAngle ,200, 200));


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
