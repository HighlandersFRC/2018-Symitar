
package org.usfirst.frc.team4499.robot;
import org.opencv.video.KalmanFilter;


import com.ctre.phoenix.motorcontrol.can.TalonSRX;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Compressor;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.hal.MatchInfoData;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tInstances;
import edu.wpi.first.wpilibj.hal.PDPJNI;



public class RobotMap {
	//practice bot robotMap comment out for Accretion
	public static PowerDistributionPanel pdp = new PowerDistributionPanel();
	public static int rightOneTalonID = 1; //Right One is Blue, ID 1
	public static int rightTwoTalonID = 2; // Right Two is Green ID 4
	public static int rightThreeTalonID = 3;
	
	public static int leftOneTalonID = 8;
	public static int leftTwoTalonID = 7;
	public static int leftThreeTalonID = 6;
	
    public static double gearRatio = 7.0;
    public static double encoderTicsPerShaftRotation = 4096;
    public static double encoderTicsPerWheelRotation = gearRatio * encoderTicsPerShaftRotation ;
    public static double wheelDiam = 6.0;
    public static double wheelCircum = Math.PI * wheelDiam;
	public static AHRS navx = new AHRS(I2C.Port.kMXP);
	
	public static double voltageControlMax = 11.0;
	public static Compressor compressor1 = new Compressor();


	public static DoubleSolenoid shifters = new DoubleSolenoid(0,1);
	public static TalonSRX motorRightOne = new TalonSRX(rightOneTalonID);
	public static TalonSRX motorRightTwo = new TalonSRX(rightTwoTalonID);
	public static TalonSRX motorRightThree = new TalonSRX(rightThreeTalonID);
	public static TalonSRX motorLeftOne = new TalonSRX(leftOneTalonID);
	public static TalonSRX motorLeftTwo = new TalonSRX(leftTwoTalonID);
	public static TalonSRX motorLeftThree = new TalonSRX(leftThreeTalonID);
	
	
	public static TalonSRX armMaster = new TalonSRX(5);
	public static TalonSRX armFollower = new TalonSRX(4);
	
	public static DoubleSolenoid.Value sameGear = DoubleSolenoid.Value.kForward;
	public static DoubleSolenoid.Value lowGear = DoubleSolenoid.Value.kForward;
	public static DoubleSolenoid.Value highGear = DoubleSolenoid.Value.kReverse;
//	public static TalonSRX componentmotorLeftThree = new TalonSRX(9);

	//Accretion RobotMap, comment out for practice bot
	/*
	public static PowerDistributionPanel pdp = new PowerDistributionPanel();
	public static int rightTwoTalonID = 4; // Right Two is Green ID 4
	public static int leftTwoTalonID = 3; //Left Two is Yellow, ID 3
	public static int rightOneTalonID = 1; //Right One is Blue, ID 1
	public static int leftOneTalonID = 2;//Left One is Red, ID 2 
	public static int rightThreeTalonID;
	public static int leftThreeTalonID;
	
    public static double gearRatio = 2.5;
    public static double encoderTicsPerShaftRotation = 4096;
    public static double encoderTicsPerWheelRotation = gearRatio * encoderTicsPerShaftRotation ;
    public static double wheelDiam = 6.2;
    public static double wheelCircum = Math.PI * wheelDiam;
	public static AHRS navx = new AHRS(I2C.Port.kMXP);
	public static float maxLeftRPM = 465.47f;
	public static float maxRightRPM = 455.63f;
	public static double voltageControlMax = 11.0;
	public static Compressor compressor1 = new Compressor();


	//public static DoubleSolenoid Solenoid1 = new DoubleSolenoid(0,1);
	public static TalonSRX motorRightOne = new TalonSRX(rightOneTalonID);
	public static TalonSRX motorRightTwo = new TalonSRX(rightTwoTalonID);
	public static TalonSRX motorLeftOne = new TalonSRX(leftOneTalonID);
	public static TalonSRX motorLeftTwo = new TalonSRX(leftTwoTalonID);
	
	public static TalonSRX componentmotorLeftOne = new TalonSRX(7);
	public static TalonSRX componentmotorLeftTwo = new TalonSRX(8);
//	public static TalonSRX componentmotorLeftThree = new TalonSRX(9);
	

	*/



}
