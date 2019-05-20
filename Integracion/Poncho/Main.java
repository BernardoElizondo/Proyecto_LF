import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
    	Parser p = new Parser("tests/input.txt");
		NFA nfa = p.tryParseNFA();
		nfa.printAlphabet();
		nfa.printStart();
		nfa.printStates();
		nfa.printAccepting();
		nfa.printTransitions();

		
		DFA dfa = nfa.toDFA();
		dfa.printAlphabet();
		dfa.printStart();
		dfa.printStates();
		dfa.printAccepting();
		dfa.printTransitions();

		Formatter f = new Formatter("tests/output.txt");
		f.writeDFA(dfa);		
    }
}
