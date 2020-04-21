import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetrePseudo extends JFrame {
    JTextField champs1;
    private String pseudo;

    public FenetrePseudo (){

        super("Puissance 4");

        this.setPreferredSize(new Dimension(300, 150));
        this.setLocation(660, 240);

        ActionListener valider;
        valider = this::actionPerformed;

        JPanel fenetre = new JPanel();
        fenetre.setLayout(new BoxLayout(fenetre, BoxLayout.Y_AXIS));

        JButton btValider = new JButton("Valider");
        btValider.addActionListener(valider);

        champs1 = new JTextField();
        champs1.addKeyListener(new ClavierListener());

        btValider.setMnemonic('A');

        fenetre.add(new JLabel("Veuillez entrer votre pseudo"));
        fenetre.add(champs1);

        fenetre.add(btValider);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                quitter();
            }
        });

        this.setContentPane(fenetre);
    }

    public String getPseudo() {
        return this.pseudo;
    }

    private void actionPerformed(ActionEvent e) {
        quitter();
    }

    private void quitter() {
        this.dispose();
        Main.cretionFichier();
    }
}