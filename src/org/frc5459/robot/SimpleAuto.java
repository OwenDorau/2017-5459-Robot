package org.frc5459.robot;

import org.strongback.command.Command;

public class SimpleAuto extends Command {

	private Drive5459 drive;
	private int ticks;
	
	public SimpleAuto(Drive5459 drive) {
		this.drive = drive;
		this.ticks = 0;
	}
	@Override
	public boolean execute() {
		drive.setSpeedLeft(0.5);
		drive.setSpeedRight(0.5);
		ticks++;
		if(ticks <= 100){
			return false;
		}else {
			return true;
		}
		
	}

}
