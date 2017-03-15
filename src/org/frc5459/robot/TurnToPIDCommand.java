package org.frc5459.robot;


import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;


import org.strongback.DataRecorder;
import org.strongback.control.SoftwarePIDController;
import org.strongback.control.SoftwarePIDController.SourceType;

public class TurnToPIDCommand{
	
SoftwarePIDController turnToPid;
DataRecorder recorder;



TurnToCommand turnToCommand;

	public TurnToPIDCommand(DoubleSupplier targetTurn, DoubleConsumer turnThis){

			
	
	

	this.turnToPid = new SoftwarePIDController(SourceType.DISTANCE,
			targetTurn, 
			turnThis);    

//	/** 
//	 *  controller = new SoftwarePIDController(model::sourceType, model::getActualValue, model::setValue)
//                                                                                                     .withGains(0.9,
//                                                                                                                    0.0,
//                                                                                                                    0.0)
//                                                                                                         .withInputRange(-1.0,
//                                                                                                                         1.0)
//                                                                                                         .withOutputRange(-1.0,
//                                                                                                                          1.0)
//                                                                                                         .withTolerance(0.02)
//                                                                                                         .withTarget(0.5);
//	 */

	turnToPid.withGains(5, 5, 0); //needs testing
	turnToPid.withInputRange(-1, 1); //needs testing
	turnToPid.withOutputRange(-1, 1); //needs testing
	turnToPid.withTolerance(1); //needs testing
	//recorder.register("turnToPid", turnToPid.errorChannels());
	//turnToPid.useProfile(2);
	

	}
	public boolean isWithinTolerance(){
	
		return turnToPid.isWithinTolerance();
		
	}
}
	


	/**
	 * private static class SystemModel {
        protected final DoubleBiFunction model;
        protected final SourceType sourceType;
        protected boolean print = false;

        public SystemModel(SourceType sourceType, DoubleBiFunction model) {
            this.model = model;
            this.sourceType = sourceType;
        }

        protected double actualValue = 0;

        public  SourceType sourceType() {
            return sourceType;
        }

        public  double getActualValue() {
            return actualValue;
        }

        public  void setValue(double input) {
            double newValue = model.applyAsDouble(actualValue, input);
            if (print) System.out.println("actual=" + actualValue + "; input=" + input + "; newValue=" + newValue);
            this.actualValue = newValue;
        }
    }

    public static  SystemModel simple() {
        return simple(SourceType.DISTANCE);
    }

	 */

	
