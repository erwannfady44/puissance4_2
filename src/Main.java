import static javax.swing.JOptionPane.*;

public class Main {
    public static void main(String[] args) {
        String pseudo = showInputDialog(null, "Veuillez renseigner le pseudo du joueur 1", "Puissance 4", QUESTION_MESSAGE);
        Joueur joueur1 = new Joueur(pseudo);

        pseudo = showInputDialog(null, "Veuillez renseigner le pseudo du joueur 2 ", "Puissance 4", QUESTION_MESSAGE);
        Joueur joueur2 = new Joueur(pseudo);

        Fenetre fenetre = new Fenetre(joueur1, joueur2);
        fenetre.pack();
        fenetre.setVisible(true);
    }
}