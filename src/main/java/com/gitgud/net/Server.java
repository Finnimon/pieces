package com.gitgud.net;

import org.zeromq.ZMQ;


import java.io.Serializable;
import java.util.LinkedList;

public class Server extends Thread {

    private static final int TIME_TO_WAIT_WHILE_RECEIVING_MESSAGE = 50;
    private static final String TCP_8332 = "tcp://172.0.0.1:5555";
    private static final int FAIL_CODE = -1;
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
        //socket.bind(TCP_8332);
        while (currentlyReceiving)
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


    private void waitForRequest() throws InterruptedException
    {
        String recMessage;
        do
        {
            recMessage = socket.recvStr();
            Thread.sleep(TIME_TO_WAIT_WHILE_RECEIVING_MESSAGE);
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
        try
        {
            return messageQueue.remove();
        }
        catch(NullPointerException e)
        {
            return FAIL_CODE;
        }

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
