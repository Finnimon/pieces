package com.gitgud.net;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class ClientController {
    private final Client client;
    private static ClientController instance = null;
    private static final ZContext context = new ZContext();
    public ClientController(Client client)
    {
        this.client = client;
    }
    public static ClientController getInstance()
    {
        if (instance == null)
        {

                ZMQ.Socket socket = context.createSocket(SocketType.CLIENT);
                instance = new ClientController(new Client(socket));

        }
        return instance;
    }

    public Client getClient()
    {
        return client;
    }
}
