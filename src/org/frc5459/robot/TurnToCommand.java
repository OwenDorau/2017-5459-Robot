package org.frc5459.robot;

import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

import org.strongback.command.Command;

import oracle.jrockit.jfr.ProducerDescriptor;

public class TurnToCommand extends Command 
implements Consumer<Double> {

	Drive5459 drive;
	DoubleSupplier targetTurn;
	double trueTurnThis;
	double b;
	double c;
	Drive5459 rightController;
	DoubleConsumer turnThis; //implemented later
	public TurnToCommand(){
		this.targetTurn = null;
		this.drive = null;
	}
	
	public TurnToCommand(DoubleSupplier targetTurn){
		this.targetTurn = targetTurn;
		this.drive = drive;
		trueTurnThis = turnThis;s
		
	}
	public boolean execute(){
		drive.setSpeedRight(trueTurnThis);
		
		
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
		*/
		if(a != b){
			return false;
		}else{
			return true;
		}
		
		
	}

	@Override
	public void accept(Double t) {
		// TODO Auto-generated method stub
		
	}
}