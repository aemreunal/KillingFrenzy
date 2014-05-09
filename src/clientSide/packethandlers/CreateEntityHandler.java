package clientSide.packethandlers;

import clientSide.attributes.player.Player;
import clientSide.attributes.world.World;
import clientSide.processors.GameMechanicsProcessor;
import packets.CreateEntityPacket;
import packets.Packet;

public class CreateEntityHandler extends PacketHandler {

    private GameMechanicsProcessor gameMechanics;

    public CreateEntityHandler(GameMechanicsProcessor gameMechanics) {
        this.gameMechanics = gameMechanics;
    }

    @Override
    public void handle(Packet pk) {
        CreateEntityPacket packet = (CreateEntityPacket) pk;
        Player player = Player.createPlayer(packet.isMine);
        System.out.println(packet.isMine);
        synchronized (World.getInstance().getEntityMap()) {
            World.getInstance().addEntity(player, packet.entityID);
        }
    }
}
