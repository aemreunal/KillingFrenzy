package clientSide.graphics;

import java.awt.Graphics;

public class Map {
    public static void printMap(Graphics g) {
        Wall wall = new Wall();
        Wall.paintWalls(g, 55, 0, true, 7);
        Wall.paintWalls(g, 110, 40, true, 7);
        Wall.paintWalls(g, 120, 100, false, 5);
        Wall.paintWalls(g, 170, 0, true, 6);
        Wall.paintWalls(g, 240, 40, true, 6);
        Wall.paintWalls(g, 240, 55, false, 7);
        Wall.paintWalls(g, 300, 65, true, 4);
        Wall.paintWalls(g, 360, 40, true, 5);
        Wall.paintWalls(g, 360, 40, true, 5);
        Wall.paintWalls(g, 370, 80, false, 5);
        Wall.paintWalls(g, 420, 50, true, 6);
        Wall.paintWalls(g, 520, 40, false, 7);
        Wall.paintWalls(g, 580, 50, true, 6);
        Wall.paintWalls(g, 480, 100, false, 4);
        Wall.paintWalls(g, 510, 110, true, 7);
        Wall.paintWalls(g, 510, 180, false, 4);
        Wall.paintWalls(g, 600, 200, false, 4);
        Wall.paintWalls(g, 600, 200, false, 4);
        Wall.paintWalls(g, 60, 150, false, 7);
        Wall.paintWalls(g, 80, 160, true, 12);
        Wall.paintWalls(g, 90, 240, false, 9);
        Wall.paintWalls(g, 170, 220, true, 7);
        Wall.paintWalls(g, 180, 240, false, 2);
        Wall.paintWalls(g, 180, 150, false, 5);
        Wall.paintWalls(g, 270, 150, false, 5);
        Wall.paintWalls(g, 380, 150, false, 6);
        Wall.paintWalls(g, 330, 200, false, 6);
        Wall.paintWalls(g, 260, 240, false, 16);
        Wall.paintWalls(g, 40, 380, false, 11);
        Wall.paintWalls(g, 80, 320, true, 6);
        Wall.paintWalls(g, 235, 310, false, 12);
        Wall.paintWalls(g, 350, 290, true, 3);
        Wall.paintWalls(g, 210, 370, false, 9);
        Wall.paintWalls(g, 370, 360, false, 5);
        Wall.paintWalls(g, 400, 410, false, 6);
        Wall.paintWalls(g, 460, 370, true, 7);
        Wall.paintWalls(g, 560, 380, false, 6);
        Wall.paintWalls(g, 530, 390, false, 6);
        Wall.paintWalls(g, 460, 280, false, 6);
        Wall.paintWalls(g, 530, 280, false, 6);
        Wall.paintWalls(g, 520, 260, true, 7);
        Wall.paintWalls(g, 510, 330, false, 6);
    }
}
