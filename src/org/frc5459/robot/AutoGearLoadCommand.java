package org.frc5459.robot;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

import org.strongback.Strongback;
import org.strongback.command.Command;

public class AutoGearLoadCommand extends Command {
	private double ultraXDistance;
	private double ultraSonicX;
	private double deltaUltraXDistance;
	private double ultraYDistance;
	private Drive5459 drive;
	private TurnToCommand targetTurn;
	private boolean first = false;
	private boolean second = false;
	private boolean third =false;
	DoubleSupplier DoubleSupplier;
	
	public AutoGearLoadCommand(Drive5459 drive, TurnToCommand targetTurn){
		this.drive = drive;
		this.targetTurn = targetTurn;
		this.ultraXDistance = drive.getUltrasonicX();
	}
	double First;
	double Second ;
	@Override
	public boolean execute(){
		DoubleSupplier FirstTargetAngle= () -> {return (0) ;} ;
		DoubleConsumer FirstTurnThis  = (x) -> First = x  ;
		
		targetTurn = new TurnToCommand (FirstTargetAngle , FirstTurnThis);
		drive.setSpeedRight(-1.0);
		drive.setSpeedLeft(-1.0);
		first = true;
		if(first = true){
			ultraSonicX = drive.getUltrasonicX();
			deltaUltraXDistance = ultraSonicX - ultraXDistance;
			if(deltaUltraXDistance > (1.2)){
				drive.setSpeedRight(0.0);
				drive.setSpeedLeft(0.0);
				second = true;
			}
		}
		DoubleSupplier SecondTargetAngle= () -> {return (45) ;} ;
		DoubleConsumer SecondTurnThis  = (x) -> Second = x  ;
		
		targetTurn = new TurnToCommand (SecondTargetAngle , SecondTurnThis);
		if(second = true){
			Strongback.submit(targetTurn);
			ultraYDistance = drive.getUltrasonicY();
			second = false;
			drive.setSpeedRight(-1.0);
			drive.setSpeedLeft(-1.0);
			if(ultraYDistance == (9.0)){
				drive.setSpeedRight(0.0);
				drive.setSpeedLeft(0.0);
			}
		}	
		return false;
	}
}
