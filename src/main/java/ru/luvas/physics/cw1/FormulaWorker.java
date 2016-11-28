package ru.luvas.physics.cw1;

import java.awt.Color;
import java.util.concurrent.TimeUnit;
import ru.luvas.physics.cw1.entity.FilledRectangle;

import static ru.luvas.physics.cw1.Constants.*;
import static ru.luvas.physics.cw1.Dynamics.*;
import ru.luvas.physics.cw1.entity.Line;
import ru.luvas.physics.cw1.entity.SemiCircle;

/**
 *
 * @author 0xC0deBabe <iam@kostya.sexy>
 */
public class FormulaWorker {
    
    private final FilledRectangle lattice;
    private final FilledRectangle firstHole, secondHole;
    private final SemiCircle[] firstHoleCircles, secondHoleCircles;
    private final Line[] lightLines;
    private Color lightColor;
    private int currentLightWave;

    FormulaWorker() {
        changeLight(PHYSICS_LIGHT_WAVE_LENGTH_MIN);
        lattice = new FilledRectangle(GRAPHICS_LATTICE_START_X, GRAPHICS_LATTICE_START_Y,
                GRAPHICS_LATTICE_WIDTH, GRAPHICS_LATTIE_LENGTH, Color.black);
        firstHole = new FilledRectangle(GRAPHICS_LATTICE_START_X, GRAPHICS_FIRST_HOLE_START_Y,
                GRAPHICS_LATTICE_WIDTH, GRAPHICS_HOLE_WIDTH, Color.white);
        secondHole = new FilledRectangle(GRAPHICS_LATTICE_START_X, GRAPHICS_SECOND_HOLE_START_Y,
                GRAPHICS_LATTICE_WIDTH, GRAPHICS_HOLE_WIDTH, Color.white);
        firstHoleCircles = new SemiCircle[GRAPHICS_AMOUNT_OF_CIRCLES];
        secondHoleCircles = new SemiCircle[firstHoleCircles.length];
        int radius = GRAPHICS_FIRST_CIRCLE_RADIUS;
        for(int i = 0; i < firstHoleCircles.length; ++i) {
            firstHoleCircles[i] = new SemiCircle(GRAPHICS_LATTICE_START_X + GRAPHICS_LATTICE_WIDTH - radius / 2,
                    GRAPHICS_FIRST_HOLE_START_Y + GRAPHICS_HOLE_WIDTH / 2 - radius / 2,
                    radius, false, lightColor);
            secondHoleCircles[i] = new SemiCircle(GRAPHICS_LATTICE_START_X + GRAPHICS_LATTICE_WIDTH - radius / 2,
                    GRAPHICS_SECOND_HOLE_START_Y + GRAPHICS_HOLE_WIDTH / 2 - radius / 2,
                    radius, false, lightColor);
            radius += GRAPHICS_FIRST_CIRCLE_RADIUS;
        }
        lightLines = new Line[GRAPHICS_AMOUNT_OF_LINES];
        int lx = GRAPHICS_LATTICE_START_X - GRAPHICS_LIGHT_LINE_DELTA;
        for(int i = 0; i < lightLines.length; ++i) {
            lightLines[i] = new Line(lx, GRAPHICS_LIGHT_LINE_START_Y, lx, GRAPHICS_LIGHT_LINE_END_Y, lightColor);
            lx -= GRAPHICS_LIGHT_LINE_DELTA;
        }
        
        Utils.schedule(() -> {
//            PHYSICS_DISTANCE_BETWEEN_HOLES += 1d * Math.pow(10, -5);
            changeLight(currentLightWave + 10);
            recalculate();
        }, 1, 1, TimeUnit.SECONDS);
    }
    
    public void recalculate() {
        GRAPHICS_FIRST_HOLE_START_Y = GRAPHICS_DISPLAY_HEIGHT / 2 - GRAPHICS_HOLE_WIDTH -
                (int) (GRAPHICS_HOLES_DELTA_FROM_CENTER / PHYSICS_DEFAULT_DISTANCE_BETWEEN_HOLES * PHYSICS_DISTANCE_BETWEEN_HOLES);
        GRAPHICS_SECOND_HOLE_START_Y = GRAPHICS_DISPLAY_HEIGHT / 2 +
                (int) (GRAPHICS_HOLES_DELTA_FROM_CENTER / PHYSICS_DEFAULT_DISTANCE_BETWEEN_HOLES * PHYSICS_DISTANCE_BETWEEN_HOLES);
        firstHole.setY(GRAPHICS_FIRST_HOLE_START_Y);
        secondHole.setY(GRAPHICS_SECOND_HOLE_START_Y);
        int radius = GRAPHICS_FIRST_CIRCLE_RADIUS;
        for(int i = 0; i < firstHoleCircles.length; ++i) {
            SemiCircle sc1 = firstHoleCircles[i], sc2 = secondHoleCircles[i];
            sc1.setRadius(radius); sc2.setRadius(radius);
            int newX = GRAPHICS_LATTICE_START_X + GRAPHICS_LATTICE_WIDTH - radius / 2;
            sc1.setX(newX); sc2.setX(newX);
            sc1.setY(GRAPHICS_FIRST_HOLE_START_Y + GRAPHICS_HOLE_WIDTH / 2 - radius / 2);
            sc2.setY(GRAPHICS_SECOND_HOLE_START_Y + GRAPHICS_HOLE_WIDTH / 2 - radius / 2);
            sc1.setColor(lightColor); sc2.setColor(lightColor);
            radius += GRAPHICS_FIRST_CIRCLE_RADIUS;
        }
        int lx = GRAPHICS_LATTICE_START_X - GRAPHICS_LIGHT_LINE_DELTA;
        for(int i = 0; i < lightLines.length; ++i) {
            lightLines[i].setX(lx);
            lightLines[i].setX2(lx);
            lightLines[i].setColor(lightColor);
            lx -= GRAPHICS_LIGHT_LINE_DELTA;
        }
    }
    
    public void changeLight(int newWaveLength) {
        newWaveLength = (int) Math.min(PHYSICS_LIGHT_WAVE_LENGTH_MAX, Math.max(PHYSICS_LIGHT_WAVE_LENGTH_MIN, newWaveLength));
        double wavePercentage = GRAPHICS_LIGHT_WAVE_PERCENTAGE_MODIFIER * (newWaveLength - PHYSICS_LIGHT_WAVE_LENGTH_MIN) / (PHYSICS_LIGHT_WAVE_LENGTH_MAX - PHYSICS_LIGHT_WAVE_LENGTH_MIN);
        wavePercentage += GRAPHICS_LIGHT_WAVE_PERCENTAGE_BASEMENT;
        //changing color
        lightColor = ColoursMapping.getClosestColor(newWaveLength);
        //changing radiuses
        GRAPHICS_FIRST_CIRCLE_RADIUS = (int) (GRAPHICS_DEFAULT_FIRST_CIRCLE_RADIUS * wavePercentage);
        //changing line deltas
        GRAPHICS_LIGHT_LINE_DELTA = (int) (GRAPHICS_DEFAULT_LIGHT_LINE_DELTA * wavePercentage);
        this.currentLightWave = newWaveLength;
    }
    
}
