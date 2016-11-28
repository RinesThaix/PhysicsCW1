package ru.luvas.physics.cw1;

import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author 0xC0deBabe <iam@kostya.sexy>
 */
public class ColoursMapping {

    private final static Map<Integer, Color> COLOURS = new LinkedHashMap<Integer, Color>() {{
        put(0, Color.black);
        put(380, rgb(51, 0, 76));
        put(390, rgb(67, 0, 121));
        put(400, rgb(73, 0, 165));
        put(410, rgb(70, 0, 210));
        put(420, rgb(56, 0, 255));
        put(430, rgb(28, 0, 255));
        put(440, rgb(0, 0, 255));
        put(450, rgb(0, 51, 255));
        put(460, rgb(0, 102, 255));
        put(470, rgb(0, 153, 255));
        put(480, rgb(0, 204, 255));
        put(490, rgb(0, 255, 255));
        put(500, rgb(0, 255, 127));
        put(510, rgb(0, 255, 0));
        int i = 511;
        for(int r = 4; r <= 255; r += 4, ++i)
            put(i, rgb(r, 255, 0));
        for(int g = 255; g >= 0; g -=4, ++i)
            put(i, rgb(255, g, 0));
    }};
    
    public static Color getClosestColor(int lightWaveLength) {
        for(Entry<Integer, Color> entry : COLOURS.entrySet()) {
            if(lightWaveLength <= entry.getKey())
                return entry.getValue();
        }
        return rgb(255, 0, 0);
    }
    
    private static Color rgb(int r, int g, int b) {
        return Utils.getColor(r, g, b);
    }
    
}
