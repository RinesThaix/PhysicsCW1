package ru.luvas.physics.cw1.entity;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import ru.luvas.physics.cw1.Launcher;

/**
 *
 * @author 0xC0deBabe <iam@kostya.sexy>
 */
public class GradientRectangle extends ColoredSizable {
    
    private final Color color2;
    
    public GradientRectangle(int x, int y, int width, int height, Color color1, Color color2) {
        super(x, y, width, height, color1);
        this.color2 = color2;
    }
    
    public Color getColor2() {
        return color2;
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        GradientPaint gp = new GradientPaint(x, y, getColor(), x, y + height, getColor2());
        g.setPaint(gp);
        g.fill(new java.awt.Rectangle(x, y, width, height));
    }

}
