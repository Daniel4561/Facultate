package PaooGame.Util;

import PaooGame.DataBase.AccesDataBase;
import PaooGame.Game;
import PaooGame.Grapics.ImageLoader;
import PaooGame.State.MenuState;
import PaooGame.State.State;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

public class Load {
    Game g;
    BufferedImage image;
    Rectangle rectangle;
    Color color = Color.BLUE;
    public int number =  -1;
    State lastState = new MenuState();

    public Load(Game g)
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
        g2.drawString("Load:", 540, 70);

        g2.setFont(new Font("Arial", Font.ITALIC, 50));
        g2.setColor(Color.white);
        g2.drawString("Nume:", 150,150);
        g2.drawString("Nivel:", 570,150);
        g2.drawString("Timp:", 1000,150);

        g2.setColor(Color.blue);

        if(mouseOver(500, 680))
            color = Color.red;
        else
            color = Color.BLUE;
        drawText(g2, "Back",color, 600, 730);
        drawRectangle(g2, 680);
        AccesDataBase.loadDataBase(g,g2);
    }


    public void update() throws FileNotFoundException, InterruptedException {

        if(mouseCliked(90, 170)) {
            number = 0;
            g.context.setState(g.playState);
            Thread.sleep(150);
        }
        if(mouseCliked(90, 270)) {
            number = 1;
            g.context.setState(g.playState);
            Thread.sleep(150);
        }
        if(mouseCliked(90, 370)) {
            number = 2;
            g.context.setState(g.playState);
            Thread.sleep(150);
        }
        if(mouseCliked(90, 470)) {
            number = 3;
            g.context.setState(g.playState);
            Thread.sleep(150);
        }
        if(mouseCliked(90, 570)) {
            number = 4;
            g.context.setState(g.playState);
            Thread.sleep(150);
        }
        if(number != -1)
            AccesDataBase.loadDataBaseUpdate(g,number);
        if(mouseCliked(90, 680))
        {
            g.context.setState(lastState);
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
        return x <= g.mouse.getX() && x +1100 >= g.mouse.getX()  && y <= g.mouse.getY() && y +60 >= g.mouse.getY();
    }

    public boolean mouseCliked(int x, int y)
    {
        return mouseOver(x, y) && g.mouse.isPressedLeft();
    }
}
