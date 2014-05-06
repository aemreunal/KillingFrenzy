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

	
    public byte type = 0;

    public static Packet fromByteArray(byte[] arr) {

        ByteArrayInputStream bis = new ByteArrayInputStream(arr);
        ObjectInput in = null;
        try {
            in = new ObjectInputStream(bis);
            Object o = in.readObject();
            return (Packet) o;
        } catch (IOException | ClassNotFoundException e) {
            if (e instanceof StreamCorruptedException) {
                System.out.println("Caught corrupted packet.");
                return null;
            }
            if (e instanceof EOFException) {
                System.out.println("eofexception : \n" + arr);
                return null;
            }
            e.printStackTrace();
        } finally {
            try {
                bis.close();
                if (in != null)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public byte getType() {
        return type;
    }

    public byte[] toByteArray() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(this);
            byte[] bytes = bos.toByteArray();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
