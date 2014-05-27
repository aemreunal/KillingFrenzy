package serverSide.packethandlers;

import packets.Packet;
import serverSide.client.Client;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * Eren Sezener
 * Deniz Sokmen
 * Erdi Gultekin
 */

public abstract class PacketHandler {

    public abstract void handle(Client client, Packet pk);
}
