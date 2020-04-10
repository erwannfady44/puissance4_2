import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame {

    private JPanel fenetre = new JPanel();
    private JPanel grille = new JPanel();
    private Grille plateau = new Grille();
    private JPanel haut = new JPanel();
    boolean finPartie = false;

    private String[] pseudo;

    int coups = 0;

    public Fenetre(Joueur joueur1, Joueur joueur2) {
        super("Puissance 4");


        pseudo = new String[]{joueur1.getPseudo(), joueur2.getPseudo()};

        fenetre.setLayout(new BorderLayout());
        grille.setLayout(new GridLayout(6, 7));


        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(600, 400));
        this.setLocation(660, 240);
        dessiner(plateau);

        haut.add(new JLabel("<html><p>Veuillez cliquer sur un case</p> <p>Tour de " + joueur1.getPseudo() + "</p></html>"));

        fenetre.add(grille);
        fenetre.add(haut, BorderLayout.NORTH);

        this.setContentPane(fenetre);
    }

    private void dessiner(Grille plateau) {
       JButton[][] boutons = new JButton[6][7];

        ActionListener ajoutPoint;
        ajoutPoint = e -> {
            if (!finPartie)
                jouer(e, boutons);
            gagne(this.pseudo);
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

            fenetre.updateUI();
        }
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

        haut.add(new JLabel("<html><p style=\"text-align: center;\">Veuillez cliquer sur un case</p> <p>Tour de " + pseudo[(coups) % 2] + "</p></html>"));
        grille.removeAll();
        dessiner(plateau);

        fenetre.updateUI();
    }

    private void gagne(String[] pseudo) {
        int joueurGagne = plateau.verifierGagne();
        if (joueurGagne != -1) {
            JOptionPane.showMessageDialog(null, this.pseudo[joueurGagne] + " gagne");
            finPartie = true;
        }
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

    public boolean finpartie() {
        return finPartie;
    }
}
