package config.core;

import static config.core.util.Opmode.*;

import androidx.annotation.NonNull;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import config.core.util.Alliance;
import config.core.util.Opmode;
import config.pedroPathing.constants.FConstants;
import config.pedroPathing.constants.LConstants;

public class Robot {
    private HardwareMap h;
    private Telemetry t;
    private Gamepad g1a, g2a, g1, g2, p1, p2;
    private Alliance a;
    private Follower f;
    private ManualInput m;
    private Opmode op = TELEOP;
    private boolean r = true;
    public static Pose autoEndPose = new Pose();
    public Pose s = new Pose();
    public Timer tTimer, sTimer, spec0Timer, spec180Timer, c0Timer, aFGTimer, aInitLoopTimer, sTTimer, fSATimer, sRTimer;
    private boolean aInitLoop, frontScore = false, backScore = true, automationActive = false;

    public Robot(HardwareMap h, Telemetry t, Alliance a, Pose startPose) {
        this.op = AUTONOMOUS;
        this.h = h;
        this.t = t;
        this.a = a;
        this.s = startPose.copy();

        f = new Follower(this.h, FConstants.class, LConstants.class);
        f.setStartingPose(startPose);

        m = new ManualInput(this.t, this.g2, 0, true);

        tTimer = new Timer();
        sTimer = new Timer();
        spec0Timer = new Timer();
        spec180Timer = new Timer();
        c0Timer = new Timer();
        aFGTimer = new Timer();
        aInitLoopTimer = new Timer();
        sTTimer = new Timer();
        fSATimer = new Timer();
        sRTimer = new Timer();

        aInitLoopTimer.resetTimer();
        aInitLoop = false;
        t.addData("s", s);
        t.addData("status", "not ready");
    }

    public void aPeriodic() {
        t.addData("path", f.getCurrentPath());

        f.update();
        t.update();
    }

    public void tPeriodic() {
        f.update();
        t.update();
    }

    public void stop() {
        autoEndPose = f.getPose();
    }

    public HardwareMap getH() {
        return h;
    }

    public Telemetry getT() {
        return t;
    }

    public Alliance getA() {
        return a;
    }

    public void setA(Alliance a) {
        this.a = a;
    }

    public Follower getF() {
        return f;
    }

    public ManualInput getM() {
        return m;
    }
}