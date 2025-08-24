package config.core.paths;

import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;

public class testPath {

    public static Pose start = new Pose(8, 108, Math.toRadians(0));
    //public static Pose startControlPoint = new Pose(70,90,Math.toRadians(0));
    public static Pose end = new Pose(35,20,Math.toRadians(0));

    public static PathChain move1() {
        return new PathBuilder()
                .addPath(
                        new BezierLine(
                                start, end
                        )
                )
                .setLinearHeadingInterpolation(start.getHeading(), end.getHeading())
                //.setZeroPowerAccelerationMultiplier(1)
                .build();
    }
}
