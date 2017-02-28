package org.frc5459.robot;



import org.strongback.components.DistanceSensor;
import org.strongback.components.Solenoid;

import com.ctre.CANTalon;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.RobotDrive;

import org.frc5459.robot.*;


public class Drive5459 {
	private CANTalon rightController;
	private CANTalon leftController;
	private DistanceSensor ultraX;
	private DistanceSensor ultraY;
	private ADIS16448IMU imu;
	private Solenoid gearShift;
	private double targetAngle;
	
	String[] rightControllerValues = new String[8];
	String[] leftControllerValues = new String[8];
	
	private long elapsedTime;
	private long currentTime;
	private long startCountRight;
	private long endCountRight;
	private long startCountLeft;
	private long endCountLeft;
	private long deltaRight;
	private long deltaLeft;
	private long deltaCount;
	private long displacement;
	private double rightGoal = 0;
	private double leftGoal = 0;
	double inchPerSec;
	private RobotDrive drive;
	private currentGear gear;
	private boolean driverEnabled = true;
	private CANTalon topRight;
	private CANTalon topLeft;
	public boolean doneShifting;
	
	public static enum currentGear{
		HIGHGEAR,
		LOWGEAR;
	}
	

	public Drive5459(CANTalon right, CANTalon left, DistanceSensor ultraX, DistanceSensor ultraY, ADIS16448IMU imu, Solenoid gearShift,CANTalon topRight,CANTalon topLeft ){
		this.ultraX = ultraX;
		this.ultraY = ultraY;
		this.imu = imu;
		this.gearShift = gearShift;
		this.rightController = right;
		this.leftController = left;
		this.gear = currentGear.LOWGEAR;
		this.topRight = 
		this.topLeft = topLeft;
		this.drive = new  RobotDrive(leftController, rightController);
		
	}
	
	
	public void rightControllerReturn(){
		//try to find velocity of wheel difference in encoder rotation * circumfrence of wheel / time passed 
		int x = 0;	
//		rightControllerValues[x++] = "" + rightController.getSpeed();
//		rightControllerValues[x++] = "" + rightController.getValue();
//		rightControllerValues[x++] = "" + rightController.getEncoderInput().getAngle();
//		rightControllerValues[x++] = "" + rightController.getBusVoltageSensor().getVoltage();
//		rightControllerValues[x++] = "" + rightController.getTemperatureSensor().getTemperatureInFahrenheit();
//		rightControllerValues[x++] = "" + rightController.isWithinTolerance();
//		rightControllerValues[x++] = "" + rightController.getDirection();
//		rightControllerValues[x++] = "" + rightController.getEncoderInput().getHeading();
	}
	
	public void leftControllerReturn(){
		int x = 0;
//		leftControllerValues[x++] = "" + leftController.getSpeed();
//		leftControllerValues[x++] = "" + leftController.getValue();
//		leftControllerValues[x++] = "" + leftController.getEncoderInput().getAngle();
//		leftControllerValues[x++] = "" + leftController.getBusVoltageSensor().getVoltage();
//		leftControllerValues[x++] = "" + leftController.getTemperatureSensor().getTemperatureInFahrenheit();
//		leftControllerValues[x++] = "" + leftController.isWithinTolerance();
//		leftControllerValues[x++] = "" + leftController.getDirection();
//		leftControllerValues[x++] = "" + leftController.getEncoderInput().getHeading();
	}
	
	public double getVelocity(){

		startCountRight = rightController.getEncVelocity();
		startCountLeft = leftController.getEncVelocity();
		startCountLeft = (startCountLeft + startCountRight)/2;
		startCountLeft = (long) (startCountRight  * Math.PI/180);
		startCountLeft = startCountLeft *2;
		
		
		return startCountLeft;
	}

	public void setSpeedRight(double power){
		rightController.setProfile(1);
		rightController.changeControlMode(TalonControlMode.PercentVbus);
		rightController.set(-power);
		

		
	}
	
	public void setSpeedLeft(double power){
		leftController.setProfile(1);
		leftController.changeControlMode(TalonControlMode.PercentVbus);
		leftController.set(power); 
		
	}
	/**
	 * 
	 * @param targetAngle
	 */
	public void setEncoderTargetAngleRight(double targetAngle){
		rightController.setProfile(0);
		rightController.changeControlMode(TalonControlMode.Position);
		this.targetAngle = targetAngle;
		this.rightGoal = rightController.getPosition() + targetAngle;
		rightController.set(rightGoal);
		
	}
	//TODO: add the current value to the target
	public void setEncoderTargetAngleLeft(double targetAngle){
		leftController.setProfile(0);
		leftController.changeControlMode(TalonControlMode.Position);
		this.targetAngle = targetAngle;

		this.leftGoal = leftController.getPosition() + targetAngle;
		leftController.set(leftGoal);

	}
	
	public double rightEncoderValue(){
		return rightController.getPosition();
	}
	
	public double leftEncoderValue(){
		return leftController.getPosition();
	}
	
	public double getUltrasonicX(){
		return ultraX.getDistanceInInches();
	}
	
	public double getUltrasonicY(){
		return ultraY.getDistanceInInches();
	}
	
	public double gyroAngle(){
		return imu.getAngleZ() * 180/Math.PI;
		//this will most likely need to be changed
	}
	
	public void extend(){
		gearShift.extend();
		gear = currentGear.HIGHGEAR;
	}
	
	public void retract(){
		gearShift.retract();
		gear = currentGear.LOWGEAR;
	}
	
	public double imuX(){
		return imu.getAngleX();
	}
	public double imuY(){
		return imu.getAngleY();
	}
	public double imuZ(){
		return imu.getAngleZ();
	}
	
	public currentGear getCurrentGear(){
		return gear;
	}
	
	public double getRightPower(){
		return rightController.getSpeed();
	}
	public double getLeftPower(){
		return leftController.getSpeed();
	}
	/**
	 * REMINDER: The imu is vertical and sideways
	 */
	public double getAccelX(){
		return imu.getAccelX();
	}
	public double getAccelY(){
		return imu.getAccelY();
	}
	public double getAccelZ(){
		return imu.getAccelZ();
	}
	
	public void setDriverEnabled(boolean state){
		this.driverEnabled = state;
	}
	
	public boolean isDriverEnabled(){
		return this.driverEnabled;
	}
	
	public void arcadeDrive(double power, double rotation){
		drive.arcadeDrive(power, rotation);
	}
	

}
