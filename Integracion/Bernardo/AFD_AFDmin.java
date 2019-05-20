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
		ArrayList<ArrayList<Estado>> tablaN = new ArrayList<ArrayList<Estado>>();
		
		//Leemos el alfabeto y sacamos su magnitud
		alfabeto = in.nextLine();
		separarAlfabeto(alfabeto, alfabetoLista);
		
		//Establecemos el estado inicial
		estadoIni = Integer.parseInt(in.nextLine());
		
		//Establecemos el/los estado(s) final(es)
		estadosFinStr = in.nextLine();
		separarEstadosFinales(estadosFinStr, estadosFin);	
		
		
		int m = 0;
		//Creando la matriz de funci�n de transiciones	
		while (in.hasNextLine()) {
			ArrayList<String> filaLista = new ArrayList <String>();
			Estado estado = new Estado();
			estado.numEstado = m;
			if(estadoIni == m) {
				estado.esInicial = true;
			}
			if(estadosFin.contains(m)) {
				estado.esFinal=true;
			}
			estados.add(estado);			
			
			fila = in.nextLine();
			separarFilas(fila, filaLista);
			estado.estados.addAll(filaLista);
			matriz.add(filaLista);	
			m++;
		} 
		
		int x = 0;
		

		//Poblamos la tabla
		for(int i = estados.size(); i > 1; i--) {		
			for(int j = 0; j < i-1; j++) {
				ArrayList<Estado> row = new ArrayList<Estado>();
				row.add(estados.get(x));
				row.add(estados.get(j+(x+1)));
				tabla.add(row);
			}
			x++;			
		}
		
		
		m = 0;
		
		//Paso 3
		for(int i = 0; i < tabla.size(); i++) {
			if(tabla.get(i).get(0).esFinal == tabla.get(i).get(1).esFinal) {
				
			} else {
				tabla.remove(i);
				i--;
			}
		}	
		
		
		ArrayList<ArrayList<Estado>> borrados = new ArrayList<ArrayList<Estado>>();
		ArrayList<ArrayList<String>> tablaPaso4 = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> tablaPaso4Copia = new ArrayList<ArrayList<String>>();
		m = 0;
		x = 0;
		
		//Paso 4
		while(m != 1) {
			x = 0;
			m = 1;			
			
			if(!borrados.isEmpty()) {
				for(int i = 0; i < tablaPaso4Copia.size(); i++) {
					for(int j = 0; j < (alfabetoLista.size()*2); j = j + 2) {
						int estadoA = Integer.parseInt(tablaPaso4Copia.get(i).get(j));
						int estadoB = Integer.parseInt(tablaPaso4Copia.get(i).get(j+1));
						if(borrados.get(i).get(0).numEstado == estadoA && borrados.get(i).get(1).numEstado == estadoB) {
							borrados.add(tabla.get(i));
							tabla.remove(i);
							tablaPaso4Copia.remove(i);
							i--;
							m = 0;
							break;
						}
					}
				}
			}
			for(int i = 0; i < tabla.size(); i++) {
				int estadoA = tabla.get(i).get(0).numEstado;
				int estadoB = tabla.get(i).get(1).numEstado;
				
				
				ArrayList<String> filaLista = new ArrayList <String>();
				for(int j = 0; j < alfabetoLista.size(); j++) {
					filaLista.add(matriz.get(estadoA).get(j));
					filaLista.add(matriz.get(estadoB).get(j));	
				}		
				tablaPaso4.add(filaLista);
			}
			for(int i = 0; i < tabla.size(); i++) {
				for(int j = 0; j < tablaPaso4.get(i).size(); j = j + 2) {
					int estadoA = Integer.parseInt(tablaPaso4.get(x).get(j));
					int estadoB = Integer.parseInt(tablaPaso4.get(x).get(j+1));					
					
					if(estados.get(estadoA).esFinal != estados.get(estadoB).esFinal) {
						borrados.add(tabla.get(i));
						tabla.remove(i);
						tablaPaso4.remove(x);
						x--;
						i--;
						m = 0;
						break;
					} 
				}
				x++;
			}
			tablaPaso4Copia = (ArrayList<ArrayList<String>>) tablaPaso4.clone();
			tablaPaso4.clear();
			
		}
		
		m = 1;
		x = 0;
		
		
		//Prueba
//		System.out.println("Estados" + matriz);
		System.out.println("Estados iguales: " + tabla);
		
//		ArrayList<Integer> numeros =  new ArrayList<Integer>();
//		for(int i = 0; i < matriz.size(); i++) {
//			numeros.add(i);
//		}
//		
//		System.out.println(numeros);
//		for(int i = 0; i < matriz.size();i++) {
//			if(numeros.contains(tabla.get(i).get(0).numEstado)) {
//				
//			}
//		}
//		
//		System.out.println(numeros);
//		
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
