import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemote extends Remote {
    String getMsgFromServer() throws RemoteException;
}
