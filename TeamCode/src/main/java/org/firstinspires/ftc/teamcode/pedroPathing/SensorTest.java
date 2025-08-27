package org.firstinspires.ftc.teamcode.pedroPathing;

import android.hardware.Sensor;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.TouchSensor;

@Autonomous(name = "SensorTest")
public class SensorTest extends OpMode {

    private DcMotorEx auxMotor;

    private TouchSensor TouchSensor;

    @Override
    public void init() {


        auxMotor = hardwareMap.get(DcMotorEx.class, "auxMotor");
        TouchSensor = hardwareMap.get(TouchSensor.class, "TouchSensor");

        auxMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    }

    @Override
    public void loop() {
        if (TouchSensor.isPressed()) {
            auxMotor.setPower(0);
        }   else {
            auxMotor.setPower(.5);
        }

    }

}
