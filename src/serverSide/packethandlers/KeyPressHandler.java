package serverSide.packethandlers;

import packets.KeyPressPacket;
import packets.Packet;
import serverSide.client.Client;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * Eren Sezener
 * Deniz Sokmen
 * Erdi Gultekin
 */

public class KeyPressHandler extends PacketHandler {

    @Override
    public void handle(Client client, Packet pk) {
        if (client.player == null) {
            return;
        }

        KeyPressPacket packet = (KeyPressPacket) pk;
        client.player.onKeyPressed(packet.key);
    }

}
