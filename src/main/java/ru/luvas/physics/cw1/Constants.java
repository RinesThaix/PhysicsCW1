package ru.luvas.physics.cw1;

/**
 *
 * @author 0xC0deBabe <iam@kostya.sexy>
 */
public class Constants {

    public final static int
            GRAPHICS_BORDER_HEIGHT = 22,
            GRAPHICS_DISPLAY_WIDTH = 800,
            GRAPHICS_DISPLAY_HEIGHT = 600,
            GRAPHICS_LATTICE_START_X = 200,
            GRAPHICS_SCREEN_WIDTH = 40,
            GRAPHICS_SCREEN_START_X_MIN = GRAPHICS_DISPLAY_WIDTH - (GRAPHICS_LATTICE_START_X << 1),
            GRAPHICS_SCREEN_START_X_MAX = GRAPHICS_DISPLAY_WIDTH - (GRAPHICS_SCREEN_WIDTH << 1),
            GRAPHICS_LATTICE_START_Y = 0,
            GRAPHICS_LATTICE_WIDTH = 20,
            GRAPHICS_LATTICE_LENGTH = GRAPHICS_DISPLAY_HEIGHT - 2 * GRAPHICS_LATTICE_START_Y,
            GRAPHICS_HOLE_WIDTH = 10,
            GRAPHICS_HOLES_DELTA_FROM_CENTER = 50,
            GRAPHICS_CIRCLE_RADIUS_MIN = 5,
            GRAPHICS_CIRCLE_RADIUS_MAX = 30,
            PHYSICS_LIGHT_WAVE_LENGTH_MIN = 350,
            PHYSICS_LIGHT_WAVE_LENGTH_MAX = 850,
            GRAPHICS_AMOUNT_OF_CIRCLES_MIN = 1,
            GRAPHICS_AMOUNT_OF_CIRCLES_MAX = 20,
            GRAPHICS_AMOUNT_OF_LINES = 3,
            GRAPHICS_LIGHT_LINE_START_Y = GRAPHICS_DISPLAY_HEIGHT / 3,
            GRAPHICS_LIGHT_LINE_END_Y = 2 * GRAPHICS_LIGHT_LINE_START_Y,
            GRAPHICS_DEFAULT_LIGHT_LINE_DELTA = 15,
            PHYSICS_DISTANCE_BETWEEN_HOLES_MIN = 35,
            PHYSICS_DISTANCE_BETWEEN_HOLES_MAX = 150,
            PHYSICS_DISTANCE_BETWEEN_SCREEN_AND_LATTICE_MIN = 1500,
            PHYSICS_DISTANCE_BETWEEN_SCREEN_AND_LATTICE_MAX = 3000,
            PHYSICS_DISTANCE_BETWEEN_SCREEN_AND_LATTICE_DEFAULT = (PHYSICS_DISTANCE_BETWEEN_SCREEN_AND_LATTICE_MIN + PHYSICS_DISTANCE_BETWEEN_SCREEN_AND_LATTICE_MAX) >> 1,
            PHYSICS_DISTANCE_BETWEEN_LIGHT_SOURCE_AND_LATTICE_MIN = 3000,
            PHYSICS_DISTANCE_BETWEEN_LIGHT_SOURCE_AND_LATTICE_MAX = 20000,
            PHYSICS_DISTANCE_BETWEEN_LIGHT_SOURCE_AND_LATTICE_DEFAULT = (PHYSICS_DISTANCE_BETWEEN_LIGHT_SOURCE_AND_LATTICE_MIN + PHYSICS_DISTANCE_BETWEEN_LIGHT_SOURCE_AND_LATTICE_MAX) >> 1,
            PHYSICS_LIGHT_SOURCE_LENGTH_MIN = 0,
            GRAPHICS_LETTER_A_X = 25,
            GRAPHICS_LETTER_A_Y = GRAPHICS_DISPLAY_HEIGHT >> 1,
            GRAPHICS_LETTER_B_X = GRAPHICS_LATTICE_START_X + 25,
            GRAPHICS_LETTER_B_Y = GRAPHICS_LATTICE_START_Y + 50,
            GRAPHICS_LETTER_C_DX = 50,
            GRAPHICS_LETTER_C_Y = 50,
            GRAPHICS_LETTER_SIZE = 60;
    
    public final static double
            GRAPHICS_LIGHT_WAVE_PERCENTAGE_MODIFIER = 3d,
            GRAPHICS_LIGHT_WAVE_PERCENTAGE_BASEMENT = 0.75d,
            PHYSICS_SCREEN_LENGTH = 100d,
            PHYSICS_SCREEN_STEP = 0.01d;
    
}
