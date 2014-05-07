package serverSide.gamemechanics;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

import packets.CreateEntityPacket;
import packets.KeyPressPacket;
import packets.KeyReleasePacket;
import packets.Packet;
import packets.UpdateEntityPacket;
import serverSide.client.Client;
import serverSide.server.Server;

public class Game {
	private Server server;
	public CopyOnWriteArrayList<Client> clients;
	private GameLogic gameLogic;
	
	public Game(Server server) {
		this.server = server;
		clients = new CopyOnWriteArrayList<Client>();
	}
	
	public void updateClients() {
		for (Client client: clients) {
			if (!client.packetQueue.isEmpty()) {
				Packet packet = client.packetQueue.poll();
				
				if (packet.type == 0) {
					KeyPressPacket pressPacket = (KeyPressPacket) packet;
					client.keys[pressPacket.key] = true;
					System.out.println("Key pressed : "+pressPacket.key);
				}
				
				if (packet.type == 1) {
					KeyReleasePacket releasePacket = (KeyReleasePacket) packet;
					client.keys[releasePacket.key] = false;
					System.out.println("Key released : "+releasePacket.key);
				}
					
				if (packet.type == 4) {
					World.getInstance().addEntity(new Player(client));
					CreateEntityPacket toSend = new CreateEntityPacket();			
					toSend.entityType = 0; 
					server.broadcast(toSend);
				}
			}
			
			if (client.keys[68]) 
				server.broadcast(new UpdateEntityPacket());
		}
	}
	
	public void run() {
		while(true) {
			updateClients();
			
			// UPDATE LOGIC
			// BROADCAST RESULTS
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
