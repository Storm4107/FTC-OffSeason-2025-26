package config.core.util;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public abstract class OpModeCommand extends OpMode {

    /**
     * Cancels all previous commands
     */
    public void reset() {
        CommandScheduler.getInstance().reset();
    }

    /**
     * Runs the {@Link Command Scheduler} instance
     */
    public void run() {
        CommandScheduler.getInstance().run();
    }

    /**
     * Schedules {@Link com.arcrobotics.ftclib.command.Command} objects to the scheduler
     */
    public void schedule(Command... commands) {
        CommandScheduler.getInstance().schedule(commands);
    }

    /**
     * Schedules {@Link com.arcrobotics.ftclib.command.Subsystem} objects to the scheduler
     */
    public void register(Subsystem... subsystems) {
        CommandScheduler.getInstance().registerSubsystem(subsystems);
    }

    @Override
    public void init() {
        initialize();
    }

    @Override
    public void loop() {
        run();
    }

    @Override
    public void stop() {
        reset();
    }

    public abstract void initialize();
}
