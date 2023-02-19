package PaooGame.Entity;

import PaooGame.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ZombieSeter extends entity {
    Game g;

    public ZombieSeter(Game g){this.g = g;
    };

    public void SetZombieLvl1() throws FileNotFoundException {
        File file = new File("res/textures/zombie_lvl1.txt");
        Scanner sc = new Scanner(file);

        g.zombie = new Zombie[sc.nextInt()];
        //g.zombie[0] = new M1(g,1000, 200);
        for(int i=0; i<5; ++i)
            g.zombie[i] = new M1(g,sc.nextInt(), sc.nextInt());
        for(int i=5; i<11; ++i)
            g.zombie[i] = new K53(g, sc.nextInt(), sc.nextInt());
        for(int i=11; i<17; ++i)
            g.zombie[i] = new R32(g, sc.nextInt(), sc.nextInt());
    }

    public void SetZombieLvl2() throws FileNotFoundException {
        File file = new File("res/textures/zombie_lvl2.txt");
        Scanner sc = new Scanner(file);

        g.zombie = new Zombie[sc.nextInt()];
        //g.zombie[0] = new M1(g,1000, 200);
        for(int i=0; i<3; ++i)
            g.zombie[i] = new M1(g,sc.nextInt(), sc.nextInt());
        for(int i=7; i<14; ++i)
            g.zombie[i] = new K53(g, sc.nextInt(), sc.nextInt());
        for(int i=14; i<20; ++i)
            g.zombie[i] = new R32(g, sc.nextInt(), sc.nextInt());
    }

    public void SetZombieLvl3() throws FileNotFoundException {
        File file = new File("res/textures/zombie_lvl3.txt");
        Scanner sc = new Scanner(file);

        g.zombie = new Zombie[sc.nextInt()];
        g.zombie[0] = new M1(g,1000, 200);
        for(int i=0; i<10; ++i)
            g.zombie[i] = new M1(g,sc.nextInt(), sc.nextInt());
        for(int i=10; i<24; ++i)
            g.zombie[i] = new K53(g, sc.nextInt(), sc.nextInt());
        for(int i=24; i<39; ++i)
            g.zombie[i] = new R32(g, sc.nextInt(), sc.nextInt());
    }
}
