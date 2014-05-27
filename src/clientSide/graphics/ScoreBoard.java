package clientSide.graphics;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * Eren Sezener
 * Deniz Sokmen
 * Erdi Gultekin
 */

import global.Settings;

import java.awt.*;

public class ScoreBoard {
    public static void paint(Graphics g, int score) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawString(score + "", Settings.SCORE_BOARD_POS_X, Settings.SCORE_BOARD_POS_Y);
    }
}
