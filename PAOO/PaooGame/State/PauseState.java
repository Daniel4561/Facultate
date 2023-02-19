package PaooGame.State;

public class PauseState implements State{
    @Override
    public void handle(Context context) {
        context.setState(this);
    }

}
