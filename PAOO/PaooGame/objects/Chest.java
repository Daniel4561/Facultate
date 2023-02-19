package PaooGame.objects;

import PaooGame.Grapics.Assets;

import java.awt.*;

public class Chest extends SuperObject{

    public Chest(int x, int y)
    {
        super(Assets.ObsChest);
        worldx = x;
        worldy = y;
        collision = true;
        name = "Chest";
        solidAreea = new Rectangle(8, 8, 48, 48);
    }
}
