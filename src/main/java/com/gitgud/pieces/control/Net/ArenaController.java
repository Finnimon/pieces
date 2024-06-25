package com.gitgud.pieces.control.Net;

import com.gitgud.net.*;
import org.zeromq.ZMQException;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ArenaController {
    public static void InitialiseArenaAsClient(String serverAddress)
    {
        Client client = ServerClientController.getInstance().getClient();
        client.setServerAddress(serverAddress);

        client.start();
        try
        {
            client.addMessage(InetAddress.getLocalHost().getHostAddress());
        }
        catch (UnknownHostException e)
        {
            throw new RuntimeException();
        }
        Server server = ServerClientController.getInstance().getServer();
        server.start();
    }
    public static void InitialiseArenaAsServer()
    {
        Server server = ServerClientController.getInstance().getServer();
        server.start();

    }
}
