import java.net.MalformedURLException;
import java.rmi.*;

public class Client {
    public static void main(String[] args) {
        try {
            Moeda moeda = new Moeda("Real", 2.5);
            InterfaceRemota ir = (InterfaceRemota) Naming.lookup("rmi://localhost/InterfaceRemota");
            ir.adicionarMoeda(moeda);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
