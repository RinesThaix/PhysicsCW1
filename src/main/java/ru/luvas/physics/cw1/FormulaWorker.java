package ru.luvas.physics.cw1;

import java.awt.Color;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import ru.luvas.physics.cw1.entity.FilledRectangle;

import static ru.luvas.physics.cw1.Constants.*;
import static ru.luvas.physics.cw1.Dynamics.*;
import ru.luvas.physics.cw1.custom.Point;
import ru.luvas.physics.cw1.custom.Transparency;
import ru.luvas.physics.cw1.entity.Entity;
import ru.luvas.physics.cw1.entity.GradientRectangle;
import ru.luvas.physics.cw1.entity.Line;
import ru.luvas.physics.cw1.entity.SemiCircle;
import ru.luvas.physics.cw1.entity.Text;

/**
 *
 * @author 0xC0deBabe <iam@kostya.sexy>
 */
public class FormulaWorker {
    
    private final FilledRectangle lattice;
    private final FilledRectangle firstHole, secondHole;
    private final Text A, B, C;
    private final SemiCircle[] firstHoleCircles, secondHoleCircles;
    private final Line[] lightLines;
    private Color lightColor;
    private final Set<Entity> screen = new HashSet<>();

    FormulaWorker() {
        changeLight((PHYSICS_LIGHT_WAVE_LENGTH_MAX + PHYSICS_LIGHT_WAVE_LENGTH_MIN) >> 1);
        lattice = new FilledRectangle(GRAPHICS_LATTICE_START_X, GRAPHICS_LATTICE_START_Y,
                GRAPHICS_LATTICE_WIDTH, GRAPHICS_LATTICE_LENGTH, Color.black);
        firstHole = new FilledRectangle(GRAPHICS_LATTICE_START_X, GRAPHICS_FIRST_HOLE_START_Y,
                GRAPHICS_LATTICE_WIDTH, GRAPHICS_HOLE_WIDTH, Color.white);
        secondHole = new FilledRectangle(GRAPHICS_LATTICE_START_X, GRAPHICS_SECOND_HOLE_START_Y,
                GRAPHICS_LATTICE_WIDTH, GRAPHICS_HOLE_WIDTH, Color.white);
        firstHoleCircles = new SemiCircle[GRAPHICS_AMOUNT_OF_CIRCLES_MAX];
        secondHoleCircles = new SemiCircle[firstHoleCircles.length];
        int radius = GRAPHICS_CIRCLE_RADIUS;
        for(int i = 0; i < firstHoleCircles.length; ++i) {
            firstHoleCircles[i] = new SemiCircle(GRAPHICS_LATTICE_START_X + GRAPHICS_LATTICE_WIDTH - radius / 2,
                    GRAPHICS_FIRST_HOLE_START_Y + GRAPHICS_HOLE_WIDTH / 2 - radius / 2,
                    radius, false, lightColor);
            secondHoleCircles[i] = new SemiCircle(GRAPHICS_LATTICE_START_X + GRAPHICS_LATTICE_WIDTH - radius / 2,
                    GRAPHICS_SECOND_HOLE_START_Y + GRAPHICS_HOLE_WIDTH / 2 - radius / 2,
                    radius, false, lightColor);
            radius += GRAPHICS_CIRCLE_RADIUS;
        }
        lightLines = new Line[GRAPHICS_AMOUNT_OF_LINES];
        int lx = GRAPHICS_LATTICE_START_X - GRAPHICS_LIGHT_LINE_DELTA;
        for(int i = 0; i < lightLines.length; ++i) {
            lightLines[i] = new Line(lx, GRAPHICS_LIGHT_LINE_START_Y, lx, GRAPHICS_LIGHT_LINE_END_Y, lightColor);
            lx -= GRAPHICS_LIGHT_LINE_DELTA;
        }
        A = new Text(GRAPHICS_LETTER_A_X, GRAPHICS_LETTER_A_Y, "A", Color.black, GRAPHICS_LETTER_SIZE);
        B = new Text(GRAPHICS_LETTER_B_X, GRAPHICS_LETTER_B_Y, "B", Color.black, GRAPHICS_LETTER_SIZE);
        C = new Text(0 /* being set in calculated() method */, GRAPHICS_LETTER_C_Y, "C", Color.black, GRAPHICS_LETTER_SIZE);
        recalculate();
    }
    
    public synchronized void recalculate() {
        double visibility = (double) (PHYSICS_DISTANCE_BETWEEN_LIGHT_SOURCE_AND_LATTICE * Math.pow(10, 3) - PHYSICS_DISTANCE_BETWEEN_LIGHT_SOURCE_AND_LATTICE_MIN) / 
                (PHYSICS_DISTANCE_BETWEEN_LIGHT_SOURCE_AND_LATTICE_MAX - PHYSICS_DISTANCE_BETWEEN_LIGHT_SOURCE_AND_LATTICE_MIN);
        visibility = 1 - visibility;
        lightColor = Transparency.translate(lightColor, visibility);
        double defHolesDist = PHYSICS_DISTANCE_BETWEEN_HOLES_MIN * Math.pow(10, -6);
        GRAPHICS_FIRST_HOLE_START_Y = GRAPHICS_DISPLAY_HEIGHT / 2 - GRAPHICS_HOLE_WIDTH -
                (int) (GRAPHICS_HOLES_DELTA_FROM_CENTER / defHolesDist * PHYSICS_DISTANCE_BETWEEN_HOLES);
        GRAPHICS_SECOND_HOLE_START_Y = GRAPHICS_DISPLAY_HEIGHT / 2 +
                (int) (GRAPHICS_HOLES_DELTA_FROM_CENTER / defHolesDist * PHYSICS_DISTANCE_BETWEEN_HOLES);
        firstHole.setY(GRAPHICS_FIRST_HOLE_START_Y);
        secondHole.setY(GRAPHICS_SECOND_HOLE_START_Y);
        recalculateCirclesRadius();
        int radius = GRAPHICS_CIRCLE_RADIUS;
        for(int i = 0; i < firstHoleCircles.length; ++i) {
            SemiCircle sc1 = firstHoleCircles[i], sc2 = secondHoleCircles[i];
            if(i > GRAPHICS_AMOUNT_OF_CIRCLES) {
                sc1.setActive(false);
                sc2.setActive(false);
                continue;
            }
            sc1.setActive(true);
            sc2.setActive(true);
            sc1.setRadius(radius); sc2.setRadius(radius);
            int newX = GRAPHICS_LATTICE_START_X + GRAPHICS_LATTICE_WIDTH - radius / 2;
            sc1.setX(newX); sc2.setX(newX);
            sc1.setY(GRAPHICS_FIRST_HOLE_START_Y + GRAPHICS_HOLE_WIDTH / 2 - radius / 2);
            sc2.setY(GRAPHICS_SECOND_HOLE_START_Y + GRAPHICS_HOLE_WIDTH / 2 - radius / 2);
            sc1.setColor(lightColor); sc2.setColor(lightColor);
            radius += GRAPHICS_CIRCLE_RADIUS;
        }
        int lx = GRAPHICS_LATTICE_START_X - GRAPHICS_LIGHT_LINE_DELTA;
        for(int i = 0; i < lightLines.length; ++i) {
            lightLines[i].setX(lx);
            lightLines[i].setX2(lx);
            lightLines[i].setColor(lightColor);
            lx -= GRAPHICS_LIGHT_LINE_DELTA;
        }
        gradientRects: {
            List<Point> f = IntensivityCalculator.calculateTranslatedF();
            double maxL = PHYSICS_SCREEN_LENGTH;
            double modifier = (GRAPHICS_LATTICE_LENGTH >> 1) / maxL;
            boolean minimum = false;
            screen.forEach(Entity::remove);
            screen.clear();
            final Color black = Color.black;
            double percentageDistance = PHYSICS_DISTANCE_BETWEEN_SCREEN_AND_LATTICE * Math.pow(10, 3) - PHYSICS_DISTANCE_BETWEEN_SCREEN_AND_LATTICE_MIN;
            percentageDistance /= PHYSICS_DISTANCE_BETWEEN_SCREEN_AND_LATTICE_MAX - PHYSICS_DISTANCE_BETWEEN_SCREEN_AND_LATTICE_MIN;
            percentageDistance *= GRAPHICS_SCREEN_START_X_MAX - GRAPHICS_SCREEN_START_X_MIN;
            int screenStartX = GRAPHICS_SCREEN_START_X_MIN + (int) (percentageDistance);
            C.setX(screenStartX + GRAPHICS_LETTER_C_DX);
            screen.add(new FilledRectangle(screenStartX, 0, GRAPHICS_SCREEN_WIDTH, GRAPHICS_DISPLAY_HEIGHT, black));
            for(int j = 0; j < 2; ++j) {
                int pheight = -1;
                for(int i = 0; i < f.size(); ++i) {
                    Point p1 = f.get(i);
                    int x = screenStartX, y = (int) (j == 0 ? ((maxL - p1.x) * modifier) : (p1.x * modifier));
                    int width = GRAPHICS_SCREEN_WIDTH;
                    int height = i == f.size() - 1 ? pheight : (int) ((f.get(i + 1).x - p1.x) * modifier) + 1;
                    pheight = height;
                    if(j == 0)
                        y -= height;
                    else
                        y += GRAPHICS_LATTICE_LENGTH >> 1;
                    Color c1, c2;
                    if(j == 0) {
                        c1 = lightColor;
                        c2 = black;
                    }else {
                        c1 = black;
                        c2 = lightColor;
                    }
                    if(minimum) {
                        screen.add(new GradientRectangle(x, y, width, height, c1, c2));
                    }else {
                        screen.add(new GradientRectangle(x, y, width, height, c2, c1));
                    }
                    minimum = !minimum;
                }
                minimum = false;
            }
        }
    }
    
    public void changeLight(int newWaveLength) {
        PHYSICS_LIGHT_WAVE = newWaveLength;
        recalculateCirclesRadius();
        newWaveLength = (int) Math.min(PHYSICS_LIGHT_WAVE_LENGTH_MAX, Math.max(PHYSICS_LIGHT_WAVE_LENGTH_MIN, newWaveLength));
        double wavePercentage = GRAPHICS_LIGHT_WAVE_PERCENTAGE_MODIFIER * (newWaveLength - PHYSICS_LIGHT_WAVE_LENGTH_MIN) / (PHYSICS_LIGHT_WAVE_LENGTH_MAX - PHYSICS_LIGHT_WAVE_LENGTH_MIN);
        wavePercentage += GRAPHICS_LIGHT_WAVE_PERCENTAGE_BASEMENT;
        //changing color
        lightColor = ColoursMapping.getClosestColor(newWaveLength);
        //changing radiuses
        //changing line deltas
        GRAPHICS_LIGHT_LINE_DELTA = (int) (GRAPHICS_DEFAULT_LIGHT_LINE_DELTA * wavePercentage);
    }
    
    private void recalculateCirclesRadius() {
        double wavePercentage = GRAPHICS_LIGHT_WAVE_PERCENTAGE_MODIFIER * (PHYSICS_LIGHT_WAVE - PHYSICS_LIGHT_WAVE_LENGTH_MIN) / (PHYSICS_LIGHT_WAVE_LENGTH_MAX - PHYSICS_LIGHT_WAVE_LENGTH_MIN);
        wavePercentage += GRAPHICS_LIGHT_WAVE_PERCENTAGE_BASEMENT;
        GRAPHICS_CIRCLE_RADIUS = (int) (GRAPHICS_CIRCLE_CLEAN_RADIUS * wavePercentage);
    }
    
}
