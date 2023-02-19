package PaooGame.State;

public class InsertNameState implements State{
    @Override
    public void handle(Context context) {
        context.setState(this);
    }
}
