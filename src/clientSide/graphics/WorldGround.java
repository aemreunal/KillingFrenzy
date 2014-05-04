package clientSide.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import clientSide.attributes.Settings;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

public class WorldGround {
    private static boolean errorOcurred = false;
    
    public static void paint(Graphics g) {
        try {
            BufferedImage img = ImageIO.read(new File(Settings.GROUND_TILE_FILE));
            int width = img.getWidth();
            int height = img.getHeight();
            for (int x = 0; x < Settings.GAME_WINDOW_WIDTH; x += width) {
                for (int y = 0; y < Settings.GAME_WINDOW_HEIGHT; y += height) {
                    g.drawImage(img, x, y, width, height, null);
                }
            }
        } catch (IOException e) {
            if (!errorOcurred) {
                System.err.println("Unable to load image!");
                System.err.println("Loading plain green background instead.");
                errorOcurred = true;
            }
            g.setColor(Settings.DEFAULT_GROUND_COLOR);
            g.fillRect(0, 0, Settings.GAME_WINDOW_WIDTH, Settings.GAME_WINDOW_HEIGHT);
        }
    }
}
