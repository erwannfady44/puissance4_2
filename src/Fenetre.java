import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame {
    JPanel jeu = new JPanel();
    JPanel grille = new JPanel();
    Grille plateau = new Grille();
    JPanel haut = new JPanel();
    boolean finPartie = false;

    int coups;

    public Fenetre() {

        super("Puissance 4");
        jeu.setLayout(new BorderLayout());
        grille.setLayout(new GridLayout(6, 7));


        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(600, 400));
        this.setLocation(660, 240);
        dessiner(plateau);

        haut.add(new JLabel("<html><p>Veuillez cliquer sur un case</p> <p>Tour de joueur 1</p></html>"));

        jeu.add(grille);
        jeu.add(haut, BorderLayout.NORTH);

        this.setContentPane(jeu);


    }

    void dessiner(Grille plateau) {
        JButton[][] boutons = new JButton[6][7];

        ActionListener ajoutPoint;
        ajoutPoint = e -> {
            if (!finPartie)
                jouer(e, boutons);
            gagne();
        };

        for (int i = 0; i <6; i++) {
            for (int j = 0; j < 7; j++) {
                boutons[i][j] = new JButton(" ");
                boutons[i][j].addActionListener(ajoutPoint);

                if (plateau.getPoint(i, j) == 2)
                    boutons[i][j].setBackground(Color.RED);
                else if (plateau.getPoint(i, j) == 1)
                    boutons[i][j].setBackground(Color.YELLOW);
                else if (plateau.getPoint(i, j) == 3)
                    boutons[i][j].setBackground(Color.BLUE);
                else
                    boutons[i][j].setBackground(Color.WHITE);

                grille.add(boutons[i][j]);
            }
        }
    }



    void jouer(ActionEvent e, JButton[][] boutons) {
        int colonne;

        colonne = getColonne(e.getSource(), boutons);
        if (colonne != -1) {
            plateau.addPoint(colonne, coups % 2 + 1);
            coups++;
        }

        haut.removeAll();
        int joueur = coups % 2 + 1;
        haut.add(new JLabel("<html><p>Veuillez cliquer sur un case</p> <p>Tour de joueur " + joueur+ "</p></html>"));
        grille.removeAll();
        dessiner(plateau);

        jeu.updateUI();
    }

    void gagne() {
        int joueurGagne = plateau.verifierGagne();
        if (joueurGagne != 0) {
            JOptionPane.showMessageDialog(null,"Joueurs " +joueurGagne+ " gagne");
            finPartie = true;
        }
    }

    int getColonne(Object bt, JButton[][] boutons) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (bt == boutons[i][j]) {
                    return j;
                }
            }
        }
        return -1;
    }
}
