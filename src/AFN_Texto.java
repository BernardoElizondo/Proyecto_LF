	import java.util.ArrayList;
	import java.util.Scanner;
	import java.io.File;
	import java.io.FileNotFoundException;


	public class AFN_Texto {
		// Esto es un comentario
		public static void main(String[] args) throws FileNotFoundException {

			// -------------------LECTURA Y PROCESAMIENTO DE ENTRADA---------------------//

			// Scanner para recibir archivo de entrada de ejemplos
			File file2 = new File("res\\ejemplos_ER.txt");
			Scanner inputAFN = new Scanner(file2);

			// Lectura de ejemplos
			ArrayList<String> CadenasAFN = new ArrayList<String>();
			do {
				// leer y almacenar todos los ejemplos hasta que el archivo llegue a su fin
				CadenasAFN.add(inputAFN.nextLine().trim());
				CadenasAFN.size();
			} while (inputAFN.hasNext() != false);
			inputAFN.close();// Cerrar el archivo de ejemplos

			// Scanner para recibir archivo de entrada del lenguaje
			File file1 = new File("res\\M3_salida.txt");
			Scanner inputEntries = new Scanner(file1);
			// Lectura de alfabeto: símbolos separados por punto y coma
			String Temporal = inputEntries.nextLine().trim();//Guardamos el alfabeto
			int AlfabetoTotal = Temporal.length() / 2;//Calculamos el total de caracteres en el alfabeto
			char[] alfabeto = new char[AlfabetoTotal];//Creamos arreglo que almacene todos los caracteres
			for (int i = 0, j = 0; i < Temporal.length(); i += 2)
				if (j < AlfabetoTotal)
					alfabeto[j++] = Temporal.charAt(i);//Almacenamos cada caracter del alfabeto en la matriz
			// Lectura de Estado inicial
			int EstadoInicial = inputEntries.nextInt();//Almacenamos el estado inicial
			Temporal = inputEntries.nextLine();
			// Lectura de Estados finales
			Temporal = inputEntries.nextLine();//Almacenamos la cadena que da a conocer los estados finales
			int TotalListChar = Temporal.length() / 2;//Conocemos el total de estados finales
			Temporal = Temporal.trim();//Eliminar espacios de documento de texto
			ArrayList<Character> ListChar = new ArrayList<Character>();
			for (int i = 0; i < Temporal.length(); i ++) {
				ListChar.add(Temporal.charAt(i));
			}
			
			ArrayList<Integer> EstadosFinales = new ArrayList<>();
			int count = 0, acum = 0, temp = 0, indice = 0;
			while (ListChar.size() != 0) {//ciclo para extraer todos los estados finales
				if(ListChar.get(indice) != ';') {//cuándo no es ;
					count++;
					indice++;
				}
				else {//Cuándo es ; comenzamos a eliminar valores y a guardarlos 
					temp = count +1;
					for(int j = 0; j < temp; j++) {
						if(ListChar.get(0)==';') {
							ListChar.remove(0);
						}else {
							acum += ((int) (((int)(ListChar.remove(0) - 48)) * (Math.pow(10, count-1))));	
						}
						count--;

					}				
					EstadosFinales.add(acum);
					count = acum = indice = 0;
				}
			}
					
			//Lectura de función de ArregloEstadosiciones y acomodo en matriz ArregloEstadosiciones[estados][simbolos]
			ArrayList<ArrayList<Character>> Estados = new ArrayList<ArrayList<Character>>();
			int indice2 = 0;
			while(inputEntries.hasNext()) {
				Estados.add(new ArrayList<Character>());
				Temporal = inputEntries.nextLine().trim(); 
				for (int i = 0; i < Temporal.length(); i++) {
					Estados.get(indice2).add(Temporal.charAt(i));
				}
				indice2++;
			}
			
			inputEntries.close();//Cerrar el documento
			
			ArrayList<ArrayList<ArrayList<Integer>>> ArregloEstados = new ArrayList<ArrayList<ArrayList<Integer>>>();//Creación de Arreglo tridimensional
			for (int i = 0; i < Estados.size(); i++) {
				ArregloEstados.add(new ArrayList<>()); 
				for (int j = 0; j < alfabeto.length; j++) {
					ArregloEstados.get(i).add(new ArrayList<Integer>());
				}
			}
			
			
			int j1 = 0,flag = 1,flag2 = 0;//j1 utilizado para conocer en que arreglo se guardará la ArregloEstadosición basado en el alfabeto, flag para mantener un ciclo corriendo hasta llegar a un ; y flag2 para valorar si el valor de acum alguna vez fue alterado en el caso de que se utilice n )(null)
			for (int i = 0; i < Estados.size(); i++) {
				count = acum =temp =indice = 0;
				while (Estados.get(i).size() != 0) {
					flag = 1;
					if (Estados.get(i).get(0)  == '{') {//Caso en el que hay más de una Transición
						Estados.get(i).remove(0);
						while(flag != 0) {
							if(Estados.get(i).get(0) == ';') {//Caso en el que encontramos ; que dicta si finalizan las trnasiciones
								flag = 0;
							}else if(Estados.get(i).get(indice) != ',' && Estados.get(i).get(indice) != '}') {//Caso en el que no sigue una coma o un }
								count++;
								indice++;
							}
							else {//Caso en el que que sigue una coma o un } en el cual se eliminaran y guardaran los estados
								temp = count +1;
								for(int j = 0; j < temp; j++) {
									if(Estados.get(i).get(0) == ',') {//remover la coma
										Estados.get(i).remove(0);
									}else if(Estados.get(i).get(0) == 'n'){//si hay una n se agrega un -1
										ArregloEstados.get(i).get(j1).add(-1);
										Estados.get(i).remove(0);
									}else if(Estados.get(i).get(0) == '}') {//remover }
										Estados.get(i).remove(0);
									}else {//saber cuál es el valor del estado en el que nos encontramos
										acum += ((int) (((int)(Estados.get(i).remove(0) - 48)) * (Math.pow(10, count-1))));
										flag2 = 1;//si pasa esto significa que el estado actual no es n
									}
									count--;
								}
								if(flag2 != 0)ArregloEstados.get(i).get(j1).add(acum);//si el estado actual no era n se guarda el valor
								count = acum = indice= flag2 = 0;
							}
						}
						Estados.get(i).remove(0);
		
					}else { //caso en el que La cadena no cuenta con más de 1 ArregloEstadosición 
						while (flag != 0) {
							if(Estados.get(i).get(indice) != ';') {//cuándo no es ; 
								count++;
								indice++;
							}
							else {//cuando es ; sabemos que acaba la transición por lo que lo que buscamos el valor del estado
								temp = count +1;
								for(int j = 0; j < temp; j++) {
									if(Estados.get(i).get(0)==';') {//cuando valor actual es ;
										Estados.get(i).remove(0);
										flag = 0;
									}else if(Estados.get(i).get(0) == 'n'){//cuándo el estado es n
										ArregloEstados.get(i).get(j1).add(-1);
										Estados.get(i).remove(0);
									}else {//cuando el estado es un número
										acum += ((int) (((int)(Estados.get(i).remove(0) - 48)) * (Math.pow(10, count-1))));
										flag2 = 1;
										
									}
									count--;
								}
								if(flag2 != 0)ArregloEstados.get(i).get(j1).add(acum);
								count = acum = indice =flag2 = 0;
							}
						}
					}
					j1++;
				}
				j1 = 0;
			}

			int f = 0;
			ArrayList<ArrayList<ArrayList<Integer>>> Transiciones = new ArrayList<ArrayList<ArrayList<Integer>>>();//Creación del Arreglo tridimensional
			for (int i = 0; i < CadenasAFN.size(); i++) {
				Transiciones.add(new ArrayList<>()); 
				for (int j = 0; j < CadenasAFN.get(i).length(); j++) {
					Transiciones.get(i).add(new ArrayList<Integer>());
				}
			}
			int flag3 = 0,flag4 = 0;
			for (int i = 0; i < CadenasAFN.size(); i++) {//Ciclo para hacer el árbol de derivaciones
				for(int j = 0;j<CadenasAFN.get(i).length() ;j++) {
					for (int k = 0; k < AlfabetoTotal; k++) {
						if(CadenasAFN.get(i).charAt(j) == alfabeto[k]) {
							if(j == 0) {//Caso en el que el estado debe ser inicial
								for (int l = 0; l < ArregloEstados.get(EstadoInicial).get(k).size(); l++) {
									if (ArregloEstados.get(EstadoInicial).get(k).get(l) == -1) {
										Transiciones.get(i).get(j).add(-1);
									}else {
										Transiciones.get(i).get(j).add(ArregloEstados.get(EstadoInicial).get(k).get(l));
									}
								}
							}else {//Casos después del estado inicial
								for (int l = 0; l < Transiciones.get(i).get(j-1).size(); l++) {
									if (Transiciones.get(i).get(j-1).get(l) == -1) {
										Transiciones.get(i).get(j).add(-1);//Caso en el que el estado llega a una epsion transición
									}else {
										for (int m = 0; m < ArregloEstados.get(Transiciones.get(i).get(j-1).get(l)).get(k).size(); m++) {//caso en el que estado es correcto
											Transiciones.get(i).get(j).add(ArregloEstados.get(Transiciones.get(i).get(j-1).get(l)).get(k).get(m));
										}
									}
								}
							}
						}
					}
				}
			}
			
			for (int i = 0; i < CadenasAFN.size(); i++) {//Valorar si la cadena recibida llega a un estado final
				for (int j = 0; j < CadenasAFN.get(i).length(); j++) {
					for (int k = 0; k < Transiciones.get(i).get(j).size(); k++) {
						for (int l = 0; l < EstadosFinales.size(); l++) {
							if (Transiciones.get(i).get(j).get(k) == EstadosFinales.get(l) && flag3 != 1) {//Si llega aqui significa que encontró un estado final en el último caracter
								flag4 = 1;
								flag3 = 1;
							}
						}
					}
					if(flag3 == 0) {
						flag4 = 0;
					}
					flag3 = 0;
				}
				if (flag4 == 1) {
					System.out.printf("%s = cadena aceptada\n", CadenasAFN.get(i));
				}else {
					System.out.printf("%s = cadena rechazada\n", CadenasAFN.get(i));
				}
				flag4 = 0;

			}
		}
	}

