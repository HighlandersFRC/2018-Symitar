
package org.usfirst.frc.team4499.robot;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;




public class RobotMap {
	public static DoubleSolenoid Solenoid1 = new DoubleSolenoid(0,1);
	public static TalonSRX motorRightOne = new TalonSRX(1);
	public static TalonSRX motorRightTwo = new TalonSRX(4);
	public static TalonSRX motorLeftOne = new TalonSRX(2);
	public static TalonSRX motorLeftTwo = new TalonSRX(3);
	
	public static TalonSRX componentmotorLeftOne = new TalonSRX(7);
	public static TalonSRX componentmotorLeftTwo = new TalonSRX(8);
	



}
