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
    JPanel painelEditarInterno;
    JLabel lbMoeda;
    JLabel lbCotacao;
    JLabel lbNovaCotacao;
    JTextField txtAddMoeda;
    JTextField txtAddCotacao;
    JTextField txtEditarCotacao;
    JComboBox cbRemoverMoeda;
    JComboBox cbEditarMoeda;
    JButton btnAdicionar;
    JButton btnRemover;
    JButton btnEditar;

    public ClientGUI() {
        super("MCotações (Cliente)");

        lbMoeda = new JLabel("Moeda:");
        lbCotacao = new JLabel("Cotação:");
        lbNovaCotacao = new JLabel("Nova Cotação:");
        txtAddMoeda = new JTextField("");
        txtAddCotacao = new JTextField("");
        txtEditarCotacao = new JTextField("");
        btnAdicionar = new JButton("Adicionar");
        btnRemover = new JButton("Remover");
        btnEditar = new JButton("Editar");
        cbRemoverMoeda = new JComboBox();
        cbEditarMoeda = new JComboBox();
    }

    public void mostrarJanela() {
        painel = new JPanel();
        painelAddInterno = new JPanel();
        painelEditarInterno = new JPanel();
        painel.setLayout(new GridLayout(3,2));
        painelAddInterno.setLayout(new GridLayout(1,4));
        painelEditarInterno.setLayout(new GridLayout(1,3));

        painelAddInterno.add(lbMoeda);
        painelAddInterno.add(txtAddMoeda);
        painelAddInterno.add(lbCotacao);
        painelAddInterno.add(txtAddCotacao);

        painelEditarInterno.add(cbEditarMoeda);
        painelEditarInterno.add(lbNovaCotacao);
        painelEditarInterno.add(txtEditarCotacao);

        painel.add(painelAddInterno);
        painel.add(btnAdicionar);

        painel.add(cbRemoverMoeda);
        painel.add(btnRemover);

        painel.add(painelEditarInterno);
        painel.add(btnEditar);

        getContentPane().add(painel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 100);
        setVisible(true);  
    }

    public static void main(String[] args) {
        ClientGUI client = new ClientGUI();
        client.mostrarJanela();
    }
}
