package javagameengine.networking;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class socket_handler implements Runnable {
    public Thread t;
    public ServerSocket socket;     // Socket to listen on
    public Socket       i_socket;   // Start listening on socket

    @Override
    public void run() {

    }

    public socket_handler(String _lineup_id)
    {
        t = new Thread(this, "t_client_" + _lineup_id);
        t.start();
        try {
            socket   = new ServerSocket(8074);
            i_socket = socket.accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public InputStream input;
    public BufferedReader reader;

    public BufferData read_client()
    {
        String data;
        BufferData parsed = new BufferData();
        try {
            input  = i_socket.getInputStream();

            reader = new BufferedReader(new InputStreamReader(input));
            data = reader.readLine();

            Gson json_parser = new Gson();

            parsed = json_parser.fromJson(data, BufferData.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return parsed;
    }
}

public class Tcp_server extends Thread {

    public Tcp_server()
    {
        Tcp_server thread = new Tcp_server();
        thread.start();
    }

    // Connected lineup ** List of connected clients **
    public int lineup_id = 0;

    public String client_joined()
    {
        lineup_id = lineup_id++;
        return Integer.toString(lineup_id);
    }

    public void client_left()
    {
        // We don't handle any queues. Meaning it doesn't matter what the ID is as long as they don't conflict.
        // So this is just a security measure to make sure that it doesn't happen.
        lineup_id = lineup_id++;
    }

    public void listenFor_lineup()
    {

    }

    public void read_lineup()
    {
    }
}
