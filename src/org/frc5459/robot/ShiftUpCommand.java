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
		drive.retract();
		return true;
	}
}
