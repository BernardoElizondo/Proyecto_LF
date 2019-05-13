import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;



public class AFD_AFDmin {
	public static void main(String args[]) throws FileNotFoundException{
		File file = new File("files/AFD.txt");
		Scanner	in = new Scanner(file);
		
		String alfabeto, estadosFinStr, fila;
		int estadoIni;
		ArrayList<Integer> estadosFin = new ArrayList<Integer>();
		ArrayList<Character> alfabetoLista = new ArrayList<Character>();
		ArrayList<Estado> estados = new ArrayList<Estado>();
		ArrayList<ArrayList<String>> matriz = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<Estado>> tabla = new ArrayList<ArrayList<Estado>>();
		
		//Leemos el alfabeto y sacamos su magnitud
		alfabeto = in.nextLine();
		separarAlfabeto(alfabeto, alfabetoLista);
		
		//Establecemos el estado inicial
		estadoIni = Integer.parseInt(in.nextLine());
		
		//Establecemos el/los estado(s) final(es)
		estadosFinStr = in.nextLine();
		separarEstadosFinales(estadosFinStr, estadosFin);	
		
		System.out.println(alfabetoLista);
		System.out.println(estadoIni);
		System.out.println(estadosFin);
		
		int m = 0;
		//Creando la matriz de función de transiciones	
		while (in.hasNextLine()) {
			ArrayList<String> filaLista = new ArrayList <String>();
			Estado estado = new Estado();
			estado.numEstado = m;
			if(estadoIni == m) {
				estado.esInicial = true;
			}
			if(estadosFin.contains(m)) {
				estado.esFinal=true;
				System.out.println(m + " " + estado.toString());
			}
			estados.add(estado);			
			
			fila = in.nextLine();
			separarFilas(fila, filaLista);
			estado.estados.addAll(filaLista);
			matriz.add(filaLista);	
			m++;
		} 
		System.out.println(estados.toString());
		
		int x = 0;
		

		//Poblamos la tabla
		for(int i = estados.size(); i > 1; i--) {		
			for(int j = 0; j < i-1; j++) {
				ArrayList<Estado> row = new ArrayList<Estado>();
				row.add(estados.get(x));
				row.add(estados.get(j+(x+1)));
				tabla.add(row);
				System.out.println(row.get(0).numEstado + " " + row.get(1).numEstado);
				System.out.println(row);			
			}
			x++;			
		}
		
		//System.out.println(tabla);
		
		
		in.close();
	}
	
	public static void separarAlfabeto(String s, ArrayList<Character> al) {
		String alfabeto = "";
		
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == ';') {
				al.add(alfabeto.charAt(0));
				alfabeto = "";
				continue;
			} else {
				alfabeto = alfabeto + s.charAt(i);
			}
		}
	}
	
	public static void separarEstadosFinales(String s, ArrayList<Integer> al) {
		String estadosFin = "";
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == ';') {
				al.add(Integer.parseInt(estadosFin));
				estadosFin = "";
				continue;
			} else {
				estadosFin = estadosFin + s.charAt(i);
			}
		}
	}
	
	public static void separarFilas(String s, ArrayList<String> al) {
		String prueba = "";
		
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == ';') {
				al.add(prueba);
				prueba = "";
				continue;
			} else {
				prueba = prueba + s.charAt(i);
			}
		}
	}
}
