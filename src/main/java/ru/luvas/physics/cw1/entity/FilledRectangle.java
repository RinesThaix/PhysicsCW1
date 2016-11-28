package ru.luvas.physics.cw1.entity;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author 0xC0deBabe <iam@kostya.sexy>
 */
public class FilledRectangle extends ColoredSizable {
    
    public FilledRectangle(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

}
