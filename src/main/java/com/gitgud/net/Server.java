package com.gitgud.net;
import org.zeromq.ZMQ;
public class Server extends Thread{

    private final ZMQ.Socket socket;

    public Server(ZMQ.Socket socket)
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

    @Override
    public void run()
    {
        ServerController.getInstance().initialize();
        waitForRequest();

    }


    private void waitForRequest()
    {

        sendAck();
    }

    private void sendAck()
    {

    }
}
