package serverSide.packethandlers;

import global.EntityType;
import packets.AngleUpdatePacket;
import packets.BulletShotPacket;
import packets.CreateEntityPacket;
import packets.Packet;
import serverSide.client.Client;
import serverSide.gamemechanics.Bullet;
import serverSide.gamemechanics.Player;
import serverSide.gamemechanics.World;

public class BulletShotHandler extends PacketHandler {

    @Override
    public void handle(Client client, Packet pk) {
        if (client.player == null) {
            return;
        }
        BulletShotPacket packet = (BulletShotPacket) pk;
        
        Bullet bullet = new Bullet();
        bullet.physicalAttributes.left = client.player.physicalAttributes.left;
        bullet.physicalAttributes.top = client.player.physicalAttributes.top;
        bullet.physicalAttributes.angle = packet.angle;
        World.getInstance().addEntity(bullet);
        client.getServer().broadcast(bullet.getCreationPacket());
    }

}
