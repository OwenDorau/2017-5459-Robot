

package org.frc5459.robot;

import java.util.function.DoubleSupplier;
//import java.lang.Thread.run;

import org.frc5459.robot.Drive5459.currentGear;
import org.strongback.Strongback;
import org.strongback.SwitchReactor;
import org.strongback.components.DistanceSensor;
import org.strongback.components.Solenoid;
import org.strongback.components.Solenoid.Direction;
import org.strongback.components.ui.Gamepad;
import org.strongback.control.SoftwarePIDController;
import org.strongback.hardware.Hardware;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.sun.xml.internal.bind.v2.model.core.ID;

import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
	private Gamepad driver;
	private Gamepad operator;
	private Solenoid bucket;
	private Solenoid shifter;
	private SwitchReactor reactor;
	private CANTalon topRight;
	private CANTalon middleRight;
	private CANTalon bottomRight;
	private CANTalon climber;
	private CANTalon topLeft;
	private CANTalon middleLeft;
	private CANTalon bottomLeft;
	private ADIS16448IMU imu;
	private DistanceSensor ultraX;
	private DistanceSensor ultraY;
	String[] bucketPosition = new String[2];
	private NetworkTable dataBase;
	private double distance;
	private double horizontalDistance;
	private double rotationalAngle;
	private Drive5459 drive;
	private SoftwarePIDController turnToPID;
	private VideoSource front;
	private VideoSource back;
	private int shifts;
	int ticks = 0;
	private boolean winning = true;
	private boolean y = true;
	private boolean direction = false;



    @Override
    public void robotInit() {
    	//User Interfaces
    	driver = Hardware.HumanInterfaceDevices.xbox360(0);
    	operator = Hardware.HumanInterfaceDevices.xbox360(1);
    	reactor = Strongback.switchReactor();
    	
    	//Manipulator 
    	bucket = Hardware.Solenoids.doubleSolenoid(0, 1, Direction.EXTENDING);
    	//Motors and Controllers
    	shifter = Hardware.Solenoids.doubleSolenoid(2, 3, Direction.EXTENDING);
    	topRight = new CANTalon(1);
    	middleRight = new CANTalon(2); //TalonSRX #2
    	bottomRight = new CANTalon(3); //TalonSRX #3
    	climber = new CANTalon(4); //TalonSRX #4
    	topLeft = new CANTalon(5); //TalonSRX #5
    	middleLeft = new CANTalon(6); //TalonSRX #6
    	bottomLeft = new CANTalon(7); //TalonSRX #7
    	climber.reverseOutput(true);    	
    	//Setting Followers
    	//topRight is Right Side Master (TalonSRX #1)0.062, 0.00062, 0.62)
    	//middleRight.reset();
    	//middleRight.setPID( 0.1, 0.0, 0.0);//TODO: make multiple profiles
    	
    	middleRight.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	middleRight.setEncPosition((middleRight.getPulseWidthPosition() & 0xFFF));
    	//middleRight.setAllowableClosedLoopErr(10);
    	//middleRight.reverseSensor(true);
    	middleRight.configNominalOutputVoltage(+0f, -0f);
    	middleRight.configPeakOutputVoltage(+12f, -12f);
    	topRight.changeControlMode(TalonControlMode.Follower);
    	topRight.set(middleRight.getDeviceID());
    	topRight.reverseOutput(true);
    	bottomRight.changeControlMode(TalonControlMode.Follower); //TalonSRX #3
    	bottomRight.set(middleRight.getDeviceID());
    	//climber is the climber Motor (TalonSRX #4)
    	//TopLeft is Right Side Master (TalonSRX #5)
    	middleLeft.reset();
    	//middleLeft.setPID(0.1, 0.00, 0.0);
    	middleLeft.setEncPosition((middleLeft.getPulseWidthPosition() & 0xFFF));
    	middleLeft.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	middleLeft.configNominalOutputVoltage(+0f, -0f);
    	middleLeft.configPeakOutputVoltage(+12f, -12f);
    	middleLeft.reverseSensor(false);
    	//middleLeft.setAllowableClosedLoopErr(10);
    	topLeft.changeControlMode(TalonControlMode.Follower); //TalonSRX #6
    	topLeft.set(middleLeft.getDeviceID());
    	topLeft.reverseOutput(true);
    	bottomLeft.changeControlMode(TalonControlMode.Follower); //TalonSRX #7
    	bottomLeft.set(middleLeft.getDeviceID());
    	//Sensors
    	imu = new ADIS16448IMU();
    	//drive
    	drive = new Drive5459(middleRight, middleLeft, ultraX, ultraY, imu, shifter,topRight, topLeft);
    	
    	NetworkTable.setServerMode();
    	NetworkTable.initialize();
    	dataBase = NetworkTable.getTable("DataBase");
    	// front = VideoSource.
    }   
    
    @Override
    public void autonomousInit() {
    	Strongback.start(); 
    	//Strongback.submit(new GoToEncoderValueCommand(12));
    	//Strongback.submit(new ShortcutForEncodersCommand(drive, 48));
    	Strongback.submit(new SimpleAuto(drive));
//    	SmartDashboard.putDouble("leftTarget", (48 * 325.9493234522016) + drive.leftEncoderValue());
//    	SmartDashboard.putDouble("rightTarget", (48 * 325.9493234522016) + drive.rightEncoderValue());
//		
//			drive.setEncoderTargetAngleLeft(10000);
//			drive.setEncoderTargetAngleRight(10000);
//			SmartDashboard.putInt("encoder positioin", middleLeft.getEncPosition());
//			SmartDashboard.putDouble("error", middleLeft.getError());
//			SmartDashboard.putDouble("position", middleLeft.getPosition());
    	
			
    	
    }
    
    @Override
    public void autonomousPeriodic() {
    	SmartDashboard.putDouble("encoder positioin Left ", drive.leftEncoderValue());
		SmartDashboard.putDouble("rotaion", imu.getAngleY());
		SmartDashboard.putDouble("encoder Position Right", drive.rightEncoderValue());
    	//drive.setEncoderTargetAngleLeft(10000);
		//drive.setEncoderTargetAngleRight(10000);
    	//if (driver.getA().isTriggered()) {
			
		//}
		
		
		
    }
    
    @Override
    public void teleopInit() {
    	Strongback.start();
    	drive.setDriverEnabled(true);
    	//Strongback.submit(new TeleopDriveCommand(drive, driver);
      //SmartDashboard.putData(key, data);
    	//("turning PID controller gains", turnToPID.getGainsFor(2));
    	//SmartDashboard.putData("turning PID controller I", turnToPID.getTarget());
    	//turnToPID.startLiveWindowMode();
    	Strongback.submit(new ShiftUpCommand(drive, driver));
    }

    @Override
    public void teleopPeriodic() {   
    	//middleLeft.changeControlMode(TalonControlMode.PercentVbus);
    	//middleRight.changeControlMode(TalonControlMode.PercentVbus);
    	//middleLeft.set(1.0);
    	//middleRight.set(1.0);
//    	SmartDashboard.putDouble("This is the velocity", drive.getVelocity());
//    	SmartDashboard.putInt("number of shifts", shifts);
//    	SmartDashboard.putDouble("power", driver.getRightY().read());
//    	SmartDashboard.putDouble("rot", driver.getLeftY().read());
    	if (operator.getRightBumper().isTriggered()) {
    		Strongback.submit(new BucketExtendCommand(bucket));
		}else if( operator.getLeftBumper().isTriggered()){
			Strongback.submit(new BucketRetractCommand(bucket));

		}


//    	if(operator.getX().isTriggered()){
//    		Strongback.submit(new TurnToCommand(90, drive));
//    	}

    	
    	if (driver.getLeftBumper().isTriggered()) {
			Strongback.submit(new ShiftUpCommand(drive, driver));
		}
    	if (driver.getRightBumper().isTriggered()) {
			Strongback.submit(new ShiftDownCommand(drive, driver));
		}
    	
//    	if (operator.getA().isTriggered()) {
//			
//			drive.setSpeedLeft(0.5);
//			drive.setSpeedRight(0.5);
//
//		}
//    	if (middleLeft.getError() == 0 || middleRight.getError() == 0) {
//			drive.setDriverEnabled(true);
//		}
    	//drive.setDriverEnabled(false);
    	if (drive.isDriverEnabled()) {
    		
			if (driver.getLeftTrigger().read() > 0.7) {
    			Strongback.submit(new TeleopDriveCommand(drive, driver,0.5/* direction*/ ));
			}else{
				Strongback.submit(new TeleopDriveCommand(drive, driver, 1.0 /*direction*/));
			}
    		
			if(driver.getRightTrigger().read() > 0.7){
				direction = false;
			}else {
				direction = true;
			}
//    		if(driver.getRightTrigger().read() > 0.7){
//		Strongback.submit(new TeleopDriveCommand(drive, driver,0.25, direction));
    	}
//    	
//    	if (driver.getB().isTriggered()) {
//			Strongback.submit(new ShortcutForEncodersCommand(drive, 10));
//		}
//    	
//    	if (driver.getY().isTriggered()) {
//			drive.setEncoderTargetAngleRight(325.9493234522016*24);
//			drive.setEncoderTargetAngleLeft(325.9493234522016*24);
//		}
//		if (operator.getY().isTriggered()) {
//			Strongback.submit(new GoToEncoderValueCommand(30));
////			drive.setEncoderTargetAngleLeft(1000000000);
////			drive.setEncoderTargetAngleRight(1000000000);
//			
//		}
//		if (drive.getVelocity() > 65 && drive.getCurrentGear().equals(currentGear.LOWGEAR)) {
//			shifts++;
//			Strongback.submit(new ShiftDownCommand(drive, driver));
//		}else if (drive.getVelocity() < 60 && drive.getCurrentGear().equals(currentGear.HIGHGEAR)) {
//			shifts++;
//			Strongback.submit(new ShiftUpCommand(drive, driver));
//		}

    	if (operator.getRightTrigger().read() > 0.7) { 
			Strongback.submit(new AscendClimbCommand(climber));
		}else {
			Strongback.submit(new StopClimbCommand(climber));
		}
//    	distance = dataBase.getNumber("Distance", 0.0);
//    	horizontalDistance = dataBase.getNumber("horizontalDistance", 0.0);
//    	rotationalAngle = dataBase.getNumber("rotationAngle", 0.0);
//    	double angle = dataBase.getNumber("angle", 0);
//    	SmartDashboard.putDouble("this is the distance", distance);
//    	SmartDashboard.putDouble("Angle", angle);
//    	//TODO: test the drive train today and try to get raspi done as well
    	SmartDashboard.putInt("Left encoder positioin", middleLeft.getEncPosition());
		SmartDashboard.putDouble("Left error", middleLeft.getError());
		SmartDashboard.putDouble("Left position", middleLeft.getPosition());
		SmartDashboard.putDouble("x", drive.imuY());
//		SmartDashboard.putInt("Right encoder positioin", middleRight.getEncPosition());
//		SmartDashboard.putDouble("Right error", middleRight.getError());
//		SmartDashboard.putDouble("Right position", middleRight.getPosition());
    	Timer.delay(0.05);
    	
    }

    @Override
    public void disabledInit() {
        // Tell Strongback that the robot is disabled so it can flush and kill commands.
    	Strongback.killAllCommands();
        Strongback.disable();
    }

    //Write a loop for the pid controlers
    
}


