package ru.luvas.physics.cw1.entity;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author 0xC0deBabe <iam@kostya.sexy>
 */
public class Line extends Colored {
    
    private int x2, y2;

    public Line(int x, int y, int x2, int y2, Color color) {
        super(x, y, color);
        this.x2 = x2;
        this.y2 = y2;
    }
    
    public int getX2() {
        return x2;
    }
    
    public int getY2() {
        return y2;
    }
    
    public void setX2(int x2) {
        this.x2 = x2;
        updated();
    }
    
    public void setY2(int y2) {
        this.y2 = y2;
        updated();
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        g.drawLine(x, y, x2, y2);
    }

}
