package ru.luvas.physics.cw1;

/**
 *
 * @author 0xC0deBabe <iam@kostya.sexy>
 */
public class Constants {

    public final static int
            GRAPHICS_DISPLAY_WIDTH = 800,
            GRAPHICS_DISPLAY_HEIGHT = 600,
            GRAPHICS_LATTICE_START_X = 200,
            GRAPHICS_LATTICE_START_Y = 0,
            GRAPHICS_LATTICE_WIDTH = 20,
            GRAPHICS_LATTIE_LENGTH = GRAPHICS_DISPLAY_HEIGHT - 2 * GRAPHICS_LATTICE_START_Y,
            GRAPHICS_HOLE_WIDTH = 10,
            GRAPHICS_HOLES_DELTA_FROM_CENTER = 50,
            GRAPHICS_DEFAULT_FIRST_CIRCLE_RADIUS = 35,
            PHYSICS_DEFAULT_LIGHT_WAVE_LENGTH = 440,
            GRAPHICS_AMOUNT_OF_CIRCLES = 4,
            GRAPHICS_AMOUNT_OF_LINES = 3,
            GRAPHICS_LIGHT_LINE_START_Y = GRAPHICS_DISPLAY_HEIGHT / 3,
            GRAPHICS_LIGHT_LINE_END_Y = 2 * GRAPHICS_LIGHT_LINE_START_Y,
            GRAPHICS_DEFAULT_LIGHT_LINE_DELTA = 15;
    
    public final static double
            PHYSICS_DEFAULT_DISTANCE_BETWEEN_HOLES = 8.5f * Math.pow(10, -5);
    
}
