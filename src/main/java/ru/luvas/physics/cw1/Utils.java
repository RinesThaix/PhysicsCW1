package ru.luvas.physics.cw1;

import java.awt.Color;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author 0xC0deBabe <iam@kostya.sexy>
 */
public class Utils {
    
    private final static ExecutorService EXECUTOR = Executors.newCachedThreadPool();
    private final static Random R = new Random();
    
    public static void schedule(Runnable runnable) {
        EXECUTOR.execute(runnable);
    }
    
    public static void schedule(Runnable runnable, long delay, TimeUnit timeUnit) {
        EXECUTOR.execute(() -> {
            sleep(timeUnit.toMillis(delay));
            runnable.run();
        });
    }
    
    public static void schedule(Runnable runnable, long delay, long interval, TimeUnit timeUnit) {
        EXECUTOR.execute(() -> {
            sleep(timeUnit.toMillis(delay));
            while(true) {
                runnable.run();
                sleep(timeUnit.toMillis(interval));
            }
        });
    }
    
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        }catch(InterruptedException ex) {}
    }

    public static Color getColor(int r, int g, int b) {
        float[] hsb = Color.RGBtoHSB(r, g, b, null);
        return Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
    }
    
    public static int r() {
        return R.nextInt();
    }
    
    public static int r(int bound) {
        return R.nextInt(bound);
    }
    
}
