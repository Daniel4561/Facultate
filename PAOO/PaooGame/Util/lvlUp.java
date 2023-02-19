package PaooGame.Util;

import PaooGame.Entity.Zombie;
import PaooGame.Game;
import PaooGame.Grapics.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.nio.Buffer;

public class lvlUp {

    Game g;
    BufferedImage image;
    Color color;

    public lvlUp(Game g)
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
        g2.drawString("Level up", 450, 150);

        g2.setFont(new Font("Arial", Font.ITALIC, 70));
        g2.drawString("Your time is: " + g.font.df.format(g.font.timeCounter), 380, 300);

        if(mouseOver(500, 350))
            color = Color.red;
        else
            color = Color.blue;
        drawText(g2, "Next level", color, 550, 400);
        drawRectangle(g2,500, 350 );

        if(mouseOver(500, 450))
            color = Color.red;
        else
            color = Color.blue;
        drawText(g2, "Load", color, 600, 500);
        drawRectangle(g2,500, 450 );

        if(mouseOver(500, 550))
            color = Color.red;
        else
            color = Color.blue;
        drawText(g2, "Menu", color, 600, 600);
        drawRectangle(g2,500, 550 );

        if(mouseOver(500, 650))
            color = Color.red;
        else
            color = Color.blue;
        drawText(g2, "Exit", color, 620, 700);
        drawRectangle(g2, 500, 650);
    }

    public void update() throws FileNotFoundException, InterruptedException {
        if(mouseCliked(500, 350))
        {
            Zombie.number = 0;
            g.lvl++;
            if(g.lvl == 2) {
                g.setLvl2();
            }
            if(g.lvl == 3)
                g.setLvl3();
            g.context.setState(g.playState);
        }
        if(mouseCliked(500, 450))
        {
            g.context.setState(g.loadState);
            g.load.lastState = g.lvlUpState;
            Thread.sleep(150);
        }
        if(mouseCliked(500, 550))
        {
            g.context.setState(g.menuState);
            g.font.timeCounter = 0;
            g.buffer = "";
            Thread.sleep(150);
        }
        if(mouseCliked(500, 650))
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
