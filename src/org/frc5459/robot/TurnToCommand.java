package org.frc5459.robot;

import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

import org.strongback.command.Command;

import oracle.jrockit.jfr.ProducerDescriptor;

public class TurnToCommand extends Command 
implements Consumer<Double> {

	Drive5459 drive;
	double inputTargetTurn;
	double trueTargetTurn;
	double trueTurnThisLeft;
	double trueTurnThisRight;
	double trueTurnThis;
	double First;
	Drive5459 rightController;
	DoubleConsumer turnThis; 
	double currentRotation;
	TurnToPIDCommand toPIDCommand;
	public TurnToCommand(){
		this.drive = null;
	}
	
	public TurnToCommand(double turn){
		this.drive = drive;
		DoubleSupplier a = () -> {return (turn + drive.imuY()) ;} ;
		DoubleConsumer  b = (x) -> First = x  ;
		this.toPIDCommand = new TurnToPIDCommand(a,b);
		this.turnThis = turnThis; 
		currentRotation = drive.imuY();
		trueTurnThisLeft = trueTurnThis * -1;
		trueTurnThisRight = trueTurnThis;
		trueTargetTurn = a.getAsDouble();   
	}
	

	
	public boolean execute(){
		drive.setSpeedRight(trueTurnThisRight);
		drive.setSpeedLeft(trueTurnThisLeft); //inverted above
		
		
		
		if(toPIDCommand.isWithinTolerance()){ //needs converted targetTurn!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			return false;
		}else{
			return true;
		}

		
		
	}

	@Override 
	public void accept(Double trueturnThis) {
		


	}
	public boolean isWithinTolerance(){
		
		return toPIDCommand.isWithinTolerance();
		
	}
	
	
}