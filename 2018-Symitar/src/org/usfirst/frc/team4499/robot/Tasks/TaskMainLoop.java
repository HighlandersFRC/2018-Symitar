package org.usfirst.frc.team4499.robot;
import org.usfirst.frc.team4499.robot.Schedulers;
import org.usfirst.frc.team4499.robot.Hardware;
import org.usfirst.frc.team4499.robot.Tasks;

import com.ctre.phoenix.ILoopable;

public class TaskMainLoop implements ILoopable{
	 public void onStart()

	    {
	        /* Default to LED strip animation */
	        Schedulers.PeriodicTasks.start(org.usfirst.frc.team4499.robot.Tasks.taskAnimatedLEDStrip);
	 //       Schedulers.PeriodicTasks.stop(Tasks.taskDirectControlArm);
	   //     Schedulers.PeriodicTasks.stop(Tasks.taskLIDAR_ControlLEDStrip);
	    }

	    public void onStop() {
	    }

	    public boolean isDone() {
	    	return false;
	    }

		public void onLoop()
	    {
	        boolean gamepadPresent = false;

	    	/* Don't have the ability to check if game-pad is connected, manually enable */
	        if (gamepadPresent)
	        {
	           //Schedulers.PeriodicTasks.start(Tasks.taskPWMmotorController);
	        }
	        else
	        {
	           // Schedulers.PeriodicTasks.stop(Tasks.taskPWMmotorController);
	        }
	        if (Hardware.gamepad.getRawButton(6))
	        {
	        	System.out.println("In onLoop Button 6 press");
	            /* Roll through color wheel*/
	            Schedulers.PeriodicTasks.start(org.usfirst.frc.team4499.robot.Tasks.taskAnimatedLEDStrip);
	            //Schedulers.PeriodicTasks.stop(Tasks.taskDirectControlArm);
	            //Schedulers.PeriodicTasks.stop(Tasks.taskLIDAR_ControlLEDStrip);
	            System.out.println("Done onLoop Button 6 press");
	        }

	        else if (Hardware.gamepad.getRawButton(5))

	        {
	            /* Let user control LED with sticks */
	            Schedulers.PeriodicTasks.stop(org.usfirst.frc.team4499.robot.Tasks.taskAnimatedLEDStrip);
	            //Schedulers.PeriodicTasks.start(Tasks.taskDirectControlArm);
	            //Schedulers.PeriodicTasks.stop(Tasks.taskLIDAR_ControlLEDStrip);
	            //Schedulers.PeriodicTasks.start(Tasks.taskMeasurePulseSensors);
	        }

	        else if (Hardware.gamepad.getRawButton(7))

	        {

	        	/* LED's controlled with the use of LIDAR sensor */
	            Schedulers.PeriodicTasks.stop(org.usfirst.frc.team4499.robot.Tasks.taskAnimatedLEDStrip);
	            //Schedulers.PeriodicTasks.stop(Tasks.taskDirectControlArm);
	            //Schedulers.PeriodicTasks.start(Tasks.taskLIDAR_ControlLEDStrip);
	            //Schedulers.PeriodicTasks.start(Tasks.taskMeasurePulseSensors);
	        }

	    }
	
}
