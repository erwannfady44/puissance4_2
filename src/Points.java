import java.io.Serializable;

public class Points  implements Serializable{
    private int i, j, joueur;

    public Points (int i, int j,int joueur) {
        this.joueur = joueur;
        this.i = i;
        this.j = j;
    }

    public void setJoueur(int joueur) {
        this.joueur = joueur;
    }

    public int getJoueur() {
        return this.joueur;
    }

    public int[] affiche() {
        int[] retour = {this.i, this.j, this.joueur};
        return retour;
    }
}
