package org.frc5459.robot;

import org.strongback.command.Command;
import org.strongback.components.DistanceSensor;


import edu.wpi.first.wpilibj.Ultrasonic;

public class AutoGearLoadCommand extends Command {
	private DistanceSensor ultraX;
	private Ultrasonic ultraY;
	private double ultraXDistance;
	private Drive5459 drive;
	
	
	public AutoGearLoadCommand(DistanceSensor ultraX, Ultrasonic ultraY, Drive5459 drive){
		this.ultraX =  ultraX;
		this.ultraY = ultraY;
		this.drive = drive;
	}
	

	
	@Override
	public boolean execute(){
		
		return false;
	}
}
