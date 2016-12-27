package ru.luvas.physics.cw1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import ru.luvas.physics.cw1.entity.EntityManager;

/**
 *
 * @author 0xC0deBabe <iam@kostya.sexy>
 */
public class MainFrame extends JFrame {
    
    private static MainFrame instance;
    private static SetupFrame setupFrame;
    
    public static MainFrame getInstance() {
        return instance;
    }
    
    private final EntityManager entityManager = new EntityManager(this);
    private FormulaWorker formulaWorker;

    MainFrame() {
        super("Физика / Интерференция");
        instance = this;
        setSize(Constants.GRAPHICS_DISPLAY_WIDTH, Constants.GRAPHICS_DISPLAY_HEIGHT + Constants.GRAPHICS_BORDER_HEIGHT);
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        getContentPane().setBackground(Color.white);
        add(new MainPanel());
        setupFrame = new SetupFrame();
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                Launcher.log(e.getKeyCode() + "");
                if(e.getKeyCode() == 68 && e.isAltDown())
                    Disco.justDoIt();
            }
        });
    }
    
    public static SetupFrame getSetupFrame() {
        return setupFrame;
    }
    
    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    public FormulaWorker getFormulaWorker() {
        return formulaWorker;
    }
    
    private class MainPanel extends JPanel {
        
        private MainPanel() {
            Utils.schedule(() -> entityManager.redraw(), 20, 20, TimeUnit.MILLISECONDS);
            MainFrame.this.formulaWorker = new FormulaWorker();
        }
    
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //сглаживаем ломаные
            g2d.setColor(Color.white);
            g2d.fillRect(0, 0, getWidth(), getHeight());
            entityManager.redraw0(g2d);
        }
        
    }
    
}
