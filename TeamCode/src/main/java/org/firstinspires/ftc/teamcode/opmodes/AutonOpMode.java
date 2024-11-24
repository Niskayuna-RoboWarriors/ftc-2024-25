package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.MecanumDrive;
import org.firstinspires.ftc.teamcode.util.GamepadWrapper;

@Autonomous(name="Autonomous", group="Robot")
public class AutonOpMode extends LinearOpMode{
    public enum StartingSide {NET, OBSERVATION};
    private static StartingSide startingSide; // Initialize this somehow
    @Override
    public void runOpMode() throws InterruptedException {
        // initialize stuff
        MecanumDrive drive = new MecanumDrive(hardwareMap);

        waitForStart();
        // run stuff

        // do stuff
        // auton
    }
}
