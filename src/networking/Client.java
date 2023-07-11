package networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("client started..");
        Socket socket = new Socket("127.0.0.1", 22222);

        System.out.println("server connected..");

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        String clientMsg = sc.nextLine();
        oos.writeObject(clientMsg);
        // receive form server
        Object serverMsg = ois.readObject();
        System.out.println((String) serverMsg);

    }
}