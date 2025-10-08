package org.firstinspires.ftc.teamcode.pedroPathing;

import android.graphics.Color;
import android.hardware.Sensor;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.TouchSensor;

@Autonomous(name = "ColorTest")
public class SensorTest extends LinearOpMode {

    private DcMotorEx Motor;

    private TouchSensor TouchSensor;
    private RevColorSensorV3 colorSensorV3;

    @Override
    public void runOpMode() {


        Motor = hardwareMap.get(DcMotorEx.class, " Motor");
        TouchSensor = hardwareMap.get(TouchSensor.class, "TouchSensor");
        colorSensorV3 = hardwareMap.get(RevColorSensorV3.class, "ColorSensor");
        colorSensorV3.enableLed(true);

        Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            int r = colorSensorV3.red();
            int g = colorSensorV3.green();
            int b = colorSensorV3.blue();

            float[] hsv = new float[3];
            Color.RGBToHSV(r, g, b, hsv);

            float hue = hsv[0];
            float sat = hsv[1];
            float val = hsv[2];

            String detetedColor = "none";

            //green detection
            if (hue > 100 && hue <= 161 && sat > 0.6 && val > 1) {
                Motor.setPower(0);
                detetedColor = "GREEN";
            }
            //purple detection
            else if (hue >= 161.1 && hue < 235 && sat > 0.1 && val < 0.7) {
                Motor.setPower(.5);
                detetedColor = "PURPLE";
            } else {
                Motor.setPower(.5);
            }

            telemetry.addData("Deteced Color", detetedColor);
            telemetry.addData("R G B", "%d  %d  %d", r, g, b);
            telemetry.addData("Hue", "%.1f", hue);
            telemetry.addData("Sat", "%.2f", sat);
            telemetry.addData("Val", "%.2f", val);
            telemetry.update();
        }
    }

}
