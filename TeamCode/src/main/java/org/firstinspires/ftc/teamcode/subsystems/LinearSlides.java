package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Basically a wrapper for DcMotor
 * if we have more than 1 slide, just inherit this and make a new constructor
 * which calls the constructor of this class
 */
public class LinearSlides {
    protected DcMotor motor;
    protected int MAX = Integer.MAX_VALUE, MIN = Integer.MIN_VALUE;

    /**
     * initialize basic things on motor
     * @param _motor the motor to initialize
     */
    protected LinearSlides(DcMotor _motor) {
        motor = _motor;
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setDirection(DcMotor.Direction.FORWARD);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /**
     * initialize single motor
     * @param hardwareMap hardware map to grab motor named "slides"
     */
    public LinearSlides(HardwareMap hardwareMap) {
        // gets the motor from hardware map
        this(hardwareMap.get(DcMotor.class, "slides"));
        // MAX = something
        // MIN = something
    }
    public void moveTo(int position) {
        position = Math.min(position, MAX);
        position = Math.max(position, MIN);
        motor.setTargetPosition(position);
    }

    public int getTargetPosition() {
        return motor.getTargetPosition();
    }
    public int getPosition() {
        return motor.getCurrentPosition();
    }
}