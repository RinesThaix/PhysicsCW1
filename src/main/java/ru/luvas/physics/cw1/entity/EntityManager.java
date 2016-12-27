package ru.luvas.physics.cw1.entity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import ru.luvas.physics.cw1.MainFrame;

/**
 *
 * @author 0xC0deBabe <iam@kostya.sexy>
 */
public class EntityManager {

    private final MainFrame frame;
    private final List<Entity> entities = new ArrayList<>();
    
    boolean updated = false;
    
    public EntityManager(MainFrame frame) {
        this.frame = frame;
    }
    
    void addEntity(Entity entity) {
        synchronized(entities) {
            entities.add(entity);
        }
        updated = true;
    }
    
    void removeEntity(Entity entity) {
        synchronized(entities) {
            entities.remove(entity);
        }
        updated = true;
    }
    
    public List<Entity> getEntities() {
        synchronized(entities) {
            return new ArrayList<>(this.entities);
        }
    }
    
    public List<Entity> getActiveEntities() {
        synchronized(entities) {
            return entities.stream().filter(Entity::isActive).collect(Collectors.toList());
        }
    }
    
    public void redraw() {
        if(!updated)
            return;
        updated = false;
        frame.repaint();
    }
    
    public void redraw0(Graphics2D g) {
        synchronized(entities) {
            entities.stream().filter(Entity::isActive).forEach(e -> e.draw(g));
        }
    }
    
}
