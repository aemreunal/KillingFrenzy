package clientSide.graphics;

import java.awt.Color;
import java.awt.Graphics;

import clientSide.attributes.Settings;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

public class Player {
    public static void paint(Graphics g, float playerX, float playerY) {
        g.setColor(Color.BLACK);
        g.fillRect((int) playerX, (int) playerY, Settings.PLAYER_SIZE, Settings.PLAYER_SIZE);
    }
}
