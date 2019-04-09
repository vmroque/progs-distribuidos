import java.net.MalformedURLException;
import java.rmi.*;
import java.util.Vector;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

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
    DefaultComboBoxModel<String> cbModel;
    JComboBox<String> cbRemoverMoeda;
    JComboBox<String> cbEditarMoeda;
    JButton btnAdicionar;
    JButton btnRemover;
    JButton btnEditar;

    public ClientGUI() throws RemoteException, MalformedURLException, NotBoundException {
        super("MCotações (Cliente)");

	String ip = JOptionPane.showInputDialog(this, "IP servidor");
        this.ir = (InterfaceRemota) Naming.lookup("rmi://" + ip + "/InterfaceRemota");

        lbMoeda = new JLabel("Moeda:");
        lbCotacao = new JLabel("Cotação:");
        lbNovaCotacao = new JLabel("Nova Cotação:");
        txtAddMoeda = new JTextField("");
        txtAddCotacao = new JTextField("");
        txtEditarCotacao = new JTextField("");
        btnAdicionar = new JButton("Adicionar");
        btnRemover = new JButton("Remover");
        btnEditar = new JButton("Editar");
        cbModel = new DefaultComboBoxModel<String>(ir.getMoedas());
        cbRemoverMoeda = new JComboBox<String>(cbModel);
        cbEditarMoeda = new JComboBox<String>(cbModel);


        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomeMoeda = txtAddMoeda.getText();
                double cotacao = Double.parseDouble(txtAddCotacao.getText());
                Moeda moeda = new Moeda(nomeMoeda, cotacao);
                try {
                    ir.adicionarMoeda(moeda);
                    cbModel.addElement(nomeMoeda);
                } catch (RemoteException a) {
                    a.printStackTrace();
                }
            }
        });

        btnRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomeMoeda = cbRemoverMoeda.getSelectedItem().toString();
                Moeda moeda = new Moeda(nomeMoeda, 0);
                try {
                    ir.excluirMoeda(moeda);
                    cbModel.removeElement(nomeMoeda);
                } catch (RemoteException a) {
                    a.printStackTrace();
                }
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomeMoeda = cbEditarMoeda.getSelectedItem().toString();
                double novaCotacao = Double.parseDouble(txtEditarCotacao.getText());
                Moeda moeda = new Moeda(nomeMoeda, 0);
                try {
                    ir.editarMoeda(moeda, novaCotacao);
                } catch (RemoteException a) {
                    a.printStackTrace();
                }
            }
        });
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
        try {
            ClientGUI client = new ClientGUI();
            client.mostrarJanela();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
