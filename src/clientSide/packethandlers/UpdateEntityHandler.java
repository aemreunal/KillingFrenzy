package clientSide.packethandlers;

import clientSide.processors.GameMechanicsProcessor;
import packets.Packet;
import packets.UpdateEntityPacket;

public class UpdateEntityHandler extends PacketHandler {

    private GameMechanicsProcessor gameMechanics;

    public UpdateEntityHandler(GameMechanicsProcessor gameMechanics) {
        this.gameMechanics = gameMechanics;
    }

    @Override
    public void handle(Packet pk) {
        UpdateEntityPacket packet = (UpdateEntityPacket) pk;
        gameMechanics.updateEntity(packet);
    }

}
