package com.gitgud.net;

import org.zeromq.ZMQ;
import org.zeromq.ZMQException;

import java.io.Serializable;
import java.util.LinkedList;

public class Client extends Thread {
    private final ZMQ.Socket socket;

    private LinkedList<Serializable> messageQueue;

    private String serverAddress = "tcp://172.0.0.1:5555";

    private boolean currentlySending;

    private boolean isConnected = false;

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
        socket.connect("tcp://" + serverAddress + ":8332");

        isConnected = true;
    }

    public void stopSending()
    {
        isConnected = false;
        currentlySending = false;
        closeSocket();
    }

    public void setServerAddress(String serverAddress)
    {
        this.serverAddress = serverAddress;
    }
    public boolean isConnected()
    {
        return isConnected;
    }

    public void setConnected(boolean connected)
    {
        isConnected = connected;
    }
}