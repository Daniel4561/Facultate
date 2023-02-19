package PaooGame.Entity;

import PaooGame.Game;
import PaooGame.Grapics.Assets;

import java.awt.*;

public class R32 extends Zombie{

    public R32(Game g, int x, int y)
    {
        super(g, x, y);
        speed = 1;
        image = Assets.R32;
        solidAreea = new Rectangle(22, 20, 20, 40);
        solidX = solidAreea.x;
        solidY = solidAreea.y;

        lives = 25;
        type = 3;
    }
}
