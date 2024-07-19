package com.gitgud.net;

import org.zeromq.ZMQ;

import java.io.Serializable;
import java.util.LinkedList;


public class Client extends Thread
{
    private final ZMQ.Socket socket;
    
    
    private final LinkedList<Serializable> messageQueue = new LinkedList<>();
    
    
    private String serverAddress;
    
    
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
        try
        {
            this.messageQueue.add(message);
        }
        catch (NullPointerException e)
        {
            addMessage(-1);
        }
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
            }
            else
            {
                sendData();
            }
            if (currentlySending)
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
        socket.connect("tcp://" + serverAddress + ":5555");
        
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