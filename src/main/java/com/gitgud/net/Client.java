package com.gitgud.net;

import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.model.activeGame.ActiveGame;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.io.Serializable;
import java.util.LinkedList;

public class Client extends Thread {
    private final ZMQ.Socket socket;

    private LinkedList<Serializable> messageQueue;

    private final String SERVER_ADRESS = "172.0.0.1";
    private final static int REQUEST_TIMEOUT = 2500;                  //  msecs, (> 1000!)
    private final static int REQUEST_RETRIES = 3;                     //  Before we abandon


    public Client(ZMQ.Socket socket)
    {
        this.socket = socket;
    }

    public void closeSocket()
    {
        this.socket.close();
    }

    public void addMessage(Serializable message)
    {
        messageQueue.add(message);
    }

    @Override
    public void run()
    {
        ClientController.getInstance().initialize();
        initComunikation(messageQueue);

        while (true) // todo ?
        {
            if (messageQueue.isEmpty())
            {
                continue;
            } else
            {
                sendData();

            }
        }
    }

    private void sendData()
    {
        socket.send(messageQueue.removeFirst().toString());
        try (ZContext context = new ZContext())
        {
            int sequence = 0;
            int retriesLeft = REQUEST_RETRIES;
            ZMQ.Poller poller = context.createPoller(1);
            poller.register(socket, ZMQ.Poller.POLLIN);
            while (retriesLeft > 0)
            {
                String data = messageQueue.removeFirst().toString().format("%d", ++sequence);
                socket.send(data);
            }
        }
    }

    private <T> void initComunikation(T message)
    {
        socket.connect("tcp://" + SERVER_ADRESS + ":8332");
    }
}