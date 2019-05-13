import java.util.ArrayList;

public class Estado {
	public boolean esFinal;
	public boolean esInicial;
	public ArrayList<ArrayList<String>> row = new ArrayList<ArrayList<String>>();
	
	
	Estado(){
		esFinal = false;
		esInicial = false;
	}
	
	public String toString() {
		return "Inicial: " + this.esInicial + "\nFinal: " + this.esFinal;
	}
	
}
