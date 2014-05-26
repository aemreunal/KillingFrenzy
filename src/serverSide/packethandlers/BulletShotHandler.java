package serverSide.packethandlers;

import packets.BulletShotPacket;
import packets.Packet;
import serverSide.client.Client;
import serverSide.gamemechanics.Bullet;

public class BulletShotHandler extends PacketHandler {

    @Override
    public void handle(Client client, Packet pk) {
        if (client.player == null) {
            return;
        }
        BulletShotPacket packet = (BulletShotPacket) pk;

        Bullet bullet = client.player.fireGun(client.player.getId(), packet.angle, packet.xCoor, packet.yCoor);
        client.getServer().broadcast(bullet.getCreationPacket());
    }

}
