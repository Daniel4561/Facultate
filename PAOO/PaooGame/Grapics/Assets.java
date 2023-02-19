package PaooGame.Grapics;


import java.awt.*;
import java.awt.image.BufferedImage;


public class Assets {

    public static BufferedImage[][] bob_walk;
    public static BufferedImage[] bob_shot;
    public static BufferedImage[] road;
    public static BufferedImage[] border;
    public static BufferedImage[] CBorder;
    public static BufferedImage grass;
    public static BufferedImage sand;
    public static BufferedImage water;
    public static BufferedImage STree;
    public static BufferedImage ObsChest;
    public static BufferedImage BnsCheast;
    public static BufferedImage[][] tree;
    public static BufferedImage[][] bHouse;
    public static BufferedImage[][] rHouse;
    public static BufferedImage[][] M1;
    public static BufferedImage heart;
    public static BufferedImage[][] K53;
    public static BufferedImage[][] R32;

    public static BufferedImage projectile;



    public static void Init()
    {

        SpriteSheet bob = new SpriteSheet(ImageLoader.LoadImage("/textures/soldat.png"));
        SpriteSheet worldTiles = new SpriteSheet(ImageLoader.LoadImage("/textures/Elem_Map1.png"));
        SpriteSheet m1 = new SpriteSheet(ImageLoader.LoadImage("/textures/M1.png"));
        SpriteSheet k53 = new SpriteSheet(ImageLoader.LoadImage("/textures/K53.png"));
        SpriteSheet r32 = new SpriteSheet(ImageLoader.LoadImage("/textures/R32.png"));
        SpriteSheet objects = new SpriteSheet(ImageLoader.LoadImage("/textures/objects.png"));
        SpriteSheet proj = new SpriteSheet(ImageLoader.LoadImage("/textures/proiectile.png"));


        bob_walk = new BufferedImage[5][4];
        for(int i=0;i<5;++i)
            for(int j=0;j<4;++j)
            {
                bob_walk[i][j] = bob.crop(i, j);
            }
        bob_shot = new BufferedImage[4];
        for(int i=0; i<4;++i)
            bob_shot[i] = bob.crop(i, 4);


        road = new BufferedImage[5];
        for(int i=0;i<3;++i)
            road[i] = worldTiles.crop(i,0);
        road[3] = worldTiles.crop(1,2);
        road[4] = worldTiles.crop(2,2);

        border = new BufferedImage[4];
        for(int i=0;i<4;++i)
            border[i] = worldTiles.crop(i+3,0);

        CBorder = new BufferedImage[4];
        for(int i=7;i<10;++i)
            CBorder[i-7] = worldTiles.crop(i,0);
        CBorder[3] = worldTiles.crop(0,1);

        grass = worldTiles.crop(1,1);

        sand = worldTiles.crop(2,1);

        water = worldTiles.crop(3,1);

        STree = worldTiles.crop(4,1);


        ObsChest = objects.crop(1,0);

        BnsCheast = objects.crop(0,0);

        tree = new BufferedImage[2][2];
        for(int i=0;i<2;++i)
            for(int j=0;j<2;++j)
                tree[i][j] = worldTiles.crop(i*2+j+3, 2);

        bHouse = new BufferedImage[3][3];

        bHouse[0][0] = worldTiles.crop(7,2);
        bHouse[0][1] = worldTiles.crop(8,2);
        bHouse[0][2] = worldTiles.crop(9,2);
        for(int i=0;i<2 ;++i)
            for(int j=0;j<3;++j)
                bHouse[i+1][j] = worldTiles.crop(i*3+j,3);

        rHouse = new BufferedImage[3][3];

        rHouse[0][0] = worldTiles.crop(6,3);
        rHouse[0][1] = worldTiles.crop(7,3);
        rHouse[0][2] = worldTiles.crop(8,3);
        rHouse[1][0] = worldTiles.crop(9,3);
        rHouse[1][1] = worldTiles.crop(0,4);
        rHouse[1][2] = worldTiles.crop(1,4);
        rHouse[2][0] = worldTiles.crop(2,4);
        rHouse[2][1] = worldTiles.crop(3,4);
        rHouse[2][2] = worldTiles.crop(4,4);

        heart = objects.crop(2,0);

        M1 = new BufferedImage[4][4];
        for(int i=0; i<4;++i)
        {
            for(int j=0;j<4;++j)
            {
                M1[i][j] = m1.crop(i,j);
            }
        }

        K53 = new BufferedImage[4][4];
        for(int i=0; i<4;++i)
        {
            for(int j=0;j<4;++j)
            {
                K53[i][j] = k53.crop(i,j);
            }
        }

        R32 = new BufferedImage[4][4];
        for(int i=0; i<4;++i)
        {
            for(int j=0;j<4;++j)
            {
                R32[i][j] = r32.crop(i,j);
            }
        }

        projectile = proj.crop(0,0);

    }
}
