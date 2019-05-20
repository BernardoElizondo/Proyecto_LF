import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Automaton {
	protected final List<String> states;
	protected final List<String> alphabet;
	protected final List<String> accepting;
	protected final Map<String, List<Transition>> transitions;
	protected final String start;
	
	public Automaton(List<String> states, 
			   List<String> alphabet, 
			   List<String> accepting,
			   Map<String, List<Transition>> transitions,
			   String start) {
        this.states = states;
        this.alphabet = alphabet;
        this.accepting = accepting;
        this.start = start;		
        this.transitions = transitions;
	}
	
	public void printStart() {
		System.out.println("Start: " + start);
	}

	public void printStates() {
		System.out.print("States: ");
		Iterator<String> itr = states.iterator();
		while(itr.hasNext()) {	
			System.out.print(itr.next() + " ");
		}
		System.out.println();
	}

	public void printAlphabet() {
		System.out.print("Alphabet: ");
		Iterator<String> itr = alphabet.iterator();
		while(itr.hasNext()) {	
			System.out.print(itr.next() + " ");
		}
		System.out.println();
	}

	public void printAccepting() {
		System.out.print("Accepting: ");
		Iterator<String> itr = accepting.iterator();
		while(itr.hasNext()) {	
			System.out.print(itr.next() + " ");
		}
		System.out.println();
	}

	public void printTransitions() {
		System.out.println("Transitions: ");
		for(Transition t: getTransitions()) {
			System.out.print("State: " + t.startState);
			System.out.print(" with input: " + t.input);
			System.out.println(" goes to state: " + t.newState);			
		}
	}
	
	public List<Transition> getTransitions(){
		List<Transition> result = new ArrayList<Transition>();
		for(Map.Entry<String, List<Transition>> entry: transitions.entrySet()) {
			result.addAll(entry.getValue());
		}
		return result;
	}
}
