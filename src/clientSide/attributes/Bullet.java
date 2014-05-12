package clientSide.attributes;

import java.awt.*;

/*
 * This code belongs to:
 * Erdi Gultekin
 */

public class Bullet extends Entity {
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval((int) physAttr.getxCoor(), (int) physAttr.getyCoor(), 2, 2);
    }
}
