package serverSide.packethandlers;

import packets.AngleUpdatePacket;
import packets.Packet;
import serverSide.client.Client;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * Eren Sezener
 * Deniz Sokmen
 * Erdi Gultekin
 */

public class AngleUpdateHandler extends PacketHandler {

    @Override
    public void handle(Client client, Packet pk) {
        if (client.player == null) {
            return;
        }
        AngleUpdatePacket packet = (AngleUpdatePacket) pk;
        // System.out.println("Packet geldi : " + packet.angle);
        client.player.physicalAttributes.angle = packet.angle;
    }

}
