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
	private long currentTime;
	private long elapsedTime;
	private long startCountLeft;
	private long endCountLeft;
	private long startCountRight;
	private long endCountRight;
	private long deltaRight;;
	private long deltaLeft;
	private long deltaCount;
	private long displacement;
	double inchPerSec;
	
	public TeleopDriveCommand(Drive5459 drive, Gamepad driver) {
		this.drive = drive;
		this.driver = driver;
		this.drive.setDriverEnabled(true);
	}
	
	//Run this in a sepreate thread
	@Override
	public boolean execute(){
		if (drive.isDriverEnabled()) {
			if (!shifting) {
				drive.setSpeedLeft(-driver.getLeftY().read());
				drive.setSpeedRight(driver.getRightY().read());
				currentTime = System.currentTimeMillis();
				startCountLeft = (long)drive.leftEncoderValue();
				startCountRight = (long)drive.rightEncoderValue();
				for (int i = 0; i < 2; i++) {
					Timer.delay(0.01);
				}
				endCountLeft = (long)drive.leftEncoderValue();
				endCountRight = (long)drive.rightEncoderValue();
				elapsedTime = System.currentTimeMillis() - currentTime;
				deltaLeft = endCountLeft - startCountLeft;
				deltaRight = endCountRight - startCountRight;
				deltaCount = (deltaLeft + deltaRight)/2;
				elapsedTime = elapsedTime*1000;
				displacement = (long)(deltaCount/375.95);
				inchPerSec = displacement/elapsedTime;
				if ((inchPerSec > 90) && (drive.getCurrentGear().equals(currentGear.LOWGEAR))) {
					shifting = true;
					shiftingUp = true;
				}else if (inchPerSec < 70 && drive.getCurrentGear().equals(currentGear.HIGHGEAR) ){
					shifting = true;
					shiftingUp = false;
				}
			}else {
				if (((driver.getLeftY().read() > 0 && driver.getRightY().read() > 0) || (driver.getLeftY().read() < 0 && driver.getRightY().read() < 0))) {
					if(shiftingUp){
						drive.setSpeedLeft(drive.getLeftPower()*0.2);
						drive.setSpeedRight(drive.getRightPower()*0.2);
						isAtCorrectSpeed = false;
						Strongback.submit(new ShiftUpCommand(drive));
						Timer.delay(0.04);
						currentTime = System.currentTimeMillis();
						while(!isAtCorrectSpeed){
							elapsedTime = System.currentTimeMillis() - currentTime;
							elapsedTime = -elapsedTime;
							exponet = elapsedTime/c;
							rightPower = driver.getRightY().read();
							rightPower = rightPower - Math.pow(Math.E, exponet);
							leftPower = driver.getLeftY().read();
							leftPower = leftPower - Math.pow(Math.E, exponet);
							drive.setSpeedLeft(leftPower);
							drive.setSpeedRight(rightPower);
							if (drive.getLeftPower() > driver.getLeftY().read()-0.1 || drive.getRightPower() > driver.getRightY().read() - 0.1) {
								isAtCorrectSpeed = true;
							}
						}
					}else {
						drive.setSpeedLeft(drive.getLeftPower()*0.2);
						drive.setSpeedRight(drive.getRightPower()*0.2);
						isAtCorrectSpeed = false;
						Strongback.submit(new ShiftDownCommand(drive));
						Timer.delay(0.04);
						currentTime = System.currentTimeMillis();
						while(!isAtCorrectSpeed){
							elapsedTime = System.currentTimeMillis() - currentTime;
							elapsedTime = -elapsedTime;
							exponet = elapsedTime/c;
							rightPower = driver.getRightY().read();
							rightPower = rightPower - Math.pow(Math.E, exponet);
							leftPower = driver.getLeftY().read();
							leftPower = leftPower - Math.pow(Math.E, exponet);
							drive.setSpeedLeft(leftPower);
							drive.setSpeedRight(rightPower);
							if (drive.getLeftPower() > driver.getLeftY().read()-0.1 || drive.getRightPower() > driver.getRightY().read() - 0.1) {
								isAtCorrectSpeed = true;
							}
						}
					}
					
				}
			shifting = false;
			}
		}
		
		return false;
	}
	
}
//TODO: add comments