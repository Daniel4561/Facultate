package PaooGame.State;

public class MenuState implements State{
    @Override
    public void handle(Context context) {
        context.setState(this);
    }
}
