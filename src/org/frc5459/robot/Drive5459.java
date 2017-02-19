package org.frc5459.robot;



import org.strongback.components.Solenoid;
import org.strongback.components.TalonSRX.StatusFrameRate;
import org.strongback.control.TalonController;
import org.strongback.control.TalonController.ControlMode;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.strongback.components.DistanceSensor;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Drive5459 {
	private TalonController rightController;
	private TalonController leftController;
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
	double inchPerSec;

	private currentGear gear;
	private boolean driverEnabled = true;
	private TalonController topRight;
	private TalonController topLeft;
	public boolean doneShifting;
	
	public static enum currentGear{
		HIGHGEAR,
		LOWGEAR;
	}
	

	public Drive5459(TalonController right, TalonController left, DistanceSensor ultraX, DistanceSensor ultraY, ADIS16448IMU imu, Solenoid gearShift,TalonController topRight,TalonController topLeft ){
		this.ultraX = ultraX;
		this.ultraY = ultraY;
		this.imu = imu;
		this.gearShift = gearShift;
		this.rightController = right;
		this.leftController = left;
		this.gear = currentGear.LOWGEAR;
		this.topRight = topRight;
		this.topLeft = topLeft;
		
	}
	
	
	public void rightControllerReturn(){
		//try to find velocity of wheel difference in encoder rotation * circumfrence of wheel / time passed 
		int x = 0;	
		rightControllerValues[x++] = "" + rightController.getSpeed();
		rightControllerValues[x++] = "" + rightController.getValue();
		rightControllerValues[x++] = "" + rightController.getEncoderInput().getAngle();
		rightControllerValues[x++] = "" + rightController.getBusVoltageSensor().getVoltage();
		rightControllerValues[x++] = "" + rightController.getTemperatureSensor().getTemperatureInFahrenheit();
		rightControllerValues[x++] = "" + rightController.isWithinTolerance();
		rightControllerValues[x++] = "" + rightController.getDirection();
		rightControllerValues[x++] = "" + rightController.getEncoderInput().getHeading();
	}
	
	public void leftControllerReturn(){
		int x = 0;
		leftControllerValues[x++] = "" + leftController.getSpeed();
		leftControllerValues[x++] = "" + leftController.getValue();
		leftControllerValues[x++] = "" + leftController.getEncoderInput().getAngle();
		leftControllerValues[x++] = "" + leftController.getBusVoltageSensor().getVoltage();
		leftControllerValues[x++] = "" + leftController.getTemperatureSensor().getTemperatureInFahrenheit();
		leftControllerValues[x++] = "" + leftController.isWithinTolerance();
		leftControllerValues[x++] = "" + leftController.getDirection();
		leftControllerValues[x++] = "" + leftController.getEncoderInput().getHeading();
	}
	
	public double getVelocity(){
		startCountRight = (long)rightController.getValue();
		startCountLeft = (long)leftController.getValue();
		currentTime = System.currentTimeMillis();
		for (int v = 0; v < 2; v++){
			Timer.delay(0.01);
		}
		endCountRight = (long)rightController.getValue();
		endCountLeft = (long)leftController.getValue();
		elapsedTime = System.currentTimeMillis() - currentTime;
		deltaRight = endCountRight - startCountRight;
		deltaLeft = endCountLeft - startCountLeft;
		deltaCount = (deltaRight + deltaLeft) / 2;
		elapsedTime = elapsedTime * 1000;
		displacement = (long)(deltaCount/375.95);
		inchPerSec = displacement/deltaCount;
		return inchPerSec;
	}

	public void setSpeedRight(double power){
		rightController.setControlMode(ControlMode.PERCENT_VBUS);
		rightController.setSpeed(power);
		updateTop();
		
	}
	
	public void setSpeedLeft(double power){
		leftController.setControlMode(ControlMode.PERCENT_VBUS);
		leftController.setSpeed(power); 
		updateTop();
	}
	
	public void setEncoderTargetAngleRight(double targetAngle){
		rightController.setStatusFrameRate(StatusFrameRate.FEEDBACK, 20);
		rightController.setControlMode(ControlMode.POSITION);
		rightController.withTarget(targetAngle);
		
		this.targetAngle = targetAngle;
	}
	//TODO: add the current value to the target
	public void setEncoderTargetAngleLeft(double targetAngle){
		leftController.setStatusFrameRate(StatusFrameRate.FEEDBACK, 20);
		leftController.setControlMode(ControlMode.POSITION);
		leftController.withTarget(targetAngle);
		this.targetAngle = targetAngle;
	}
	
	public double rightEncoderValue(){
		return rightController.getValue();
	}
	
	public double leftEncoderValue(){
		return leftController.getValue();
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
	
	//TODO write get acceleration methods
	
	public void setDriverEnabled(boolean state){
		this.driverEnabled = state;
	}
	
	
	public boolean isDriverEnabled(){
		return this.driverEnabled;
	}
	
	public void updateTop(){
		topRight.setControlMode(ControlMode.PERCENT_VBUS);
		topLeft.setControlMode(ControlMode.PERCENT_VBUS);
		topLeft.setSpeed(-leftController.getSpeed());
		topRight.setSpeed(-rightController.getSpeed());
	}
	

}
