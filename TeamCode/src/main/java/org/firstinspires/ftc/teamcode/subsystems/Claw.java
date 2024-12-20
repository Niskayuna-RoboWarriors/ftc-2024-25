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
        PwmControl pwm = (PwmControl) claw;
        pwm.setPwmEnable();
        claw.setDirection(FORWARD);
    }


    protected void openClaw(){
        claw.setPosition(claw.MAX_POSITION);
    }
    protected void closeClaw(){
        claw.setPosition(claw.MIN_POSITION);
    }
    protected void increaseClawRotation(){

        clawRotator.setPosition(clawRotator.getPosition()+1);

    }
    protected void decreaseClawRotation(){

        clawRotator.setPosition(clawRotator.getPosition()-1);

    }

    protected void onRecieveInput(){
        desiredPosition = tempTestInput;
    }

    @Override
    protected void update() {

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
                System.out.println("Error in update statement or input alreadu fulfilled: invalid input recieved");
        }
    }

    @Override
    protected void telemetry(Telemetry telemetry) {

    }
}
