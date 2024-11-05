package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.LinearSlides;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDrive;
import org.firstinspires.ftc.teamcode.util.GamepadWrapper;

@TeleOp(name="Tele-Op", group="TeleOp OpMode")
public class TeleOpMode extends OpMode {
    protected ElapsedTime elapsedTime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    protected GamepadWrapper driver, operator;
    protected MecanumDrive drive;
    protected LinearSlides slides;

    /**
     * called once when init button is pressed
     */
    @Override
    public void init() {
        telemetry.log().add("initializing"); // idk if log actually works, guess we'll see.
        driver = new GamepadWrapper(gamepad1);
        operator = new GamepadWrapper(gamepad2);
        drive = new MecanumDrive(hardwareMap);
        slides = new LinearSlides(hardwareMap);

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
        // telemetry.addData(String caption, Object value), simple call for 1 data values

        // telemetry.addData(String caption, String format, Object... args), multiple data values

        // the format string takes %.1f to mean a floating point value displayed with 1 digit past decimal point
        // %d is used for an integer, there's more, you can search for them
        // In "Object..." the "..." is known as varargs, it allows a function to take any number of parameters
        // It's basically just a shortcut for an array though, so nothing special

        // update gamepad wrappers
        driver.update();
        operator.update();

        // read controller inputs

        // random function to control slides for now, just for testing purposes
        if (operator.isPressed(GamepadWrapper.Buttons.DPAD_UP)) {
            slides.moveTo(slides.getPosition()+20);
        }
        if (operator.isPressed(GamepadWrapper.Buttons.DPAD_DOWN)) {
            slides.moveTo(slides.getPosition()-20);
        }
        telemetry.addData("Linear Slides ", "%d target %d", slides.getPosition(), slides.getTargetPosition());

        // drive stuff

        // To whoever is reading this, here's a mini-lesson on the Math class
        // Math.atan2(double, double) is like the tan^-1, but it automatically adjusts for quadrants, so no adjustments needed
        double strafeDirection = Math.atan2(driver.left_stick_y,driver.left_stick_x);
        // Math.hypot(double, double) returns the hypotenuse length
        double strafePower = Math.hypot(driver.left_stick_y,driver.left_stick_x);
        double turn = driver.right_stick_x;
        drive.moveIn(strafeDirection, strafePower, turn);

        telemetry.addData("Moving ", "in %.1f rad at %.1f, turning %.1f", strafeDirection, strafePower, turn);

        telemetry.update();
    }
}
