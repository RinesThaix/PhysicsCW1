package ru.luvas.physics.cw1.entity;

import java.awt.Graphics2D;
import ru.luvas.physics.cw1.MainFrame;

/**
 *
 * @author 0xC0deBabe <iam@kostya.sexy>
 */
public abstract class Entity {

    protected int x, y;
    protected boolean active = true;
    
    Entity(int x, int y) {
        this.x = x;
        this.y = y;
        MainFrame.getInstance().getEntityManager().addEntity(this);
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setX(int x) {
        this.x = x;
        updated();
    }
    
    public void setY(int y) {
        this.y = y;
        updated();
    }
    
    public void updated() {
        MainFrame.getInstance().getEntityManager().updated = true;
    }
    
    public void remove() {
        MainFrame.getInstance().getEntityManager().removeEntity(this);
    }
    
    public void setActive(boolean value) {
        this.active = value;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public abstract void draw(Graphics2D g);
    
}
