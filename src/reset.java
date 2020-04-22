public class reset {
    public static Fichier fichier;
    public static void main(String[] args) {
        fichier = new Fichier(true);
        fichier.delete();
    }
}
