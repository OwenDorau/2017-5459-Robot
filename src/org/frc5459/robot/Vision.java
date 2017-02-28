package org.frc5459.robot;

import org.strongback.Strongback;
import org.strongback.command.Command;
import java.lang.Math;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Vision extends Command{

	private NetworkTable dataBase;
	private Drive5459 drive;
	private double peg;
	private double d;
	private double x;
	private double y;
	private double theta;
	private double hyp;
	private double alpha;
	private double z;
	private double beta;
	private double w;
	private double e;
	private double disToDrive;
	private double angleToTurn;
	private Command turn;
	
	public Vision(NetworkTable dataBase, Drive5459 drive) {
		this.drive = drive;
		this.dataBase = dataBase;
		this.peg = 11;
		this.y = dataBase.getNumber("Distance");
		this.theta = dataBase.getNumber("angle");
		this.x = this.y/Math.tan(this.theta);
	}
	
	@Override
	public boolean execute() {
		if (y != dataBase.getNumber("Distance") || theta != dataBase.getNumber("angle")){
			y = dataBase.getNumber("Distance");
			theta = dataBase.getNumber("angle");
			x = y/Math.tan(theta);
		}
		
		hyp = Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2)));
		alpha = 90-theta;
		z = Math.sqrt((Math.pow(peg, 2) + Math.pow(hyp, 2) - 2*peg*hyp*Math.cos(alpha)));
		beta = Math.asin((peg*Math.sin(alpha))/z);
		w = 180 - beta;
		e = Math.sqrt((Math.pow(z, 2) + Math.pow(d, 2) - 2*z*d*Math.cos(w)));
		disToDrive = (e - d)*325.9493234522016;
		angleToTurn = Math.asin((z*Math.sin(w))/e);
		
		turn =  new TurnToCommand(angleToTurn,drive);
		Strongback.submit(turn);
		while(!turn.execute()) {
			Timer.delay(0.01);
		}
		drive.setEncoderTargetAngleLeft(disToDrive);
		drive.setEncoderTargetAngleRight(disToDrive);
		Timer.delay(0.05);
		if (y <= 11 && (x < 0.5 || x > -0.5) && theta < 2) {
			return true;
		} else {
			return false;
		}
		
	}
}
