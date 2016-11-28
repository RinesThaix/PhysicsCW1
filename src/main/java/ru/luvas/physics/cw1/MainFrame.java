package ru.luvas.physics.cw1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import ru.luvas.physics.cw1.entity.EntityManager;

/**
 *
 * @author 0xC0deBabe <iam@kostya.sexy>
 */
public class MainFrame extends JFrame {
    
    private static MainFrame instance;
    
    public static MainFrame getInstance() {
        return instance;
    }
    
    private final EntityManager entityManager = new EntityManager(this);
    private final FormulaWorker formulaWorker;

    MainFrame() {
        super("Физика / Интерференция");
        instance = this;
        setSize(Constants.GRAPHICS_DISPLAY_WIDTH, Constants.GRAPHICS_DISPLAY_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        getContentPane().setBackground(Color.white);
        Utils.schedule(() -> entityManager.redraw(), 100, 100, TimeUnit.MILLISECONDS);
        formulaWorker = new FormulaWorker();
    }
    
    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //сглаживаем ломаные
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        entityManager.getEntities().forEach(e -> e.draw(g2d));
    }
    
}
