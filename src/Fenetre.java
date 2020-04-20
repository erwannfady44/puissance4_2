import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Fenetre extends JFrame {

    private JPanel fenetre = new JPanel();
    private JPanel grille = new JPanel();
    protected Grille plateau = new Grille();
    private JPanel haut = new JPanel();
    private JButton[][] boutons = new JButton[6][7];
    private Joueur[] joueurs = new Joueur[2];

    public static int coups = 0;

    //constructeur de la fenêtre
    public Fenetre(String pseudo1, String pseudo2) {
        //titre de la fenêtre
        super("Puissance 4");

        joueurs[0] = new Joueur(pseudo1);
        joueurs[1] = new Joueur(pseudo2);

        //définition du design de chaque partie de la fenêtre
        fenetre.setLayout(new BorderLayout());
        grille.setLayout(new GridLayout(6, 7));

        //paramétrage de la fenêtre (croix pour quitter, taille et position)
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(600, 400));
        this.setLocation(660, 240);

        //texte de la partie haute
        haut.add(new JLabel("<html><p>Veuillez cliquer sur un case</p> <p>Tour de " + joueurs[0].getPseudo() + "</p></html>"));

        //ajout à la fenêtre des différentes partie (haut et grille)
        fenetre.add(grille);
        fenetre.add(haut, BorderLayout.NORTH);

        //dessin et definition des boutons et pions
        dessiner();

        //déclare le panneau fenêtre comme panneau principale
        this.setContentPane(fenetre);
    }

    //fonction qui créer les boutons et dessine les pions
    public void dessiner() {
        //suppressions des boutons (pour les actualiser)
       grille.removeAll();

       //fonction réalisé au cliv sur un bouton
        ActionListener ajoutPoint;
        ajoutPoint = e -> {
            //ajout du pions
            jouer(e, boutons);

            //vérification de la victoire
            gagne();
        };


        //création des pions
        for (int i = 0; i <6; i++) {
            for (int j = 0; j < 7; j++) {
                //si le pions appartient au joueur 1
                if (plateau.getPoint(i, j) == 2)
                    boutons[i][j] = new Bouton_rond(Color.RED);
                //si le pions appartient au joueur 2
                else if (plateau.getPoint(i, j) == 1)
                    boutons[i][j] = new Bouton_rond(Color.YELLOW);
                //s'il n'y a pas de pions
                else
                    boutons[i][j] = new Bouton_rectangle();

                //rend le bouton cliquable
                boutons[i][j].addActionListener(ajoutPoint);

                //ajout de tous le boutons (et pions) à la grille
                grille.add(boutons[i][j]);
            }
        }
        fenetre.updateUI();
    }

    //fonction qui ajoute les pions
    private void jouer(ActionEvent e, JButton[][] boutons) {
        int colonne;

        //récupération de la colonne du bouton cliqué
        colonne = getColonne(e.getSource(), boutons);

        //vérification qu'il n'y a pas d'erreur dans la récupération de la colonne
        if (colonne != -1) {
            //ajout du pions et vérification qu'il n'y a pas d'erreur (exemple : la colonne est pleine)
            if (!plateau.addPoint(colonne, coups % 2 + 1))
                //affichage d'un message d'erreur
            {
                JOptionPane.showMessageDialog(null, "Impossible la colonne est déjà pleine");
            }
            //S'il n'y a pas d'erreur le tour et joué
            else {
                coups++;
            }
        }
        //S'il y a une erreur dans la récupération de la colonne
        else {
            //affichage d'un message d'erreur
            JOptionPane.showMessageDialog(null, "Veuillez recliquer ");
        }

        //Mise à jour du joueur qui doit jouer
        haut.removeAll();
        haut.add(new JLabel("<html><p style=\"text-align: center;\">Veuillez cliquer sur un case</p> <p>Tour de " + joueurs[(coups) % 2].getPseudo() + "</p></html>"));

        dessiner();
        plateau.majGrille();
    }

    //fonction qui vérifie si qu'un a gagné
    private void gagne() {
        //récupération du vainqueur
        int joueurGagne = plateau.verifierGagne();

        //vainqueur vaut -1 si personne n'a gagné
        if (joueurGagne != -1) {
            //incrémentation du nombre de victoire du joueur vainqueur
            joueurs[joueurGagne].gagne();

            //affichage du vainqueur
            JOptionPane.showMessageDialog(null,
                    joueurs[joueurGagne].getPseudo() + " gagne");

            //boite de confirmation pour recommencer la partie
            int option = JOptionPane.showConfirmDialog(null, "Voulez-vous recommencer la partie", "Recommencer", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            //si l'on recommencer
            if (option == JOptionPane.YES_OPTION)
                recommencer(joueurGagne);
            else
                quitter();
        }
    }

    //fonction qui récupère la colonne où ajoute le pions
    private int getColonne(Object bt, JButton[][] boutons) {
        //vérification du bouton cliqué pour récupérer la colonne
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (bt == boutons[i][j]) {
                    return j;
                }
            }
        }
        return -1;
    }

    //fonction qui remets le jeu à 0
    private void recommencer(int joueur) {
        //remise à 0 du plateau (= suppression de tous les pions)
        this.plateau.recommencer();

        //incrémentation du nombre de coups pour que se soit le perdant qui commence la prochaine partie
        coups = (joueur + 1) % 2;

        //mise à jour du prochain joueur qui joue
        haut.removeAll();
        haut.add(new JLabel("<html><p style=\"text-align: center;\">Veuillez cliquer sur un case</p> <p>Tour de " + joueurs[(coups) % 2].getPseudo() + "</p></html>"));

        //redessinage des pions
        dessiner();
    }

    //fonction qui quitte le jeu
    private void quitter() {
        //récupération du nombre de victoire des joueurs
        int victoireJoueur1 = joueurs[0].getVictoire();
        int victoireJoueur2 = joueurs[1].getVictoire();

        //si l'on a fait plus d'une partie
        if (victoireJoueur1 >= 2 || victoireJoueur2 >= 2) {
            //si joueur 1 est gagnant
            if (victoireJoueur1 > victoireJoueur2)
                //affichage d'un message
                JOptionPane.showMessageDialog(null, joueurs[0].getPseudo() + " gagne " + victoireJoueur1 + " - " + victoireJoueur2);

            //si joueur 2 est gagnant
            else if (victoireJoueur1 < victoireJoueur2)
                //affichage d'un message
                JOptionPane.showMessageDialog(null, joueurs[1].getPseudo() + " gagne " + victoireJoueur2 + " - " + victoireJoueur1);

            //Si il y a égalité
            else
                //affichage d'un message
                JOptionPane.showMessageDialog(null, "Egalité " + victoireJoueur2 + " - " + victoireJoueur1);
        }
        plateau.ecrireTableau(true);
        System.exit(0);
    }
}
