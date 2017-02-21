package org.frc5459.robot;

import org.strongback.command.Command;
import org.strongback.components.Motor;
import org.strongback.control.TalonController;

import com.ctre.CANTalon;

public class StopClimbCommand extends Command{
	private CANTalon ClimberMotor;
	
	public StopClimbCommand(CANTalon ClimberMotor){
		this.ClimberMotor = ClimberMotor;

	}

	@Override
	public boolean execute(){
		ClimberMotor.set(0.0);
		return true;
	}
}

