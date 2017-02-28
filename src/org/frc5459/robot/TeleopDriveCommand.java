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
	private boolean arcade;
	
	public TeleopDriveCommand(Drive5459 drive, Gamepad driver, boolean arcade) {
		this.drive = drive;
		this.driver = driver;
		this.drive.setDriverEnabled(true);
		this.arcade = arcade;
	}
	
	
	@Override
	public boolean execute(){
		if (arcade) {
			drive.arcadeDrive(driver.getRightY().read()*0.5, driver.getLeftY().read()*0.5);
		}else {
			drive.arcadeDrive(driver.getRightY().read(), driver.getLeftY().read());
		}
		return true;
	}
	
}
//TODO: add comments