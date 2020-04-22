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

        fenetre = new Fenetre(fichier.getGrille(), fichier.getCoups(), fichier.getJoueur(), fichier.getNumero());
        fichier.start();
        fenetre.pack();
        fenetre.setVisible(true);
    }

    public static void update() {
        System.out.println("update2");
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