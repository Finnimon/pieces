package com.gitgud.pieces.control.Net;

import com.gitgud.net.Client;
import com.gitgud.net.Server;
import com.gitgud.net.ServerClientController;


/**
 * @author Julius Rohe
 * @version 0.1
 * @Owner: Julius Rohe
 * @since 23.06.2024
 */
public class ArenaController
{
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
