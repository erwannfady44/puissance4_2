import javax.swing.*;
import java.awt.*;

public class Bouton_rectangle extends JButton{
    public Bouton_rectangle() {
        //texte du bouton
        super();

        //affichage des bords
        setBorderPainted(true);
    }

    protected void paintComponent(Graphics g) {
        //définition de la couleur et de la forme
        g.setColor(Color.WHITE);
        g.fillRect(0,0,this.getWidth(), this.getHeight());
    }
}
