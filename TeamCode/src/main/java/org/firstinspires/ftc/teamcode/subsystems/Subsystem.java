package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class Subsystem {
    public static boolean globalSubSystemTelemetry = true;
    public boolean componentTelemetry = false;
    /**
     * Update() is intended to be called every loop iteration regardless of desired state change
     */
    protected abstract void update();

    /**
     * telemetry uses telemetry to give information on this subsystem
     */
    protected abstract void telemetry(Telemetry telemetry);

    /**
     * all updates are forced to go through this method
     */
    public final void update(Telemetry telemetry) {
        update();
        if (globalSubSystemTelemetry || componentTelemetry) {
            telemetry(telemetry);
        }
    }
}
