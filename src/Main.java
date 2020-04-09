public class Main {
    public static void main(String[] args) {
        Fenetre fenetre = new Fenetre();
        Grille grille = new Grille();

        grille.addPoint(0,0,1);
        System.out.println(grille.getPoint(2,2));
       // System.out.println(grille.getPoint(0,0));

        fenetre.pack();
        fenetre.setVisible(true);
    }
}