import lejos.hardware.motor.*;
import lejos.hardware.lcd.*;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.hardware.port.Port;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.Keys;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.hardware.sensor.*;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.robotics.RangeFinder;


class Project{
	public static void main(String[] args) throws Exception
	{
		UltraSonicSensor ultrasensor = new UltraSonicSensor(s1);
		 Motor.A.setSpeed(450);
	     Motor.B.setSpeed(450);

	     Motor.A.backward();
		 Motor.B.backward();
        Thread.sleep(2000);

        Brick brick = BrickFinder.getDefault();
    	Port s1 = brick.getPort("S1");


    	EV3 ev3 = (EV3) BrickFinder.getLocal();
		TextLCD lcd = ev3.getTextLCD();
		Keys keys = ev3.getKeys();


        float range;
		//EV3UltrasonicSensor UltraSonicSensor = new EV3UltrasonicSensor(s1);

		System.out.println("Ultrasonic Demo");
		System.out.println("Press any key to start");
		Button.LEDPattern(4);                              //flash green led and
		Sound.beepSequenceUp();                           //make sound when ready.

		Button.waitForAnyPress();
		range = ultrasensor.getRange();
		Lcd.print(7, "range=");

		//run until we find an obstacle within 1/4 of a meter.

		while(range > .25){
			Lcd.clear(7, 7, 10);
			Lcd.print(7, 7, "%.3f", range);
			Delay.msDelay(500);

			Motor.C.setSpeed(200);
			boolean fortsett = true;

			while(fortsett){
				Motor.C.rotate();
				Thread.sleep(2000);
		    }
		    range = ultrasensor.getRange();
		}

			//free up resources;

			ultrasensor.close();
			Sound.beepSequence();
			Lcd.clear(7, 7, 10);
			Lcd.print(7, 7, "%.3f", range);
			Button.waitForAnyPress();
	}
}
