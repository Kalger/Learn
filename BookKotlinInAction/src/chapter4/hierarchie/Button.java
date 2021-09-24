package chapter4.hierarchie;

public class Button implements View{

    @Override
    public State getCurrentState() {
        return new ButtonState();
    }

    @Override
    public void restoreState(State state) {}

    private static class ButtonState implements State { }
}