package PaooGame;

import PaooGame.Entity.*;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Grapics.Assets;
import PaooGame.Grapics.GameCamera;
import PaooGame.KeyEvent.MouseManager;
import PaooGame.LoadLvl.LoadLvl;
import PaooGame.Map.Map;
import PaooGame.Sound.SoundManager;
import PaooGame.State.*;
import PaooGame.Util.*;
import PaooGame.Util.Menu;
import PaooGame.objects.AssetsSeter;
import PaooGame.objects.SuperObject;


import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static PaooGame.Grapics.Assets.heart;

public class Game implements Runnable {



    private static int width;
    private static int height;

    public GameWindow wnd;
    private boolean runState;
    private Thread gameThread;
    private BufferStrategy bs;

    public Graphics2D g;

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    private GameCamera gameCamera;

    public Map lvl1 ;

    public BOB bob;

    public Zombie[] zombie;
    public ZombieSeter zSetter = new ZombieSeter(this);

    public ColisionDetection cDetect;
    public ArrayList<SuperObject> obj = new ArrayList<>();
    //public SuperObject[] obj = new SuperObject[10];

    public AssetsSeter aSetter = new AssetsSeter(this);

    public Heart hearts = new Heart(this);

    public Altele font = new Altele(this);

    public ArrayList<Projectile> projecties = new ArrayList<>();

    public Context context = new Context();
    public State loseState = new LoseState();
    public State lvlUpState = new LvlUpState();
    public State pauseState = new PauseState();
    public State playState = new PlayState();
    public State winState = new WinState();
    public State scoreState = new ScoreState();
    public State setingState = new SettingsState();
    public State menuState = new MenuState();
    public State nameState = new InsertNameState();
    public State loadState = new LoadState();

    public Menu menu = new Menu(this);
    public MouseManager mouse = new MouseManager(this);
    public Lose lose = new Lose(this);
    public lvlUp lvlup = new lvlUp(this);
    public Win win = new Win(this);
    public Pause pause = new Pause(this);
    public InsertName inName = new InsertName(this);
    public Setings setings = new Setings(this);
    public Scores scores = new Scores(this);
    public Load load = new Load(this);

    public int lvl = 0;

    public String buffer = "";

    public SoundManager soundManager = new SoundManager(this);

    private int GC = 300;

    public LoadLvl loadLvl = new LoadLvl(this);


    public Game(String title, int width, int height) throws IOException {
        this.width = width;
        this.height = height;
        wnd = new GameWindow(this, title, width, height, mouse);
        runState = false;
        wnd.BuildGameWindow();
    }


    private void InitGame() throws FileNotFoundException {
        cDetect = new ColisionDetection(this);
        gameCamera = new GameCamera(this,0, 0);
        context.setState(menuState);
        soundManager.playMusic();

    }

    public int showFps;
    @Override
    public void run(){
        try {
            InitGame();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long oldTime = System.nanoTime();
        long currentTime;
        double delta = 0;
        int frameCount = 0;
        long timer = 0;

        final int FPS = 60;
        final double timeFrame = 1000000000. / FPS;

        while(runState)
        {
            currentTime = System.nanoTime();
            delta += (currentTime - oldTime)/timeFrame;
            timer += (currentTime - oldTime);
            oldTime=currentTime;
            if(delta >= 1)
            {

                try {
                    Update();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Draw();
                delta--;
                frameCount++;

                GC--;
                if(GC == 0)
                {
                    System.gc();
                    GC = 300;
                }
            }

            //numaram fps-urile
            if(timer >= 1000000000)
            {
                showFps = frameCount;
                timer = 0;
                frameCount = 0;
            }
        }
    }

    public synchronized void StartGame()
    {
        if(!runState)
        {
            runState = true;

            gameThread = new Thread(this);

            gameThread.start();
        }
        else
            return;
    }

    public synchronized void StopGame()
    {
        if(runState){
            runState = false;

            try
            {
                gameThread.join();
            }
            catch(InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
        else
            return;
    }


    private void Update() throws FileNotFoundException, InterruptedException {

        if (menuState.equals(context.getState())) {
            menu.update();
        }

        if (playState.equals(context.getState())) {
            if(wnd.key.esc) context.setState(pauseState);
            bob.update();
            for(int i=0; i<zombie.length; ++i) {
                if(zombie[i] != null)
                    zombie[i].update();
            }
            for(int i=0; i<projecties.size();++i)
            {
                if(projecties.get(i) != null) {
                    if (!projecties.get(i).alive) {
                        projecties.set(i, null);
                    }
                    if (projecties.get(i) != null)
                        projecties.get(i).update();
                }
            }
        }
        if (lvlUpState.equals(context.getState())) {
            lvlup.update();
        }
        if(loseState.equals(context.getState()))
        {
            lose.update();
        }
        if(winState.equals(context.getState()))
        {
            win.update();
        }
        if(pauseState.equals(context.getState()))
        {
            pause.update();
        }
        if(nameState.equals(context.getState()))
        {
            inName.update();
        }
        if(setingState.equals(context.getState()))
        {
            setings.update();
        }
        if(scoreState.equals(context.getState()))
        {
            scores.update();
        }
        if(loadState.equals(context.getState()))
        {
            load.update();
        }

    }

    private void Draw()
    {
        /// Returnez bufferStrategy pentru canvasul existent
        bs = wnd.GetCanvas().getBufferStrategy();
        /// Verific daca buffer strategy a fost construit sau nu
        if(bs == null)
        {
            /// Se executa doar la primul apel al metodei Draw()
            try
            {
                /// Se construieste tripul buffer
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            }
            catch (Exception e)
            {
                /// Afisez informatii despre problema aparuta pentru depanare.
                e.printStackTrace();
            }
        }
        /// Se obtine contextul grafic curent in care se poate desena.
        g = (Graphics2D) bs.getDrawGraphics();
        /// Se sterge ce era
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());

        if(context.getState() == menuState)
            menu.draw(g);
        if(context.getState() == playState)
        {

            lvl1.Draw(g, 64,64);
            for(int i = 0;i< obj.size();++i)
            {
                if(obj.get(i) != null)
                    obj.get(i).draw(g, this);
            }

            bob.draw(g);

            for(int i=0;i<zombie.length;++i)
            {
                if(zombie[i] != null)
                    zombie[i].draw(g);
            }

            for(int i=0; i<projecties.size();++i)
            {
                if(projecties.get(i) != null)
                    projecties.get(i).draw(g);
            }
            hearts.draw(g);

            font.draw(g);
            gameCamera.SetOnEntity(bob, lvl1);
        }

        if(loseState.equals(context.getState()))
        {
            lose.draw(g);
        }

        if (lvlUpState.equals(context.getState())) {
            lvlup.draw(g);
        }

        if(winState.equals(context.getState()))
        {
            win.draw(g);
        }

        if(pauseState.equals(context.getState()))
        {
            pause.draw(g);
        }

        if(nameState.equals(context.getState()))
        {
            inName.draw(g);
        }
        if(setingState.equals(context.getState()))
        {
            setings.draw(g);
        }
        if(scoreState.equals(context.getState()))
        {
            scores.draw(g);
        }
        if(loadState.equals(context.getState()))
        {
            load.draw(g);
        }

        // end operatie de desenare
        /// Se afiseaza pe ecran
        bs.show();

        /// Elibereaza resursele de memorie aferente contextului grafic curent (zonele de memorie ocupate de
        /// elementele grafice ce au fost desenate pe canvas).
        g.dispose();
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public void setLvl1() throws FileNotFoundException {


        bob.Reset();
        bob = BOB.getInstance(this, wnd);
        Assets.Init();

        lvl1 = null;
        lvl1 = new Map(this, "res/textures/lvl1.txt");

        obj.removeAll(obj);
        projecties.removeAll(projecties);
        aSetter.SetObjLvl1();
        zSetter.SetZombieLvl1();

        lvl = 1;
    }

    public void  setLvl2() throws FileNotFoundException {
        bob.Reset();
        bob = BOB.getInstance(this, wnd);
        Assets.Init();


        lvl1 = null;
        lvl1 = new Map(this, "res/textures/lvl2.txt");

        obj.removeAll(obj);
        projecties.removeAll(projecties);
        aSetter.SetObjLvl2();
        zSetter.SetZombieLvl2();

        lvl = 2;
    }

    public void setLvl3() throws FileNotFoundException {
        bob.Reset();
        bob = BOB.getInstance(this, wnd);
        Assets.Init();

        lvl1 = null;
        lvl1 = new Map(this, "res/textures/lvl3.txt");

        obj.removeAll(obj);
        projecties.removeAll(projecties);
        aSetter.SetObjLvl3();
        zSetter.SetZombieLvl3();

        lvl = 3;
    }
}
