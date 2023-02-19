package PaooGame.State;

public class WinState implements State{

    @Override
    public void handle(Context context) {
        context.setState(this);
    }

}
