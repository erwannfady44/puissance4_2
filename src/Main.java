public class Main {
    public static Fichier fichier;
    public static Ftp ftp;
    public static FenetrePseudo pseudo;
    public static Fenetre fenetre;

    public static void main(String[] args) {
        /*ThreadJeu jeu = new ThreadJeu();
        //jeu.start();
        jeu.run();*/

        pseudo = new FenetrePseudo();
        pseudo.pack();
        pseudo.setVisible(true);

        /*ftp = new Ftp();
        fichier = new Fichier();
        fichier.writeCoups(0);
        ftp.upload();*/
    }

    public static void cretionFichier() {
        fichier = new Fichier();

        int coups = fichier.getCoups();
        fichier.incrementerCoups();
        Joueur[] joueurs = fichier.getJoueur();
        int numero = fichier.getNumero();
        Grille grille = fichier.getGrille();

        fenetre = new Fenetre(grille, coups, joueurs, numero);
        fenetre.pack();
        fenetre.setVisible(true);

        //fichier.start();
    }
}