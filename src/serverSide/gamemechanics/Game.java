package serverSide.gamemechanics;

import java.util.ArrayList;

import packets.Packet;
import clientSide.Client;
import serverSide.server.Server;

public class Game {
	private Server server;
	private ArrayList<Client> clients;
	
	public Game(Server server) {
		this.server = server;
	}
	
	public void updateClients() {
		for (Client client: clients) {
			if (!client.packetQueue.isEmpty()) {
				Packet packet = client.packetQueue.poll();
				// bi seyler yap
			}
		}
	}
	
	public void run() {
		while(true) {
			updateClients();
			
			// DISPATCH PACKETS
			// UPDATE LOGIC
			// BROADCAST RESULTS
			
		}
	}

}
