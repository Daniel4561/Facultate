package PaooGame.Entity;

import PaooGame.Animations.ZombieAnimation;
import PaooGame.Game;
import PaooGame.Grapics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Zombie extends entity{
    public static int number = 0;
    Game g;
    BufferedImage[][] image;
    public boolean colid = false;
    public int type;


    public Zombie(Game g, int x, int y)
    {
        this.g = g;
        this.x = x;
        this.y = y;
        number++;
    }

    public void update(){
        int deltaX = g.bob.x - x;
        int deltaY = g.bob.y - y;
        if(deltaX*deltaX + deltaY*deltaY < 192*192) {
            if(Math.abs(deltaY) <= Math.abs(deltaX))
            {
                if(deltaX >= 0)
                    direction = "right";
                else
                    direction = "left";
            }
            else
            {
                if(deltaY >= 0)
                    direction = "down";
                else
                    direction = "up";
            }
        }
        else {
            actionLock++;
            if (actionLock == 60) {
                Random random = new Random();
                int i = random.nextInt(100) + 1;
                {
                    if (i < 25) {
                        direction = "up";
                    }
                    if (i >= 25 && i < 50) {
                        direction = "right";
                    }
                    if (i >= 50 && i < 75) {
                        direction = "down";
                    }
                    if (i >= 75) {
                        direction = "left";
                    }
                }
                actionLock = 0;
            }
        }

        //colision check
        CollisionOn = false;
        g.cDetect.checkTile(this);

        colid = g.cDetect.checkPLayer(this);



        if(countSprite > 100) {
            countSprite = 0;
        }
        else {
            countSprite += 0.1;
        }

        if(!CollisionOn) {
            switch (direction) {
                case "up":
                    y -= speed;
                    break;
                case "down":
                    y += speed;
                    break;
                case "left":
                    x -= speed;
                    break;
                case "right":
                    x += speed;
                    break;
            }
        }
        System.out.println(x+ " -- " + y);
    }

    public void draw(Graphics2D g2)
    {
        if(x + 64  > g.bob.x - g.bob.screenX &&
                x - 64  < g.bob.x + g.bob.screenX &&
                y + 64  > g.bob.y - g.bob.screenY &&
                y - 64  < g.bob.y + g.bob.screenY &&
                image !=null )
        {
        ZombieAnimation.Animation(g2, (int)(x - g.getGameCamera().getXoffset()), (int)(y - g.getGameCamera().getYoffset()), direction, countSprite, image);
        }
        else
            if(g.bob.screenX > g.bob.x ||
                g.bob.screenY > g.bob.y

            )
            {
                ZombieAnimation.Animation(g2, (int)(x - g.getGameCamera().getXoffset()), (int)(y - g.getGameCamera().getYoffset()), direction, countSprite, image);
            }
    }


}
