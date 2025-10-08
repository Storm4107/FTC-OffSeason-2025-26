package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "TurnTest")
public class TurnTest extends LinearOpMode {

    private DcMotorEx Motor;

    private static final int TICKS_PER_REV = 1120;
    private static final int TARGET_TICKS = TICKS_PER_REV / 3;

    @Override
    public void runOpMode() {
        Motor = hardwareMap.get(DcMotorEx.class, "Motor");
        Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            if (Motor.getMode() == DcMotor.RunMode.RUN_TO_POSITION && Motor.isBusy()) {

                telemetry.addData("Status", "Executing Turn...");
                telemetry.addData("Target Pos", Motor.getTargetPosition());
                telemetry.addData("Current Pos", Motor.getCurrentPosition());

            } else {
                if (Motor.getMode() == DcMotor.RunMode.RUN_TO_POSITION);
            }
        }

        while (opModeIsActive()) {
            if (gamepad1.a) {
                int newTarget = Motor.getCurrentPosition() + TARGET_TICKS;

                Motor.setTargetPosition(newTarget);
                Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Motor.setPower(0.1);

                while (opModeIsActive() && Motor.isBusy()) {
                    telemetry.addData("Target", TARGET_TICKS);
                    telemetry.addData("Current Position", Motor.getCurrentPosition());
                    telemetry.update();
                }

                Motor.setPower(0);
                Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                while (opModeIsActive() && gamepad1.a) {
                    idle();
                }
            }

            telemetry.addData("Motor Pos", Motor.getCurrentPosition());
            telemetry.update();
        }
    }
}
