package clientSide.attributes;

import java.awt.Color;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

public class Settings {
    public static final int GAME_WINDOW_WIDTH = 500;
    public static final int GAME_WINDOW_HEIGHT = 300;
    
    // Refresh rate unit is FPS
    public static final int REFRESH_RATE = 100;
    
    public static final int PLAYER_SPEED = 5;
    public static final int BULLET_SPEED = 5;
    
    public static final int CROSSHAIR_SIZE = 12;
    
    public static final int PLAYER_SIZE = 10;
    
    public static final String IMG_PATH = "images/";
    
    public static final String GROUND_TILE_NAME = "grass.jpg";
    public static final String GROUND_TILE_FILE = IMG_PATH + GROUND_TILE_NAME;
    public static final Color DEFAULT_GROUND_COLOR = new Color(110, 133, 61);
}
