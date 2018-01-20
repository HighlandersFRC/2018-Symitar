
package org.usfirst.frc.team4499.robot;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.I2C;




public class RobotMap {
	public static AHRS navx = new AHRS(I2C.Port.kMXP);
	public static DoubleSolenoid Solenoid1 = new DoubleSolenoid(0,1);
	public static TalonSRX motorRightOne = new TalonSRX(1);
	public static TalonSRX motorRightTwo = new TalonSRX(4);
	public static TalonSRX motorLeftOne = new TalonSRX(2);
	public static TalonSRX motorLeftTwo = new TalonSRX(3);
	
	public static TalonSRX componentmotorLeftOne = new TalonSRX(7);
	public static TalonSRX componentmotorLeftTwo = new TalonSRX(8);
//	public static TalonSRX componentmotorLeftThree = new TalonSRX(9);
	public static float maxLeftRPM = 465.47f;
	public static float maxRightRPM = 465.63f;

	



}
