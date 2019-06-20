import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {

    public static Socket socket;
    public static DataInputStream dataInputStream;
    public static DataOutputStream dataOutputStream;

    public static void main(String[] args) {
        // init socket
        (new Thread() {
            @Override
            public void run() {
                try {
                    socket = new Socket("127.0.0.1", 1201);

                    while(true) {
                        dataInputStream = new DataInputStream(socket.getInputStream());
                        dataOutputStream = new DataOutputStream(socket.getOutputStream());

                        System.out.println("Server(String): " + dataInputStream.readUTF());
                        System.out.println("Server(Int): " + dataInputStream.readInt());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
