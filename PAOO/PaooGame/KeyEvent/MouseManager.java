package PaooGame.KeyEvent;

import PaooGame.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {

    Game g;

    public boolean isPressedLeft() {
        return pressedLeft;
    }

    public boolean isPressedRight() {
        return pressedRight;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private boolean pressedLeft, pressedRight;
    private int x, y;

    public MouseManager(Game g)
    {
        this.g = g;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int code = e.getButton();
        if(code == MouseEvent.BUTTON1)
        {
            pressedLeft = true;
        }
        else
            if(code == MouseEvent.BUTTON3)
            {
                pressedRight = true;
            }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int code = e.getButton();
        if(code == MouseEvent.BUTTON1)
        {
            pressedLeft = false;
        }
        else
        if(code == MouseEvent.BUTTON3)
        {
            pressedRight = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }


}
