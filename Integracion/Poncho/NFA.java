import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NFA extends Automaton{
	public NFA(List<String> states, 
			   List<String> alphabet, 
			   List<String> accepting,
			   Map<String, List<Transition>> transitions,
			   String start) {
		super(states, alphabet, accepting, transitions, start);
	}
	
	public DFA toDFA() {
		List<StateSet> marked = new ArrayList<StateSet>();
		
		// Create the starting T, which only contains the start state
		List<String> starting = new ArrayList<String>();
		starting.add(start);
		StateSet startingStateSet = new StateSet(starting);
		
		// Create the Dstates list of states
		List<StateSet> Dstates = new ArrayList<StateSet>();
		Dstates.add(startingStateSet);
		
		// Dtran
		List<DTransition> Dtran = new ArrayList<DTransition>();
		
		StateSet T = null;
		while(marked.size() != Dstates.size()) {
			// There is an unmarked state in Dstates
			for(StateSet t: Dstates) {
				if(!marked.contains(t)) {
					T = t;
					marked.add(T);
					break;
				}	
			}

			if(T == null) {
				break;
			}
			
			for(String symbol: this.alphabet) {
				StateSet U = new StateSet(move(T, symbol));

				if(U.getStates().size() > 0 && !Dstates.contains(U))
					Dstates.add(U);
			
				if(U.getStates().size() > 0)
					Dtran.add(new DTransition(T, symbol, U));
			}
		}
		
		// Map states to integers
		Map<StateSet, Integer> dict = new HashMap<StateSet, Integer>();
		int i = 0;
		for(StateSet state: Dstates) {
			dict.put(state, i);
			i++;
		}

		// Rename states
		List<String> newStates = new ArrayList<String>();
		Map<String, List<Transition>> newTrans = new HashMap<String, List<Transition>>();
		
		for(StateSet set: Dstates) {
			String newName = Integer.toString(dict.get(set));
			newStates.add(newName);
			newTrans.put(newName, new ArrayList<Transition>());
		}

		// Build new transitions
		for(DTransition dt: Dtran) {
			String startState = Integer.toString(dict.get(dt.startState));
			String newState = Integer.toString(dict.get(dt.newState));
			Transition t = new Transition(startState, dt.input, newState);
			
			newTrans.get(startState).add(t);
		}
		
		// Add null transitions
		for(i = 0; i < newTrans.size(); i++) {
			String curState = Integer.toString(i);
			List<Transition> transitions = newTrans.get(curState);
			for(int j = 0; j < alphabet.size(); j++) {
				Transition temp = null;
				for(Transition t: transitions) {
					if(t.input == alphabet.get(j))
						temp = t;
				}
				if(temp == null) {
					transitions.add(j, new Transition(curState, alphabet.get(j), "n"));
				}
			}
		}
		
		// Build accepting states
		List<String> newAccepting = new ArrayList<String>();
		for(StateSet set: Dstates) {
			for(String state: set.getStates()) {
				if(accepting.contains(state)) {
					newAccepting.add(Integer.toString(dict.get(set)));
				}
			}
		}
		
		return new DFA(newStates, alphabet, newAccepting, newTrans, start);
	}
	
	private List<String> move(StateSet t, String symbol){
		List<String> result = new ArrayList<String>();
		
		for(String state: t.getStates()) {
			List<String> moves = moveOne(state, symbol);
			for(String move: moves)
				if(!result.contains(move))
					result.add(move);
		}
		return result;
	}
	
	private List<String> moveOne(String state, String symbol){
		List<String> result = new ArrayList<String>();
		
		for(Transition t: transitions.get(state)) {
			if(t.input == symbol) {
				if(!result.contains(t.newState))
					result.add(t.newState);
			}
		}

		return result;
	}
}
