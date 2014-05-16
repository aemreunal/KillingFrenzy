package serverSide.packethandlers;

import global.Settings;
import packets.CreateEntityPacket;
import global.EntityType;
import packets.Packet;
import serverSide.client.Client;
import serverSide.gamemechanics.Entity;
import serverSide.gamemechanics.PhysicalAttributes;
import serverSide.gamemechanics.Player;
import serverSide.gamemechanics.World;
import serverSide.server.Server;

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
        player.physicalAttributes = new PhysicalAttributes((float) Math.random() * 100.0f, (float) Math.random() * 100.0f, Settings.PLAYER_SIZE, Settings.PLAYER_SIZE);
        sendNewPlayer(client, player);
    }

    private void sendNewPlayer(Client client, Player player) {
        CreateEntityPacket toSend = new CreateEntityPacket();
        toSend.x = player.physicalAttributes.left;
        toSend.y = player.physicalAttributes.top;
        toSend.angle = 10.0f;
        toSend.entityID = player.getId();
        toSend.entityType = EntityType.ENTITY_PLAYER;
        toSend.isMine = true;
        client.sendPacket(toSend);
        toSend.isMine = false;
        server.broadcast(toSend, client);
    }

    private void sendMapInfo(Client client) {
        for (Entity e : World.getInstance().idToEntityMap.values()) {
            CreateEntityPacket toSend = new CreateEntityPacket();
            toSend.x = e.physicalAttributes.left;
            toSend.y = e.physicalAttributes.top;
            toSend.angle = 10.0f;
            toSend.entityID = e.getId();
            toSend.entityType = EntityType.ENTITY_PLAYER;
            client.sendPacket(toSend);
        }
    }

}
