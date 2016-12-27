package ru.luvas.physics.cw1.custom;

import java.awt.Color;

/**
 *
 * @author RINES <iam@kostya.sexy>
 */
public class Transparency {

    public static Color translate(Color color, double visibility) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) (visibility * 255));
    }
    
}
