package global;

import java.awt.*;
import java.awt.event.KeyEvent;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

public class Settings {
    /**
     * Sizes
     */

    public static final int GAME_WINDOW_WIDTH = 660;
    public static final int GAME_WINDOW_HEIGHT = 440;

    public static final int CROSSHAIR_SIZE = 12;

    public static final int PLAYER_SIZE = 30;

    public static final int BULLET_WIDTH = 5;
    public static final int BULLET_HEIGHT = 5;

    public static final int HEALTH_BAR_WIDTH = 40;
    public static final int HEALTH_BAR_HEIGHT = 8;
    public static final int HEALTH_BAR_POS_X = 10;
    public static final int HEALTH_BAR_POS_Y = 10;

    public static final int KILLING_BONUS = 10;
    public static final int DYING_PENALTY = -5;

    public static final int RESPAWN_POSITION_X = 450;
    public static final int RESPAWN_POSITION_Y = 350;

    public static final int SCORE_BOARD_WIDTH = 50;
    public static final int SCORE_BOARD_POS_X = GAME_WINDOW_WIDTH - SCORE_BOARD_WIDTH;
    public static final int SCORE_BOARD_POS_Y = 20;

    public static final int WALL_BLOCK_SIZE = 10;

    /**
     * Speeds & frequencies
     */

    // General game mechanics
    public static final int FPS = 100;
    public static final long GRAPHICS_PROC_SLEEP_MILLIS = (long) (1000.0 / FPS);
    public static final int PPS = 50;
    public static final long MECHANICS_PROC_SLEEP_MILLIS = (long) (1000.0 / PPS);
    public static final int UPS = 5;
    public static final long SYNC_PROC_SLEEP_MILLIS = (long) (1000.0 / UPS);

    // Player
    public static final float PLAYER_SPEED = 200.0f;
    public static final float PLAYER_LOC_UPDATE_AMOUNT = PLAYER_SPEED / FPS;
    public static final int PLAYER_ANIMATION_SPEED = (int) ((70 * PLAYER_SPEED) / FPS);

    // Bullet
    public static final int BULLET_SPEED = 5;

    public static final float TEST_ERROR_ALLOWANCE = 0.0001f;

    /**
     * Misc.
     */

    public static final int BULLET_DAMAGE = 20;

    public static final int PLAYER_MAX_HEALTH = 100;

    public static final int PACKET_HEADER_BYTES = 2;
    public static final int PORT = 17001;

    public static final int KEY_NORTH = KeyEvent.VK_W;
    public static final int KEY_SOUTH = KeyEvent.VK_S;
    public static final int KEY_WEST = KeyEvent.VK_A;
    public static final int KEY_EAST = KeyEvent.VK_D;

    public static final int MOUSE_CLICK = -1;

    /**
     * Images (locations, etc.)
     */

    public static final String IMG_PATH = "images/";

    // Ground
    public static final String GROUND_IMAGE_FILE_PATH = IMG_PATH + "mapSample.png";

    public static final Color DEFAULT_GROUND_COLOR = new Color(110, 133, 61);
    public static final Color DEFAULT_WALL_COLOR = Color.BLACK;

    // Player
    public static final int NUM_PLAYER_ANIMATION_IMAGES = 4;

    public static final String FRIENDLY_PLAYER_STANDING_IMAGE_FILE_PATH = IMG_PATH + "friendlyPlayer/standing.png";
    public static final String FRIENDLY_PLAYER_MOVING_IMAGE_FILE_PATH = IMG_PATH + "friendlyPlayer/moving";
    public static final String ENEMY_PLAYER_STANDING_IMAGE_FILE_PATH = IMG_PATH + "enemyPlayer/standing.png";
    public static final String ENEMY_PLAYER_MOVING_IMAGE_FILE_PATH = IMG_PATH + "enemyPlayer/moving";
    public static final String WALL_IMAGE_FILE_PATH = IMG_PATH + "wall/wall.png";
    public static final String MOVING_IMAGE_FILE_EXTENSION = ".png";

    /**
     * Sounds
     */

    public static final String GUN_SOUND_FILE_PATH = "sounds/gunshot.wav";

    /**
     * Wall positions
     */

    public static final int[] WALL_XS = {60, 60, 60, 60, 60, 60, 60};
    public static final int[] WALL_YS = {0, 10, 20, 30, 40, 50, 60};
}
