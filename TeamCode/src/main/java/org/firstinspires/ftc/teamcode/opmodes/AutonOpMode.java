package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.firstinspires.ftc.teamcode.subsystems.MecanumDrive;
import org.firstinspires.ftc.teamcode.util.GamepadWrapper;

@Autonomous(name="Autonomous", group="Robot")
public class AutonOpMode extends LinearOpMode {
    public enum StartingSide {NET, OBSERVATION};
    private static StartingSide startingSide;
    private static SharedPreferences sharedPrefs;

    private ElapsedTime elapsedTime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

    @Override
    public void runOpMode() throws InterruptedException {
        // initialize stuff
        MecanumDrive drive = new MecanumDrive(hardwareMap);

        initSharedPreferences();

        waitForStart();
        // Should we try to score on the baskets or the chambers?
        while (opModeIsActive()) {
            if (startingSide == StartingSide.NET) { // Plan: just drop a sample into the net zone
                /* Drive forward, do we want a dedicated navigation file? This sucks and we need to test it anyways.
                   It would be better if we had one function that could move the robot anywhere.
                */
                elapsedTime.reset();
                while (elapsedTime.time() < 500) {
                    MecanumDrive.moveIn(0, 20, 0);
                }
                MecanumDrive.moveIn(0, 0, 0); // This is how I assume it works.

                Claw. // TODO: drop
            } else { // Should we try bringing the samples to the oberservation zone?

            }
        }
        // do stuff
        // auton
    }

    // Get shared preferences
    public void initSharedPreferences() {
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.hardwareMap.appContext);

        startingSidePref = sharedPrefs.getString("starting_side", "ERROR");
        telemetry.addData("Starting side", startingSidePref);
        if (startingSidePref.equals("NET")) {
            startingSide = StartingSide.NET;
        } else {
            startingSide = StartingSide.OBSERVATION;
        }

        telemetry.update();
    }
}
