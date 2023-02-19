package PaooGame.Entity;

import PaooGame.Game;
import PaooGame.Grapics.Assets;

import java.awt.*;

public class K53 extends Zombie{

    public K53(Game g, int x, int y) {
        super(g, x, y);
        speed = 2;
        image = Assets.K53;
        solidAreea = new Rectangle(22, 20, 20, 40);
        solidX = solidAreea.x;
        solidY = solidAreea.y;

        lives = 15;
        type = 2;
    }
}
