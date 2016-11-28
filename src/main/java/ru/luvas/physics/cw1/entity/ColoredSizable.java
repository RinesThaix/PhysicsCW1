package ru.luvas.physics.cw1.entity;

import java.awt.Color;

/**
 *
 * @author 0xC0deBabe <iam@kostya.sexy>
 */
public abstract class ColoredSizable extends Colored {
    
    protected int width, height;

    ColoredSizable(int x, int y, int width, int height, Color color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setWidth(int width) {
        this.width = width;
        updated();
    }
    
    public void setHeight(int height) {
        this.height = height;
        updated();
    }

}
