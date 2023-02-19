package PaooGame.State;

public class SettingsState implements State{
    @Override
    public void handle(Context context)
    {
        context.setState(this);
    }
}
