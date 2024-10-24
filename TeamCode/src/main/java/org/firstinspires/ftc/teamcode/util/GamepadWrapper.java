package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.Gamepad;

// wrapper for gamepad to remove some boiler code
// trying to make a lighter wrapper for simplicity
public class GamepadWrapper {
    // all the possible buttons
    public enum Buttons {
        LEFT_BUMPER, RIGHT_BUMPER,
        DPAD_UP, DPAD_DOWN, DPAD_RIGHT, DPAD_LEFT,
        B, X, Y, A
    }
    Gamepad current, previous =  new Gamepad();

    /**
     * initialize with gamepad
     * @param gamepad
     */
    public GamepadWrapper(Gamepad gamepad) {
        this.current = gamepad;
        this.update();
    }

    /**
     * updates previous
     */
    public void update() {
        previous.copy(this.current);
    }


    /**
     * returns true if a button that was not pressed in prev but is now pressed
      * @param button
     * @return
     */
    public boolean isPressed(Buttons button) {
        switch (button) {
            case LEFT_BUMPER:
                return current.left_bumper && !previous.left_bumper;
            case RIGHT_BUMPER:
                return current.right_bumper && !previous.left_bumper;
            case DPAD_UP:
                return current.dpad_up && !previous.dpad_up;
            case DPAD_DOWN:
                return current.dpad_down && !previous.dpad_down;
            case DPAD_RIGHT:
                return current.dpad_right && !previous.dpad_right;
            case DPAD_LEFT:
                return current.dpad_left && !previous.dpad_left;
            case A:
                return current.a && !previous.a || current.cross && !previous.cross;
            case B:
                return current.b && !previous.b || current.circle && !previous.circle;
            case X:
                return current.x && !previous.x || current.square && !previous.square;
            case Y:
                return current.y && !previous.y || current.triangle && !previous.triangle;
            default:
                throw new Error("requested button does not exist");
        }
    }
}