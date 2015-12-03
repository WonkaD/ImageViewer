package application;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseShift implements MouseListener {

    private int first;
    private String command;
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    public String getCommand() {
        return command;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.first = e.getX();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getX() < first) {
            command = "prev";
        } else if (e.getX() > first) {
            command = "next";
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
