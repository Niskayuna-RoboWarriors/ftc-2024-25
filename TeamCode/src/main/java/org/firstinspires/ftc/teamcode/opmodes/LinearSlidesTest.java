package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.subsystems.LinearSlides;
import org.firstinspires.ftc.teamcode.util.GamepadWrapper;

/**
 * NO MORE WORKING AROUND ROBOT MANAGER TO TEST STUFF, bro this feels so nice
 */
@TeleOp(name="Slides Test", group="Testing")
//@Disabled // avoid clutter in menu
public class LinearSlidesTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        LinearSlides slides = new LinearSlides(hardwareMap);
        GamepadWrapper operator = new GamepadWrapper(gamepad2, true);

        telemetry.addLine("working");
        telemetry.update();

        waitForStart();

        ElapsedTime elapsedTime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        int count = 0;

        while(opModeIsActive()) {
            count++;
            telemetry.addData("Time", "%.2f ms %.2f Hz", elapsedTime.milliseconds(), (double)count/(elapsedTime.milliseconds()/1000.0));

            operator.update();

            slides.setVelocity(operator.left_stick_y);

            slides.update(telemetry);

            telemetry.update();
        }
    }
}