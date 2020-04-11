import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame {

    private JPanel fenetre = new JPanel();
    private JPanel grille = new JPanel();
    protected Grille plateau = new Grille();
    private JPanel haut = new JPanel();
    private JButton[][] boutons = new JButton[6][7];
    boolean finPartie = false;

    private Joueur[] joueurs;

    int coups = 0;

    public Fenetre(Joueur joueur1, Joueur joueur2) {
        super("Puissance 4");


        joueurs = new Joueur[]{joueur1, joueur2};

        fenetre.setLayout(new BorderLayout());
        grille.setLayout(new GridLayout(6, 7));


        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(600, 400));
        this.setLocation(660, 240);

        haut.add(new JLabel("<html><p>Veuillez cliquer sur un case</p> <p>Tour de " + joueur1.getPseudo() + "</p></html>"));

        fenetre.add(grille);
        fenetre.add(haut, BorderLayout.NORTH);
        dessiner();
        this.setContentPane(fenetre);
    }

    public void dessiner() {

       grille.removeAll();

        ActionListener ajoutPoint;
        ajoutPoint = e -> {
            if (!finPartie)
                jouer(e, boutons);
            finPartie = gagne();
        };

        for (int i = 0; i <6; i++) {
            for (int j = 0; j < 7; j++) {

                if (plateau.getPoint(i, j) == 2)
                    boutons[i][j] = new Bouton_rond(Color.RED);
                else if (plateau.getPoint(i, j) == 1)
                    boutons[i][j] = new Bouton_rond(Color.YELLOW);
                else if (plateau.getPoint(i, j) == 3)
                    boutons[i][j] = new Bouton_rond(Color.GREEN);
                else
                    boutons[i][j] = new Bouton_rectangle();
                boutons[i][j].addActionListener(ajoutPoint);

                grille.add(boutons[i][j]);
            }

        }
        fenetre.updateUI();
    }

    private void jouer(ActionEvent e, JButton[][] boutons) {

        int colonne;


        colonne = getColonne(e.getSource(), boutons);
        if (colonne != -1) {
            if (!plateau.addPoint(colonne, coups % 2 + 1)) {
                coups--;
                JOptionPane.showMessageDialog(null, "Impossible la colonne est déjà pleine");
            }
            coups++;
        }

        haut.removeAll();

        haut.add(new JLabel("<html><p style=\"text-align: center;\">Veuillez cliquer sur un case</p> <p>Tour de " + joueurs[(coups) % 2].getPseudo() + "</p></html>"));
        grille.removeAll();
        dessiner();

        fenetre.updateUI();
    }

    private boolean gagne() {
        int joueurGagne = plateau.verifierGagne();
        if (joueurGagne != -1) {
            JOptionPane.showMessageDialog(null, this.joueurs[joueurGagne].getPseudo() + " gagne");
            return true;
        }
        return false;
    }


    private int getColonne(Object bt, JButton[][] boutons) {
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
