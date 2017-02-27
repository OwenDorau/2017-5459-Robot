package org.frc5459.robot;

import edu.wpi.first.wpilibj.Timer;

import org.frc5459.robot.Drive5459.currentGear;
import org.strongback.Strongback;
import org.strongback.command.Command;
import org.strongback.components.ui.Gamepad;




public class TeleopDriveCommand extends Command{


	private double c = 432.809;
	private double exponet = 0;
	private double rightPower;
	private double leftPower;
	private Drive5459 drive;
	private Gamepad driver;
	private boolean shifting;
	private boolean shiftingUp;
	private boolean isAtCorrectSpeed;
	double inchPerSec;
	
	public TeleopDriveCommand(Drive5459 drive, Gamepad driver) {
		this.drive = drive;
		this.driver = driver;
		this.drive.setDriverEnabled(true);
	}
	
	
	@Override
	public boolean execute(){
		drive.setSpeedLeft(driver.getLeftY().read());
		drive.setSpeedRight(driver.getRightY().read());
		return true;
	}
	
}
//TODO: add comments