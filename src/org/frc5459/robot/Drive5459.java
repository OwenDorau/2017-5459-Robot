package org.frc5459.robot;



import java.util.Timer;

import org.strongback.components.Solenoid;
import org.strongback.components.TalonSRX.StatusFrameRate;
import org.strongback.control.TalonController;
import org.strongback.control.TalonController.ControlMode;
import org.strongback.components.DistanceSensor;
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
	private currentGear gear;
	private boolean driverEnabled = true;
	
	public static enum currentGear{
		HIGHGEAR,
		LOWGEAR;
	}
	
	public Drive5459(TalonController right, TalonController left, DistanceSensor ultraX, DistanceSensor ultraY, ADIS16448IMU imu, Solenoid gearShift){
		this.ultraX = ultraX;
		this.ultraY = ultraY;
		this.imu = imu;
		this.gearShift = gearShift;
		this.rightController = right;
		this.leftController = left;
		this.gear = currentGear.LOWGEAR;
		
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
	
//	public void getVelocity(){
//
//		(12 * y) / timer.get
//	}

	public void setPowerRight(double power){
		rightController.setControlMode(ControlMode.SPEED);
		rightController.setSpeed(power);
	}
	
	public void setPowerLeft(double power){
		leftController.setControlMode(ControlMode.SPEED);
		leftController.setSpeed(power);
	}
	
	public void setEncoderTargetAngleRight(double targetAngle){
		rightController.setStatusFrameRate(StatusFrameRate.FEEDBACK, 20);
		rightController.setControlMode(ControlMode.POSITION);
		rightController.withTarget(targetAngle);
		this.targetAngle = targetAngle;
	}
	
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
	
	public double getDistanceSensorX(){
		return ultraX.getDistanceInInches();
	}
	
	public double getDistanceSensorY(){
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
	
	
	

}
