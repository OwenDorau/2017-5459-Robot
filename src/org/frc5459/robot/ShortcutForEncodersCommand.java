package org.frc5459.robot;

import org.strongback.command.Command;

public class ShortcutForEncodersCommand extends Command {

	
	Drive5459 drive;
	double leftTarget;
	double rightTarget;
	double target;
	
	public ShortcutForEncodersCommand(Drive5459 drive, double target) {
		this.drive = drive;
		this.target = target * 325.9493234522016;
		this.leftTarget = (drive.leftEncoderValue() + target) * 325.9493234522016;
		this.rightTarget = (drive.rightEncoderValue() + target) * 325.9493234522016;
	}
	
	@Override
	public boolean execute() {
		
		if (leftTarget > drive.leftEncoderValue()) {
			drive.setSpeedLeft((leftTarget - drive.leftEncoderValue()) * 0.1);
		}else {
			drive.setSpeedLeft((drive.leftEncoderValue() - leftTarget) * 0.1);
		}
		
		if (rightTarget > drive.rightEncoderValue()) {
			drive.setSpeedRight((rightTarget - drive.rightEncoderValue()) * 0.1);
		}else {
			drive.setSpeedRight((drive.rightEncoderValue() - rightTarget) * 0.1);
		}
		
		if (Math.abs(leftTarget - drive.leftEncoderValue()) < 20 || Math.abs(rightTarget - drive.rightEncoderValue()) < 20) {
			return true;
		}else {
			return false;
		}
	}

}
