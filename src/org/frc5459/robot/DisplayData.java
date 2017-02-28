package org.frc5459.robot;

import org.strongback.command.Command;
import org.strongback.components.Solenoid;
import org.strongback.control.SoftwarePIDController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DisplayData extends Command{
	private Drive5459 drive5459;
	private Solenoid bucket;
	private SoftwarePIDController softwarePIDController;
	
//	private double[] rightControllerValues = new double[6];
//	private double[] leftControllerValues = new double[6];
	
	
	public DisplayData(Solenoid bucket, Drive5459 drive5459){
		this.bucket = bucket;
		this.drive5459 = drive5459;
	}
	
	@Override
	public boolean execute(){
		//Sensor Returns
		SmartDashboard.putNumber("Ultrasonic X", drive5459.getUltrasonicX());
		SmartDashboard.putNumber("Ultrasonic Y", drive5459.getUltrasonicY());
		SmartDashboard.putNumber("Gyro Angle X", drive5459.imuX());
		SmartDashboard.putNumber("Gyro Angle Y", drive5459.imuY());
		SmartDashboard.putNumber("Gyro Angle Z", drive5459.imuZ());
		
		//Right Side Returns
		int x = 0;
//		SmartDashboard.putString("Right Controller Speed", drive5459.rightControllerValues[x++]);
		SmartDashboard.putString("Right Controller Value", drive5459.rightControllerValues[x++]);
//		SmartDashboard.putString("Right Controller Angle", drive5459.rightControllerValues[x++]);
//		SmartDashboard.putString("Right Controller Votage", drive5459.rightControllerValues[x++]);
//		SmartDashboard.putString("Right Controller Temperature", drive5459.rightControllerValues[x++]);
//		SmartDashboard.putString("Right Controller is Within Tolerance", drive5459.rightControllerValues[x++]);
//		SmartDashboard.putString("Right Controller Direction", drive5459.rightControllerValues[x++]);
//		SmartDashboard.putString("Right Controller Heading", drive5459.rightControllerValues[x++]);
//		//Left Side return
//		SmartDashboard.putString("Left Controller Speed", drive5459.leftControllerValues[x++]);
		SmartDashboard.putString("Left Controller Value", drive5459.leftControllerValues[x++]);
//		SmartDashboard.putString("Left Controller Angle", drive5459.leftControllerValues[x++]);
//		SmartDashboard.putString("Left Controller Votage", drive5459.leftControllerValues[x++]);
//		SmartDashboard.putString("Left Controller Temperature", drive5459.leftControllerValues[x++]);
//		SmartDashboard.putString("Left Controller is Within Tolerance", drive5459.leftControllerValues[x++]);
//		SmartDashboard.putString("Left Controller Direction", drive5459.leftControllerValues[x++]);
//		SmartDashboard.putString("Left Controller Heading", drive5459.leftControllerValues[x++]);
//		SmartDashboard.putNumber("Velocity", drive5459.inchPerSec);
		//Bucket
		
		
		//TurnToPID
		softwarePIDController.useProfile(2);
		SmartDashboard.putDouble("TurnToPID Target", softwarePIDController.getTarget());
		SmartDashboard.putDouble("TurnToPID Tolerance", softwarePIDController.getTolerance());
		SmartDashboard.putDouble("TurnToPID Value", softwarePIDController.getValue());
		//SmartDashboard.putString(key, value);("TurnToPID Error", softwarePIDController.getSmartDashboardType());
		//SmartDashboard.putString("TurnToPID Tolerance", softwarePIDController.getGainsForCurrentProfile());
		SmartDashboard.putDouble("TurnToPID Tolerance", softwarePIDController.getTolerance() - softwarePIDController.getTarget());
		
		
		
		return true;
	
	}
}
