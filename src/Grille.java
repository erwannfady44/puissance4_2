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

    public boolean addPoint(int colonne, int joueur) {
        for (int i = 5; i >= 0; i--) {
            if (this.lesPoints[i][colonne].getJoueur() == -1) {
                this.lesPoints[i][colonne].setJoueur(joueur);
                return true;
            }
        }
        return false;
    }

    public void addPoint2(int c, int l, int joueur) {
        this.lesPoints[c][l].setJoueur(joueur);
    }

    public int verifierGagne() {
        int joueur;
        if (horiozontale(1) || verticale(1) || diagonnaleDroit(1) || diagonnaleGauche(1))
            joueur = 0;
        else if (horiozontale(2) || verticale(2) || diagonnaleDroit(2) || diagonnaleGauche(2))
            joueur = 1;
        else
            joueur = -1;
        System.out.println("gagnant : " + joueur);
        return joueur;

    }

    boolean horiozontale (int joueur) {
        int pions;
        for (int i = 0; i < 6; i++) {
            pions = 0;
            for (int j = 0; j < 7; j++) {
                if(this.lesPoints[i][j].getJoueur() == joueur)
                    pions++;
                else
                    pions = 0;
                if (pions >= 4) {
                    System.out.println("horizontal : oui");
                    return true;
                }

            }
        }
        System.out.println("horizontal : non");
        return false;
    }

    boolean verticale(int joueur) {
        int pions;
        for (int j = 0; j < 7; j++) {
            pions = 0;
             for (int i = 0; i < 6; i++) {
                if(this.lesPoints[i][j].getJoueur() == joueur)
                    pions++;
                else
                    pions = 0;
                if (pions >= 4) {
                    System.out.println("vertical : oui");
                    return true;
                }
            }
        }
        System.out.println("vertical : non");
        return false;
    }

    boolean diagonnaleDroit(int joueur) {
        int points = 0;
        for (int ligne = 0; ligne < 6; ligne++) {
            int i = ligne;

            for (int j = 0; j <= ligne; j++) {
                if (this.lesPoints[i][j].getJoueur() == joueur)
                    points++;
                else
                    points = 0;

                if (points >= 4) {
                    System.out.println("diago droit : oui");
                    return true;
                }
                i--;
            }
        }

        points = 0;

        for (int colonne = 0; colonne < 4; colonne++) {
            int j = colonne;
            int i = 5;
            while (i >= 0 && j < 7) {
                if (this.lesPoints[i][j].getJoueur() == joueur) {
                    points++;
                }
                else {
                    points = 0;
                }

                if (points >= 4) {
                    System.out.println("diago droit : oui");
                    return true;
                }

                j++;
                i--;
            }
        }

        System.out.println("diago droit : non");
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
                    System.out.println(i +" - "+j);
                    if (this.lesPoints[i][j].getJoueur() == joueur)
                        points++;
                    else
                        points = 0;

                    if (points >= 4) {
                        System.out.println("diago gauche : non");
                        return true;
                    }
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

                if (points >= 4) {
                    System.out.println("diago gauche : non");
                    return true;
                }
                System.out.println(i +" - "+ j);
                i++;
                j++;
            }
        }
        System.out.println("diago gauche : non");
        return false;
    }
}
