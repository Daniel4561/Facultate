package PaooGame.State;

public class LoseState implements State{

    @Override
    public void handle(Context context) {
        context.setState(this);
    }

}
