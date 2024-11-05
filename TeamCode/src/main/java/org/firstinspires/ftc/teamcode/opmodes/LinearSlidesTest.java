package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
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
        GamepadWrapper operator = new GamepadWrapper(gamepad2);

        waitForStart();

        while(opModeIsActive()) {

            if (operator.isPressed(GamepadWrapper.Buttons.DPAD_UP)) {
                slides.moveTo(slides.getPosition()+20);
            }
            if (operator.isPressed(GamepadWrapper.Buttons.DPAD_DOWN)) {
                slides.moveTo(slides.getPosition()-20);
            }

            telemetry.addData("Linear Slides ", "%d target %d", slides.getPosition(), slides.getTargetPosition());
        }
    }
}