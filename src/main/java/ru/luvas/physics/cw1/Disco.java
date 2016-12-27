package ru.luvas.physics.cw1;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.swing.JSlider;

/**
 *
 * @author RINES <iam@kostya.sexy>
 */
public class Disco {
    
    private final static int stepMin = 10, stepMax = 100;
    private final static Random r = new Random();
    private final static Set<SliderInfo> sliders = new HashSet<>();

    public static void justDoIt() {
        SetupFrame sf = MainFrame.getSetupFrame();
        sliders.add(new SliderInfo(sf.rSlider));
        sliders.add(new SliderInfo(sf.cSlider));
        sliders.add(new SliderInfo(sf.dbhSlider));
        sliders.add(new SliderInfo(sf.dbslSlider));
        sliders.add(new SliderInfo(sf.lwSlider));
        Utils.schedule(() -> sliders.forEach(SliderInfo::process), 0l, 25l, TimeUnit.MILLISECONDS);
    }
    
    private static class SliderInfo {
        
        private final JSlider slider;
        private final float step;
        private boolean goingUpwards = true;
        
        public SliderInfo(JSlider slider) {
            this.slider = slider;
            this.step = 0.001f * (r.nextInt(stepMax - stepMin) + stepMin);
        }
        
        private void process() {
            if(slider.isFocusOwner())
                return;
            int min = slider.getMinimum(), max = slider.getMaximum();
            int current = slider.getValue();
            int step = (int) Math.max(1, this.step * (max - min));
            if(r.nextInt(10) == 0)
                goingUpwards = !goingUpwards;
            if(goingUpwards) {
                current += step;
                if(current > max) {
                    current = max;
                    goingUpwards = false;
                }
            }else {
                current -= step;
                if(current < min) {
                    current = min;
                    goingUpwards = true;
                }
            }
            slider.setValue(current);
        }
        
    }
    
}
