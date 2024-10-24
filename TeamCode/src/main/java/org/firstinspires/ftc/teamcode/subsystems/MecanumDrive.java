package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
// import com.qualcomm.robotcore.util.Range;

// basically replacement for navigation class
public class MecanumDrive {
    protected DcMotor frontLeft, frontRight, rearLeft, rearRight;

    // initialize motors given motors
    public MecanumDrive(DcMotor frontLeft, DcMotor frontRight, DcMotor rearLeft, DcMotor rearRight) {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.rearLeft = rearLeft;
        this.rearRight = rearRight;

        // do some other init stuff
        // some of the motors may spin in the wrong direction, so this is to account for stuff like that
    }

    // initialize motors given hardware map
    public MecanumDrive(HardwareMap hardwareMap) {
        this.frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        this.frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        this.rearLeft = hardwareMap.get(DcMotor.class, "rearLeft");
        this.rearRight = hardwareMap.get(DcMotor.class, "rearRight");

        // do some other init stuff
        // some of the motors may spin in the wrong direction, so this is to account for stuff like that
    }

    // write code to actually do stuff

}
