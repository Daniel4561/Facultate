package PaooGame.Util;
import PaooGame.Game;
import PaooGame.Grapics.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

public class Lose {

    Game g;
    BufferedImage image;
    Rectangle rectangle;
    Color color = Color.BLUE;

    public Lose(Game g)
    {
        this.g = g;
        image = ImageLoader.LoadImage("/textures/menu.jpg");
        rectangle = new Rectangle(100, 60);
    }

    public void draw(Graphics2D g2)
    {
        g2.drawImage(image, null, 0, 0);

        g2.setFont(new Font("Arial", Font.ITALIC, 90 ));
        g2.setColor(Color.red);
        g2.drawString("YOU  LOST", 450, 150);


        if(mouseOver(500, 350))
            color = Color.red;
        else
            color = Color.blue;
        drawText(g2, "Menu", color, 600, 400);
        drawRectangle(g2,500, 350 );

        if(mouseOver(500, 450))
            color = Color.red;
        else
            color = Color.blue;
        drawText(g2, "Load", color, 605, 500);
        drawRectangle(g2, 500, 450);

        if(mouseOver(500, 550))
            color = Color.red;
        else
            color = Color.blue;
        drawText(g2, "Exit", color, 615, 600);
        drawRectangle(g2, 500, 550);
    }

    public void update() throws FileNotFoundException, InterruptedException {
        if(mouseCliked(500, 350))
        {
            g.context.setState(g.menuState);
            Thread.sleep(150);
        }
        if(mouseCliked(500, 450))
        {
            g.context.setState(g.loadState);
            g.load.lastState = g.loseState;
            Thread.sleep(150);
        }
        if(mouseCliked(500, 550))
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
