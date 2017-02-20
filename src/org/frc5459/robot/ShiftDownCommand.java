package org.frc5459.robot;

//when retracted in high gear
//puts in high gear
import org.strongback.command.Command;
import org.strongback.components.ui.Gamepad;

import edu.wpi.first.wpilibj.Timer;

public class ShiftDownCommand extends Command {
	
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
	
	public ShiftDownCommand(){
		this.drive = null;
	}
	public ShiftDownCommand(Drive5459 drive, Gamepad driver){
		this.drive = drive;
		this.driver = driver;
		this.ticks = 0;
	}
	@Override
	public boolean execute(){
		drive.extend();
		return true;
		
	}
}
