package PaooGame.Util;

import PaooGame.Game;
import PaooGame.Grapics.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

public class Win {

    Game g;
    BufferedImage image;
    Color color = Color.BLUE;

    public Win(Game g)
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
        g2.drawString("You Win", 450, 150);

        g2.setFont(new Font("Arial", Font.ITALIC, 70));
        g2.drawString("Your  total score is: " + g.font.df.format(g.font.timeCounter), 320, 300);

        if(mouseOver(510, 450))
            color = Color.red;
        else
            color = Color.blue;
        drawText(g2, "New Game", color, 560, 500);
        drawRectangle(g2,510, 450 );


        if(mouseOver(510, 550))
            color = Color.red;
        else
            color = Color.blue;
        drawText(g2, "Exit", color, 620, 600);
        drawRectangle(g2, 510, 550);
    }

    public void update() throws FileNotFoundException, InterruptedException {
        if(mouseCliked(510, 450))
        {
            g.context.setState(g.nameState);
            g.buffer = "";
            Thread.sleep(150);
        }
        if(mouseCliked(510, 550))
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
