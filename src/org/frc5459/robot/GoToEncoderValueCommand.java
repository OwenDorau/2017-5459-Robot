 package org.frc5459.robot;

import org.strongback.command.Command;

public class GoToEncoderValueCommand extends Command {

	Drive5459 drive;
	double targetDistance;
	double leftDistance;
	double rightDistance;
	
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
    	leftDistance = drive.leftEncoderValue();
    	rightDistance = drive.rightEncoderValue();
		trueTargetDistance = targetDistance * 325.9493234522016; //end up in steps
		drive.setEncoderTargetAngleLeft(trueTargetDistance);
		drive.setEncoderTargetAngleRight(trueTargetDistance);
		
		
		return true;
		
	}
	
}
