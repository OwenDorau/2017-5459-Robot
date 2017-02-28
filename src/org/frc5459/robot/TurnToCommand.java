package org.frc5459.robot;

import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

import org.strongback.command.Command;



public class TurnToCommand extends Command {

	Drive5459 drive;
	
	Drive5459 rightController;
	DoubleConsumer turnThis; 
	double currentRotation;
	TurnToPIDCommand toPIDCommand;
	DoubleConsumer  b;
	DoubleSupplier a;
	public TurnToCommand(){
		this.drive = null;
	}
	
	public TurnToCommand(double turn, Drive5459 drive){
		this.drive = drive;
		this.a = () -> {return (turn + drive.imuY()) ;} ;
		this.b = (x) -> {drive.setSpeedRight(x); drive.setSpeedLeft(-1*x); } ;
		this.toPIDCommand = new TurnToPIDCommand(a,b);
				  
	}
	

	
	public boolean execute(){
			
		
		if(toPIDCommand.isWithinTolerance()){ //needs converted targetTurn!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			return false;
		}else{
			return true;
		}

		
		
	}

	
	
	public boolean isWithinTolerance(){
		
		return toPIDCommand.isWithinTolerance();
		
	}
	
	
}