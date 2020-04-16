public class Joueur {
    private String pseudo;
    private int victoire;

    public Joueur (String sonPseudo) {
        this.pseudo = sonPseudo;
        this.victoire = 0;
    }

    public Joueur() {
       this.victoire = 0;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPseudo() {
        return this.pseudo;
    }

    public void gagne() {
        this.victoire++;
    }

    public int getVictoire() {
        return this.victoire;
    }
}
