package org.firstinspires.ftc.teamcode.util;

public class Position {
    double x, y, rotation;

    public Position(double _x, double _y, double _rotation) {
        x = _x;
        y = _y;
        rotation = _rotation;
    }

    public Position getDifference(Position pos) {
        return new Position(x-pos.x, y-pos.y, rotation-pos.rotation);
    }
}