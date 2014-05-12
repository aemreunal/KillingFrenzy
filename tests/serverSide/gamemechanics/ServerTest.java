package serverSide.gamemechanics;

import java.net.BindException;

import org.junit.Test;

import serverSide.server.Server;
import static org.junit.Assert.*;

public class ServerTest {

    @Test
    public void testServerRunning() throws InterruptedException, BindException {
        Server server = new Server();
        server.run();
        //Thread.sleep(2000);
        assertEquals(Server.State.RUNNING, server.getState().get());
    } 
}
