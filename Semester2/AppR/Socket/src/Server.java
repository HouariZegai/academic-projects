import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static ServerSocket serverSocket;
    public static Socket socket;
    public static DataInputStream dataInputStream;
    public static DataOutputStream dataOutputStream;

    public static void main(String[] args) {
        // init socket
        (new Thread() {
            @Override
            public void run() {

                try {
                    serverSocket = new ServerSocket(1201);

                    while(true) {
                        socket = serverSocket.accept();
                        dataInputStream = new DataInputStream(socket.getInputStream());
                        dataOutputStream = new DataOutputStream(socket.getOutputStream());

                        System.out.println("Client(String): " + dataInputStream.readUTF());
                        System.out.println("Client(Int): " + dataInputStream.readInt());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Scanner mScanner = new Scanner(System.in);

        // read input
        System.out.print("Server: ");
        String msgToSend = mScanner.nextLine();

        // send msg
        try {
            dataOutputStream.writeUTF(msgToSend);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // read input
        System.out.print("Server: ");
        int numberToSend = mScanner.nextInt();

        // send msg
        try {
            dataOutputStream.writeInt(numberToSend);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
