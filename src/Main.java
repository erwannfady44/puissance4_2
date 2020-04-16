import java.awt.*;
//import static javax.swing.JOptionPane.*;

public class Main {
    public static FenetrePseudo fenetrePseudo;
    public static void main(String[] args) {

        fenetrePseudo = new FenetrePseudo();
        //fenetrePseudo.setIconImage(new ImageIcon("images/logo-puissance-4.png").getImage());

        Image icone = Toolkit.getDefaultToolkit().getImage(".\\images\\logo-puissance-4.png");
        fenetrePseudo.setIconImage(icone);

        fenetrePseudo.pack();
        fenetrePseudo.setVisible(true);
    }
}
