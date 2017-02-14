package org.frc5459.robot;

import org.strongback.command.Command;

public class GoToEncoderValueCommand extends Command {

	Drive5459 drive;
	double targetDistance;
	double distance;
	double encoderGlobalVariable;
	double trueTargetDistance;
	
	public GoToEncoderValueCommand(){
		this.targetDistance = 0;
		this.drive = null;
	}
	public GoToEncoderValueCommand(double targetDistance){
		this.targetDistance = targetDistance;
		this.drive = drive;
	}
	public boolean execute(){
		targetDistance = encoderGlobalVariable;
//		distance = Drive5459.getEncoderValue();
		trueTargetDistance = targetDistance * 325.9493234522016;
		if(trueTargetDistance != distance){
			drive.setSpeedRight(1.0);
			drive.setSpeedLeft(1.0);
		}
		
		if(trueTargetDistance == distance){
			return true;
		}else{
			return false;
		}
		
	}
	
}
