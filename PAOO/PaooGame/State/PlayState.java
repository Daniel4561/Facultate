package PaooGame.State;

public class PlayState implements State {

    public void handle(Context context)
    {
        context.setState(this);
    }

}
