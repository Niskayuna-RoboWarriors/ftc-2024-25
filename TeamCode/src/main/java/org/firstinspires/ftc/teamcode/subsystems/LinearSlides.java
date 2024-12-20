package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Basically a wrapper for DcMotor
 * if we have more than 1 slide, just inherit this and make a new constructor
 * which calls the constructor of this class
 * (this is getting significantly more complicated than I expected)
 */
public class LinearSlides extends Subsystem {

    // states
    public static final int RETRACTED = 0;
    public static final int LOW_BUCKET = 5000;
    public static final int HIGH_BUCKET = 10000;
    public static final int LOW_RUNG = 3000;
    public static final int HIGH_RUNG = 6000;
    // end of states

    protected DcMotorEx motor;
    protected int MAX = 20000, MIN = 0;
    protected double MAX_VELOCITY = 250;
    protected int desiredPosition, currentPosition;
    protected double desiredVelocity, currentVelocity;
    protected boolean isVelocityControl = false;

    /**
     * initialize basic things on motor
     * @param _motor the motor to initialize
     */
    protected LinearSlides(DcMotorEx _motor) {
        motor = _motor;

        motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        motor.setDirection(DcMotorEx.Direction.FORWARD);
        motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        //motor.setPIDFCoefficients(
        //        DcMotor.RunMode.RUN_TO_POSITION,
        //        new PIDFCoefficients(0, 0, 0, 0)
        //);

        //motor.setTargetPosition(0);
        //motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        //motor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

    }

    /**
     * initialize single motor
     * @param hardwareMap hardware map to grab motor named "slides"
     */
    public LinearSlides(HardwareMap hardwareMap) {
        // gets the motor from hardware map
        this(hardwareMap.get(DcMotorEx.class, "slides"));
    }
    public void setPosition(int position) {
        position = Range.clip(position, MIN, MAX);
        desiredPosition = position;
        isVelocityControl = false;
    }

    public void setVelocity(double velocity) {
        velocity = Range.clip(velocity, -1.0, 1.0);
        desiredVelocity = Range.scale(velocity, -1.0, 1.0, -MAX_VELOCITY, MAX_VELOCITY);
        isVelocityControl = true;
    }


    @Override
    protected void update() {
        currentPosition = motor.getCurrentPosition();
        currentVelocity = motor.getVelocity();

        if (isVelocityControl) {
            motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
            double velocity = desiredVelocity;
            if (currentPosition > MAX) {
                velocity = Range.clip(currentVelocity, -MAX_VELOCITY, 0);
            } else if (currentPosition < MIN) {
                velocity = Range.clip(currentVelocity, 0, MAX_VELOCITY);
            }
            motor.setVelocity(velocity);
        } else {
            motor.setTargetPosition(desiredPosition);
            motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        }
    }

    @Override
    protected void telemetry(Telemetry telemetry) {
        telemetry.addData("Slides:", "Position: Min %d Current %d Max %d Velocity: %.2f", MIN, currentPosition, MAX, currentVelocity);
        if (isVelocityControl) {
            telemetry.addData("Slides: Velocity Control: Desired", desiredVelocity);
        } else {
            telemetry.addData("Slides: Position Control: Desired", desiredPosition);
        }
    }
}