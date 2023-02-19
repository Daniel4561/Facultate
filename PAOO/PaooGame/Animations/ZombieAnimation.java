package PaooGame.Animations;

import PaooGame.Grapics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ZombieAnimation {

    public static void Animation(Graphics2D g2, int x, int y, String direction, double count, BufferedImage[][] image)
    {
        switch(direction)
        {
            case"up":
                g2.drawImage(image[(int) count%4][3], x, y, 64, 64, null);
                break;
            case"down":
                g2.drawImage(image[(int) count%4][0], x, y, 64, 64, null);
                break;
            case"right":
                g2.drawImage(image[(int)count%4][2], x, y, 64, 64, null);
                break;
            case"left":
                g2.drawImage(image[(int)count%4][1], x, y, 64, 64, null);
                break;

        }
    }
}
