import java.net.MalformedURLException;
import java.rmi.*;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ClientGUI extends JFrame {
    InterfaceRemota ir;
    JPanel painel;
    JPanel painelAddInterno;
    JLabel lbMoeda;
    JLabel lbCotacao;
    JTextField txtAddMoeda;
    JTextField txtAddCotacao;
    JComboBox cbRemoverMoeda;
    JComboBox cbEditarMoeda;
    JButton btnAdicionar;
    JButton btnRemover;
    JButton btnEditar;

    public ClientGUI() {
        super("MCotações (Cliente)");

        lbMoeda = new JLabel("Moeda:");
        lbCotacao = new JLabel("Cotação:");
        txtAddMoeda = new JTextField("");
        txtAddCotacao = new JTextField("");
        btnAdicionar = new JButton("Adicionar");
        btnRemover = new JButton("Remover");
        btnEditar = new JButton("Editar");
        cbRemoverMoeda = new JComboBox();
        cbEditarMoeda = new JComboBox();
    }

    public void mostrarJanela() {
        painel = new JPanel();
        painelAddInterno = new JPanel();
        painel.setLayout(new GridLayout(3,2));
        painelAddInterno.setLayout(new GridLayout(1,4));

        painelAddInterno.add(lbMoeda);
        painelAddInterno.add(txtAddMoeda);
        painelAddInterno.add(lbCotacao);
        painelAddInterno.add(txtAddCotacao);

        painel.add(painelAddInterno);
        painel.add(btnAdicionar);

        painel.add(cbRemoverMoeda);
        painel.add(btnRemover);

        painel.add(cbEditarMoeda);
        painel.add(btnEditar);

        getContentPane().add(painel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 100);
        setVisible(true);  
    }

    public static void main(String[] args) {
        ClientGUI client = new ClientGUI();
        client.mostrarJanela();
    }
}
