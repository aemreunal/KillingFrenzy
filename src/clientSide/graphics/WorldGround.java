package clientSide.graphics;

import clientSide.Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

public class WorldGround {
    private static BufferedImage groundImage;
    private static int width;
    private static int height;
    private static boolean errorOccurred = false;

    public WorldGround() {
        try {
            groundImage = ImageIO.read(new File(Settings.GROUND_IMAGE_FILE_PATH));
            width = groundImage.getWidth();
            height = groundImage.getHeight();
        } catch (IOException e) {
            if (!errorOccurred) {
                System.err.println("Unable to load image!");
                System.err.println("Loading plain green background instead.");
                errorOccurred = true;
            }
        }
    }

    public void paint(Graphics g) {
        if (!errorOccurred) {
            for (int x = 0; x < Settings.GAME_WINDOW_WIDTH; x += width) {
                for (int y = 0; y < Settings.GAME_WINDOW_HEIGHT; y += height) {
                    g.drawImage(groundImage, x, y, width, height, null);
                }
            }
        } else {
            g.setColor(Settings.DEFAULT_GROUND_COLOR);
            g.fillRect(0, 0, Settings.GAME_WINDOW_WIDTH, Settings.GAME_WINDOW_HEIGHT);
        }
    }
}
