package ru.luvas.physics.cw1.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
        entities.add(entity);
        updated = true;
    }
    
    void removeEntity(Entity entity) {
        entities.remove(entity);
        updated = true;
    }
    
    public List<Entity> getEntities() {
        return entities;
    }
    
    public void redraw() {
        if(!updated)
            return;
        updated = false;
        frame.repaint();
    }
    
}
