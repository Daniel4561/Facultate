package PaooGame.Util;

import PaooGame.DataBase.AccesDataBase;
import PaooGame.Game;
import PaooGame.Grapics.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.lang.ref.SoftReference;

public class Scores{

    Game g;
    BufferedImage image;
    Rectangle rectangle;
    Color color = Color.BLUE;
    public Scores(Game g)
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
        g2.drawString("Scores", 530, 80);

        g2.setFont(new Font("Arial", Font.ITALIC, 50));
        g2.setColor(Color.yellow);
        g2.drawString("Name: ",180, 130 );
        g2.drawString("Time: ",920, 130 );

        AccesDataBase.getScores(g2);

        if(mouseOver(500, 650))
            color = Color.red;
        else
            color = Color.BLUE;
        drawText(g2, "Menu",color, 590, 700);
        drawRectangle(g2, 650);
    }

    public void update() throws FileNotFoundException, InterruptedException {

        if(mouseCliked(500, 650))
        {
            g.context.setState(g.menuState);
            Thread.sleep(150);
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
