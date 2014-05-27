package clientSide.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * Eren Sezener
 * Deniz Sokmen
 * Erdi Gultekin
 */

public class BlankCursor {
    private static Cursor blankCursor;

    public static Cursor getBlankCursor() {
        if (blankCursor == null) {
            blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "blank cursor");
        }
        return blankCursor;
    }
}
