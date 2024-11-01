package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
// import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.util.Position;


// basically replacement for navigation class (not really**)
public class MecanumDrive {
    protected DcMotor frontLeft, frontRight, rearLeft, rearRight;
    protected static final double MINIMUM_POWER = 0.001; // defines the point at which robot will stop trying to move

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
        // mecanum has basically no ability to resist movement. So I don't see why you would ever want brake
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

    /**
     * tells robot what direction to move in, and how much to do so
     * @param strafeDirection direction of movement
     * @param strafePower amount of movement
     * @param turnPower amount of rotation
     */
    public void moveIn(double strafeDirection, double strafePower, double turnPower) {
        // allows robot to stop if it's close enough to 0
        if (strafePower < MINIMUM_POWER) strafePower = 0;
        if (turnPower < MINIMUM_POWER) turnPower = 0;

        // one set of 2 wheels produce force in northwest and one set of 2 wheels produce a force in northeast
        // assuming robot forward is north and the motors are spinning forwards
        // you can do some math to get the vector pointing to strafe_direction in this basis which gives you the following
        double powerSet1 = Math.sin(strafeDirection) + Math.cos(strafeDirection);
        double powerSet2 = Math.sin(strafeDirection) - Math.cos(strafeDirection);

        // this process is probably improvable
        double frontRightPower = powerSet2 * strafePower - turnPower;
        double rearLeftPower = powerSet2 * strafePower + turnPower;

        double rearRightPower = powerSet1 * strafePower - turnPower;
        double frontLeftPower = powerSet1 * strafePower + turnPower;

        double scale = Math.max(Math.max(Math.abs(frontRightPower), Math.abs(frontLeftPower)), Math.max(Math.abs(rearRightPower), Math.abs(rearLeftPower)));

        frontRightPower /= scale;
        rearRightPower /= scale;
        frontLeftPower /= scale;
        rearLeftPower /= scale;

        frontRight.setPower(frontRightPower);
        rearRight.setPower(rearRightPower);
        frontLeft.setPower(frontLeftPower);
        rearLeft.setPower(rearLeftPower);
    }

}