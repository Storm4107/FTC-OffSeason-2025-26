package org.firstinspires.ftc.teamcode.pedroPathing;

import android.hardware.Sensor;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.TouchSensor;

import java.util.List;

@Autonomous(name = "AprilTagTest")
public class AprilTagTest extends LinearOpMode {

    private DcMotorEx Motor;
    private DcMotor frontRight, frontLeft, backLeft, backRight;
    private Limelight3A limelight;


    @Override
    public void runOpMode() throws InterruptedException {

        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        Motor = hardwareMap.get(DcMotorEx.class, " Motor");


        Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        limelight.start();


        while (opModeIsActive()) {

            LLResult result = limelight.getLatestResult();
            if (result != null) {
                for (LLResultTypes.FiducialResult f : result.getFiducialResults()) {
                    int id = f.getFiducialId();

                    if (id == 21) {
                        Motor.setPower(0.5);
                    } else if (id == 22) {
                        Motor.setPower(-0.5);
                    } else if (id == 23) {
                        Motor.setPower(0.2);
                    } else {
                        //do nothing
                    }
                }
            }
        }
    }

}
