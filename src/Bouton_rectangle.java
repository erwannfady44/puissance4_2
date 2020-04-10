import javax.swing.*;
import java.awt.*;

public class Bouton_rectangle extends JButton{
    public Bouton_rectangle() {
        super();
        setBorderPainted(true);
        setFocusPainted(false);
        setContentAreaFilled(false);
    }

    protected void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,this.getWidth(), this.getHeight());
    }
}
