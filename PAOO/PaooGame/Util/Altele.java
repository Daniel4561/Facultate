package PaooGame.Util;

import PaooGame.Game;

import java.awt.*;
import java.text.DecimalFormat;

public class Altele {

    Font fontFPS ;
    Font damagefont;
    Game g;
    public Boolean mesageON = false;

    public int time = 0;
    public double timeCounter = 0;

    public String mesage;
    DecimalFormat df = new DecimalFormat("#0.00");



    public Altele(Game g)
    {
        this.g = g;
        fontFPS = new Font("Arial", Font.BOLD, 20);
        damagefont = new Font("Arial", Font.ITALIC, 30);
    }

    public void draw(Graphics2D g2)
    {

        //damage taken
        g2.setFont(damagefont);
        g2.setColor(Color.BLUE);
        g2.drawString("Damage taken : " + g.bob.damage, 500, 40);

        //fps
        g2.setFont(fontFPS);
        g2.setColor(Color.BLUE);
        g2.drawString(String.valueOf(g.showFps), 20, 35);

        if(mesageON)
        {
            if(time != 60)
            {
                g2.setFont(new Font("Arial", Font.ITALIC,35));
                g2.setColor(Color.BLACK);
                g2.drawString(mesage+": + 1x", 502, 352 );
                g2.setColor(Color.white);
                g2.drawString(mesage+": + 1x", 500, 350 );
                time++;
            }
            else
                mesageON = false;
        }
        timeCounter += 1./60;
        g2.setFont(new Font("Arial", Font.BOLD, 30));
        g2.setColor(Color.blue);
        g2.drawString("Time: " + df.format(timeCounter), 1080, 35);

    }
}
