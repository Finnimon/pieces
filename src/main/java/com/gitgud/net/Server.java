package com.gitgud.net;
import org.zeromq.ZMQ;


import java.io.Serializable;
import java.util.LinkedList;

public class Server extends Thread{

    public static final int TIME_TO_WAIT_WHILE_RECEVING_MESSAGE = 50;
    public static final String TCP_8332 = "tcp://*:8332";
    private LinkedList<Serializable> messageQueue;
    private final ZMQ.Socket socket;
    private boolean currentlyReceiving;

    private boolean isConnected = false;

    public Server(ZMQ.Socket socket)
    {
        this.socket = socket;
    }

    public void closeSocket()
    {
        this.socket.close();
    }

    @Override
    public void run()
    {
        currentlyReceiving = true;
        ServerController.getInstance().initialize();
        socket.bind(TCP_8332);
        while(currentlyReceiving)
        {
            try
            {
                waitForRequest();

            } catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
    }


    private void waitForRequest()throws InterruptedException
    {
        String recMessage;
        do
        {
            recMessage = socket.recvStr();
            Thread.sleep(TIME_TO_WAIT_WHILE_RECEVING_MESSAGE);
        } while (recMessage == null);
        messageQueue.add(recMessage);
        checkStatus();
    }

    private void checkStatus()
    {
        isConnected = true;
    }

    public Serializable getLatestUnprecedentedMessage()
    {
        return messageQueue.remove();
    }
    public void stopReceiving()
    {
        isConnected = false;
        currentlyReceiving = false;
        closeSocket();
    }

    public boolean isConnected()
    {
        return isConnected;
    }
}
