import java.rmi.*;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.*;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ServerGUI extends JFrame {
    JPanel painel;
    JTable tabelaCotacoes;
    DefaultTableModel model;
    JScrollPane barraRolagem;

    public ServerGUI() {
        super("MCotações (Servidor)");
    }

    public void criarTabelaCotacoes(JPanel painel) {
        model = new DefaultTableModel();
        tabelaCotacoes = new JTable(model);
        barraRolagem = new JScrollPane(tabelaCotacoes);

        model.addColumn("Moeda");
        model.addColumn("Cotação");

        painel.add(barraRolagem);
    }

    public void adicionarTabela(Moeda moeda) {
        String nomeMoeda = moeda.getNome();
        String cotMoeda = moeda.getCotacao() + "";

        model.addRow(new Object[]{nomeMoeda, cotMoeda});
    }

    public void criarJanela() {
        painel = new JPanel();
        painel.setLayout(new GridLayout(1,1));

        criarTabelaCotacoes(painel);

        getContentPane().add(painel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 120);
        setVisible(true);
    }

    public static void main(String[] args) throws RemoteException, java.net.MalformedURLException {
        ServerGUI gui = new ServerGUI();
        Server server = new Server(gui);

        gui.criarJanela();

        Naming.rebind("rmi://localhost/InterfaceRemota", server);
    }
}
