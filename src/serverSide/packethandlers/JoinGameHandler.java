package serverSide.packethandlers;

import packets.CreateEntityPacket;
import packets.Packet;
import serverSide.client.Client;
import serverSide.gamemechanics.Entity;
import serverSide.gamemechanics.PhysicalAttributes;
import serverSide.gamemechanics.Player;
import serverSide.gamemechanics.World;
import serverSide.server.Server;
import global.Settings;

public class JoinGameHandler extends PacketHandler {
    private Server server;

    public JoinGameHandler(Server server) {
        this.server = server;
    }

    @Override
    public void handle(Client client, Packet pk) {
        sendMapInfo(client);

        Player player = new Player();
        World.getInstance().addEntity(player);
        client.setPlayer(player);
        player.physicalAttributes = new PhysicalAttributes(20.0f, 20.0f, Settings.PLAYER_SIZE, Settings.PLAYER_SIZE);
        sendNewPlayer(client, player);
    }

    private void sendNewPlayer(Client client, Player player) {
        CreateEntityPacket toSend = player.getCreationPacket();
        toSend.isMine = true;
        client.sendPacket(toSend);
        toSend.isMine = false;
        server.broadcast(toSend, client);
    }

    private void sendMapInfo(Client client) {
        for (Entity e : World.getInstance().idToEntityMap.values()) {
            client.sendPacket(e.getCreationPacket());
        }
    }

}
