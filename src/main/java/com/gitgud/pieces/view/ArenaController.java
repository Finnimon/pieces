package com.gitgud.pieces.view;

import com.gitgud.net.Client;
import com.gitgud.net.ClientController;
import com.gitgud.net.Server;
import com.gitgud.net.ServerController;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ArenaController {
    public static void InitialiseArenaAsClient(String serverAddress)
    {
        Client client = ClientController.getInstance().getClient();
        client.start();
        client.setServerAddress(serverAddress);
        try
        {
            client.addMessage(InetAddress.getLocalHost().getHostAddress());
        }
        catch (UnknownHostException e)
        {
            throw new RuntimeException();
        }
        Server server = ServerController.getInstance().getServer();
        server.start();
    }
    public static void InitialiseArenaAsServer()
    {
        Server server= ServerController.getInstance().getServer();
        server.start();
        Client client = ClientController.getInstance().getClient();
        client.start();
        client.setServerAddress(server.getLatestUnprecedentedMessage().toString());
    }
}
