package com.gitgud.net;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class ServerClientController {
    private final Server server;
    private final Client client;
    private static ServerClientController instance = null;
    private static final ZContext context = new ZContext();//todo should not work. Only one ZContext is allowed.
    // Is it possible to use just one Controller Singleton Class for Server and Client?
    // Then there would also just be one of the two?

    public ServerClientController(Server server, Client client)
    {
        this.server = server;
        this.client = client;
    }

    public static ServerClientController getInstance()
    {
        if (instance == null)
        {
            ZMQ.Socket socket = context.createSocket(SocketType.REP);
            instance = new ServerClientController(new Server(socket), new Client(socket));
        }
        return instance;
    }

    public Server getServer()
    {
        return server;
    }
    public Client getClient()
    {
        return client;
    }
}


