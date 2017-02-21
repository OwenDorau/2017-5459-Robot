package org.frc5459.robot;

import org.strongback.command.Command;
import org.strongback.components.Motor;
import org.strongback.control.TalonController;


public class AscendClimbCommand extends Command{
	private TalonController ClimberMotor;
	
	public AscendClimbCommand(TalonController motor){
		this.ClimberMotor = motor;
	}

	@Override
	public boolean execute(){
		ClimberMotor.setSpeed(-1.0);
		return true;
	}
	
}

