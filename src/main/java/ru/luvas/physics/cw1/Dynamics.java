package ru.luvas.physics.cw1;

import static ru.luvas.physics.cw1.Constants.*;

/**
 *
 * @author 0xC0deBabe <iam@kostya.sexy>
 */
public class Dynamics {
    
    public static double
            PHYSICS_DISTANCE_BETWEEN_HOLES = (PHYSICS_DISTANCE_BETWEEN_HOLES_MAX + PHYSICS_DISTANCE_BETWEEN_HOLES_MIN) / 2 * Math.pow(10, -6), //d
            PHYSICS_LIGHT_SOURCE_INTENSIVITY = 1, //I0
            PHYSICS_LIGHT_SOURCE_LENGTH = 0.1d, //D
            PHYSICS_DISTANCE_BETWEEN_LIGHT_SOURCE_AND_LATTICE = PHYSICS_DISTANCE_BETWEEN_LIGHT_SOURCE_AND_LATTICE_DEFAULT * Math.pow(10, -3), //H
            PHYSICS_DISTANCE_BETWEEN_SCREEN_AND_LATTICE = PHYSICS_DISTANCE_BETWEEN_SCREEN_AND_LATTICE_DEFAULT * Math.pow(10, -3); //L
            
    
    public static int
            GRAPHICS_FIRST_HOLE_START_Y = GRAPHICS_DISPLAY_HEIGHT / 2 - GRAPHICS_HOLE_WIDTH - GRAPHICS_HOLES_DELTA_FROM_CENTER,
            GRAPHICS_SECOND_HOLE_START_Y = GRAPHICS_DISPLAY_HEIGHT / 2 + GRAPHICS_HOLES_DELTA_FROM_CENTER,
            GRAPHICS_CIRCLE_CLEAN_RADIUS = 13,
            GRAPHICS_CIRCLE_RADIUS = GRAPHICS_CIRCLE_CLEAN_RADIUS,
            GRAPHICS_AMOUNT_OF_CIRCLES = 7,
            GRAPHICS_LIGHT_LINE_DELTA = GRAPHICS_DEFAULT_LIGHT_LINE_DELTA,
            PHYSICS_LIGHT_WAVE = 0;

}
