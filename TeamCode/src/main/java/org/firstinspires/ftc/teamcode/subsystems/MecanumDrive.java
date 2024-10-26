package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
// import com.qualcomm.robotcore.util.Range;

// basically replacement for navigation class
public class MecanumDrive {
    protected DcMotor frontLeft, frontRight, rearLeft, rearRight;

    /**
     * Initializes the drive system using motors
     * @param _frontLeft front left motor
     * @param _frontRight front right motor
     * @param _rearLeft rear left motor
     * @param _rearRight rear right motor
     */
    public MecanumDrive(DcMotor _frontLeft, DcMotor _frontRight, DcMotor _rearLeft, DcMotor _rearRight) {
        // this method is probably not necessary, it would be easier to pass hardware map into everything
        // not completely sure why I made 2 constructors but why not I guess

        // underscore is used to make frontLeft (the object's variable) different from _frontLeft (the param)
        // alternatively, and maybe more semantically correct would be to use this.frontLeft = frontLeft
        // but I find this more convenient
        frontLeft = _frontLeft;
        frontRight = _frontRight;
        rearLeft = _rearLeft;
        rearRight = _rearRight;

        // if motor spins in the wrong direction then change to DcMotor.Direction.REVERSE
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        rearLeft.setDirection(DcMotor.Direction.FORWARD);
        rearRight.setDirection(DcMotor.Direction.FORWARD);

        // sets motors to spin freely // BRAKE will actively resist movement
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        // reset encoder counts by changing RunMode
        setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // run using encoder will try to set motors to a fraction of max speed
        setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * Initializes the drive system
     * @param hardwareMap used to grab hardware components
     */
    public MecanumDrive(HardwareMap hardwareMap) {
        // calls the other constructor using hardware map  to grab motors
        // the notation "this(params)" is used to call another constructor
        this(hardwareMap.get(DcMotor.class, "frontLeft"),
            hardwareMap.get(DcMotor.class, "frontRight"),
            hardwareMap.get(DcMotor.class, "rearLeft"),
            hardwareMap.get(DcMotor.class, "rearRight"));
    }

    /**
     * Changes all motors to the same runMode
     * @param runMode An enum defined under DcMotor.RunMode
     */
    private void setRunMode(DcMotor.RunMode runMode) {
        frontLeft.setMode(runMode);
        frontRight.setMode(runMode);
        rearLeft.setMode(runMode);
        rearRight.setMode(runMode);
    }

    // write code to actually do stuff

}
