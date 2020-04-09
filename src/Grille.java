public class Grille {
    private Points[][] lesPoints = new Points [7][6];

    public Grille() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                lesPoints[i][j]  = null;
            }
        }
    }

    public int getPoint(int i, int j) {
        if (this.lesPoints[i][j] != null)
            return this.lesPoints[i][j].getJoueur();
        else
            return 0;
    }

    public void addPoint(int i, int j, int joueur) {
        this.lesPoints[i][j] = new Points(joueur);
    }


}
