package clientSide.attributes.player;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

import clientSide.attributes.Entity;

import java.awt.*;

public class Player extends Entity {

    public Player(float xCoor, float yCoor, float angle) {
        physicalAttributes.setPosition(xCoor, yCoor, angle);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect((int) physicalAttributes.getxCoor(), (int) physicalAttributes.getyCoor(), 10, 10);
    }
}
