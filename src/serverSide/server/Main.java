package serverSide.server;

import serverSide.gamemechanics.Game;

public class Main {
	public static void main(String[] args) {
		Server server = new Server();
		new Thread(server).start();
		Game game = new Game(server);
		game.run();
	}
}
