package org.frc5459.robot;

import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import org.junit.experimental.theories.Theories;
import org.strongback.DataRecorder;
import org.strongback.control.SoftwarePIDController;
import org.strongback.control.SoftwarePIDController.SourceType;

public class TurnToPIDCommand{
	
SoftwarePIDController turnToPid;
DataRecorder recorder;
TurnToCommand turnToCommand;

	public TurnToPIDCommand(){
	this.turnToPid = new SoftwarePIDController(SourceType.DISTANCE, turnToCommand.targetTurn, turnToCommand.turnThis);    
	
	turnToPid.withGains(5, 5, 0); //needs testing
	turnToPid.withInputRange(-1, 1); //needs testing
	turnToPid.withOutputRange(-1, 1);
	turnToPid.withTolerance(1);
	recorder.register("turnToPid", turnToPid.basicChannels());
	turnToPid.useProfile(2);
	}
	
	
}