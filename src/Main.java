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



        if (fenetre.finPartie) {
            int refaire = showConfirmDialog(null, "Voulez recommencer une partie ?", "Recommencer", YES_NO_OPTION, QUESTION_MESSAGE);
            if (refaire == YES_OPTION) {
                fenetre.plateau.recommencer();
                main(new String[]{});
            }
        }
    }
}