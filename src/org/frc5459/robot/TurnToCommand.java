package org.frc5459.robot;

import org.strongback.command.Command;

public class TurnToCommand extends Command{

	Drive5459 drive;
	double targetTurn;
	double a;
	double b;
	double c;
	Drive5459 rightController;
	
	public TurnToCommand(){
		this.targetTurn = 0;
		this.drive = null;
	}
	
	public TurnToCommand(double targetTurn){
		this.targetTurn = targetTurn;
		this.drive = drive;
		
		
	}
	public boolean execute(){
		
		
		
		/*
		b = targetTurn;
		a = drive.imuY(); //needs current rotation
		c = a - b; //above
		
		
		
		if(a != b){
			if (c >= 90){
				//where left is put
				drive.setSpeedRight(1.0);
				drive.setSpeedLeft(-1.0);
			}else{
				//where right is put
				drive.setSpeedRight(-1.0);
				drive.setSpeedLeft(1.0);
			}
		}
		if(a != b){
			return false;
		}else{
			return true;
		}
		*/
		
	}
}