package PaooGame.State;

public class ScoreState implements State{
    public void handle(Context context)
    {
        context.setState(this);
    }
}
