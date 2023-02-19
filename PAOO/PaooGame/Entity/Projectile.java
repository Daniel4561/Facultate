package PaooGame.Entity;

import PaooGame.DataBase.AccesDataBase;
import PaooGame.Game;
import PaooGame.Grapics.Assets;
import PaooGame.objects.Bns_Chest;
import PaooGame.objects.Chest;

import java.awt.*;
import java.util.Random;

public class Projectile extends entity{

    Game g;



    public Projectile(Game g, int x, int y, String direction)
    {
        this.g = g;
        speed = 10;
        init(x, y, direction);
    }

    public void init(int x, int y, String direction)
    {
        this.x = x;
        this.y = y;
        this.direction = direction;

        solidAreea = new Rectangle(18, 20, 7, 7);
        solidX = solidAreea.x;
        solidY = solidAreea.y;

        switch (direction)
        {
            case"left":
                this.x -= 10;
                break;
            case"right":
                this.x += 30;
                break;
            case"up":
                this.y -= 5;
                this.x += 20;
                break;
            case"down":
                this.y += 8;
                break;
        }
    }

    public void update() throws InterruptedException {
        CollisionOn = false;
        int enemyIndex = g.cDetect.checkZombie(this, g.zombie);
        if(enemyIndex != -1) {
            g.zombie[enemyIndex].lives -= g.bob.damage;
            if (g.zombie[enemyIndex].lives <= 0) {
                Random random = new Random();
                int i = random.nextInt(100) + 1;
                if (i <= 30) {
                    g.obj.add(new Bns_Chest(g.zombie[enemyIndex].x, g.zombie[enemyIndex].y));
                }
                if (i > 30 && i <= 50) {
                    g.obj.add(new Chest(g.zombie[enemyIndex].x, g.zombie[enemyIndex].y));
                }

                g.zombie[enemyIndex] = null;
                if(Zombie.number > 1)
                    g.soundManager.playZombieDeath();
                --Zombie.number;
                System.out.println(Zombie.number);
                if(Zombie.number == 0) {
                    if (g.lvl > 2) {
                        AccesDataBase.insertScore(g.buffer, g.font.timeCounter);
                        g.context.setState(g.winState);
                    }
                    else
                        g.context.setState(g.lvlUpState);
                }
            }
        }

        g.cDetect.checkTile(this);

        if(!CollisionOn)
        {
            switch (direction)
            {
                case"up":
                    y -= speed;
                    break;
                case"down":
                    y += speed;
                    break;
                case"left":
                    x -= speed;
                    break;
                case"right":
                    x += speed;
                    break;
            }
        }
        else
            alive = false;

    }

    public void draw(Graphics2D g2)
    {
        if(alive)
            g2.drawImage(Assets.projectile, (int)(x - g.getGameCamera().getXoffset()), (int)(y - g.getGameCamera().getYoffset()), 64, 64, null);
    }
}
