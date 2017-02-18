
package org.frc5459.robot;

import org.strongback.Strongback;
import org.strongback.SwitchReactor;

import org.strongback.components.DistanceSensor;
import org.strongback.components.Solenoid;
import org.strongback.components.Solenoid.Direction;
import org.strongback.components.TalonSRX.FeedbackDevice;
import org.strongback.components.ui.FlightStick;
import org.strongback.components.ui.Gamepad;
import org.strongback.control.SoftwarePIDController;
import org.strongback.control.TalonController;
import org.strongback.control.TalonController.ControlMode;
import org.strongback.hardware.Hardware;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.networktables.NetworkTable;


public class Robot extends IterativeRobot {
	private Gamepad driver;
	private Gamepad operator;
	private Solenoid bucket;
	private Solenoid shifter;
	private SwitchReactor reactor;
	private TalonController topRight;
	private TalonController middleRight;
	private TalonController bottomRight;
	private TalonController climber;
	private TalonController topLeft;
	private TalonController middleLeft;
	private TalonController bottomLeft;
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
	
	


    @Override
    public void robotInit() {
    	//User Interfaces
    	driver = Hardware.HumanInterfaceDevices.xbox360(0);
    	//operator = Hardware.HumanInterfaceDevices.xbox360(1);
    	reactor = Strongback.switchReactor();
    	
    	//Manipulator 
    	bucket = Hardware.Solenoids.doubleSolenoid(0, 1, Direction.RETRACTING);
    	System.out.println("hi");
    	//Motors and Controllers
    	shifter = Hardware.Solenoids.doubleSolenoid(2, 3, Direction.EXTENDING);
    	topRight = Hardware.Controllers.talonController(1, 11.37, 0); //TalonSRX #1
    	middleRight = Hardware.Controllers.talonController(2, 11.37, 0); //TalonSRX #2
    	bottomRight = Hardware.Controllers.talonController(3, 11.37, 0); //TalonSRX #3
    	climber = Hardware.Controllers.talonController(4, 11.37, 0); //TalonSRX #4
    	topLeft = Hardware.Controllers.talonController(5, 11.37, 0); //TalonSRX #5
    	middleLeft = Hardware.Controllers.talonController(6, 11.37,0); //TalonSRX #6
    	bottomRight = Hardware.Controllers.talonController(7, 11.37,0); //TalonSRX #7
    	climber.reverseOutput(true);
    	System.out.println("hello");
    	
    	//Setting Followers
    	//topRight is Right Side Master (TalonSRX #1)
    	topRight.withGains(0.062, 0.00062, 0.62);//TODO: make multiple profiles
    	topRight.setFeedbackDevice(FeedbackDevice.MAGNETIC_ENCODER_ABSOLUTE);
    	middleRight.setControlMode(ControlMode.FOLLOWER);//TalonSRX #2
    	middleRight.withTarget(topRight.getDeviceID());
    	bottomRight.setControlMode(ControlMode.FOLLOWER); //TalonSRX #3
    	bottomRight.withTarget(topRight.getDeviceID());
    	//climber is the climber Motor (TalonSRX #4)
    	//TopLeft is Right Side Master (TalonSRX #5)
    	topLeft.withGains(0.062, 0.00062, 0.62);
    	topLeft.setFeedbackDevice(FeedbackDevice.MAGNETIC_ENCODER_ABSOLUTE);
    	topLeft.reverseOutput(true);
    	middleLeft.setControlMode(ControlMode.FOLLOWER); //TalonSRX #6
    	middleLeft.withTarget(topLeft.getDeviceID());
    	//bottomLeft.setControlMode(ControlMode.FOLLOWER); //TalonSRX #7
    	//bottomLeft.withTarget(topLeft.getDeviceID());
    	//Sensors
    	//imu = new ADIS16448IMU();
    	//drive
    	drive = new Drive5459(topRight, topLeft, ultraX, ultraY, null, shifter);
    	//dataBase = NetworkTable.getTable("DataBase");

  
    }   
    
    @Override
    public void autonomousInit() {
    	
    	Strongback.start();
//    	NetworkTable.setClientMode();
//    	NetworkTable.setIPAddress("10.10.148.120");
    	
    }
    
    @Override
    public void autonomousPeriodic() {
    	
    	
    	
    }
    
    @Override
    public void teleopInit() {
    	Strongback.start();
    	//Strongback.submit(new TeleopDriveCommand(drive, driver));
    }

    @Override
    public void teleopPeriodic() {    	
    	if (driver.getRightBumper().isTriggered()) {
    		//Strongback.submit(new BucketExtendCommand(bucket));
    		bucket.extend();
		}else if( driver.getLeftBumper().isTriggered()){
			//Strongback.submit(new BucketRetractCommand(bucket));
			bucket.retract();
		}

    	//reactor.whileTriggered(driver.getRightBumper(), () -> Strongback.submit(new AscendClimbCommand(climber)));
    	//reactor.whileUntriggered(driver.getRightBumper(), () -> Strongback.submit(new StopClimbCommand(climber)));
//    	distance = dataBase.getNumber("Distance", 0.0);
//    	horizontalDistance = dataBase.getNumber("horizontalDistance", 0.0);
//    	rotationalAngle = dataBase.getNumber("rotationAngle", 0.0);
//    	double angle = dataBase.getNumber("angle", 0);
//    	System.out.println("The distance to the target is" + distance);
//    	System.out.println("The horizontal distance is " + horizontalDistance);
//    	System.out.println("The first angle is " + rotationalAngle + ".  The second one (based on dis) is " + angle);
//    	//TODO: test the drive train today and try to get raspi done as well
    	Timer.delay(0.05);
    }

    @Override
    public void disabledInit() {
        // Tell Strongback that the robot is disabled so it can flush and kill commands.
        Strongback.disable();
    }

    
    
}

