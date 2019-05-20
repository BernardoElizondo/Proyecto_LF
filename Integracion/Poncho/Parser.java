import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.*;

public class Parser {
	private String filename;
	
	public Parser(String filename) {
		this.filename = filename;
	}
	
	public NFA tryParseNFA() {
		List<String> states = new ArrayList<String>();
		String[] alphabet;
	    List<String> accepting = new ArrayList<String>();
	    Map<String, List<Transition>> transitions = new HashMap<String, List<Transition>>();
	    String start;

	    List<String> lines = new ArrayList<String>();
		try(BufferedReader br = new BufferedReader(new FileReader(filename))){
			for(String line; (line = br.readLine()) != null;) {
				lines.add(line);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		// First line contains the alphabet
		alphabet = lines.get(0).split(";");
		
		// Second line contains the start state
		start = lines.get(1);
		
		//Third line contains accepting states
		for(String item: lines.get(2).split(";")) {
			accepting.add(item);
		}
		
		// Leave just the transitions
		lines.remove(2);
		lines.remove(1);
		lines.remove(0);
		
		// Process transitions
		for(int i = 0; i < lines.size(); i++) {
			// Each line represents the transitions starting in state "i"
			states.add(Integer.toString(i));
			
			List<Transition> trans = new ArrayList<Transition>();
			String[] row = lines.get(i).split(";");
			for(int k = 0; k < row.length; k++) {
				if (row[k].startsWith("{")) {
					String[] destinationStates = row[k].substring(1, row[k].length() - 1).split(",");
					for(int j = 0; j < destinationStates.length; j++) {
						trans.add(new Transition(Integer.toString(i), alphabet[k], destinationStates[j]));
					}
				}
				else {

					if(!row[k].equals("n")) {
						trans.add(new Transition(Integer.toString(i), alphabet[k], row[k]));
					}
				}
				
				transitions.put(Integer.toString(i), trans);
			}
		}
		
		return new NFA(states, Arrays.asList(alphabet), accepting, transitions, start);
	}	
}
