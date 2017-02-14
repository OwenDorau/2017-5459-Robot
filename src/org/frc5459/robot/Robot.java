/* Created Sat Jan 14 09:56:00 EST 2017 */
package org.frc5459.robot;

import org.strongback.Strongback;
import org.strongback.SwitchReactor;

import org.strongback.components.DistanceSensor;
import org.strongback.components.Solenoid;
import org.strongback.components.Solenoid.Direction;
import org.strongback.components.TalonSRX.FeedbackDevice;
import org.strongback.components.ui.Gamepad;
import org.strongback.control.TalonController;
import org.strongback.control.TalonController.ControlMode;
import org.strongback.hardware.Hardware;
import org.frc5459.robot.BucketExtendCommand;
import org.frc5459.robot.BucketRetractCommand;
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
	


    @Override
    public void robotInit() {
    	//User Interfaces
    	driver = Hardware.HumanInterfaceDevices.xbox360(0);
    	operator = Hardware.HumanInterfaceDevices.xbox360(1);
    	reactor = Strongback.switchReactor();
    	
    	//Manipulator 
    	bucket = Hardware.Solenoids.doubleSolenoid(0, 1, Direction.EXTENDING);
    	//11.37
    	//Motors and Controllers
    	topRight = Hardware.Controllers.talonController(1, 11.37, 0); //TalonSRX #1
    	middleRight = Hardware.Controllers.talonController(2, 11.37, 0); //TalonSRX #2
    	bottomRight = Hardware.Controllers.talonController(3, 11.37, 0); //TalonSRX #3
    	climber = Hardware.Controllers.talonController(4, 11.37, 0); //TalonSRX #4
    	topLeft = Hardware.Controllers.talonController(5, 11.37, 0); //TalonSRX #5
    	middleLeft = Hardware.Controllers.talonController(6, 11.37,0); //TalonSRX #6
    	bottomRight = Hardware.Controllers.talonController(7, 11.37,0); //TalonSRX #7
    	//Setting Followers
    	//topRight is Right Side Master (TalonSRX #1)
    	topRight.withGains(0.1, 0.001, 0.0);
    	topRight.setFeedbackDevice(FeedbackDevice.MAGNETIC_ENCODER_ABSOLUTE);
    	middleRight.setControlMode(ControlMode.FOLLOWER);//TalonSRX #2
    	middleRight.withTarget(topRight.getDeviceID());
    	bottomRight.setControlMode(ControlMode.FOLLOWER); //TalonSRX #3
    	bottomRight.withTarget(topRight.getDeviceID());
    	//climber is the climber Motor (TalonSRX #4)
    	//TopLeft is Left Side Master (TalonSRX #5)
    	topLeft.withGains(0.1, 0.001, 0.0);
    	topLeft.setFeedbackDevice(FeedbackDevice.MAGNETIC_ENCODER_ABSOLUTE);
    	middleLeft.setControlMode(ControlMode.FOLLOWER); //TalonSRX #6
    	middleLeft.withTarget(topLeft.getDeviceID());
    	bottomLeft.setControlMode(ControlMode.FOLLOWER); //TalonSRX #7
    	bottomLeft.withTarget(topLeft.getDeviceID());
    	//Sensors
    	imu = new ADIS16448IMU();
    	//drive
    	drive = new Drive5459(topRight, topLeft, ultraX, ultraY, imu, shifter);
    	dataBase = NetworkTable.getTable("SmartDashboard");

  
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
        Strongback.submit(new TeleopDriveCommand(drive, driver));
    }

    @Override
    public void teleopPeriodic() {    	
    	if (operator.getRightTrigger().read() > 0.5) {
    		Strongback.submit(new BucketExtendCommand(bucket));
		}else if( operator.getLeftTrigger().read() > 0.5){
			Strongback.submit(new BucketRetractCommand(bucket));
		}

    	reactor.whileTriggered(operator.getRightBumper(), () -> Strongback.submit(new AscendClimbCommand(climber)));
    	reactor.whileUntriggered(operator.getRightBumper(), () -> Strongback.submit(new StopClimbCommand(climber)));
    	distance = dataBase.getNumber("Distance", 0.0);
    	horizontalDistance = dataBase.getNumber("horizontalDistance", 0.0);
    	rotationalAngle = dataBase.getNumber("rotationAngle", 0.0);
    	System.out.println(distance);
    	System.out.println(horizontalDistance);
    	System.out.println(rotationalAngle);
    }

    @Override
    public void disabledInit() {
        // Tell Strongback that the robot is disabled so it can flush and kill commands.
        Strongback.disable();
    }

    
    
}

