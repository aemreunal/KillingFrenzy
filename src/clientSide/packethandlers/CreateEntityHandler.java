package clientSide.packethandlers;

import clientSide.processors.GameMechanicsProcessor;
import packets.CreateEntityPacket;
import packets.Packet;
import packets.UpdateEntityPacket;

public class CreateEntityHandler extends PacketHandler{
    
    private GameMechanicsProcessor gameMechanics;
    
    public CreateEntityHandler(GameMechanicsProcessor gameMechanics) {
        this.gameMechanics = gameMechanics;
    }

    @Override
    public void handle(Packet pk) {
        CreateEntityPacket packet = (CreateEntityPacket) pk;
        gameMechanics.createEntity(packet);
    }
}
