
/* Created Sat Jan 14 09:56:00 EST 2017 */
package org.frc5459.robot;



import org.fest.assertions.Assertions;


import static org.fest.assertions.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.Test;
import org.strongback.mock.Mock;

//import org.strongback.mock.MockGyroscope;
//import org.strongback.mock.MockSolenoid;


//import org.strongback.mock.MockMotor;
//import org.strongback.mock.MockSolenoid;


public class TestRobot {
	//private MockMotor motor;
	//for AscendClimbCommand
//    @Test
//    @Ignore
//    public void shouldAscendWhenAscending() {
//       motor = Mock.stoppedTalonSRX();
//       System.out.println("motor created");
//       AscendClimbCommand command = new AscendClimbCommand(motor);
//       System.out.println("Command Created");
//       double speed = motor.getSpeed();
//       //assertions
//       Assertions.assertThat(speed == 0.0).isTrue();
//       assertThat(command.execute()).isTrue();
//       speed = motor.getSpeed();
//       assertThat(speed == 1.0).isTrue();
//       motor.stop();
//       speed = motor.getSpeed();
//       assertThat(speed == 0.0).isTrue();
//       System.out.println("shouldAscendingWhenAscending passed");
//    }
//    //for StopClimbCommand
//    @Test
//    @Ignore
//    public void shouldStopWhenStopped(){
//    	motor = Mock.stoppedTalonSRX();
//        System.out.println("motor created");
//        StopClimbCommand command = new StopClimbCommand(motor);
//        System.out.println("Command Created");
//        double speed = motor.getSpeed();
//        //assertions
//        Assertions.assertThat(speed == 0.0).isTrue();
//        assertThat(command.execute()).isTrue();
//        assertThat(speed == 0.0).isTrue();
//        motor.stop();
//        assertThat(speed == 0.0).isTrue();
//        System.out.println("shouldStopWhenStopped passed");
//    }

//	@Ignore
//	@Test
//	public void shouldExtendWhenAlreadyExtended(){
//	    MockSolenoid solenoid = Mock.manualSolenoid().extend();
//	    System.out.println("Solenoid created");
//	    BucketExtendCommand command = new BucketExtendCommand(solenoid);
//	    System.out.println("Command created");
//	    assertThat(solenoid.isExtending()).isTrue();
//	    assertThat(solenoid.isRetracting()).isFalse();
//	    assertThat(command.execute()).isTrue();
//	    solenoid.stop();
//	    assertThat(solenoid.isStopped()).isTrue();
//	    System.out.println("shouldExtendWhenAlreadyExtended passed");
//	}
//	@Ignore
//	@Test
//	public void shouldExtendWhenRetracted(){
//        MockSolenoid solenoid = Mock.manualSolenoid().retract();
//        System.out.println("Solenoid created");
//        BucketExtendCommand command = new BucketExtendCommand(solenoid);
//        System.out.println("Command created");
//        assertThat(solenoid.isExtending()).isTrue();
//        assertThat(solenoid.isRetracting()).isFalse();
//        assertThat(command.execute()).isTrue();
//        solenoid.stop();
//        assertThat(solenoid.isStopped()).isTrue();   
//        System.out.println("shouldExtendWhenRetracted passed");
//	}
//	@Ignore
//	@Test
//	public void shouldRetractWhenAlreadyExtended(){
//	    MockSolenoid solenoid = Mock.manualSolenoid().extend();
//	    System.out.println("Solenoid created");
//	    BucketRetractCommand command = new BucketRetractCommand(solenoid);
//	    System.out.println("Command created");
//	    assertThat(command.execute()).isTrue();
//	    System.out.println(solenoid.isRetracting());
//	    assertThat(solenoid.isRetracting()).isTrue();
//	    assertThat(solenoid.isExtending()).isFalse();
//	    
//	    
//	    solenoid.stop();
//	    assertThat(solenoid.isStopped()).isTrue();
//	    System.out.println("shouldRetractWhenAlreadyExtended passed");
//	}
//	@Ignore
//	@Test
//	public void shouldRetractWhenRetracted(){
//        MockSolenoid solenoid = Mock.manualSolenoid().retract();
//        System.out.println("Solenoid created");
//        BucketRetractCommand command = new BucketRetractCommand(solenoid);
//        System.out.println("Command created");
//        solenoid.retract();
//        assertThat(command.execute()).isTrue();
//        System.out.println(solenoid.isRetracting());
//        assertThat(solenoid.isExtending()).isFalse();
//        assertThat(solenoid.isRetracting()).isTrue();
//        
//        solenoid.stop();
//        assertThat(solenoid.isStopped()).isTrue();   
//        System.out.println("shouldRetractWhenRetracted passed");
//	}
//	@Test
//	@Ignore
//	public void Drive5459test(){
//		MockGyroscope gyro;
//	//	MockTalonController right;
//	//	Drive5459 Test = new Drive5459();
//	}
    	
	
}

