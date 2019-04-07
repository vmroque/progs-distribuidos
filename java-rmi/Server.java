import java.rmi.*;
import java.rmi.server.*;

public class Server extends UnicastRemoteObject implements InterfaceRemota {
    ServerGUI gui;

    public Server(ServerGUI gui) throws RemoteException {
        this.gui = gui;
    }

    public void adicionarMoeda(Moeda moeda) throws RemoteException {
        this.gui.adicionarTabela(moeda);
    }
    public void excluirMoeda(Moeda moeda) throws RemoteException {
    }
    public void editarMoeda(Moeda moeda, double cotacao) throws RemoteException {
    }
}
