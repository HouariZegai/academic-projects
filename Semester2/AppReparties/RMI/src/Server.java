import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public static void main(String[] args) {
        try {
            RemoteImpl remoteImpl = new RemoteImpl();
            IRemote objDis = (IRemote) UnicastRemoteObject.exportObject(remoteImpl, 22222);
            LocateRegistry.createRegistry(22222).bind("objDis", objDis);
            while (1==1) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
