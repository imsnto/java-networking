package networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread implements Runnable{
    Thread t;
    Socket socket;
    public ServerThread(Socket socket){
        this.socket = socket;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {

            while (true) {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                // read from client
                Object clientMsg = ois.readObject();
                System.out.println("From client " + (String) clientMsg);

                String serverMsg = (String) ((String) clientMsg).toUpperCase();
                // send to client
                oos.writeObject(serverMsg);
            }
        }catch (RuntimeException | ClassNotFoundException | IOException e){
            e.getStackTrace();
        }
    }

}

class ServerT {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(22222);
        System.out.println("Server stared..");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("client connected..");
            new ServerThread(socket);
        }
    }
}