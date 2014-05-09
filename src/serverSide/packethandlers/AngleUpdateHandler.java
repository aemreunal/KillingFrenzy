package serverSide.packethandlers;

import packets.AngleUpdatePacket;
import packets.KeyPressPacket;
import packets.Packet;
import serverSide.client.Client;
import serverSide.gamemechanics.World;

public class AngleUpdateHandler extends PacketHandler {

    @Override
    public void handle(Client client, Packet pk) {
        if (client.player == null)
            return;

        AngleUpdatePacket packet = (AngleUpdatePacket) pk;

        client.player.physicalAttributes.angle = packet.angle;
    }

}
