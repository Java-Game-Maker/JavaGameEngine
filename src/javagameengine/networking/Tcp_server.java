package javagameengine.networking;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Tcp_server extends Thread {
    public ServerSocket socket;     // Socket to listen on
    public Socket       i_socket;   // Start listening on socket

    public Tcp_server()
    {
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
