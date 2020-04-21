import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ClavierListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            Main.pseudo.dispose();
            Main.fichier = new Fichier();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
