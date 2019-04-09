import java.rmi.*;
import java.util.Vector;

public interface InterfaceRemota extends Remote {
    public void adicionarMoeda(Moeda moeda) throws RemoteException;
    public void excluirMoeda(Moeda moeda) throws RemoteException;
    public void editarMoeda(Moeda moeda, double cotacao) throws RemoteException;
    public Vector<String> getMoedas() throws RemoteException;
}
