import java.io.Serializable;

public class Points  implements Serializable {
    private int i, j, joueur;

    public Points (int joueur) {
        this.joueur = joueur;
    }

    public void setJoueur(int joueur) {
        this.joueur = joueur;
    }

    public int getJoueur() {
        return this.joueur;
    }

    public int[] affiche() {
        return new int[]{this.i, this.j, this.joueur};
    }
}
