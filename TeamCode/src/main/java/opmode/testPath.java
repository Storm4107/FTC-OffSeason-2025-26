package opmode;

import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.pedropathing.follower.Follower;
import com.pedropathing.pathgen.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import config.core.Robot;
import config.core.util.Alliance;
import config.core.util.OpModeCommand;
import com.pedropathing.commands.FollowPath;

@Autonomous(name = "testPath")
public class testPath extends OpModeCommand{
    Robot r;

    @Override
    public void initialize() {
        r = new Robot(hardwareMap, telemetry, Alliance.BLUE, config.core.paths.testPath.start);

        schedule(
                new RunCommand(r::aPeriodic),
                new SequentialCommandGroup(
                        new FollowPath(r.getF(), config.core.paths.testPath.move1()))
        );
    }
}