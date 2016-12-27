package ru.luvas.physics.cw1.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author RINES <iam@kostya.sexy>
 */
public class Text extends Colored {
    
    private String text;
    private int size;

    public Text(int x, int y, String text, Color color, int size) {
        super(x, y, color);
        this.text = text;
        this.size = size;
    }
    
    public String getText() {
        return text;
    }
    
    public int getSize() {
        return size;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        g.setFont(new Font("Arial", 0, size));
        g.drawString(text, x, y);
    }

}
