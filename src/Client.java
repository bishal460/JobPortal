import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) {
        try {
            Adder add = (Adder) Naming.lookup("//localhost/MyServer");
            if(add.signUp("bishal","bishal")){
                System.out.println("Your account is created");
            }else{
                System.out.println("Your account is not created");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
