package org.frc5459.robot;

import org.strongback.Strongback;
import org.strongback.command.Command;

public class AutoGearLoadCommand extends Command {
	private double ultraXDistance;
	private double ultraYDistance;
	private Drive5459 drive;
	private TurnToCommand TurnTo;
	
	
	public AutoGearLoadCommand(Drive5459 drive, TurnToCommand TurnTo){
		this.drive = drive;
		this.TurnTo = TurnTo;
		this.ultraXDistance = drive.getUltrasonicX();
	}
	
	@Override
	public boolean execute(){
		Strongback.submit(TurnTo);
		drive.setSpeedRight(-1.0);
		drive.setSpeedLeft(-1.0);
		if(drive.getUltrasonicX() - (1.2) > ultraXDistance || ultraXDistance < drive.getUltrasonicX() - (1.2)){
			drive.setSpeedRight(0.0);
			drive.setSpeedLeft(0.0);
		}
		//TurnTo = new TurnToCommand(45);
		Strongback.submit(TurnTo);
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
