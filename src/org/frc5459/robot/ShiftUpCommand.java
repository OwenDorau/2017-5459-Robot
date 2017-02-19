package org.frc5459.robot;
import org.strongback.Strongback;
//when retracted in high gear
//puts in high gear
import org.strongback.command.Command;
import org.strongback.components.ui.Gamepad;

import edu.wpi.first.wpilibj.Timer;

public class ShiftUpCommand extends Command {
	
	Drive5459 drive;
	private boolean isAtCorrectSpeed;
	private long currentTime;
	private long elapsedTime;
	private double exponet;
	private double rightPower;
	private double leftPower;
	private Gamepad driver;
	private int ticks;
	private double c = 432.809;
	
	public ShiftUpCommand(){
		this.drive = null;
	}
	public ShiftUpCommand(Drive5459 drive, Gamepad driver){
		this.drive = drive;
		this.driver = driver;
		this.ticks = 0;
	}
	@Override
	public boolean execute(){
		if (ticks == 0) {
			drive.setSpeedLeft(drive.getLeftPower()*0.2);
			drive.setSpeedRight(drive.getRightPower()*0.2);
			isAtCorrectSpeed = false;
			drive.retract();
			Timer.delay(0.04);
			currentTime = System.currentTimeMillis();
		}else {
			elapsedTime = System.currentTimeMillis() - currentTime;
			elapsedTime = -elapsedTime;
			exponet = elapsedTime/c ;
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
		ticks++;
        if (isAtCorrectSpeed) {
        	drive.doneShifting = true;
			return true;
		}else {
			return false;
		}
	}
}
