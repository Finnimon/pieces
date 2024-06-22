package com.gitgud.net;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class ClientController {
    private final Client client;
    private static ClientController instance = null;

    public ClientController(Client client)
    {
        this.client = client;
    }
    public static ClientController getInstance()
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
            ZMQ.Socket socket = context.createSocket(SocketType.CLIENT);
            instance = new ClientController(new Client(socket));
        }
    }
}
