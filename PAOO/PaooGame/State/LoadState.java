package PaooGame.State;

public class LoadState implements State{
    @Override
    public void handle(Context context) {
        context.setState(this);
    }
}
