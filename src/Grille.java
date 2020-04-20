import java.io.*;

public class Grille {
    Ftp ftp = new Ftp();
    ObjectInputStream contenu;
    ObjectOutputStream ecrire;
    File fichier = new File("fichiers\\grille.txt");
    private Points[][] lesPoints = new Points[6][7];

    public Grille() {
        //initialistation des pions
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++)
                lesPoints[i][j] = new Points(i, j, -1);
        }
        ecrireTableau(false);
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
                ecrireTableau(false);
                return true;
            }
        }

        return false;
    }

    public void ecrireTableau(boolean raz) {
        if (!raz) {
            try {
                ecrire = new ObjectOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(
                                        fichier)));

                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 7; j++) {
                        ecrire.writeObject(lesPoints[i][j]);
                    }
                }
                ecrire.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else {
            fichier.delete();
            File fichier = new File("fichiers\\grille.txt");

            try {
                fichier.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ftp.upload();
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
                if (this.lesPoints[i][j].getJoueur() == joueur)
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

    public void majGrille() {
        try {
            contenu = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(fichier)));

            try {
                int[] resultat;
                int i = 0, j = 0;
                for (int nb = 0; nb < 42; nb++) {
                    resultat = ((Points) contenu.readObject()).affiche();
                    this.lesPoints[i][j] = new Points(resultat[0], resultat[1], resultat[2]);
                    j++;
                    if (j == 7) {
                        j = 0;
                        i++;
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            contenu.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        ftp.upload();
    }
}

