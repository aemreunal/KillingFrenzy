package clientSide.graphics;

import java.awt.Color;
import java.awt.Graphics;


/*
 * This code belongs to:
 * Erdi Gultekin
 */

public class Bullet {
    public static void paint(Graphics g, float bulletX, float bulletY) {
        g.setColor(Color.BLACK);
        int x = (int) bulletX;
        int y = (int) bulletY;
        g.drawOval(x, y, 2, 2);
    }
}
