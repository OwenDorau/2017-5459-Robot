

package org.frc5459.robot;

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
    	middleRight.reset();
    	middleRight.setPID(0.062, 0.00062, 0.0);//TODO: make multiple profiles
    	middleRight.setPID(0, 0, 0);
    	middleRight.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	middleRight.setEncPosition((middleRight.getPulseWidthPosition() & 0xFFF));
    	middleRight.setAllowableClosedLoopErr(10);
    	middleRight.reverseSensor(true);
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
    	middleLeft.setPID(0.062, 0.00062, 0.0);
    	middleLeft.setPID(0, 0, 0);
    	middleLeft.setEncPosition((middleLeft.getPulseWidthPosition() & 0xFFF));
    	middleLeft.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	middleLeft.configNominalOutputVoltage(+0f, -0f);
    	middleLeft.configPeakOutputVoltage(+12f, -12f);
    	middleLeft.setAllowableClosedLoopErr(10);
    	topLeft.changeControlMode(TalonControlMode.Follower); //TalonSRX #6
    	topLeft.set(middleLeft.getDeviceID());
    	topLeft.reverseOutput(true);
    	bottomLeft.changeControlMode(TalonControlMode.Follower); //TalonSRX #7
    	bottomLeft.set(middleLeft.getDeviceID());
    	//Sensors
    	imu = new ADIS16448IMU();
    	//drive
    	drive = new Drive5459(middleRight, middleLeft, ultraX, ultraY, imu, shifter,topRight,topLeft);
    	
    	
    	// front = VideoSource.
    }   
    
    @Override
    public void autonomousInit() {
    	Strongback.start(); 
    	
		if (ticks  == 0) {
			drive.setEncoderTargetAngleLeft(10);
			drive.setEncoderTargetAngleRight(10);
			SmartDashboard.putInt("encoder positioin", middleLeft.getEncPosition());
			SmartDashboard.putDouble("dsohfousdhofh", middleLeft.getError());
			SmartDashboard.putDouble("djfhhhhhhh", middleLeft.getPosition());
    	}
    	ticks++;
    }
    
    @Override
    public void autonomousPeriodic() {
    	
    	//if (driver.getA().isTriggered()) {
			
		//}
    }
    
    @Override
    public void teleopInit() {
    	Strongback.start();
    	
    	//Strongback.submit(new TeleopDriveCommand(drive, driver));
      //SmartDashboard.putData(key, data);
    	//("turning PID controller gains", turnToPID.getGainsFor(2));
    	//SmartDashboard.putData("turning PID controller I", turnToPID.getTarget());
    	//turnToPID.startLiveWindowMode();
    }

    @Override
    public void teleopPeriodic() {    	
    	SmartDashboard.putDouble("This is the velocity", drive.getVelocity());
    	SmartDashboard.putInt("number of shifts", shifts);
    	if (operator.getRightBumper().isTriggered()) {
//    		Strongback.submit(new BucketExtendCommand(bucket));
		}else if( operator.getLeftBumper().isTriggered()){
//			Strongback.submit(new BucketRetractCommand(bucket));

		}
    	if(operator.getX().isTriggered()){
    		Strongback.submit(new TurnToCommand(90));
    	}
    	if (driver.getLeftBumper().isTriggered()) {
			Strongback.submit(new ShiftUpCommand(drive, driver));
		}
    	if (driver.getRightBumper().isTriggered()) {
			Strongback.submit(new ShiftDownCommand(drive, driver));
		}
    	if (drive.isDriverEnabled()) {
    		if (driver.getLeftTrigger().read() > 0.7) {
    			Strongback.submit(new TeleopDriveCommand(drive, driver,true));
			}else {
				Strongback.submit(new TeleopDriveCommand(drive, driver,false));
			}
    		
    		
    	}
		if (operator.getY().isTriggered()) {
			Strongback.submit(new GoToEncoderValueCommand(30));
//			drive.setEncoderTargetAngleLeft(1000000000);
//			drive.setEncoderTargetAngleRight(1000000000);
			
		}
		if (drive.getVelocity() > 65&& drive.getCurrentGear().equals(currentGear.LOWGEAR)) {
			shifts++;
			Strongback.submit(new ShiftDownCommand(drive, driver));
		}else if (drive.getVelocity() < 55 && drive.getCurrentGear().equals(currentGear.HIGHGEAR)) {
			shifts++;
			Strongback.submit(new ShiftUpCommand(drive, driver));
		}

    	if (operator.getA().isTriggered()) { 
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
    	Timer.delay(0.05);
    	
    }

    @Override
    public void disabledInit() {
        // Tell Strongback that the robot is disabled so it can flush and kill commands.
        Strongback.disable();
    }

    
    
}


