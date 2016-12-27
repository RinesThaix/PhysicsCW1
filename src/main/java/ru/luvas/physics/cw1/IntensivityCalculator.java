package ru.luvas.physics.cw1;

import java.util.ArrayList;
import java.util.List;
import static ru.luvas.physics.cw1.Constants.*;
import static ru.luvas.physics.cw1.Dynamics.*;
import ru.luvas.physics.cw1.custom.Point;

/**
 *
 * @author RINES <iam@kostya.sexy>
 */
public class IntensivityCalculator {
    
    public static List<Point> calculateTranslatedF() {
        double[] f = f();
        List<Point> list = new ArrayList<>();
        int i = 0;
        double x = 0, prev = 0;
        for(; i < f.length; ++i, x += PHYSICS_SCREEN_STEP)
            if(f[i] == f[i]) {
                list.add(new Point(0, f[i]));
                break;
            }
        prev = f[i];
        boolean minimum = true;
        for(; i < f.length; ++i, x += PHYSICS_SCREEN_STEP) {
            if(minimum) {
                if(f[i] > prev) {
                    list.add(new Point(x - PHYSICS_SCREEN_STEP, prev));
                    minimum = false;
                }
            }else {
                if(f[i] < prev) {
                    list.add(new Point(x - PHYSICS_SCREEN_STEP, prev));
                    minimum = true;
                }
            }
            prev = f[i];
        }
        return list;
    }
    
    public static double[] f() {
        double[] f = new double[(int) (PHYSICS_SCREEN_LENGTH / PHYSICS_SCREEN_STEP)];
        for(double x = 0, i = 0; i < f.length; x += PHYSICS_SCREEN_STEP, ++i) {
            f[(int) i] = f(x);
        }
        return f;
    }
    
    public static double f(double x) {
        double L = PHYSICS_DISTANCE_BETWEEN_SCREEN_AND_LATTICE;
        double d = PHYSICS_DISTANCE_BETWEEN_HOLES;
        double lambda = PHYSICS_LIGHT_WAVE * Math.pow(10, -9);
        int k = (int) (x * d / (lambda * L));
        double dsx = x * d / L;
        return f(k, dsx);
    }
    
    public static double f(int k, double dsx) {
        double I0 = PHYSICS_LIGHT_SOURCE_INTENSIVITY;
        double D = PHYSICS_LIGHT_SOURCE_LENGTH;
        double d = PHYSICS_DISTANCE_BETWEEN_HOLES;
        double H = PHYSICS_DISTANCE_BETWEEN_LIGHT_SOURCE_AND_LATTICE;
        double t = k * D * d / (2 * H);
        double cos = Math.cos(k * dsx), sin = Math.sin(t);
        double mult = cos * sin / t;
        return I0 * (1 + mult);
    }
    
}
