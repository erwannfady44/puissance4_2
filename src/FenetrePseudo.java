import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FenetrePseudo extends JFrame implements KeyListener {

    JTextField champs1;
    JTextField champs2;

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
        champs2 = new JTextField();

        btValider.setMnemonic('A');

        fenetre.add(new JLabel("Veuillez entrer le nom du joueurs 1"));
        fenetre.add(champs1);
        fenetre.add(new JLabel("Veuillez entrer le nom du joueurs 2"));
        fenetre.add(champs2);

        fenetre.add(btValider);
        this.setContentPane(fenetre);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == 10){
            System.out.println("Touche pressée : " + e.getKeyCode() +
                    " (" + e.getKeyChar() + ")");
        }
        Fenetre fenetre = new Fenetre(champs1.getText(), champs2.getText());
        fenetre.pack();
        fenetre.setVisible(true);
        this.dispose();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void actionPerformed(ActionEvent e) {
        Fenetre fenetre = new Fenetre(champs1.getText(), champs2.getText());
        fenetre.pack();
        fenetre.setVisible(true);
        this.dispose();
    }
}
