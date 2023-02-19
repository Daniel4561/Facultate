package PaooGame.Sound;

import PaooGame.Game;
import PaooGame.Utils.Sound;

public class SoundManager {

    Game g;

    public Sound music = new Sound("res/music/music.wav");

    public SoundManager(Game g)
    {
        this.g = g;
    }

    public void playMusic()
    {
        music.loop();
    }
    public void stopMusic() { music.stop();}

    public void playGunShot() {
        if(g.setings.soundon == "on")
            new Sound("res/music/gunShot.wav").play();
    }


    public void playDamageTacken()
    {
        if(g.setings.soundon == "on")
            new Sound("res/music/damageTacken.wav").play();
    }

    public void playPickUpDamage()
    {
        if(g.setings.soundon == "on")
            new Sound("res/music/pickUpDamage.wav").play();
    }

    public void playPickUpLifes(){
        if(g.setings.soundon == "on")
            new Sound("res/music/pickUpLifes.wav").play();
    }

    public void playZombieDeath()
    {
        if(g.setings.soundon == "on")
            new Sound("res/music/zombieDeath.wav").play();
    }
}
