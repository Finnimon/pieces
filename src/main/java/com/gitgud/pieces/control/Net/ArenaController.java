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

        Server server = ServerClientController.getInstance().getServer();
        server.start();
    }
    public static void InitialiseArenaAsServer(String serverAddress)
    {
        Server server = ServerClientController.getInstance().getServer();
        server.start();
        Client client = ServerClientController.getInstance().getClient();
        client.setServerAddress(serverAddress);
        client.start();
    }
}
