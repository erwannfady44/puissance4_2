import static java.lang.Thread.sleep;

public class Main {
    public static Fichier fichier;
    public static Ftp ftp;
    public static FenetrePseudo pseudo;
    public static Fenetre fenetre;

    public static void main(String[] args) {
        fichier = new Fichier(true);
        fichier.delete();

        pseudo = new FenetrePseudo();
        pseudo.pack();
        pseudo.setVisible(true);



    }

    public static void cretionFichier() {
        fichier = new Fichier(false);

        fenetre = new Fenetre(fichier.getGrille(), fichier.getCoups(), fichier.getJoueur(), fichier.getNumero());
        fenetre.pack();
        fenetre.setVisible(true);
        System.out.println(fichier.getCoups());
        fichier.start();
    }

    public static void update() {
        fichier.suspend();
        fichier.update();
        fenetre.update(fichier.getGrille(), fichier.getCoups(), fichier.getJoueur());
        fichier.resume();
    }

    public static void quitter() {
        fichier.delete();
        System.out.println(fichier.getCoups());
        fichier.stop();
        System.exit(0);
    }
}