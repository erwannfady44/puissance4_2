import java.awt.*;

public class ThreadJeu extends Thread {
    public static FenetrePseudo fenetrePseudo;

    public ThreadJeu() {
        super ("jeu");
    }
    @Override
    public void run() {
        Main.fichier = new Fichier();
    }
}
