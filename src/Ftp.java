import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.net.SocketException;

public class Ftp extends Thread{
    private String serveur = "149.202.19.146";
    private int port = 21;
    private String user = "puissance4";
    private String password = "Xona31~0";

    public Ftp() {
        super("ftp");
        this.download();
    }

    @Override
    public void run() {
        while (true) {
            Main.fichier.update();

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void upload() {
        FTPClient ftpClient = new FTPClient();
        try {

            ftpClient.connect(serveur, port);
            ftpClient.login(user, password);
            ftpClient.enterLocalPassiveMode();

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // Approche 1: upload d'un fichier en utilisant InputStream
            File fichierCoups = new File("fichiers/coups.txt");
            File fichierGrille = new File("fichiers/grille.txt");
            File fichierJoueurs = new File("fichiers/joueurs.txt");

            String chemin = "grille.txt";
            InputStream inputStream = new FileInputStream(fichierGrille);

            //résultat de l'upload
            boolean res = ftpClient.storeFile(chemin, inputStream);
            inputStream.close();

            chemin = "coups.txt";
            inputStream = new FileInputStream(fichierCoups);

            //résultat de l'upload
            res = ftpClient.storeFile(chemin, inputStream);
            inputStream.close();

            chemin = "joueurs.txt";
            inputStream = new FileInputStream(fichierJoueurs);

            //résultat de l'upload
            res = ftpClient.storeFile(chemin, inputStream);
            //fermet le flut de lecture
            inputStream.close();

        } catch (SocketException e) {

            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void  download () {

        FTPClient ftpClient = new FTPClient();
        try {

            ftpClient.connect(serveur, port);
            ftpClient.login(user, password);
            ftpClient.enterLocalPassiveMode();

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // Approche 1: upload d'un fichier en utilisant InputStream
            File fichierCoups = new File("fichiers/coups.txt");
            File fichierGrille = new File("fichiers/grille.txt");
            File fichierJoueurs = new File("fichiers/joueurs.txt");

            String chemin = "coups.txt";
            FileOutputStream ops = new FileOutputStream(fichierCoups);

            //résultat de l'upload
            boolean res = ftpClient.retrieveFile(chemin, ops);
            ops.close();

            chemin = "grille.txt";
            ops = new FileOutputStream(fichierGrille);

            //résultat de l'upload
            res = ftpClient.retrieveFile(chemin, ops);
            ops.close();

            chemin = "joueurs.txt";
            ops = new FileOutputStream(fichierJoueurs);

            //résultat de l'upload
            res = ftpClient.retrieveFile(chemin, ops);
            //fermet le flut de lecture
            ops.close();

        } catch (SocketException e) {

            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
