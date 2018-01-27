
package org.usfirst.frc.team4499.robot;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.hal.MatchInfoData;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tInstances;




public class RobotMap {
	public static int rightTwoTalonID = 4; // Right Two is Green ID 4
	public static int leftTwoTalonID = 3; //Left Two is Yellow, ID 3
	public static int rightOneTalonID = 1; //Right One is Blue, ID 1
	public static int leftOneTalonID = 2; //Left One is Red, ID 2
    public static double gearRatio = 2.5;
    public static double encoderTicsPerShaftRotation = 4096;
    public static double encoderTicsPerWheelRotation = gearRatio * encoderTicsPerShaftRotation ;
    public static double wheelDiam = 6.2;
    public static double wheelCircum = Math.PI * wheelDiam;
	public static AHRS navx = new AHRS(I2C.Port.kMXP);
	public static float maxLeftRPM = 465.47f;
	public static float maxRightRPM = 455.63f;
	public static double voltageControlMax = 11.0;
	


	//public static DoubleSolenoid Solenoid1 = new DoubleSolenoid(0,1);
	public static TalonSRX motorRightOne = new TalonSRX(rightOneTalonID);
	public static TalonSRX motorRightTwo = new TalonSRX(rightTwoTalonID);
	public static TalonSRX motorLeftOne = new TalonSRX(leftOneTalonID);
	public static TalonSRX motorLeftTwo = new TalonSRX(leftTwoTalonID);
	
	public static TalonSRX componentmotorLeftOne = new TalonSRX(7);
	public static TalonSRX componentmotorLeftTwo = new TalonSRX(8);
//	public static TalonSRX componentmotorLeftThree = new TalonSRX(9);
	

	



}
