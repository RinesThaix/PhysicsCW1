package ru.luvas.physics.cw1.entity;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author 0xC0deBabe <iam@kostya.sexy>
 */
public class SemiCircle extends Colored {
    
    private int radius;
    private boolean left;

    public SemiCircle(int x, int y, int radius, boolean left, Color color) {
        super(x, y, color);
        this.radius = radius;
        this.left = left;
    }
    
    public int getRadius() {
        return radius;
    }
    
    public boolean isLeft() {
        return left;
    }
    
    public void setRadius(int radius) {
        this.radius = radius;
        updated();
    }
    
    public void setIsLeft(boolean left) {
        this.left = left;
        updated();
    }
    
    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        g.drawArc(x, y, radius, radius, left ? 90 : -90, 180);
    }

}
