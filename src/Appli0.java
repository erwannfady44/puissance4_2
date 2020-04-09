package ihm.cm1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author lanoix-a
 * @version 3
 */

// Une fenetre personnalisée en Swing surcharge la fenetre "générique" JFrame
public class Appli0 extends JFrame {

    // On peut garder des références (=attributs) vers
    // certains composants graphiques de l'application =
    // ceux qu'il faudra modifier lors de l'utilisation de l'application
    private JLabel texte;
    private JButton bouton;

    // le constructeur de la classe
    public Appli0() {
        // on commence par appeler le constructeur de la super-classe
        super("Bonjour");

        // on initialise les composants de la fenetre :
        // ici un champ de texte
        // et un bouton
        texte = new JLabel("<html>Bonjour <p> chers <b>étudiants d'info1</b></html>");
        bouton = new JButton("C'est déjà la fin du cours ?");

        // on initialise un panneau (= un fond qui portera des composants)
        JPanel panneau = new JPanel();
        // ... sur lequel on ajoute le champ de texte et le bouton
        panneau.add(texte);
        panneau.add(bouton);
        // on fixe notre panneau comme panneau principal de la fenêtre
        this.setContentPane(panneau);

        // on ajoute du comportement sur le bouton
        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // on ouvre une fenetre de dialogue
                JOptionPane.showMessageDialog(Appli0.this,
                        "et bien non !!!!",
                        "Réponse",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // on détermine le comportement lors de l'appui sur la croix
        // de fermeture de la fenetre
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // On fixe une taille de départ pour la fenetre
        this.setPreferredSize(new Dimension(600, 400));

        // on positionne la fenêtre sur l'écran
        this.setLocation(100, 100);
    }


    // La classe Appli0 est une classe exécutable =
    // elle possède une méthode main(...) dans laquelle
    public static void main(String[] args) {

        // on initialise une fenetre de Type Appli0
        Appli0 appli = new Appli0();

        // on "construit" la fenetre
        appli.pack();

        // on rend visible la fenetre
        appli.setVisible(true);
    }

}
