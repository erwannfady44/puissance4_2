import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.net.SocketException;

public class Ftp {
    private String serveur = "149.202.19.146";
    private int port = 21;
    private String user = "puissance4";
    private String password = "Xona31~0";


    public void upload() {
        FTPClient ftpClient = new FTPClient();
        try {

            ftpClient.connect(serveur, port);
            ftpClient.login(user, password);
            ftpClient.enterLocalPassiveMode();

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // Approche 1: upload d'un fichier en utilisant InputStream
            File file = new File("fichiers\\grille.txt");

            String chemin = "grille.txt";
            InputStream inputStream = new FileInputStream(file);

            //résultat de l'upload
            boolean res = ftpClient.storeFile(chemin, inputStream);
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
            File file = new File("fichiers\\grilles.txt");

            String chemin = "grille.txt";
            FileOutputStream ops = new FileOutputStream(file);

            //résultat de l'upload
            boolean res = ftpClient.retrieveFile(chemin, ops);
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
