package clientSide.processors;

import clientSide.Client;
import clientSide.GamePanel;
import clientSide.Settings;
import clientSide.attributes.Player;
import clientSide.attributes.World;
import clientSide.graphics.WorldGround;
import packets.AngleUpdatePacket;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

public class GraphicsProcessor extends Thread implements Runnable {
    private Client client;
    private GamePanel panel;
    private WorldGround worldGround;

    public GraphicsProcessor(Client client, GamePanel panel) {
        this.client = client;
        this.panel = panel;
        worldGround = new WorldGround();
//        panel.addEntity(worldGround);
    }

    @Override
    public void run() {
        while (true) {
            panel.repaint();
            sendPlayerAngleUpdate();
            try {
                TimeUnit.MILLISECONDS.sleep(Settings.GRAPHICS_PROC_SLEEP_MILLIS);
            } catch (InterruptedException e) {
                System.err.println("Graphics processor thread sleep is interrupted!");
                e.printStackTrace();
            }
        }
    }

    private void sendPlayerAngleUpdate() {
        Player thisPlayer = World.getThisPlayer();
        if (thisPlayer != null) {
            client.sendPacket(new AngleUpdatePacket(thisPlayer.getAngle()));
        }
    }

    public static BufferedImage rotate(BufferedImage img, double angle, float width, float height) {
        double sin = Math.abs(Math.sin(angle));
        double cos = Math.abs(Math.cos(angle));
        int newWidth = (int) Math.floor(width * cos + height * sin);
        int newHeight = (int) Math.floor(height * cos + width * sin);

        BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = rotatedImage.createGraphics();
        g.rotate(angle, width / 2, height / 2);
        g.drawRenderedImage(img, null);
        g.dispose();
        return rotatedImage;
    }
}
