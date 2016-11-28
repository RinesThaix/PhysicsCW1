package ru.luvas.physics.cw1.entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

/**
 *
 * @author 0xC0deBabe <iam@kostya.sexy>
 */
public abstract class Colored extends Entity {
    
    protected Color color;
    protected Stroke stroke = new BasicStroke(1f);

    Colored(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }
    
    public Color getColor() {
        return color;
    }
    
    public Stroke getStroke() {
        return stroke;
    }
    
    public void setColor(Color color) {
        this.color = color;
        updated();
    }
    
    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
        updated();
    }
    
    public void setStroke(int width) {
        this.setStroke(new BasicStroke(width));
    }
    
    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.setStroke(stroke);
    }

}
