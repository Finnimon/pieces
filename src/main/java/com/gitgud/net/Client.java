package com.gitgud.net;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.io.Serializable;
import java.util.LinkedList;

public class Client extends Thread {
    private final ZMQ.Socket socket;

    private LinkedList<Serializable> messageQueue;

    private final String SERVER_ADRESS = "172.0.0.1";

    private boolean currentlySending;

    public Client(ZMQ.Socket socket)
    {
        this.socket = socket;
        currentlySending = false;
    }

    public void closeSocket()
    {
        this.socket.close();
    }

    public void addMessage(Serializable message)
    {
        this.messageQueue.add(message);
    }

    @Override
    public void run()
    {
        currentlySending = true;
        ClientController.getInstance().initialize();
        initComunikation();
        while (true)
        {
            if (messageQueue.isEmpty())
            {
                continue;
            } else
            {
                sendData();
            }
            if(currentlySending)
            {
                break;
            }
        }
    }

    private void sendData()
    {
        socket.send(messageQueue.removeFirst().toString());

    }

    private void initComunikation()
    {
        socket.connect("tcp://" + SERVER_ADRESS + ":8332");
    }
    public void stopSending()
    {
        currentlySending = false;
        closeSocket();
    }
}