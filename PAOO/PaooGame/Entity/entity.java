package PaooGame.Entity;

import java.awt.*;

public class entity {
    public int x,y;
    public int speed;
    protected int actionLock = 59;
    public double countSprite = 0;

    public String direction = "null";

    public Rectangle solidAreea;
    public boolean CollisionOn = false;

    public int solidX;
    public int solidY;

    public int lives;

    public boolean alive = true;
}
