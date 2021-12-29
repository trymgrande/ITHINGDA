import lejos.hardware.motor.*;
import lejos.hardware.lcd.*;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.NXTTouchSensor;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.hardware.sensor.NXTColorSensor;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.hardware.port.Port;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.hardware.sensor.*;
import lejos.hardware.Keys;

public class rally{
    public static void main (String[] args) throws Exception
    {

        Brick brick = BrickFinder.getDefault();
        Port s1 = brick.getPort("S1"); // fargesensor

        EV3 ev3 = (EV3) BrickFinder.getLocal();
        TextLCD lcd = ev3.getTextLCD();
        Keys keys = ev3.getKeys();


        /* Definerer en fargesensor og fargeAvleser */
        EV3ColorSensor fargesensor = new EV3ColorSensor(s1); // ev3-fargesensor
        SampleProvider fargeLeser = fargesensor.getMode("RGB");  // svart = 0.01..
        //SampleProvider fargeLeser = fargesensor.getColorIDMode();  //
        float[] fargeSample = new float[fargeLeser.sampleSize()];  // tabell som innholder avlest verdi


        //Motor.A.setSpeed(200); //hastighet = 200
        //Motor.B.setSpeed(200);  //HAstighet = 200

        //while (fortsett){
        fargeLeser.fetchSample(fargeSample, 0);
        if(fargeSample[0]>=0.01 && fargeSample[0]<=0.025){   // sjekk sort linje

            Motor.A.setSpeed(200); //hastighet = 200
            Motor.B.setSpeed(200);


            if(fargeSample[0]<=0.01 || fargeSample[0]>=0.025){ //sejkk om det ikke er på svart linje




