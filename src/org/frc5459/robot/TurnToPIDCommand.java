package org.frc5459.robot;


import org.strongback.DataRecorder;
import org.strongback.control.SoftwarePIDController;
import org.strongback.control.SoftwarePIDController.SourceType;

public class TurnToPIDCommand {
	
SoftwarePIDController turnToPid;
DataRecorder recorder;

	public TurnToPIDCommand(){
		this.turnToPid = turnToPid;    
		turnToPid.withGains(5, 5, 0); //needs testing
		turnToPid.withInputRange(-1, 1); //needs testing
		turnToPid.withOutputRange(-1, 1);
		turnToPid.withTolerance(1);
		recorder.register("turnToPid", turnToPid.basicChannels());
	}
	
	
}