package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "ProtoTeleOp")
public class ProtoTeleop extends LinearOpMode {

    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    private DcMotor Intake;
    private DcMotor Holder;
    private DcMotor Shooter;

    @Override
    public void runOpMode() {

        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        Intake = hardwareMap.get(DcMotor.class, "Intake");
        Holder = hardwareMap.get(DcMotor.class, "Holder");
        Shooter = hardwareMap.get(DcMotor.class, "Shooter");

        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        Shooter.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
            double y = gamepad1.left_stick_y;
            double x = -gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;

            double leftFrontPower = y + x + rx;
            double leftBackPower = y - x + rx;
            double rightFrontPower = y - x - rx;
            double rightBackPower = y + x - rx;

            leftFront.setPower(leftFrontPower);
            leftBack.setPower(leftBackPower);
            rightFront.setPower(rightFrontPower);
            rightBack.setPower(rightBackPower);


            if (gamepad1.left_bumper) {
                Intake.setPower(1);
            } else {
                Intake.setPower(0);
            }

            if (gamepad1.dpad_up) {
                Holder.setPower(.8);
            } else {
                Holder.setPower(0);
            }

            if (gamepad1.right_bumper) {
                Shooter.setPower(.5);
            } else {
                Shooter.setPower(0);
            }
        }
    }
}
