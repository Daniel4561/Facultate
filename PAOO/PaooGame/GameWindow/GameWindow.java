package PaooGame.GameWindow;

import PaooGame.Game;
import PaooGame.KeyEvent.KeyManager;
import PaooGame.KeyEvent.MouseManager;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    public Game g;
    private JFrame wndFrame;
    private String wndTitle;
    private int wndWidth;
    private int wndHeight;

    private Canvas canvas;
    public KeyManager key ;
    public MouseManager mouse;


    public GameWindow(Game g, String title, int width, int height, MouseManager mouse)
    {
        this.g = g;
        wndTitle = title;
        wndHeight = height;
        wndWidth = width;
        wndFrame = null;
        this.mouse = mouse;
        key = new KeyManager(g);
    }

    public void BuildGameWindow()
    {

        if(wndFrame != null)
        {
            return;
        }
        wndFrame = new JFrame(wndTitle);//alocare memorie

        wndFrame.setSize(wndWidth, wndHeight);//setare dimensiuni

        wndFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close window

        wndFrame.setResizable(false);//not resizeble

        wndFrame.setLocationRelativeTo(null);//fereastra in mijlocul ecranului

        wndFrame.setVisible(true);

        canvas = new Canvas();

        canvas.setPreferredSize(new Dimension(wndWidth, wndHeight));

        canvas.setMaximumSize(new Dimension(wndWidth, wndHeight));
        canvas.setMinimumSize(new Dimension(wndWidth, wndHeight));

        canvas.addKeyListener(key);
        canvas.addMouseListener(mouse);
        canvas.addMouseMotionListener(mouse);

        canvas.setFocusable(true);
        canvas.setBackground(Color.darkGray);
        wndFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/textures/icon.png")));
        wndFrame.add(canvas);

        wndFrame.pack();


    }

    public int GetWndWidth(){
        return wndWidth;
    }

    public int GetWndHeight(){
        return wndHeight;
    }

    public Canvas GetCanvas(){
        return canvas;
    }
}


