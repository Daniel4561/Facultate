package PaooGame.Animations;

import PaooGame.Grapics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BobAnimations {
    public static void Animation(Graphics2D g, String direction, String shotDirection, boolean shot, int x, int y, double count)
    {
        switch (direction)
        {
            case "null":
                if(shot)
                {
                    switch(shotDirection)
                    {
                        case"up":
                            g.drawImage(Assets.bob_shot[2],x,y,64,64,null);
                            break;
                        case"down":
                            g.drawImage(Assets.bob_shot[0],x,y,64,64,null);
                            break;
                        case"left":
                            g.drawImage(Assets.bob_shot[3],x,y,64,64,null);
                            break;
                        case"right":
                            g.drawImage(Assets.bob_shot[1],x,y,64,64,null);
                            break;
                    }
                }
                else
                    g.drawImage(Assets.bob_walk[4][0],x,y,64,64,null);
                break;
            case "up":
                if (shot)
                    g.drawImage(Assets.bob_shot[2],x,y,64,64,null);
                else
                    g.drawImage(Assets.bob_walk[(int) count%4][2],x,y,64,64,null);
                break;
            case "down":
                if (shot)
                    g.drawImage(Assets.bob_shot[0],x,y,64,64,null);
                else
                    g.drawImage(Assets.bob_walk[(int) count%4][0],x,y,64,64,null);
                break;
            case "right":
                if (shot)
                    g.drawImage(Assets.bob_shot[1],x,y,64,64,null);
                else
                    g.drawImage(Assets.bob_walk[(int) count%4][3],x,y,64,64,null);
                break;
            case "left":
                if (shot)
                    g.drawImage(Assets.bob_shot[3],x,y,64,64,null);
                else
                    g.drawImage(Assets.bob_walk[(int) count%4][1],x,y,64,64,null);
                break;

        }
    }
}
