import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Fichier extends Thread {
    Ftp ftp;
    File fichierGrille;
    File fichierJoueurs;
    File fichierCoups;
    private int numero;
    private boolean pseudoSet;
    private boolean pause;

    public Fichier(boolean del) {
        super("ftp");
        this.pause = true;
        this.pseudoSet = false;
        fichierCoups = new File("fichiers/coups.txt");
        fichierJoueurs = new File("fichiers/joueurs.txt");
        fichierGrille = new File("fichiers/grille.txt");
        Joueur[] joueurs;
        ftp = new Ftp();
        if (!del) {
            if (this.getCoups() == -1) {
                this.writeCoups(0);
                this.numero = 0;
                joueurs = new Joueur[]{new Joueur(Main.pseudo.getPseudo()), new Joueur()};
                writeJoueur(joueurs);
            } else {
                this.numero = 1;

                ObjectInputStream ois;

                try {
                    ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fichierJoueurs)));

                    Joueur joueur = (Joueur) ois.readObject();
                    joueurs = new Joueur[]{joueur, new Joueur(Main.pseudo.getPseudo())};
                    ois.close();

                    this.writeJoueur(joueurs);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }


                joueurs = new Joueur[]{new Joueur(), new Joueur(Main.pseudo.getPseudo())};
                writeJoueur(joueurs);

            }
            writeGrille(new Grille());
        }
        ftp.upload();
    }

    public void pseudoSet() {
        this.pseudoSet = true;
    }

    public void setPause(boolean b) {
        this.pause = b;
    }

    public boolean isPause() {
        return pause;
    }

    @Override
    public void run() {
        while (true) {
            while (!this.pause && (Main.fenetre.getCoups() % 2) != this.getNumero()) {
                this.update();
            }
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Joueur[] updateJoueurs() {
        Joueur[] joueurs = this.getJoueur();
        while (joueurs[0].getPseudo() == null || joueurs[1].getPseudo() == null) {
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return joueurs;
    }

    public void writeGrille(Grille grille) {
        ObjectOutputStream oos;

        try {
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fichierGrille)));

            oos.writeObject(grille);

            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ftp.upload();
    }

    public void writeJoueur(Joueur[] joueur) {
        ObjectOutputStream oos;

        try {
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fichierJoueurs)));

            oos.writeObject(joueur[0]);
            oos.writeObject(joueur[1]);

            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        ftp.upload();
    }

    public Joueur[] getJoueur() {
        ObjectInputStream ois;
        Joueur[] joueurs = new Joueur[2];
        Joueur j1 = null, j2 = null;
        try {
            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fichierJoueurs)));
            j1 = (Joueur) ois.readObject();
            j2 = (Joueur) ois.readObject();
            ois.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        joueurs[0] = j1;
        joueurs[1] = j2;
        return joueurs;
    }

    public void writeCoups(int coups) {
        DataOutputStream dos;

        try {
            dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fichierCoups)));
            dos.writeInt(coups);
            dos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        ftp.upload();
    }

    public int getCoups() {
        ftp.download();

        int coups = 0;
        DataInputStream dis;

        try {
            dis = new DataInputStream(new BufferedInputStream(new FileInputStream(fichierCoups)));
            coups = (int) dis.readInt();

            dis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return coups;
    }

    public void update() {
        ftp.download();
        Main.fenetre.update(this.getGrille(), this.getCoups(), this.getJoueur());
    }

    public Grille getGrille() {
        ObjectInputStream ois;
        Grille grille = null;
        try {
            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fichierGrille)));
            int i = 0, j = 0;
            grille = (Grille) ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return grille;
    }

    public int getNumero() {
        return this.numero;
    }

    public void incrementerCoups() {
        int coups = this.getCoups();

        this.writeCoups(coups + 1);

        ftp.upload();
    }

    public void delete() {
        /*this.fichierCoups.delete();
        this.fichierGrille.delete();
        this.fichierJoueurs.delete();*/

        try {
            Files.delete(Paths.get("fichiers/grille.txt"));
            Files.delete(Paths.get("fichiers/coups.txt"));
            Files.delete(Paths.get("fichiers/joueurs.txt"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fichierJoueurs.createNewFile();
            fichierCoups.createNewFile();
            fichierGrille.createNewFile();

            this.writeCoups(-1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

