import java.util.ArrayList;

public class Estado {
	public int numEstado;
	public boolean esFinal;
	public boolean esInicial;
	public ArrayList<String> estados = new ArrayList<String>();
	
	
	Estado(){
		esFinal = false;
		esInicial = false;
	}
	
	public String toString() {
		//return "[Inicial: " + this.esInicial + " Final: " + this.esFinal + "Estados" + estados.toString() + "]";
		return Integer.toString(numEstado);
	}
	
}
