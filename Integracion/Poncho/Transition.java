/*
 * Represents a transition between two states based on an input.
 */
public class Transition {
    public final String startState;
    public final String input;
    public final String newState;

    /*
     * Constructs a new transition.
     *  startState: The state to start from.
     *  input: The input on which the transition should trigger.
     *  newState: The state to transition to.
     */
    public Transition(String startState, String input, String newState) {
        this.startState = startState;
        this.input = input;
        this.newState = newState;
    }
}