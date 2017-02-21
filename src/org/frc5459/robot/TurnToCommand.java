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
	double inputTargetTurn;
	double trueTargetTurn;
	double trueTurnThisLeft;
	double trueTurnThisRight;
	double trueTurnThis;
	Drive5459 rightController;
	DoubleConsumer turnThis; 
	double currentRotation;
	public TurnToCommand(){
		this.targetTurn = null;
		this.drive = null;
	}
	
	public TurnToCommand(DoubleSupplier targetTurn, DoubleConsumer turnThis){
		this.targetTurn = targetTurn;
		this.drive = drive;
		this.turnThis = turnThis; 
		currentRotation = drive.imuY();
		trueTurnThisLeft = trueTurnThis * -1;
		trueTurnThisRight = trueTurnThis;
		trueTargetTurn = targetTurn.getAsDouble();
		
		
<<<<<<< HEAD
		
	}
	
	public void setInputTargetTurn(){
		//return inputTargetTurn.setValue();
		
	}
=======
	   
	}
	
>>>>>>> 0fd35784eb3a2aca00809df4c9c9c88e3ab63735
	
	public boolean execute(){
		drive.setSpeedRight(trueTurnThisRight);
		drive.setSpeedLeft(trueTurnThisLeft); //inverted above
		
		
		
		if(currentRotation !=  trueTargetTurn){ //needs converted targetTurn!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			return false;
		}else{
			return true;
		}

		
		
	}

	@Override 
	public void accept(Double trueturnThis) {
		

	}
	
	
}