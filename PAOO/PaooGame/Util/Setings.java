package PaooGame.Util;

import PaooGame.Game;
import PaooGame.Grapics.ImageLoader;
import PaooGame.State.MenuState;
import PaooGame.State.State;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Setings {

    Game g;
    Color color;
    BufferedImage image;
    State lastState = new MenuState();
    Rectangle rectangle;
    String musicon = "on";
    public String soundon = "on";

    public Setings(Game g)
    {
        this.g = g;
        image = ImageLoader.LoadImage("/textures/menu.jpg");
        rectangle = new Rectangle(100, 60);
    }

    public void draw(Graphics2D g2)
    {
        g2.drawImage(image, null, 0, 0);

        g2.setFont(new Font("Arial", Font.BOLD, 90 ));
        g2.setColor(Color.red);
        g2.drawString("SETTINGS", 450, 150);


        if(mouseOver(500, 350))
            color = Color.red;
        else
            color = Color.blue;
        drawText(g2, "Music "+musicon, color, 555, 400);
        drawRectangle(g2,500, 350 );

        if(mouseOver(500, 450))
            color = Color.red;
        else
            color = Color.blue;
        drawText(g2, "Sound "+soundon, color, 550, 500);
        drawRectangle(g2, 500, 450);

        if(mouseOver(500, 550))
            color = Color.red;
        else
            color = Color.blue;
        drawText(g2, "Back", color, 600, 600);
        drawRectangle(g2, 500, 550);
    }

    public void update() throws InterruptedException {
        if(mouseCliked(500, 350))
        {
            if(musicon == "on") {
                musicon = "off";
                g.soundManager.stopMusic();
            }
            else {
                musicon = "on";
                g.soundManager.playMusic();
            }
            Thread.sleep(150);
        }
        if(mouseCliked(500, 450))
        {
            if(soundon == "on")
                soundon = "off";
            else
                soundon = "on";
            Thread.sleep(150);
        }
        if(mouseCliked(500, 550))
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

    public void drawRectangle(Graphics2D g2, int x, int y)
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
