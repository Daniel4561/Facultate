package PaooGame.Map;

import PaooGame.Animations.ZombieAnimation;
import PaooGame.Tile.Tile;
import PaooGame.Game;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {

    Game game ;
    String path;
    public int[][] map;
    private int width, height;


    public Map(Game game, String path) throws FileNotFoundException {
        this.game = game;
        this.path = path;
        init(path);
    }

    public void init(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner sc = new Scanner(file);
        width = sc.nextInt();
        height = sc.nextInt();

        map = new int[width][height];

        for(int i=0; i<width ; ++i)
        {
            for(int j=0; j<height; ++j)
            {
                map[i][j] = sc.nextInt();
            }
        }
    }

    public void Draw(Graphics2D g, int TILE_WIDTH, int TILE_HEIGHT) {
        int i, j;
        for (i = 0; i < map.length; ++i)
            for (j = 0; j < map[0].length; ++j){
                int x = j * 64;
                int y = i * 64;
                if(x + 128 > game.bob.x - game.bob.screenX &&
                        x - 128  < game.bob.x + game.bob.screenX &&
                        y + 128  > game.bob.y - game.bob.screenY &&
                        y - 128  < game.bob.y + game.bob.screenY)
                {
                    Tile.tiles[map[i][j]].Draw(g, (int)(j * TILE_WIDTH - game.getGameCamera().getXoffset()), (int)(i * TILE_HEIGHT - game.getGameCamera().getYoffset()));
                }
                else
                if(((game.getGameCamera().getXoffset() == 0 || game.getGameCamera().getYoffset() == 0)&&
                        (x - 64 < game.getWidth() || y - 64 < game.getHeight()))||
                        ((game.getGameCamera().getXoffset() + game.getWidth() == height * 64 ||
                            game.getGameCamera().getYoffset() + game.getHeight() == width * 64) &&
                                (x + 64 > game.getGameCamera().getXoffset() || y + 64 > game.getGameCamera().getYoffset())))
                {
                    Tile.tiles[map[i][j]].Draw(g, (int)(j * TILE_WIDTH - game.getGameCamera().getXoffset()), (int)(i * TILE_HEIGHT - game.getGameCamera().getYoffset()));
                }
            }
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

}
