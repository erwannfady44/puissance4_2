import javax.swing.*;
import java.awt.*;

public class Bouton_rond extends JButton{
    private Color c;

    public Bouton_rond(Color couleur) {
        super();
        this.c = couleur;
        setBorderPainted(true);
        setFocusPainted(false);
        setContentAreaFilled(false);
    }

    protected void paintComponent(Graphics g) {
        g.setColor(c);
        g.fillOval((int) (this.getWidth() / 6),(int) (this.getHeight() / 6),(int) (this.getWidth() / 1.5),(int) (this.getHeight() / 1.5));
    }

}
