package com.gitgud.net;

import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.model.activeGame.ActiveGame;
import org.zeromq.ZMQ;

import java.io.Serializable;
import java.util.LinkedList;

public class Client extends Thread{
    private final ZMQ.Socket socket;

    private LinkedList<Serializable> messageQueue;

    private final String SERVER_ADRESS = "172.0.0.1";


    public Client(ZMQ.Socket socket)
    {
        this.socket = socket;
    }

    public ZMQ.Socket getSocket()
    {
        return socket;
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
        while(true)
        {
          sendData();
        }
    }

    private void sendData()
    {
        socket.send(messageQueue.getFirst().toString());
    }

    private <T> void initComunikation(T message)
    {
        socket.connect(SERVER_ADRESS + "");
    }
}
