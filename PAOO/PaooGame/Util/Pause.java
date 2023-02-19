package PaooGame.Util;

import PaooGame.DataBase.AccesDataBase;
import PaooGame.Game;
import PaooGame.Grapics.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

public class Pause {

    Game g;
    BufferedImage image;
    Color color = Color.BLUE;

    public Pause(Game g)
    {
        this.g = g;
        image = ImageLoader.LoadImage("/textures/menu.jpg");
        color = Color.blue;
    }

    public void draw(Graphics2D g2)
    {
        g2.drawImage(image, null, 0, 0);
        g2.setFont(new Font("Arial",Font.BOLD, 100));
        g2.setColor(Color.red);
        g2.drawString("Pause:", 500, 100);


        if(mouseOver(510, 150))
            color = Color.red;
        else
            color = Color.blue;
        drawText(g2, "Resume", color, 570, 200);
        drawRectangle(g2,510, 150 );

        if(mouseOver(510, 250))
            color = Color.red;
        else
            color = Color.blue;
        drawText(g2, "Menu", color, 600, 300);
        drawRectangle(g2,510, 250 );

        if(mouseOver(510, 350))
            color = Color.red;
        else
            color = Color.blue;
        drawText(g2, "Load", color, 600, 400);
        drawRectangle(g2,510, 350 );

        if(mouseOver(510, 450))
            color = Color.red;
        else
            color = Color.blue;
        drawText(g2, "Save", color, 600, 500);
        drawRectangle(g2,510, 450 );

        if(mouseOver(510, 550))
            color = Color.red;
        else
            color = Color.blue;
        drawText(g2, "Settings", color, 570, 600);
        drawRectangle(g2,510, 550 );


        if(mouseOver(510, 650))
            color = Color.red;
        else
            color = Color.blue;
        drawText(g2, "Exit", color, 635, 700);
        drawRectangle(g2, 510, 650);
    }

    public void update() throws FileNotFoundException, InterruptedException {
        if(mouseCliked(510, 150))
        {
            g.context.setState(g.playState);
        }
        if(mouseCliked(510, 250))
        {
            g.context.setState(g.menuState);
            g.font.timeCounter = 0;
            g.buffer = "";
            Thread.sleep(150);
        }
        if(mouseCliked(510, 350))
        {
            g.context.setState(g.loadState);
            g.load.lastState = g.pauseState;
            Thread.sleep(150);
        }
        if(mouseCliked(510, 450))
        {
            AccesDataBase.saveToDataBase(g.buffer, g.bob, g.font.timeCounter, g.lvl, g.zombie);
            Thread.sleep(200);
        }
        if(mouseCliked(510, 550))
        {
            g.context.setState(g.setingState);
            g.setings.lastState = g.pauseState;
            Thread.sleep(150);
        }
        if(mouseCliked(510, 650))
        {
            System.exit(0);
        }
    }

    public void drawText(Graphics2D g2, String text,Color color, int x, int y)
    {
        g2.setFont(new Font("Arial", Font.BOLD, 50));
        g2.setColor(color);
        g2.drawString(text, x, y);
    }

    public void drawRectangle(Graphics2D g2,int x, int y)
    {
        g2.setColor(Color.white);
        g2.draw(new Rectangle( x, y,320, 60));
    }

    public boolean mouseOver(int x, int y)
    {
        return x <= g.mouse.getX() && x +320 >= g.mouse.getX()  && y <= g.mouse.getY() && y +60 >= g.mouse.getY();
    }

    public boolean mouseCliked(int x, int y)
    {
        return mouseOver(x, y) && g.mouse.isPressedLeft();
    }

}
