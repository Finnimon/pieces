package com.gitgud.net;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class ServerController {

    private final Server server;
    private static ServerController instance = null;

    public ServerController(Server server)
    {
        this.server = server;
    }
    public static ServerController getInstance()
    {
        if (instance == null)
        {
            throw new IllegalStateException();
        }
        return instance;
    }

    public void initialize()
    {
        if (instance != null)
        {
            return;
        }
        try (ZContext context = new ZContext())
        {
            ZMQ.Socket socket = context.createSocket(SocketType.REP);
            instance = new ServerController(new Server(socket));
        }
    }

    public Server getServer()
    {
        return server;
    }
}
