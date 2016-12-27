package ru.luvas.physics.cw1.custom;

/**
 *
 * @author RINES <iam@kostya.sexy>
 */
public class Point {

    public final double x;
    public final double y;
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString() {
        return String.format("{%.5f|%.5f}", x, y);
    }
    
}
