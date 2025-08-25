package config.vision;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.List;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LL3A {

    public static double limelightHeight = 8; //Camera height in inches
    public static double getLimelightAngle = 90; //Camera angle (0 faces down: 90 faces forward)

    private Pose spec = new Pose(), target = new Pose(); //the Specs location
    private Pose cachedTarget = new Pose(); //cached position
    private Limelight3A limelight;
    private PathChain toTarget;
    private LLResult result;
    private Telemetry telemetry;
    private Follower f;

    public LL3A(HardwareMap hardwareMap, Telemetry telemetry, Follower f) {
        this.telemetry = telemetry;
        this.f = f;

        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.setPollRateHz(100);
        limelight.pipelineSwitch(1);
        limelight.start();
        cachedTarget = f.getPose();
        f.update();
    }

    public void find() {
        result = limelight.getLatestResult();
        List<LLResultTypes.DetectorResult> detections = result.getDetectorResults();
    }

    public Pose getTarget() {
        return target;
    }

    public PathChain toTarget() {
        toTarget = new PathBuilder()
                .addPath(new BezierLine(f.getPose(), target))
                .setConstantHeadingInterpolation(f.getPose().getHeading())
                .build();
        return toTarget;
    }

    public void off() {
        limelight.stop();
    }

    public void on() {
        limelight.start();
    }
}
