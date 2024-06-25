package com.gitgud.net;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class ServerController {

    private final Server server;
    private static ServerController instance = null;
    private static final ZContext context = new ZContext();

    public ServerController(Server server)
    {
        this.server = server;
    }

    public static ServerController getInstance()
    {
        if (instance == null)
        {
                ZMQ.Socket socket = context.createSocket(SocketType.REP);
                instance = new ServerController(new Server(socket));
        }
        return instance;
    }

    public Server getServer()
    {
        return server;
    }
}
