package ru.luvas.physics.cw1;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 *
 * @author RINES <iam@kostya.sexy>
 */
public class SetupFrame extends JFrame {
    
    private final JPanel panel;
    final JLabel lwsLabel, lwLabel, dbhsLabel, dbhLabel, dbslsLabel, dbslLabel, dbllsLabel, dbllLabel, csLabel, cLabel, rsLabel, rLabel;
    final JSlider lwSlider, dbhSlider, dbslSlider, dbllSlider, cSlider, rSlider;

    SetupFrame() {
        super("Управление");
        setSize((int) (Constants.GRAPHICS_DISPLAY_WIDTH / 1.75d), (Constants.GRAPHICS_DISPLAY_HEIGHT >> 1) + Constants.GRAPHICS_BORDER_HEIGHT);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        
        add(panel = new JPanel());
        panel.add(lwsLabel = new JLabel("Длина волны"));
        panel.add(lwSlider = new LightWaveSlider());
        panel.add(lwLabel = new JLabel(lwSlider.getValue() + " нм"));
        panel.add(dbhsLabel = new JLabel("Расстояние м/у щелями"));
        panel.add(dbhSlider = new DistanceBetweenHolesSlider());
        panel.add(dbhLabel = new JLabel(dbhSlider.getValue() + " мкм"));
        panel.add(dbslsLabel = new JLabel("Расстояние м/у B и C"));
        panel.add(dbslSlider = new DistanceBetweenScreenAndLatticeSlider());
        panel.add(dbslLabel = new JLabel(dbslSlider.getValue() + " мм"));
        panel.add(dbllsLabel = new JLabel("Расстояние м/у A и B"));
        panel.add(dbllSlider = new DistanceBetweenLightSourceAndScreenSlider());
        panel.add(dbllLabel = new JLabel(dbllSlider.getValue() + " мм"));
        panel.add(csLabel = new JLabel("Количество окружностей"));
        panel.add(cSlider = new CirclesSlider());
        panel.add(cLabel = new JLabel(cSlider.getValue() + " шт"));
        panel.add(rsLabel = new JLabel("Радиус окружностей"));
        panel.add(rSlider = new RadiusSlider());
        panel.add(rLabel = new JLabel(rSlider.getValue() + " ед"));
    }
    
    private FormulaWorker getFormulaWorker() {
        return MainFrame.getInstance().getFormulaWorker();
    }
    
    private class LightWaveSlider extends JSlider {
        
        public LightWaveSlider() {
            super(Constants.PHYSICS_LIGHT_WAVE_LENGTH_MIN, Constants.PHYSICS_LIGHT_WAVE_LENGTH_MAX, (Constants.PHYSICS_LIGHT_WAVE_LENGTH_MAX + Constants.PHYSICS_LIGHT_WAVE_LENGTH_MIN) >> 1);
            this.addChangeListener(e -> {
                JSlider me = (JSlider) e.getSource();
                getFormulaWorker().changeLight(me.getValue());
                getFormulaWorker().recalculate();
                lwLabel.setText(me.getValue() + " нм");
            });
        }
    }
    
    private class DistanceBetweenHolesSlider extends JSlider {
        
        public DistanceBetweenHolesSlider() {
            super(Constants.PHYSICS_DISTANCE_BETWEEN_HOLES_MIN, Constants.PHYSICS_DISTANCE_BETWEEN_HOLES_MAX, (Constants.PHYSICS_DISTANCE_BETWEEN_HOLES_MAX + Constants.PHYSICS_DISTANCE_BETWEEN_HOLES_MIN) >> 1);
            this.addChangeListener(e -> {
                JSlider me = (JSlider) e.getSource();
                Dynamics.PHYSICS_DISTANCE_BETWEEN_HOLES = me.getValue() * Math.pow(10, -6);
                getFormulaWorker().recalculate();
                dbhLabel.setText(me.getValue() + " мкм");
            });
        }
    }
    
    private class DistanceBetweenScreenAndLatticeSlider extends JSlider {
        
        public DistanceBetweenScreenAndLatticeSlider() {
            super(Constants.PHYSICS_DISTANCE_BETWEEN_SCREEN_AND_LATTICE_MIN, Constants.PHYSICS_DISTANCE_BETWEEN_SCREEN_AND_LATTICE_MAX, Constants.PHYSICS_DISTANCE_BETWEEN_SCREEN_AND_LATTICE_DEFAULT);
            this.addChangeListener(e -> {
                JSlider me = (JSlider) e.getSource();
                Dynamics.PHYSICS_DISTANCE_BETWEEN_SCREEN_AND_LATTICE = me.getValue() * Math.pow(10, -3);
                getFormulaWorker().recalculate();
                dbslLabel.setText(me.getValue() + " мм");
            });
        }
    }
    
    private class DistanceBetweenLightSourceAndScreenSlider extends JSlider {
        
        public DistanceBetweenLightSourceAndScreenSlider() {
            super(Constants.PHYSICS_DISTANCE_BETWEEN_LIGHT_SOURCE_AND_LATTICE_MIN, Constants.PHYSICS_DISTANCE_BETWEEN_LIGHT_SOURCE_AND_LATTICE_MAX, Constants.PHYSICS_DISTANCE_BETWEEN_LIGHT_SOURCE_AND_LATTICE_DEFAULT);
            this.addChangeListener(e -> {
                JSlider me = (JSlider) e.getSource();
                Dynamics.PHYSICS_DISTANCE_BETWEEN_LIGHT_SOURCE_AND_LATTICE = me.getValue() * Math.pow(10, -3);
                getFormulaWorker().recalculate();
                dbllLabel.setText(me.getValue() + " мм");
            });
        }
    }
    
    private class CirclesSlider extends JSlider {
        
        public CirclesSlider() {
            super(Constants.GRAPHICS_AMOUNT_OF_CIRCLES_MIN, Constants.GRAPHICS_AMOUNT_OF_CIRCLES_MAX, Dynamics.GRAPHICS_AMOUNT_OF_CIRCLES);
            this.addChangeListener(e -> {
                JSlider me = (JSlider) e.getSource();
                Dynamics.GRAPHICS_AMOUNT_OF_CIRCLES = me.getValue();
                getFormulaWorker().recalculate();
                cLabel.setText(me.getValue() + " шт");
            });
        }
    }
    
    private class RadiusSlider extends JSlider {
        
        public RadiusSlider() {
            super(Constants.GRAPHICS_CIRCLE_RADIUS_MIN, Constants.GRAPHICS_CIRCLE_RADIUS_MAX, Dynamics.GRAPHICS_CIRCLE_CLEAN_RADIUS);
            this.addChangeListener(e -> {
                JSlider me = (JSlider) e.getSource();
                Dynamics.GRAPHICS_CIRCLE_CLEAN_RADIUS = me.getValue();
                getFormulaWorker().recalculate();
                rLabel.setText(me.getValue() + " ед");
            });
        }
    }
    
}
