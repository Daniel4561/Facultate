package PaooGame.Entity;

import PaooGame.Game;
import PaooGame.Grapics.Assets;

import java.awt.*;

public class M1 extends Zombie{

    public M1(Game g, int x, int y)
    {
        super(g, x, y);
        speed = 1;
        image = Assets.M1;
        solidAreea = new Rectangle(22, 20, 20, 40);
        solidX = solidAreea.x;
        solidY = solidAreea.y;

        lives = 10;
        type = 1;
    }
}
