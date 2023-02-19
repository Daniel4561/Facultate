package PaooGame.LoadLvl;

import PaooGame.Entity.K53;
import PaooGame.Entity.M1;
import PaooGame.Entity.R32;
import PaooGame.Entity.Zombie;
import PaooGame.Game;
import PaooGame.Grapics.Assets;
import PaooGame.Map.Map;

import java.io.FileNotFoundException;

public class LoadLvl {

    Game g;

    public LoadLvl(Game g)
    {
        this.g = g;
    }

    public  void loadLvl1(Game g,String M_1,String K_53,String R_32) throws FileNotFoundException {
        Assets.Init();
        g.lvl1 = null;
        g.lvl1 = new Map(g, "res/textures/lvl1.txt");
        loadZombie(17,M_1, K_53, R_32);

        g.obj.removeAll(g.obj);
        g.projecties.removeAll(g.projecties);

        g.lvl = 1;
    }

    public  void loadLvl2(Game g,String M_1,String K_53,String R_32) throws FileNotFoundException {
        Assets.Init();

        g.lvl1 = null;
        g.lvl1 = new Map(g, "res/textures/lvl2.txt");

        loadZombie(20,M_1, K_53, R_32);


        g.obj.removeAll(g.obj);
        g.projecties.removeAll(g.projecties);

        g.lvl = 2;
    }

    public void loadLvl3(Game g,String M_1,String K_53,String R_32) throws FileNotFoundException {
        Assets.Init();
        g.lvl1 = null;
        g.lvl1 = new Map(g, "res/textures/lvl3.txt");
        loadZombie(39, M_1, K_53, R_32);

        g.obj.removeAll(g.obj);
        g.projecties.removeAll(g.projecties);

        g.lvl = 3;
    }

    public void loadZombie(int nr, String M_1, String K_53, String R_32)
    {
        if(g.zombie != null)
            for(int i=0;i<g.zombie.length;++i)
                g.zombie[i] = null;

        String [] m1 = M_1.split(",");
        int n = Integer.parseInt(m1[m1.length - 1]);
        String[] k53 = K_53.split(",");
        int m = Integer.parseInt(k53[k53.length - 1]);
        String[] r32 = R_32.split(",");
        int k = Integer.parseInt(r32[r32.length - 1]);
        g.zombie = new Zombie[nr];

        for(int i=0;i<n;i++)
        {
            g.zombie[i] = new M1(g, Integer.parseInt(m1[2*i]), Integer.parseInt(m1[2*i+1]));
        }

        for(int i = 0;i<m;i++)
        {
            g.zombie[i +n] = new K53(g, Integer.parseInt(k53[2*i]), Integer.parseInt(k53[2*i+1]));
        }


        for(int i = 0;i<k;i++)
        {
            g.zombie[i+n+m] = new R32(g, Integer.parseInt(r32[2*i]), Integer.parseInt(r32[2*i+1]));
        }
        g.load.number = -1;
    }

}
