import java.io.Serializable;

public class Joueur implements Serializable {
    private String pseudo;
    private int victoire;
    private int numero;

    public Joueur (String sonPseudo) {
        this.pseudo = sonPseudo;
        this.victoire = 0;
    }

    public Joueur() {
       this.victoire = 0;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
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
