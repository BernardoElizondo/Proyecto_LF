import java.util.List;
import java.util.Map;
import java.util.Set;

public class DFA extends Automaton{
	public DFA(List<String> states, 
			   List<String> alphabet, 
			   List<String> accepting,
			   Map<String, List<Transition>> transitions,
			   String start) {
		super(states, alphabet, accepting, transitions, start);
	}
}
