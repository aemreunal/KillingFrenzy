package serverSide.gameMechanics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameMap {
    private Scanner scanner;
    private ArrayList<Integer> wallXCoordinates;
    private ArrayList<Integer> wallYCoordinates;

    public GameMap() {
        wallXCoordinates = new ArrayList<>();
        wallYCoordinates = new ArrayList<>();

        try {
            scanner = new Scanner(new File("Mapdata.txt"));

            while (scanner.hasNextInt()) {
                wallXCoordinates.add(scanner.nextInt());
                wallYCoordinates.add(scanner.nextInt());
            }

        } catch (FileNotFoundException e) {
            System.err.println("Map file not found! No walls will be present.");
//			e.printStackTrace();
        }
    }

    public Integer[] getWallXCoordinates() {
        Integer[] xCoordinates = new Integer[wallXCoordinates.size()];
        xCoordinates = wallXCoordinates.toArray(xCoordinates);
        return xCoordinates;
    }

    public Integer[] getWallYCoordinates() {
        Integer[] yCoordinates = new Integer[wallYCoordinates.size()];
        yCoordinates = wallYCoordinates.toArray(yCoordinates);
        return yCoordinates;
    }
}
