/*
 * Represents a transition between two states based on an input
 */
public class DTransition {
    public final StateSet startState;
    public final String input;
    public final StateSet newState;

    /*
     * Constructs a new transition
     *  startState: The state to start from
     *  input: The input on which the transition should trigger
     *  newState: The state to transition to
     */
    public DTransition(StateSet startState, String input, StateSet newState) {
        this.startState = startState;
        this.input = input;
        this.newState = newState;
    }
}