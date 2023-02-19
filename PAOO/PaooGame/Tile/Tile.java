package PaooGame.Tile;

import PaooGame.Grapics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Tile
    \brief Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile
{
    private boolean colision ;
    private static final int NO_TILES   = 100;
    public static Tile[] tiles          = new Tile[NO_TILES];       /*!< Vector de referinte de tipuri de dale.*/

    /// De remarcat ca urmatoarele dale sunt statice si publice. Acest lucru imi permite sa le am incarcate
    /// o singura data in memorie

    private static BufferedImage Assets1;
    public static Tile road = new Tile(Assets.road[0], 1, false);
    public static Tile roadR = new Tile(Assets.road[1], 2, false);
    public static Tile roadL = new Tile(Assets.road[2], 3, false);
    public static Tile lborder = new Tile(Assets.border[0], 4, false);
    public static Tile rborder = new Tile(Assets.border[1], 5, false);
    public static Tile uborder = new Tile(Assets.border[2], 6, false);
    public static Tile dborder = new Tile(Assets.border[3], 7, false);
    public static Tile rUborder = new Tile(Assets.CBorder[0], 8, false);
    public static Tile lUborder = new Tile(Assets.CBorder[1], 9, false);
    public static Tile rDborder = new Tile(Assets.CBorder[2], 10, false);
    public static Tile lDborder = new Tile(Assets.CBorder[3], 11, false);
    public static Tile grass = new Tile(Assets.grass, 12, false);
    public static Tile sand = new Tile(Assets.sand, 13, true);
    public static Tile water = new Tile(Assets.water, 14, true);
    public static Tile stree = new Tile(Assets.STree, 15, true);
    public static Tile obsChest = new Tile(Assets.ObsChest, 20, true);
    public static Tile droad = new Tile(Assets.road[3], 22, false);
    public static Tile uroad = new Tile(Assets.road[4], 23, false);
    public static Tile tree1 = new Tile(Assets.tree[0][0], 24, true);
    public static Tile tree2 = new Tile(Assets.tree[0][1], 25, true);
    public static Tile tree3 = new Tile(Assets.tree[1][0], 26, true);
    public static Tile tree4 = new Tile(Assets.tree[1][1], 27, true);

    public static Tile BHouse1 = new Tile(Assets.bHouse[0][0], 28, true);
    public static Tile BHouse2 = new Tile(Assets.bHouse[0][1], 29, true);
    public static Tile BHouse3 = new Tile(Assets.bHouse[0][2], 30, true);
    public static Tile BHouse4 = new Tile(Assets.bHouse[1][0], 31, true);
    public static Tile BHouse5 = new Tile(Assets.bHouse[1][1], 32, true);
    public static Tile BHouse6 = new Tile(Assets.bHouse[1][2], 33, true);
    public static Tile BHouse7 = new Tile(Assets.bHouse[2][0], 34, true);
    public static Tile BHouse8 = new Tile(Assets.bHouse[2][1], 35, true);
    public static Tile BHouse9 = new Tile(Assets.bHouse[2][2], 36, true);

    public static Tile RHouse1 = new Tile(Assets.rHouse[0][0], 37, true);
    public static Tile RHouse2 = new Tile(Assets.rHouse[0][1], 38, true);
    public static Tile RHouse3 = new Tile(Assets.rHouse[0][2], 39, true);
    public static Tile RHouse4 = new Tile(Assets.rHouse[1][0], 40, true);
    public static Tile RHouse5 = new Tile(Assets.rHouse[1][1], 41, true);
    public static Tile RHouse6 = new Tile(Assets.rHouse[1][2], 42, true);
    public static Tile RHouse7 = new Tile(Assets.rHouse[2][0], 43, true);
    public static Tile RHouse8 = new Tile(Assets.rHouse[2][1], 44, true);
    public static Tile RHouse9 = new Tile(Assets.rHouse[2][2], 45, true);

    public static final int TILE_WIDTH  = 64;                       /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT = 64;                       /*!< Inaltimea unei dale.*/

    protected BufferedImage img;                                    /*!< Imaginea aferenta tipului de dala.*/
    protected final int id;                                         /*!< Id-ul unic aferent tipului de dala.*/

    /*! \fn public Tile(BufferedImage texture, int id)
        \brief Constructorul aferent clasei.

        \param image Imaginea corespunzatoare dalei.
        \param id Id-ul dalei.
     */
    public Tile(BufferedImage image, int idd, boolean colision)
    {
        img = image;
        id = idd;
        this.colision = colision;
        tiles[id-1] = this;
    }

    /*! \fn public void Update()
        \brief Actualizeaza proprietatile dalei.
     */
    public void Update()
    {

    }

    /*! \fn public void Draw(Graphics g, int x, int y)
        \brief Deseneaza in fereastra dala.

        \param g Contextul grafic in care sa se realizeze desenarea
        \param x Coordonata x in cadrul ferestrei unde sa fie desenata dala
        \param y Coordonata y in cadrul ferestrei unde sa fie desenata dala
     */
    public void Draw(Graphics g, int x, int y)
    {
        /// Desenare dala
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    /*! \fn public boolean IsSolid()
        \brief Returneaza proprietatea de dala solida (supusa coliziunilor) sau nu.
     */
    public boolean IsSolid()
    {
        return false;
    }

    /*! \fn public int GetId()
        \brief Returneaza id-ul dalei.
     */
    public int GetId()
    {
        return id;
    }

    public boolean getColision()
    {
        return colision;
    }

    public void setColision(boolean col)
    {
        colision = col;
    }
}