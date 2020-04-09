import javax.swing.*;
import java.awt.*;

public class Grille {
    private Points[][] lesPoints = new Points [6][7];

    public Grille() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                lesPoints[i][j]  = new Points(-1);
            }
        }
    }

    public int getPoint(int i, int j) {
        if (this.lesPoints[i][j] != null)
            return this.lesPoints[i][j].getJoueur();
        else
            return 0;
    }

    public void addPoint2(int i, int j, int joueur) {
        this.lesPoints[i][j].setJoueur(joueur);
    }

    public boolean addPoint(int colonne, int joueur) {
        for (int i = 5; i >= 0; i--) {
            if (this.lesPoints[i][colonne].getJoueur() == -1) {
                this.lesPoints[i][colonne] = new Points(joueur);
                return true;
            }
        }
        return false;
    }

    public int verifierGagne() {
        int joueur = 1;
        if  (horiozontale(1) || verticale(1) || diagonnaleDroit(1) || diagonnaleGauche(1))
            return 1;
        else if  (horiozontale(2) || verticale(2) || diagonnaleDroit(2) || diagonnaleGauche(2))
            return 2;
        else
            return 0;

    }

    boolean horiozontale (int joueur) {
        /*int pions;
        for (int i = 0; i < 6; i++) {
            pions = 0;
            for (int j = 0; j < 7; j++) {
                if(this.lesPoints[i][j].getJoueur() == joueur)
                    pions++;
                else
                    pions = 0;
                if (pions >= 4)
                    return true;
            }
        }*/
        return false;
    }

    boolean verticale(int joueur) {
        /*int pions;
        for (int j = 0; j < 7; j++) {
            pions = 0;
             for (int i = 0; i < 6; i++) {
                if(this.lesPoints[i][j].getJoueur() == joueur)
                    pions++;
                else
                    pions = 0;
                if (pions >= 4)
                    return true;
            }
        }*/
        return false;
    }

    boolean diagonnaleDroit(int joueur) {
        /*for (int ligne = 0; ligne < 6; ligne++) {
            int i = ligne;
            int points = 0;

            for (int j = 0; j <= ligne; j++) {
                if (this.lesPoints[i][j].getJoueur() == joueur)
                    points++;

                if (points >= 4)
                    return true;
                i--;
            }
        }*/
        return false;
    }

    boolean diagonnaleGauche (int joueur) {
        int i = 5;
        for (int colonne = 0; colonne < 7; colonne++) {
            int points = 0;
            if (i != 0) {
                i = 5;
                for (int j = colonne; j >= 0; j--) {
                    if (this.lesPoints[i][j].getJoueur() == joueur)
                        points++;

                    if (points >= 4)
                        return true;
                    i--;
                }
            }
        }
        return false;
    }

    void addPoint (int i, int j, int joueur){
        this.lesPoints[i][j].setJoueur(3);
    }

}
