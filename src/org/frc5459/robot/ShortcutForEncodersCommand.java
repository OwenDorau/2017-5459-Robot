package org.frc5459.robot;

import org.strongback.command.Command;

public class ShortcutForEncodersCommand extends Command {

	
	Drive5459 drive;
	double leftTarget;
	double rightTarget;
	double target;
	private boolean kill = false;
	
	public ShortcutForEncodersCommand(Drive5459 drive, double target) {
		this.drive = drive;
		this.target = target * 325.9493234522016;
		this.drive.setLeftEncoderValue(0);
		this.drive.setRightEncoderValue(0);
//		this.leftTarget = drive.leftEncoderValue() + this.target;
//		this.rightTarget = drive.rightEncoderValue() + this.target;
	}
	
	@Override
	public boolean execute() {
		
		drive.setSpeedLeft((target - drive.leftEncoderValue()) * 0.00001);
		drive.setSpeedRight((target - drive.rightEncoderValue()) * 0.00001);
		
		if (Math.abs(this.target - drive.leftEncoderValue()) < 80 || Math.abs(this.target - drive.rightEncoderValue()) < 80) {
			drive.setDriverEnabled(true);
			drive.setSpeedRight(0);
			drive.setSpeedLeft(0);
			this.kill  = true;
		}
		if(this.kill){
			drive.setDriverEnabled(true);
			return true;
		
		}else {
			drive.setDriverEnabled(false);
			return false;
		}
	}

}
