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

    private DcMotorEx Motor;

    private TouchSensor TouchSensor;

    @Override
    public void init() {


        Motor = hardwareMap.get(DcMotorEx.class, " Motor");
        TouchSensor = hardwareMap.get(TouchSensor.class, "TouchSensor");

        Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    }

    @Override
    public void loop() {
        if (gamepad1.b)    {
            Motor.setPower(0);
        }   else {
            Motor.setPower(.5);
        }

    }

}
