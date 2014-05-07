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
    private float health;
    private boolean isAlive;

    public Player() {
        this.isAlive = true;
    }

    public void decreaseHealth(float damage) {
        health -= damage;
        if (health < 0){
            die();
        }
    }



    private void die() {
        isAlive = false;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect((int) physicalAttributes.getxCoor(), (int) physicalAttributes.getyCoor(), 10, 10);
    }
}
