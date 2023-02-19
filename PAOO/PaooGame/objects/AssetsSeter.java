package PaooGame.objects;

import PaooGame.Game;

public class AssetsSeter {
    Game g;

    public AssetsSeter(Game g)
    {
        this.g = g;
    }

    public void SetObjLvl1()
    {
        g.obj.add(0,new Bns_Chest(500, 450));
        g.obj.add(1,new Chest(500, 250));

    }

    public void SetObjLvl2()
    {
        g.obj.add(0,new Bns_Chest(500, 400));
        g.obj.add(1,new Bns_Chest(300, 400));
        g.obj.add(2,new Chest(300, 200));
        g.obj.add(3,new Chest(500, 200));
    }

    public void SetObjLvl3()
    {
        g.obj.add(0,new Bns_Chest(500, 400));
        g.obj.add(1,new Bns_Chest(300, 400));
        g.obj.add(1,new Bns_Chest(4700, 400));
        g.obj.add(1,new Bns_Chest(3400, 1800));
        g.obj.add(2,new Chest(300, 200));
        g.obj.add(3,new Chest(500, 200));
        g.obj.add(3,new Chest(400, 900));
        g.obj.add(3,new Chest(1800, 2500));
    }

}
