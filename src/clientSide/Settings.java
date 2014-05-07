package clientSide;

import java.awt.*;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

public class Settings {
    public static final int GAME_WINDOW_WIDTH = 500;
    public static final int GAME_WINDOW_HEIGHT = 300;

    public static final int FPS = 100;
    public static final long GRAPHICS_PROC_SLEEP_MILLIS = (long) (1000.0 / FPS);
    public static final int PPS = 50;
    public static final long MECHANICS_PROC_SLEEP_MILLIS =  (long) (1000.0 / PPS);

    public static final float PLAYER_SPEED = 150.0f;
    public static final float PLAYER_LOC_UPDATE_AMOUNT = PLAYER_SPEED / FPS;
    public static final int PLAYER_ANIMATION_SPEED = 10000 / FPS;

    public static final int BULLET_SPEED = 5;

    public static final int CROSSHAIR_SIZE = 12;
    public static final int PLAYER_SIZE = 10;

    public static final String IMG_PATH = "images/";

    public static final String GROUND_IMAGE_FILE_PATH = IMG_PATH + "grass.jpg";

    public static final Color DEFAULT_GROUND_COLOR = new Color(110, 133, 61);

    public static final int NUM_CHAR_ANIMATION_IMAGES = 4;

    public static final String STANDING_IMAGE_FILE_PATH = IMG_PATH + "friendlyPlayer/standing.png";
    public static final String MOVING_IMAGE_FILE_PATH = IMG_PATH + "friendlyPlayer/moving";
    public static final String MOVING_IMAGE_FILE_EXTENSION = ".png";

    public static int movingImageWidth;
    public static int movingImageHeight;
}
