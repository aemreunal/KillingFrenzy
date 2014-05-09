package clientSide.packethandlers;

import clientSide.attributes.player.Player;
import clientSide.attributes.world.World;
import clientSide.processors.GameMechanicsProcessor;
import packets.CreateEntityPacket;
import packets.DestroyEntityPacket;
import packets.Packet;
import packets.UpdateEntityPacket;

public class DestroyEntityHandler extends PacketHandler{
    
    private GameMechanicsProcessor gameMechanics;
    
    public DestroyEntityHandler(GameMechanicsProcessor gameMechanics) {
        this.gameMechanics = gameMechanics;
    }

    @Override
    public void handle(Packet pk) {
        DestroyEntityPacket packet = (DestroyEntityPacket) pk;
        
        World.getInstance().getEntity(packet.entityID).setAlive(false);
    }
}
