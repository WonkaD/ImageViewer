package application;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardManager implements KeyListener {

    private String command;

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    public String getCommand() {
        return command;
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            command = "prev";
        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            command = "next";
        }
    }
    
}
