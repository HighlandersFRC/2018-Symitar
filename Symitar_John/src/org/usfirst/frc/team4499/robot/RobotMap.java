/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4499.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//Name all Talon ID's for Easy Acess
	public static int rightDriveLeadID = 2;
	public static int leftDriveLeadID = 6;
	
	public static int rightDriveFollowerOneID = 1; 
	public static int rightDriveFollowerTwoID = 3;
	public static int leftDriveFollowerOneID = 7;
	public static int leftDriveFollowerTwoID = 8;
	
	public static int armMasterID = 5;
	public static int armFollowerID = 4;
	
	public static int intakeLeftID = 9;
	public static int intakeRightID = 10;
	
	
	//Initialize all TalonsSRX
	public static TalonSRX rightDriveLead = new TalonSRX(rightDriveLeadID);
	public static TalonSRX leftDriveLead = new TalonSRX(leftDriveLeadID);
	
	public static TalonSRX rightDriveFollowerOne = new TalonSRX(rightDriveFollowerOneID);
	public static TalonSRX rightDriveFollowerTwo = new TalonSRX(rightDriveFollowerTwoID);
	public static TalonSRX leftDriveFollowerOne = new TalonSRX(leftDriveFollowerOneID);
	public static TalonSRX leftDriveFollowerTwo = new TalonSRX(leftDriveFollowerTwoID);
	
	public static TalonSRX armMaster = new TalonSRX(armMasterID);
	public static TalonSRX armFollower = new TalonSRX(armFollowerID);
	
	public static TalonSRX intakeLeft = new TalonSRX(intakeLeftID);
	public static TalonSRX intakeRight = new TalonSRX(intakeRightID);
	
	
	
	public static AHRS navx = new AHRS(I2C.Port.kMXP);

	//Initialize all pneumatic Actuators, predefine actuation directions
	public static DoubleSolenoid shifters = new DoubleSolenoid(0,1);
	public static DoubleSolenoid.Value lowGear = DoubleSolenoid.Value.kForward;
	public static DoubleSolenoid.Value highGear = DoubleSolenoid.Value.kReverse;
	
	public static DoubleSolenoid brake = new DoubleSolenoid(2,3);
	public static DoubleSolenoid.Value releaseBrake = DoubleSolenoid.Value.kForward;
	public static DoubleSolenoid.Value setBrake = DoubleSolenoid.Value.kReverse;
	
	public static DoubleSolenoid intake = new DoubleSolenoid(4,5);
	public static DoubleSolenoid.Value openIntake = DoubleSolenoid.Value.kForward;
	public static DoubleSolenoid.Value closeIntake = DoubleSolenoid.Value.kReverse;
	
	//Array of drive motors to simplify configuration
	public static TalonSRX driveMotors[] = {
			  RobotMap.leftDriveLead,
			  RobotMap.rightDriveLead,
			  RobotMap.leftDriveFollowerOne,
			  RobotMap.leftDriveFollowerTwo,
			  RobotMap.rightDriveFollowerOne,
			  RobotMap.rightDriveFollowerTwo};
}
