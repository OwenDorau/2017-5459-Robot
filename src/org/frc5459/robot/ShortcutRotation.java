package org.frc5459.robot;

import org.strongback.command.Command;

public class ShortcutRotation extends Command {

	private Drive5459 drive;
	private double target;
	private double turnRight;
	private double rotation;
	
	public ShortcutRotation(Drive5459 drive, double target) {
		this.drive = drive;
		this.target = target;
		this.rotation = drive.imuY();
	}
	
	@Override
	public boolean execute() {
		rotation = drive.imuY();
		
		if (target > 0) {
			drive.setSpeedRight((target) * 0.1);
			drive.setSpeedLeft((target) * -0.1);
			
			
		}else {
			drive.setSpeedRight((target) * 0.1);
			drive.setSpeedLeft((target) * -0.1);
		}
		
		
		target = target - (int)(rotation - drive.imuY()); 
		
		if (Math.abs(target) < 10) {
			return false;
		}else {
			return true;
		}
	}
	
}
