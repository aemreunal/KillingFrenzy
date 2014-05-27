package serverSide.packethandlers;

import packets.KeyReleasePacket;
import packets.Packet;
import serverSide.client.Client;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * Eren Sezener
 * Deniz Sokmen
 * Erdi Gultekin
 */

public class KeyReleaseHandler extends PacketHandler {

    @Override
    public void handle(Client client, Packet pk) {
        if (client.player == null) {
            return;
        }

        KeyReleasePacket packet = (KeyReleasePacket) pk;
        client.player.onKeyReleased(packet.key);
    }
}
