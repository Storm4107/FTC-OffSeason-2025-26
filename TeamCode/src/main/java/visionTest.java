import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp(name = "vision Test")

public class visionTest extends LinearOpMode{

    private Limelight3A limelight;
    private DcMotor motor;
    private Gamepad gamepad;

    public void runOpMode(){
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        motor = hardwareMap.get(DcMotor.class, "motor");
        gamepad = gamepad1;

        limelight.start();
        limelight.setPollRateHz(100);

        waitForStart();

        while (opModeIsActive()) {

            LLResult result = limelight.getLatestResult();

            if (gamepad.a) {
                limelight.pipelineSwitch(0);
                if (result.isValid()) {
                    motor.setPower(1);
                } else {
                    motor.setPower(0);
                }
            }

            if (gamepad.b) {
                limelight.pipelineSwitch(1);
                if (result.isValid()) {
                    motor.setPower(-1);
                }
            } else {
                motor.setPower(0);
            }
        }
    }
}