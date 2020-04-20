import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ClavierListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            Fenetre fenetre = new Fenetre(Main.fenetrePseudo.getPseudo1(), Main.fenetrePseudo.getPseudo2());
            fenetre.pack();
            fenetre.setVisible(true);
            Main.fenetrePseudo.dispose();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}