package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.subsystems.LinearSlides;
import org.firstinspires.ftc.teamcode.util.GamepadWrapper;

//@Disabled // avoid clutter in menu
/**
 * NO MORE WORKING AROUND ROBOT MANAGER TO TEST STUFF, bro this feels so nice
 */
@TeleOp(name="Servo Test", group="Testing")
public class ServoTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        GamepadWrapper operator = new GamepadWrapper(gamepad2);

        Servo servo = hardwareMap.get(Servo.class, "servo");

        telemetry.addLine("working");
        telemetry.update();

        waitForStart();

        ElapsedTime elapsedTime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        int count = 0;
        double position = servo.getPosition();

        while(opModeIsActive()) {
            count++;
            telemetry.addData("Time", "%.2f ms %.2f Hz", elapsedTime.milliseconds(), (double)count/(elapsedTime.milliseconds()/1000.0));

            operator.update();

            if (operator.isPressed(GamepadWrapper.Buttons.DPAD_UP)) {
                position += 0.01;
            }

            if (operator.isPressed(GamepadWrapper.Buttons.DPAD_RIGHT)) {
                position += 0.001;
            }

            if (operator.isPressed(GamepadWrapper.Buttons.DPAD_DOWN)) {
                position -= 0.01;
            }

            if (operator.isPressed(GamepadWrapper.Buttons.DPAD_LEFT)) {
                position -= 0.001;
            }

            servo.setPosition(position);

            telemetry.addData("Desired", servo.getPosition());

            telemetry.update();
        }
    }
}
