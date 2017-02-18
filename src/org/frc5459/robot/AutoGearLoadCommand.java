package org.frc5459.robot;

import org.strongback.command.Command;

public class AutoGearLoadCommand extends Command {
	private double ultraXDistance;
	private double ultraYDistance;
	private Drive5459 drive;
	private TurnToCommand TurnTo;
	
	
	public AutoGearLoadCommand(Drive5459 drive, TurnToCommand TurnTo){
		this.drive = drive;
		this.TurnTo = TurnTo;
	}
	
	@Override
	public boolean execute(){
		TurnTo.targetTurn = (0);
		ultraXDistance = drive.getUltrasonicX();
		drive.setSpeedRight(-1.0);
		drive.setSpeedLeft(-1.0);
		if(drive.getUltrasonicX() != ultraXDistance){
			drive.setSpeedRight(0.0);
			drive.setSpeedLeft(0.0);
		}
		TurnTo.targetTurn = (45);
		ultraYDistance = drive.getUltrasonicY();
		drive.setSpeedRight(-1.0);
		drive.setSpeedLeft(-1.0);
		if(ultraYDistance == (9.0)){
			drive.setSpeedRight(0.0);
			drive.setSpeedLeft(0.0);
		}	
		return false;
	}
}
