import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 22222);
            IRemote objDis = (IRemote) registry.lookup("objDis");

            System.out.println(objDis.getMsgFromServer());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
