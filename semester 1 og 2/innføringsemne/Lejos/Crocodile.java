/**
*
*Crocodile.java
*
*/

import lejos.hardware.motor.*;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.hardware.sensor.*;
import lejos.hardware.port.Port;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.Keys;
import lejos.hardware.sensor.SensorModes;
import lejos.hardware.lcd.*;

import java.util.Random;

class Crocodile{
    public static void main(String[] args)throws Exception{
	try{

    	Brick brick = BrickFinder.getDefault();
        Port s3 = brick.getPort("S3"); // ultrasonisksensor

        EV3 ev3 = (EV3) BrickFinder.getLocal();
	TextLCD lcd = ev3.getTextLCD();
	Keys keys = ev3.getKeys();

    	/* Definerer en ultrasonisksensor */
	EV3UltrasonicSensor ultraSensor = new EV3UltrasonicSensor(s3);
	SampleProvider ultraLeser = ultraSensor.getDistanceMode();
	float[] ultraSample = new float[ultraLeser.sampleSize()]; // tabell som inneholder avlest verdi

	Motor.A.setSpeed(450);  // sett hastighet (toppfart = 900)
	Motor.B.setSpeed(450);
	Motor.C.setSpeed(450); //crocodile mouth



	boolean fortsett = true;
	while(fortsett){
	    Motor.A.forward();  // Start motor A - kjør framover
	    Motor.B.forward();  // Start motor B - kjør framover
	    Thread.sleep(2000); // Vent i 1000 ms før vi går videre i koden

	    ultraLeser.fetchSample(ultraSample, 0);
	    lcd.drawString("Avstand: " + ultraSample[0], 0,5);

	    boolean hinder = false; //hinder ikke møtt

	    while((ultraSample[0]) <= 0.015){
	    	Motor.A.backward();
	    	Motor.B.backward();
	    	Motor.C.forward(); // åpne og lukke crocodile mouthm
	    	//System.out.println("avstand sensor: " + ultraSample[0]);
	    	Thread.sleep(2000);
	    	hinder = true; // hinder møtt
	    }
	    if(hinder == true){
	    	Random rand = new Random(); //genererer to tall
		int  n = rand.nextInt(2);
		if(n==0){
		    Motor.A.rotate(90);  // roter 90 gr med motor A
		    Motor.B.stop();
		    while (Motor.A.isMoving()) Thread.yield();  // vent til rotasjon er ferdig
		}else{
		    Motor.B.rotate(90);  // roter 90 gr med motor A
	    	    Motor.A.stop();
		    while (Motor.B.isMoving()) Thread.yield();  // vent til rotasjon er ferdig
		}
	    }
        }

	Motor.A.stop();  // stop motor A
	Motor.B.stop();  // stop motor B


    	}catch(Exception e){
    	    System.out.println("Feil: " + e);
    	    Thread.sleep(100000);

	} //try-catch
    }
}
