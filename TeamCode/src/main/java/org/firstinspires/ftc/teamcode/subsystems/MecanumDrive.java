package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
// import com.qualcomm.robotcore.util.Range;

// basically replacement for navigation class
public class MecanumDrive {
    protected DcMotor frontLeft, frontRight, rearLeft, rearRight;

    /**
     * Initializes the drive system
     * @param hardwareMap used to grab hardware components
     */
    public MecanumDrive(HardwareMap hardwareMap) {
        // gets the motors from hardware map
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        rearLeft = hardwareMap.get(DcMotor.class, "rearLeft");
        rearRight = hardwareMap.get(DcMotor.class, "rearRight");

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
