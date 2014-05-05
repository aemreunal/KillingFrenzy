package clientSide.graphics;

import clientSide.Settings;

import java.awt.*;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

public class Crosshair {
    public static void paint(Graphics g, float mouseX, float mouseY) {
        g.setColor(Color.RED);
        // Draw vertical line
        g.drawLine((int) mouseX, (int) (mouseY - (Settings.CROSSHAIR_SIZE >> 1)), (int) mouseX, (int) (mouseY + (Settings.CROSSHAIR_SIZE >> 1)));
        // Draw horizontal line
        g.drawLine((int) (mouseX - (Settings.CROSSHAIR_SIZE >> 1)), (int) mouseY, (int) (mouseX + (Settings.CROSSHAIR_SIZE >> 1)), (int) mouseY);
        // Draw oval
        g.drawOval((int) (mouseX - (Settings.CROSSHAIR_SIZE >> 1)), (int) (mouseY - (Settings.CROSSHAIR_SIZE >> 1)), Settings.CROSSHAIR_SIZE, Settings.CROSSHAIR_SIZE);
    }
}
