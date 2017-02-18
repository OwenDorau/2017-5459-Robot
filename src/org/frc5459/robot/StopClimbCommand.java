package org.frc5459.robot;

import org.strongback.command.Command;
import org.strongback.components.Motor;
import org.strongback.control.TalonController;

public class StopClimbCommand extends Command{
	private TalonController ClimberMotor;
	
	public StopClimbCommand(TalonController ClimberMotor){
		this.ClimberMotor = ClimberMotor;

	}

	@Override
	public boolean execute(){
		ClimberMotor.stop();
		return true;
	}
}

