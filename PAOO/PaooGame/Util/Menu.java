package PaooGame.Util;

import PaooGame.Game;
import PaooGame.Grapics.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.lang.ref.SoftReference;

public class Menu {

    Game g;
    BufferedImage image;
    Rectangle rectangle;
    Color color = Color.BLUE;
    public Menu(Game g)
    {
        this.g = g;
        image = ImageLoader.LoadImage("/textures/menu.jpg");
        rectangle = new Rectangle(100, 60);
    }

    public void draw(Graphics2D g2)
    {
         g2.drawImage(image, null, 0, 0);
        g2.setFont(new Font("Arial",Font.BOLD, 80));
        g2.setColor(Color.red);
        g2.drawString("Soldat BOB VS Zombies", 190, 130);

        if(mouseOver( 500, 190))
            color = Color.red;
        else
            color = Color.BLUE;
        drawText(g2, "New Game",color, 530, 240);
        drawRectangle(g2, 190);
         if(mouseOver( 500, 290))
             color = Color.red;
         else
             color = Color.BLUE;
         drawText(g2, "Load",color, 590, 340);
         drawRectangle(g2, 290);
        if(mouseOver(500, 390))
            color = Color.red;
        else
            color = Color.BLUE;
         drawText(g2, "Scores",color, 570, 440);
         drawRectangle(g2, 390);
        if(mouseOver(500, 490))
            color = Color.red;
        else
            color = Color.BLUE;
         drawText(g2, "Setings",color, 562, 540);
         drawRectangle(g2, 490);
        if(mouseOver(500, 590))
            color = Color.red;
        else
            color = Color.BLUE;
         drawText(g2, "Exit",color, 600, 640);
         drawRectangle(g2, 590);
    }

    public void update() throws FileNotFoundException, InterruptedException {
        if(mouseCliked(500, 190))
        {
            g.buffer = "";
            g.context.setState(g.nameState);
            Thread.sleep(150);
        }
        if(mouseCliked(500, 290))
        {
            g.context.setState(g.loadState);
            g.load.lastState = g.menuState;
            Thread.sleep(150);
        }
        if(mouseCliked(500, 390))
        {
            g.context.setState(g.scoreState);
            Thread.sleep(150);
        }
        if(mouseCliked(500, 490))
        {
            g.context.setState(g.setingState);
            g.setings.lastState = g.menuState;
            Thread.sleep(150);
        }
        if(mouseCliked(500, 590))
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
    
    public void drawRectangle(Graphics2D g2, int y)
    {
        g2.setColor(Color.white);
        g2.draw(new Rectangle( 500, y,320, 60));
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
