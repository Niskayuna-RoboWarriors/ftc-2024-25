package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.MecanumDrive;
import org.firstinspires.ftc.teamcode.util.GamepadWrapper;

@TeleOp(name="Tele-Op", group="TeleOp OpMode")
public class TeleOpMode extends OpMode {
    protected ElapsedTime elapsedTime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    protected GamepadWrapper driver, operator;
    protected MecanumDrive drive;

    /**
     * called once when init button is pressed
     */
    @Override
    public void init() {
        telemetry.log().add("initializing"); // idk if log actually works, guess we'll see.
        driver = new GamepadWrapper(gamepad1);
        operator = new GamepadWrapper(gamepad2);
        drive = new MecanumDrive(hardwareMap);

        // init some other things
        telemetry.log().add("initialized");
    }

    /**
     * called once when play button is pressed
     */
    @Override
    public void start() {
        telemetry.log().add("starting");
    }

    /**
     * repeatedly called after start runs
     */
    @Override
    public void loop() {
        // use telemetry to output things

        // read controller inputs

        // drive stuff

        telemetry.update();
    }
}
