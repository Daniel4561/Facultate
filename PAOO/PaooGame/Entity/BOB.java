package PaooGame.Entity;

import PaooGame.Game;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Animations.BobAnimations;
import PaooGame.KeyEvent.KeyManager;

import java.awt.*;
import java.util.Objects;


public class BOB extends entity{
    Game game;
    KeyManager key;
    public final int screenX, screenY;
    boolean shot = false;
    double count=0;
    String shotDirection = "null";
    public int damage;
    public int damageCounter = 0;

    private int shotnow = 14;

    private boolean mh = false;

    private static BOB instance = null;

    public static BOB getInstance(Game g, GameWindow wnd)
    {
        if(instance == null)
        {
            instance = new BOB(g, wnd);
        }
        return instance;
    }

    public static void Reset()
    {
        instance = null;
    }

    public BOB(Game g, GameWindow wnd)
    {
        this.game = g;
        this.key = wnd.key;
        screenX = 1280/2-25;
        screenY = 768/2-50;
        solidAreea = new Rectangle(22, 20, 20, 40);
        solidX = solidAreea.x;
        solidY = solidAreea.y;
        init();
    }


    public void init(){
        x = 128;
        y = 256;
        speed = 5;
        lives = 5;
        damage = 2;
    }

    public void update(){

        if(!shot) {
            if (key.up || key.down || key.left || key.right) {
                if (key.down) {
                    direction = "down";
                    shotDirection = "down";
                }

                if (key.up) {
                    direction = "up";
                    shotDirection = "up";
                }

                if (key.right) {
                    direction = "right";
                    shotDirection = "right";
                }

                if (key.left) {
                    direction = "left";
                    shotDirection = "left";
                }
                if(count > 7)
                    count = 0;
                else
                    count += 0.14;
            } else
                direction = "null";

            CollisionOn = false;
            game.cDetect.checkTile(this);
            int colidIndex =  game.cDetect.checkZombie(this, game.zombie);
            for(int i=0; i< game.zombie.length;++i) {
                if(game.zombie[i] != null)
                    if(i == colidIndex)
                        colid(colidIndex, game.zombie[i].colid );
            }
            int index = game.cDetect.checkObject(this, true);
            pickUpObject(index);


            if (!CollisionOn) {
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
        }

        shotnow++;
        if(shotnow == 15 ) {
            mh = true;
            shotnow = 0;
        }
        if(!Objects.equals(direction, "null")) {
            if (key.space) {
                shot = true;
                count = 0;
                if (mh) {
                    game.soundManager.playGunShot();
                    game.projecties.add(new Projectile(game, x, y, direction));
                    mh = false;
                }
            } else
                shot = false;

        }
        if(lives == 0)
        {
            game.context.setState(game.loseState);
        }
    }

    public void pickUpObject(int i)
    {
        if(i != -1)
        {
            if(game.obj.get(i).name == "BnsChest")
            {
                if(lives < 11) {
                    game.soundManager.playPickUpLifes();
                    lives++;
                    game.font.time = 0;
                    game.font.mesage = "Life increase";
                    game.font.mesageON = true;
                    game.obj.set(i, null);
                }
            }
            else if(game.obj.get(i).name == "Chest")
            {
                if(damage < 15) {
                    game.soundManager.playPickUpDamage();
                    game.font.time = 0;
                    damage++;
                    game.font.mesage = "Damage increase";
                    game.font.mesageON = true;
                    game.obj.set(i, null);
                }
            }
        }
    }

    public void colid(int i, boolean colid)
    {
        if(i !=- 1 || colid )
        {
            damageCounter++;
            if(damageCounter == 60)
            {
                if(lives > 0) {
                    lives--;
                    if(lives > 0)
                        game.soundManager.playDamageTacken();
                }
                damageCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g){
        BobAnimations.Animation(g,direction, shotDirection, shot, (int)(x - game.getGameCamera().getXoffset()), (int)(y - game.getGameCamera().getYoffset()), count);
    }


}
