package com.gitgud.net;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class ServerController {

    private final Server server;
    private static ServerController instance = null;
    private static final ZContext context = new ZContext();//todo should not work. Only one ZContext is allowed.
    // Is it possible to use just one Controller Singleton Class for Server and Client?
    // Then there would also just be one of the two?

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
