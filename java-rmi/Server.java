import java.rmi.*;
import java.rmi.server.*;
import javax.swing.table.*;

public class Server extends UnicastRemoteObject implements InterfaceRemota {
    ServerGUI gui;

    public Server(ServerGUI gui) throws RemoteException {
        this.gui = gui;
    }

    public void adicionarMoeda(Moeda moeda) throws RemoteException {
        String nomeMoeda = moeda.getNome();
        String cotMoeda  = moeda.getCotacao() + "";

        this.gui.model.addRow(new Object[]{nomeMoeda, cotMoeda});
    }

    public void excluirMoeda(Moeda moeda) throws RemoteException {
        int i;
        String nomeMoeda = moeda.getNome();
        DefaultTableModel m = gui.model;

        for (i = 0; i < m.getRowCount(); ++i) {
            if (m.getValueAt(i,0).equals(nomeMoeda))
                break;
        }
        if (i < m.getRowCount())
            gui.model.removeRow(i);
    }

    public void editarMoeda(Moeda moeda, double cotacao) throws RemoteException {
        int i;
        String nomeMoeda = moeda.getNome();
        DefaultTableModel m = gui.model;

        for (i = 0; i < m.getRowCount(); ++i) {
            if (m.getValueAt(i,0).equals(nomeMoeda))
                break;
        }
        if (i < m.getRowCount())
            gui.model.setValueAt(cotacao + "", i, 1);
    }
}
