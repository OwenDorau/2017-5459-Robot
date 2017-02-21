package org.frc5459.robot;

import org.strongback.command.Command;
import org.strongback.components.Motor;
import org.strongback.control.TalonController;

import com.ctre.CANTalon;


public class AscendClimbCommand extends Command{
	private CANTalon ClimberMotor;
	
	public AscendClimbCommand(CANTalon motor){
		this.ClimberMotor = motor;
	}

	@Override
	public boolean execute(){
		ClimberMotor.set(-1.0);
		return true;
	}
	
}

