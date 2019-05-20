import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class Formatter {
	private String filename;
	
	public Formatter(String filename) {
		this.filename = filename;
	}

	public void writeDFA(DFA dfa) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
			// Write alphabet
			StringJoiner semicolonJoiner = new StringJoiner(";");
			for(String symbol: dfa.alphabet) {
				semicolonJoiner.add(symbol);
			}
			writer.write(semicolonJoiner.toString() + ";\n");
			
			// Write start state
			writer.write(dfa.start + "\n");
			
			// Write accepting
			semicolonJoiner = new StringJoiner(";");
			for(String symbol: dfa.accepting) {
				semicolonJoiner.add(symbol);
			}
			writer.write(semicolonJoiner.toString() + ";\n");

			// Write transitions
			for(int i = 0; i < dfa.transitions.size(); i++) {
				semicolonJoiner = new StringJoiner(";");
				for(Transition trans: dfa.transitions.get(Integer.toString(i))) {
					semicolonJoiner.add(trans.newState);
				}
				writer.write(semicolonJoiner.toString() + ";\n");				
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
