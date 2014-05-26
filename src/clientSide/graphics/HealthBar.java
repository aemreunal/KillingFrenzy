package clientSide.graphics;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

import global.Settings;

import java.awt.*;

public class HealthBar {

    public static void paint(Graphics g, float health) {
        g.setColor(Color.WHITE);
        g.drawRect(Settings.HEALTH_BAR_POS_X, Settings.HEALTH_BAR_POS_Y, Settings.HEALTH_BAR_WIDTH, Settings.HEALTH_BAR_HEIGHT);
        g.setColor(Color.RED);
        int healthBarWidth = (int) ((Settings.HEALTH_BAR_WIDTH - 2) * (health / Settings.PLAYER_MAX_HEALTH));
        g.fillRect(Settings.HEALTH_BAR_POS_X + 1, Settings.HEALTH_BAR_POS_Y + 1, healthBarWidth, Settings.HEALTH_BAR_HEIGHT - 2);
    }
}
