import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class StateSet {
	private List<String> states;
	
	public StateSet(List<String> states) {
		this.states = states;
	}
	
	public List<String> getStates(){
		return states;
	}
	
	@Override
	public int hashCode() {
		StringJoiner joiner = new StringJoiner("");
		
		String[] s = new String[states.size()];
		s = states.toArray(s);
		Arrays.sort(s);
		
		for(String state: states) {
			joiner.add(state);
		}
		
		String result = joiner.toString();
		return result.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {				
		return hashCode() == obj.hashCode();
	}
}
