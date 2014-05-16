package serverSide.packethandlers;

import packets.BulletShotPacket;
import packets.Packet;
import serverSide.client.Client;
import serverSide.gamemechanics.Bullet;
import serverSide.gamemechanics.World;

public class BulletShotHandler extends PacketHandler {

    @Override
    public void handle(Client client, Packet pk) {
        if (client.player == null) {
            return;
        }
        BulletShotPacket packet = (BulletShotPacket) pk;

        Bullet bullet = client.player.fireGun(packet.angle, packet.xCoor, packet.yCoor);

//        Bullet bullet = new Bullet(packet.angle, packet.xCoor, packet.yCoor);
        World.getInstance().addEntity(bullet);
        client.getServer().broadcast(bullet.getCreationPacket());
    }

}
