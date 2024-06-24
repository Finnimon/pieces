package com.gitgud.net;
import org.zeromq.ZFrame;
import org.zeromq.ZMQ;
import org.zeromq.ZMsg;

import java.io.Serializable;
import java.util.LinkedList;

public class Server extends Thread{

    public static final int TIME_TO_WAIT_WHILE_RECEVING_MESSAGE = 50;
    private LinkedList<Serializable> messageQueue;
    private final ZMQ.Socket socket;

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
        ServerController.getInstance().initialize();
        socket.bind("tcp://*:8332");
        try
        {
            waitForRequest();

        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
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

    }

    public LinkedList<Serializable> getMessageQueue()
    {
        return messageQueue;
    }
    public Serializable getLatestUnprecedentedMessage()
    {
        return messageQueue.remove();
    }
}
