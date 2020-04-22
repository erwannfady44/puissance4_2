import static java.lang.Thread.sleep;

public class Main {
    public static Fichier fichier;
    public static Ftp ftp;
    public static FenetrePseudo pseudo;
    public static Fenetre fenetre;

    public static void main(String[] args) {
        pseudo = new FenetrePseudo();
        pseudo.pack();
        pseudo.setVisible(true);
    }

    public static void cretionFichier() {
        fichier = new Fichier(false);

        System.out.println("nb : "+fichier.getNumero());

        fenetre = new Fenetre(fichier.getGrille(), fichier.getCoups(), fichier.getJoueur(), fichier.getNumero());
        fenetre.pack();
        fenetre.setVisible(true);
        System.out.println(fichier.getCoups());
        fichier.start();
    }

    public static void update() {
        fichier.setPause(true);
        fichier.update();
        //System.out.println(fenetre.getCoups());
        fenetre.update(fichier.getGrille(), fichier.getCoups(), fichier.getJoueur());
        fichier.setPause(false);
    }

    public static void quitter() {
        if (!fichier.isPause()) {
            fichier.setPause(true);
            fichier.delete();
            System.out.println(fichier.getCoups());
        }
        fichier.interrupt();
        System.exit(0);
    }
}