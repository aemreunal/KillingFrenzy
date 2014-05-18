package serverSide.gamemechanics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameMap {
	private static Scanner scanner;
	private static int[] wallXCoordinates = new int[257];
	private static int[] wallYCoordinates = new int[257];
	public GameMap() {

		try {
			scanner = new Scanner(new File("Mapdata.txt"));

			int i = 0;
			while (scanner.hasNextInt()) {
				wallXCoordinates[i] = scanner.nextInt();
				wallYCoordinates[i] = scanner.nextInt();
				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public int[] getWallXCoordinates() {
		return wallXCoordinates;
	}

	public int[] getWallYCoordinates() {
		return wallYCoordinates;
	}
}