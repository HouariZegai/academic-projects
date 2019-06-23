import java.rmi.RemoteException;

public class RemoteImpl implements IRemote {
    @Override
    public String getMsgFromServer() throws RemoteException {
        return "Hello World from server!";
    }
}
