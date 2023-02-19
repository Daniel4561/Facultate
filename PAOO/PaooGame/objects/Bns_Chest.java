package PaooGame.objects;

import PaooGame.Grapics.Assets;

import java.awt.*;

public class Bns_Chest extends SuperObject{



    public Bns_Chest(int x, int y)
    {
        super(Assets.BnsCheast);
        worldx = x;
        worldy = y;
        collision = true;
        name = "BnsChest";
        solidAreea = new Rectangle(0,0,32, 32);
    }
}
