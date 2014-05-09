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
        printMap(g);
    }

	private void printMap(Graphics g) {
		Wall.paintWalls(g, 55, 0, true, 7);
		Wall.paintWalls(g, 110, 40, true, 7);
		Wall.paintWalls(g, 120, 100, false, 5);
		Wall.paintWalls(g, 170, 0, true, 6);
		Wall.paintWalls(g, 240, 40, true, 6);
		Wall.paintWalls(g, 240, 55, false, 7);
		Wall.paintWalls(g, 300, 65, true, 4);
		Wall.paintWalls(g, 360, 40, true, 5);
		Wall.paintWalls(g, 360, 40, true, 5);
		Wall.paintWalls(g, 370, 80, false, 5);
		Wall.paintWalls(g, 420, 50, true, 6);
		Wall.paintWalls(g, 520, 40, false, 7);
		Wall.paintWalls(g, 580, 50, true, 6);
		Wall.paintWalls(g, 480, 100, false, 4);
		Wall.paintWalls(g, 510, 110, true, 7);
		Wall.paintWalls(g, 510, 180, false, 4);
		Wall.paintWalls(g, 600, 200, false, 4);
		Wall.paintWalls(g, 600, 200, false, 4);
	}
}
