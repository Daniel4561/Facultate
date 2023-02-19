package PaooGame.Util;
import PaooGame.Game;

import java.awt.*;


import static PaooGame.Grapics.Assets.heart;

public class Heart{

    Game g;
    int xPoz;

    public Heart(Game g){
        this.g = g;
        xPoz = 20;
    }

    public void draw(Graphics2D g2)
    {
        for (int i=0; i < g.bob.lives; ++i)
        {

            g2.drawImage(heart, xPoz, 700, 64, 64, null);
            xPoz += 50;
        }
        xPoz = 20;
    }

}