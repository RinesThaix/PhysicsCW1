package ru.luvas.physics.cw1.entity;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author 0xC0deBabe <iam@kostya.sexy>
 */
public class FilledOval extends ColoredSizable {
    
    public FilledOval(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }
    
    @Override
    public void draw(Graphics2D g) {
        g.fillOval(x, y, width, height);
    }

}