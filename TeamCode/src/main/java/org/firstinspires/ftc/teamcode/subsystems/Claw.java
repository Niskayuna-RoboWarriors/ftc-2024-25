package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.Servo;
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//pwm
//import org.firstinpsires.ftc.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import static com.qualcomm.robotcore.hardware.Servo.Direction.FORWARD;



/**
 * To be implemented
 */
public abstract class Claw extends Subsystem {
    Servo claw;
    Servo clawRotator;
    protected int desiredPosition;
    private int tempTestInput;

    public void initClaw(HardwareMap hardwareMap) {
        claw = hardwareMap.get(Servo.class, "claw servo");
        // claw.setDirection(FORWARD);
    }


    protected void openClaw(){
        claw.setPosition(claw.MAX_POSITION);
    }
    protected void closeClaw(){
        claw.setPosition(claw.MIN_POSITION);
    }
    protected void increaseClawRotation() { // i don't recommend

        clawRotator.setPosition(clawRotator.getPosition()+1);

    }
    protected void decreaseClawRotation(){

        clawRotator.setPosition(clawRotator.getPosition()-1);

    }

    protected void onRecieveInput(){ // what does this functino do?
        desiredPosition = tempTestInput;
    }

    @Override
    protected void update() { // tbh just leave the update function blank
/*
        switch (desiredPosition){
            case 1:
                openClaw();
                desiredPosition = 5;
            case 2:
                closeClaw();
                desiredPosition = 5;
            case 3:
                increaseClawRotation();
                desiredPosition = 5;
            case 4:
                decreaseClawRotation();
                desiredPosition = 5;
            default:
                System.out.println("Error in update statement or input already fulfilled: invalid input recieved");
                // don't use println
                // telemetry if you want to show anything for output
                // i would just throw an exception/error if something goes wrong
        }
*/
    }

    @Override
    protected void telemetry(Telemetry telemetry) {
        telemetry.addData("Claw Position", claw.getPosition());
        telemetry.addData("Claw Rotator Pos", clawRotator.getPosition());
    }
}
