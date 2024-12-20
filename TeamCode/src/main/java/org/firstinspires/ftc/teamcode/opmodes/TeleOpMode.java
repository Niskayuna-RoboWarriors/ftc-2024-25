package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.LinearSlides;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;
import org.firstinspires.ftc.teamcode.util.GamepadWrapper;

import java.util.List;

@TeleOp(name="Tele-Op", group="TeleOp OpMode")
public class TeleOpMode extends OpMode {
    protected ElapsedTime elapsedTime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    protected GamepadWrapper driver, operator;
    protected MecanumDrive drive;
    protected LinearSlides slides;
    protected int count = 0;
    //List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);

    /**
     * called once when init button is pressed
     */
    @Override
    public void init() {
        telemetry.addLine("Initializing");


        // every time you look up a sensor value or something, it will send a hardware call
        // hardware calls take a couple milliseconds (slow) but will add up if called many times over a loop
        // bulk read reads ALL the sensor values at once with a single hardware call and caches the values
        // this is the same cost as reading a single sensor value, so it helps performance
        // bulk read is OFF by default, AUTO automatically updates when you read the same sensor value twice
        // MANUAL only updates when clearBulkCache is manually invoked, *USE MANUAL WITH CAUTION*
        //for (LynxModule hub : allHubs) {
        //    hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        //}

        driver = new GamepadWrapper(gamepad1);
        operator = new GamepadWrapper(gamepad2);

        Subsystem.globalSubsystemTelemetry = true;
        drive = new MecanumDrive(hardwareMap);
        slides = new LinearSlides(hardwareMap);

        // init some other things
        telemetry.addLine("Initialized");
    }

    /**
     * called once when play button is pressed
     */
    @Override
    public void start() {
        telemetry.addLine("starting");
    }

    /**
     * repeatedly called after start runs
     */
    @Override
    public void loop() {
        // telemetry.addData(String caption, Object value), simple call for 1 data values

        // telemetry.addData(String caption, String format, Object... args), multiple data values
        // in format string, %.1f is a float displayed with 1 decimal place. %d is used for an integers, etc.
        // In "Object..." the "..." is known as varargs, it allows a function to take any number of parameters

        // telemetry.addLine(String s), this is how you just write a line with a string.
        // It's not telemetry.addData("words", null); that's just dumb.

        telemetry.addData("Time", "%.2f ms %.2f Hz", elapsedTime.milliseconds(), (double)count/(elapsedTime.milliseconds()/1000.0));

        // perform bulk read
        //for (LynxModule hub : allHubs) {
        //    hub.clearBulkCache();
        //}


        // update gamepad wrappers
        driver.update();
        operator.update();

        // read controller inputs

        // random function to control slides for now, just for testing purposes
        slides.setVelocity(operator.left_stick_y);

        slides.update(telemetry);

        // drive stuff

        // To whoever is reading this, here's a mini-lesson on the Math class
        // Math.atan2(double, double) is like the tan^-1, but it automatically adjusts for quadrants, so no adjustments needed
        double strafeDirection = Math.atan2(driver.left_stick_y,driver.left_stick_x);
        // Math.hypot(double, double) returns the hypotenuse length
        double strafePower = Math.hypot(driver.left_stick_y,driver.left_stick_x);
        double turn = driver.right_stick_x;
        drive.moveIn(strafeDirection, strafePower, turn);

        telemetry.addData("Moving:", "in %.1f rad at %.1f, turning %.1f", strafeDirection, strafePower, turn);

        telemetry.update();
    }
}
