package PaooGame.KeyEvent;

import PaooGame.Game;

import java.awt.event.*;

public class KeyManager implements KeyListener {

    public Game g;
    public boolean up, down, left, right;
    public boolean space;
    public boolean esc;

    public KeyManager(Game g)
    {
        this.g = g;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code=e.getKeyCode();
        char keyChar = e.getKeyChar();

        if(code==KeyEvent.VK_W || code==KeyEvent.VK_UP) {
            up = true;
        }
        if(code==KeyEvent.VK_S || code==KeyEvent.VK_DOWN) {
            down = true;
        }
        if(code==KeyEvent.VK_A || code==KeyEvent.VK_LEFT) {
            left = true;
        }
        if(code==KeyEvent.VK_D || code==KeyEvent.VK_RIGHT) {
            right = true;
        }
        if(code==KeyEvent.VK_SPACE) {
            space = true;
        }
        if(code==KeyEvent.VK_ESCAPE) {
            esc = true;
        }
        if(g.context.getState() == g.nameState)
            if((keyChar >= 'A' && keyChar <= 'Z') ||(keyChar >= 'a' && keyChar <= 'z'))
            {
                if(g.buffer.length() < 10)
                {
                    g.buffer += (Character.toString(keyChar));
                }
            }
        if(keyChar == KeyEvent.VK_BACK_SPACE && g.buffer.length() > 0)
            g.buffer = g.buffer.substring(0, g.buffer.length() -1);
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code=e.getKeyCode();

        if(code==KeyEvent.VK_W || code==KeyEvent.VK_UP) {
            up = false;
        }
        if(code==KeyEvent.VK_S || code==KeyEvent.VK_DOWN) {
            down = false;
        }
        if(code==KeyEvent.VK_A || code==KeyEvent.VK_LEFT) {
            left = false;
        }
        if(code==KeyEvent.VK_D || code==KeyEvent.VK_RIGHT) {
            right = false;
        }
        if(code==KeyEvent.VK_SPACE) {
            space = false;
        }
        if(code==KeyEvent.VK_ESCAPE) {
            esc = false;
        }
    }
}


