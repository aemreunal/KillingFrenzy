package clientSide.attributes;

import global.Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/*
 * This code belongs to:
 * Erdi Gultekin
 */

public class Wall extends Entity {
    private BufferedImage wallBlockImage;
    private boolean errorOccurred = false;

    public Wall(float xCoor, float yCoor) {
        physAttr.setxCoor(xCoor);
        physAttr.setyCoor(yCoor);
        try {
            wallBlockImage = ImageIO.read(new File(Settings.WALL_IMAGE_FILE_PATH));
        } catch (IOException e) {
            System.err.println("Unable to load wall image!");
            System.err.println("Loading plain black for walls instead.");
            errorOccurred = true;
        }
    }

    @Override
    public void paint(Graphics g) {
        if (errorOccurred) {
            g.setColor(Settings.DEFAULT_WALL_COLOR);
            g.fillRect((int) physAttr.getxCoor(), (int) physAttr.getyCoor(), Settings.WALL_BLOCK_SIZE, Settings.WALL_BLOCK_SIZE);
        } else {
            g.drawImage(wallBlockImage, (int) physAttr.getxCoor(), (int) physAttr.getyCoor(), Settings.WALL_BLOCK_SIZE, Settings.WALL_BLOCK_SIZE, null);
            System.out.println("X is: " + physAttr.getxCoor() + " Y is: " + physAttr.getyCoor());
        }
    }
}
