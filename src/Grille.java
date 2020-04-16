public class Grille {
    private Points[][] lesPoints = new Points [6][7];

    public Grille() {
        //initialistation des pions
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                lesPoints[i][j]  = new Points(-1);
            }
        }
    }

    //fonction qui renvoie le numéro du joueur propriétaire du pions
    public int getPoint(int i, int j) {
        //Si le pions existe
        if (this.lesPoints[i][j] != null)
            return this.lesPoints[i][j].getJoueur();
        else
            return -1;
    }
    //fonction qui ajoute un pions en fonction de sa colonne
    public boolean addPoint(int colonne, int joueur) {
        for (int i = 5; i >= 0; i--) {
            //S'il n'y a pas de pions de la case
            if (this.lesPoints[i][colonne].getJoueur() == -1) {
                this.lesPoints[i][colonne].setJoueur(joueur);
                return true;
            }
        }
        return false;
    }

    //fonction qui vérifie la victore
    public int verifierGagne() {
        int joueur;
        if (horiozontale(1) || verticale(1) || diagonnaleDroit(1) || diagonnaleGauche(1))
            joueur = 0;
        else if (horiozontale(2) || verticale(2) || diagonnaleDroit(2) || diagonnaleGauche(2))
            joueur = 1;
        else
            joueur = -1;

        return joueur;
    }
    //fonction qui vérifie la victore à l'horizontale
    boolean horiozontale (int joueur) {
        int pions;
        //parcours du tableau a la verticle puis horizontale
        for (int i = 0; i < 6; i++) {
            pions = 0;
            for (int j = 0; j < 7; j++) {
                //si le pions appartient au joueur en question
                if(this.lesPoints[i][j].getJoueur() == joueur)
                    pions++;
                else
                    pions = 0;
                //si 4 pions sont allignés
                if (pions >= 4) {
                    return true;
                }

            }
        }
        return false;
    }
    //fonction qui vérifie la victore à la verticale
    boolean verticale(int joueur) {
        int pions;
        //parcours du tableau a l'horizontale puis à la verticle
        for (int j = 0; j < 7; j++) {
            pions = 0;
             for (int i = 0; i < 6; i++) {
                 //Si le pions appartient à un joueur
                if(this.lesPoints[i][j].getJoueur() == joueur)
                    pions++;
                else
                    pions = 0;
                //Si 4 pions sont allignés
                if (pions >= 4) {
                    return true;
                }
            }
        }
        return false;
    }

    //fonction qui vérifie la victore en diagonnale vers la droite
    boolean diagonnaleDroit(int joueur) {
        int points = 0;
        for (int ligne = 0; ligne < 6; ligne++) {
            int i = ligne;

            for (int j = 0; j <= ligne; j++) {
                if (this.lesPoints[i][j].getJoueur() == joueur)
                    points++;
                else
                    points = 0;

                if (points >= 4)
                    return true;

                i--;
            }
        }

        points = 0;

        for (int colonne = 0; colonne < 4; colonne++) {
            int j = colonne;
            int i = 5;
            while (i >= 0 && j < 7) {
                if (this.lesPoints[i][j].getJoueur() == joueur)
                    points++;

                else
                    points = 0;


                if (points >= 4)
                    return true;

                j++;
                i--;
            }
        }
        return false;
    }

    boolean diagonnaleGauche (int joueur) {
        int i = 5;
        int points;
        for (int colonne = 0; colonne < 7; colonne++) {
            if (i != 0) {
                i = 5;
                points = 0;
                for (int j = colonne; j >= 0; j--) {
                    if (this.lesPoints[i][j].getJoueur() == joueur)
                        points++;
                    else
                        points = 0;

                    if (points >= 4)
                        return true;

                    i--;
                }
            }
        }

        for (int colonne = 0; colonne < 4; colonne++) {
            i = 0;
            int j = colonne;
            points = 0;
            while (i < 6 && j < 7) {
                if (this.lesPoints[i][j].getJoueur() == joueur)
                    points++;
                else
                    points = 0;

                if (points >= 4)
                    return true;

                i++;
                j++;
            }
        }
        return false;
    }

    public void recommencer() {
        //remise à zéro du plateau
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                lesPoints[i][j].setJoueur(-1);
            }
        }
    }
}
