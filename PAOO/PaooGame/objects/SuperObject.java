package PaooGame.objects;

import PaooGame.Animations.ZombieAnimation;
import PaooGame.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {


    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldx, worldy;
    public Rectangle solidAreea;
    public int solidX = 0;
    public int solidY = 0;

    public SuperObject(BufferedImage image) {
        this.image = image;
    }

    public void draw(Graphics2D g2, Game g)
    {

        if(worldx + 64  > g.bob.x - g.bob.screenX &&
                worldx - 64  < g.bob.x + g.bob.screenX &&
                worldy + 64  > g.bob.y - g.bob.screenY &&
                worldy - 64  < g.bob.y + g.bob.screenY &&
                image !=null )
        {
            g2.drawImage(image, (int)(worldx - g.getGameCamera().getXoffset()), (int)(worldy - g.getGameCamera().getYoffset()), 64, 64, null);
        }
        else
        if(g.bob.screenX > g.bob.x ||
                g.bob.screenY > g.bob.y)
        {
            g2.drawImage(image, (int)(worldx - g.getGameCamera().getXoffset()), (int)(worldy - g.getGameCamera().getYoffset()), 64, 64, null);
        }
    }

}
