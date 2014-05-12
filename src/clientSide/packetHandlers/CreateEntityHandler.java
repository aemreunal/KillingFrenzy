package clientSide.packetHandlers;

import clientSide.attributes.Player;
import clientSide.attributes.World;
import packets.CreateEntityPacket;
import packets.Packet;

public class CreateEntityHandler extends PacketHandler {

    @Override
    public void handle(Packet pk) {
        CreateEntityPacket packet = (CreateEntityPacket) pk;
        Player player = Player.createPlayer(packet.isMine);
        player.getPhysAttr().setxCoor(packet.x);
        player.getPhysAttr().setyCoor(packet.y);
        player.getPhysAttr().setAngle(packet.angle);
//        System.out.println(packet.isMine);
        synchronized (World.getInstance().getEntityMap()) {
            World.getInstance().addEntity(player, packet.entityID);
        }
    }
}
