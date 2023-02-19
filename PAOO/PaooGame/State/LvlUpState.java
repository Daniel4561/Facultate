package PaooGame.State;

public class LvlUpState implements State{
    @Override
    public void handle(Context context) {
        context.setState(this);
    }

}
