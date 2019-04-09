import java.net.MalformedURLException;
import java.rmi.*;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class ClientGUI extends JFrame {
    InterfaceRemota ir;
    JPanel painel;
    JTextField txtAddMoeda;
    JTextField txtRemoverMoeda;
    JComboBox cbEditarMoeda;
    JButton btnAdicionar;
    JButton btnRemover;
    JButton btnEditar;

    public ClientGUI() {
        super("MCotações (Cliente)");

        txtAddMoeda = new JTextField("Adicionar Moeda");
        txtRemoverMoeda = new JTextField("Remover Moeda");
        btnAdicionar = new JButton("Adicionar");
        btnRemover = new JButton("Remover");
        btnEditar = new JButton("Editar");
        cbEditarMoeda = new JComboBox();
    }

    public void mostrarJanela() {
        painel = new JPanel();
        painel.setLayout(new GridLayout(3,2));

        painel.add(txtAddMoeda);
        painel.add(btnAdicionar);

        painel.add(txtRemoverMoeda);
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
