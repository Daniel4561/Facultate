package PaooGame.Entity;

import PaooGame.Game;
import PaooGame.Tile.Tile;

public class ColisionDetection {

    private Game g;
    public ColisionDetection(Game g)
    {
        this.g = g;
    }

    public void checkTile(entity e)
    {
        int entityLeftWorldX = e.x + e.solidAreea.x;
        int entityRightWorldX = e.x + e.solidAreea.x + e.solidAreea.width;
        int entityTopWorldY = e.y + e.solidAreea.y;
        int entityBottomWorldY = e.y + e.solidAreea.y + e.solidAreea.height;


        int entityLeftCol = entityLeftWorldX/64;
        int entityRightCol = entityRightWorldX/64;
        int entityTopRow = entityTopWorldY/64;
        int entityBottomRow = entityBottomWorldY/64;

        int tileNum1, tileNum2;

        switch (e.direction)
        {
            case"up":
                entityTopRow = (entityTopWorldY-e.speed)/64;

                tileNum1 = g.lvl1.map[entityTopRow][entityLeftCol];
                tileNum2 = g.lvl1.map[entityTopRow][entityRightCol];

                if(Tile.tiles[tileNum1].getColision() == true || Tile.tiles[tileNum2].getColision() == true)
                {
                    e.CollisionOn = true;
                }
                break;
            case"down":
                entityBottomRow = (entityBottomWorldY + e.speed)/64;
                tileNum1 = g.lvl1.map[entityBottomRow][entityLeftCol];
                tileNum2 = g.lvl1.map[entityBottomRow][entityRightCol];

                if(Tile.tiles[tileNum1].getColision() == true || Tile.tiles[tileNum2].getColision() == true)
                {
                    e.CollisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX-e.speed)/64;
                tileNum1 = g.lvl1.map[entityTopRow][entityLeftCol];
                tileNum2 = g.lvl1.map[entityBottomRow][entityLeftCol];

                if(Tile.tiles[tileNum1].getColision() == true || Tile.tiles[tileNum2].getColision() == true)
                {
                    e.CollisionOn = true;
                }
                break;
            case"right":
                entityRightCol = (entityRightWorldX + e.speed)/64;
                tileNum1 = g.lvl1.map[entityTopRow][entityRightCol];
                tileNum2 = g.lvl1.map[entityBottomRow][entityRightCol];

                if(Tile.tiles[tileNum1].getColision() == true || Tile.tiles[tileNum2].getColision() == true)
                {
                    e.CollisionOn = true;
                }
                break;
        }

    }

    public int checkObject(entity player, boolean isPlayer)
    {
        int index = -1;
        for(int i = 0; i<g.obj.size();++i)
        {
            if(g.obj.get(i) != null)
            {
                player.solidAreea.x = player.solidAreea.x + player.x;
                player.solidAreea.y = player.solidAreea.y + player.y;

                g.obj.get(i).solidAreea.x = g.obj.get(i).solidAreea.x + g.obj.get(i).worldx + g.obj.get(i).solidAreea.width/2;
                g.obj.get(i).solidAreea.y = g.obj.get(i).solidAreea.y + g.obj.get(i).worldy + g.obj.get(i).solidAreea.height/2;

                switch (player.direction)
                {
                    case"up":
                        player.solidAreea.y -= player.speed;
                        if(player.solidAreea.intersects(g.obj.get(i).solidAreea)) {
                            player.CollisionOn = true;
                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;
                    case"down":
                        player.solidAreea.y += player.speed;
                        if(player.solidAreea.intersects(g.obj.get(i).solidAreea)) {
                            player.CollisionOn = true;
                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;
                    case"left":
                        player.solidAreea.x -= player.speed;
                        if(player.solidAreea.intersects(g.obj.get(i).solidAreea)) {
                            player.CollisionOn = true;
                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;
                    case"right":
                        player.solidAreea.x += player.speed;
                        if(player.solidAreea.intersects(g.obj.get(i).solidAreea)) {
                            player.CollisionOn = true;
                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;
                }
                player.solidAreea.x = player.solidX;
                player.solidAreea.y = player.solidY;

                g.obj.get(i).solidAreea.x = g.obj.get(i).solidX;
                g.obj.get(i).solidAreea.y = g.obj.get(i).solidY;
            }
        }
        return index;
    }

    public int checkZombie(entity player, entity[] zombies)
    {
        int index = -1;
        for(int i=0; i<zombies.length;++i)
        {
            if(zombies[i] != null)
            {
                player.solidAreea.x = player.solidAreea.x + player.x;
                player.solidAreea.y = player.solidAreea.y + player.y;

                zombies[i].solidAreea.x = zombies[i].solidAreea.x + zombies[i].x;
                zombies[i].solidAreea.y = zombies[i].solidAreea.y + zombies[i].y;

                switch (player.direction)
                {
                    case"up":
                        player.solidAreea.y -= player.speed;
                        if(player.solidAreea.intersects(zombies[i].solidAreea))
                        {
                            player.CollisionOn = true;
                            index = i;
                        }
                        break;
                    case"down":
                        player.solidAreea.y += player.speed;
                        if(player.solidAreea.intersects(zombies[i].solidAreea))
                        {
                            player.CollisionOn = true;
                            index = i;
                        }
                        break;
                    case"left":
                        player.solidAreea.x -= player.speed;
                        if(player.solidAreea.intersects(zombies[i].solidAreea))
                        {
                            player.CollisionOn = true;
                            index = i;
                        }
                        break;
                    case"right":
                        player.solidAreea.x += player.speed;
                        if(player.solidAreea.intersects(zombies[i].solidAreea))
                        {
                            player.CollisionOn = true;
                            index = i;
                        }
                        break;
                }
                player.solidAreea.x = player.solidX;
                player.solidAreea.y = player.solidY;

                zombies[i].solidAreea.x = zombies[i].solidX;
                zombies[i].solidAreea.y = zombies[i].solidY;

            }
        }
        return index;
    }


    public boolean checkPLayer(entity enemy)
    {
        boolean colid = false;
        enemy.solidAreea.x = enemy.solidAreea.x + enemy.x;
        enemy.solidAreea.y = enemy.solidAreea.y + enemy.y;

        g.bob.solidAreea.x = g.bob.solidAreea.x + g.bob.x;
        g.bob.solidAreea.y = g.bob.solidAreea.y + g.bob.y;

        switch (enemy.direction)
        {
            case "up":
                enemy.solidAreea.y -= enemy.speed;
                if(enemy.solidAreea.intersects(g.bob.solidAreea))
                {
                    enemy.CollisionOn = true;
                    colid = true;
                }
                break;
            case"down":
                enemy.solidAreea.y += enemy.speed;
                if(enemy.solidAreea.intersects(g.bob.solidAreea))
                {
                    enemy.CollisionOn = true;
                    colid = true;
                }
                break;
            case"left":
                enemy.solidAreea.x -= enemy.speed;
                if(enemy.solidAreea.intersects(g.bob.solidAreea))
                {
                    enemy.CollisionOn = true;
                    colid = true;
                }
                break;
            case"right":
                enemy.solidAreea.x += enemy.speed;
                if(enemy.solidAreea.intersects(g.bob.solidAreea))
                {
                    enemy.CollisionOn = true;
                    colid = true;
                }
                break;
        }

        enemy.solidAreea.x = enemy.solidX;
        enemy.solidAreea.y = enemy.solidY;

        g.bob.solidAreea.x = g.bob.solidX;
        g.bob.solidAreea.y = g.bob.solidY;

        return colid;
    }
}
