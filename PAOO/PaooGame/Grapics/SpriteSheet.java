package PaooGame.Grapics;


import java.awt.*;
import java.awt.image.BufferedImage;


public class SpriteSheet {

    private BufferedImage spriteSheet;
    public static int tileWidth = 64;
    public static int tileHeight = 64;


    public SpriteSheet(BufferedImage buffIm)
    {
        spriteSheet = buffIm;
    }

    public BufferedImage crop(int x, int y)
    {
        return spriteSheet.getSubimage(x*tileWidth,y*tileHeight, tileWidth, tileHeight);
    }
}