package clientSide.graphics;

import java.awt.*;


/*
 * This code belongs to:
 * Erdi Gultekin
 */

public class Bullet {
    public static void paint(Graphics g, float bulletX, float bulletY) {
        g.setColor(Color.BLACK);
        int x = (int) bulletX;
        int y = (int) bulletY;
        g.fillOval(x, y, 2, 2);

    }
}
