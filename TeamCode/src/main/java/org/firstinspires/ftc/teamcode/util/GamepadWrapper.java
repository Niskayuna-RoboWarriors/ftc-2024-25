package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.Gamepad;

// wrapper for gamepad to remove some boiler code
// trying to make a lighter wrapper for simplicity
public class GamepadWrapper extends Gamepad{
    // all the possible buttons
    public enum Buttons {
        LEFT_BUMPER, RIGHT_BUMPER,
        DPAD_UP, DPAD_DOWN, DPAD_RIGHT, DPAD_LEFT,
        B, X, Y, A
    }
    public Gamepad current = new Gamepad();
    public Gamepad previous = new Gamepad();
    Gamepad gamepad;

    private final float ANALOG_DEAD_ZONE = 0.001f;

    private boolean isInvertedY = false;

    /**
     * initialize with gamepad
     * @param gamepad the gamepad
     */
    public GamepadWrapper(Gamepad gamepad) {
        this.gamepad = gamepad;
        this.update();
    }

    public GamepadWrapper(Gamepad gamepad, boolean isInvertedY) {
        this(gamepad);
        this.isInvertedY = isInvertedY;
    }

    /**
     * updates previous
     */
    public synchronized void update() { // synchronized cuz intellij got mad at me
        previous.copy(current);
        current.copy(this.gamepad);

        this.copy(this.gamepad);

        if (isInvertedY) {
            this.left_stick_y = -this.left_stick_y;
            this.right_stick_y = -this.right_stick_y;
        }
        if (Math.abs(this.left_trigger) < ANALOG_DEAD_ZONE) this.left_trigger = 0.0f;
        if (Math.abs(this.right_trigger) < ANALOG_DEAD_ZONE) this.right_trigger = 0.0f;
        if (Math.abs(this.left_stick_x) < ANALOG_DEAD_ZONE) this.left_stick_x = 0.0f;
        if (Math.abs(this.left_stick_y) < ANALOG_DEAD_ZONE) this.left_stick_y = 0.0f;
        if (Math.abs(this.right_stick_y) < ANALOG_DEAD_ZONE) this.right_stick_x = 0.0f;
        if (Math.abs(this.right_stick_x) < ANALOG_DEAD_ZONE) this.right_stick_y = 0.0f;
    }


    /**
     * returns true if a button that was not pressed in prev but is now pressed
      * @param button the button being fetched
     * @return if button was pressed
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
        }
        return false;
    }
}