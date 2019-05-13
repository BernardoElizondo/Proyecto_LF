import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
		ArrayList<ArrayList<ArrayList<String>>> tabla = new ArrayList<ArrayList<ArrayList<String>>>();
		
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
		
		//Creando la matriz de función de transiciones	
		while (in.hasNextLine()) {
			ArrayList<String> filaLista = new ArrayList <String>();
			fila = in.nextLine();
			separarFilas(fila, filaLista);
			matriz.add(filaLista);			
		} 
		System.out.println(matriz);
		System.out.println(matriz.size());
		
		int x = 0;
		
		//Establecemos estados iniciales y finales
		for(int i = 0; i < matriz.size(); i++) {
			Estado estado = new Estado();
			if(estadoIni == i) {
				estado.esInicial = true;
			}
			if(estadosFin.contains(i)) {
				estado.esFinal=true;
				System.out.println(i + " " + estado.toString());
			}
			estados.add(estado);
		}
		System.out.println(estados);
		
		
		//Poblamos la tabla
		for(int i = matriz.size(); i > 1; i--) {		
			for(int j = 0; j < i-1; j++) {
				ArrayList<ArrayList<String>> row = new ArrayList<ArrayList<String>>();
				row.add(matriz.get(x));
				row.add(matriz.get(j+(x+1)));
				tabla.add(row);
				System.out.println(row);			
			}
			x++;			
		}
		
		System.out.println(tabla);
		
		
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
