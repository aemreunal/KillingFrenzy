package serverSide.gamemechanics;

import java.util.ArrayList;
import java.util.Scanner;

import packets.CreateEntityPacket;
import packets.Packet;
import clientSide.Client;
import serverSide.server.Server;

public class Game {
	private Server server;
	private ArrayList<Client> clients;
	private GameLogic gameLogic;
	
	public Game(Server server) {
		this.server = server;
		clients = new ArrayList<Client>();
	}
	
	public void updateClients() {
		for (Client client: clients) {
			if (!client.packetQueue.isEmpty()) {
				Packet packet = client.packetQueue.poll();
				if (packet.type == 4) {
					World.getInstance().addEntity(new Player());
					CreateEntityPacket toSend = new CreateEntityPacket();			
					toSend.entityType = 0; 
					server.broadcast(toSend);
				}
			}
		}
	}
	
	public void run() {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			scanner.next();
			server.broadcast(new CreateEntityPacket());
			updateClients();
			
			// UPDATE LOGIC
			// BROADCAST RESULTS
			
		}
	}

}
