import java.rmi.Remote;
import java.rmi.RemoteException;
public interface Adder extends Remote {
    public boolean loginUser(String username, String password) throws RemoteException;
    public boolean signUp(String username, String password) throws RemoteException;

}
