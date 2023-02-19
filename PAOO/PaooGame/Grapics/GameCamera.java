package PaooGame.Grapics;

import PaooGame.Entity.entity;
import PaooGame.Game;
import PaooGame.Map.Map;

public class GameCamera {

    private Game game;
    private float xoffset, yoffset;


    public GameCamera(Game game, float xoffset, float yoffset){
        this.game = game;
        this.xoffset = xoffset;
        this.yoffset = yoffset;
    }

    public void SetOnEntity(entity e, Map map)
    {
        xoffset = e.x - game.getWidth() / 2 + 32;
        yoffset = e.y - game.getHeight() / 2 + 32;
        if(xoffset < 0) xoffset = 0;
        if(yoffset < 0) yoffset = 0;
        if(map.getHeight()*64 - game.getWidth() < xoffset ) xoffset = map.getHeight() * 64 - game.getWidth();
        if(map.getWidth()*64 - game.getHeight() < yoffset ) yoffset = map.getWidth() * 64 - game.getHeight();

    }


    public float getXoffset() {
        return xoffset;
    }

    public float getYoffset() {
        return yoffset;
    }

}
