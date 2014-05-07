package packets;

import java.io.*;

/* Packet ID (type)  - Packet Name
 * 0x00 - KeyPressPacket
 * 0x01 - KeyReleasePacket
 * 0x02 - CreateEntityPacket
 * 0x03 - UpdateEntityPacket
 * 0x04 - JoinGamePacket
 */

public abstract class Packet implements Serializable {
    private byte type = 0;

    protected Packet(byte type) {
        this.type = type;
    }

    public static Packet fromByteArray(byte[] arr) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(arr);

        try {
            ObjectInput in = new ObjectInputStream(inputStream);
            Object o = in.readObject();
            inputStream.close();
            if (in != null)
                in.close();
            return (Packet) o;
        } catch (IOException | ClassNotFoundException e) {
            if (e instanceof StreamCorruptedException) {
                System.err.println("Caught corrupted packet.");
                return null;
            }
            if (e instanceof EOFException) {
                System.err.println("EOFException : \n" + arr);
                return null;
            }
            e.printStackTrace();
        }
        return null;
    }

    public byte[] toByteArray() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ObjectOutput output = new ObjectOutputStream(outputStream);
            output.writeObject(this);
            byte[] bytes = outputStream.toByteArray();
            output.close();
            outputStream.close();
            return bytes;
        } catch (IOException e) {
            System.err.println("IOException occurred when trying to open/close ByteArrayOutputStream/ObjectOutput");
            e.printStackTrace();
        }
        return null;
    }

    public byte getType() {
        return type;
    }
}
