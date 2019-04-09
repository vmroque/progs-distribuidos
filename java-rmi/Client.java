import java.net.MalformedURLException;
import java.rmi.*;

public class Client {
    public static void main(String[] args) {
        try {
            Moeda m1 = new Moeda("Real", 2.5);
            Moeda m2 = new Moeda("Euro", 1.5);
            Moeda m3 = new Moeda("Dolar", 4.5);
            Moeda m4 = new Moeda("Peso", 2.3);

            InterfaceRemota ir = (InterfaceRemota) Naming.lookup("rmi://localhost/InterfaceRemota");

            ir.adicionarMoeda(m1);
            ir.adicionarMoeda(m2);
            ir.adicionarMoeda(m3);
            ir.adicionarMoeda(m4);

            ir.excluirMoeda(m3);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
