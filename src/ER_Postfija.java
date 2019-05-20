import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ER_Postfija{
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Set<Character> alfabeto = new HashSet<Character>();
		Set<Character> operadores = new HashSet<Character>();
		
		agregarAlfabeto(alfabeto);
		agregarOperadores(operadores);
		File file = new File("files/ER.txt");
		Scanner	in = new Scanner(file);
		
		
		
		
		
		
		
	}
	
	
	public static void agregarAlfabeto(Set<Character> alfabeto) {
		alfabeto.add('á');
		alfabeto.add('Á');
		alfabeto.add('é');
		alfabeto.add('É');
		alfabeto.add('í');
		alfabeto.add('Í');
		alfabeto.add('ó');
		alfabeto.add('Ó');
		alfabeto.add('ú');
		alfabeto.add('Ú');
		alfabeto.add('a');
		alfabeto.add('b');
		alfabeto.add('c');
		alfabeto.add('d');
		alfabeto.add('e');
		alfabeto.add('f');
		alfabeto.add('g');
		alfabeto.add('h');
		alfabeto.add('i');
		alfabeto.add('j');
		alfabeto.add('k');
		alfabeto.add('l');
		alfabeto.add('m');
		alfabeto.add('n');
		alfabeto.add('ñ');
		alfabeto.add('o');
		alfabeto.add('p');
		alfabeto.add('q');
		alfabeto.add('r');
		alfabeto.add('s');
		alfabeto.add('t');
		alfabeto.add('u');
		alfabeto.add('v');
		alfabeto.add('w');
		alfabeto.add('x');
		alfabeto.add('y');
		alfabeto.add('z');
		alfabeto.add('A');
		alfabeto.add('B');
		alfabeto.add('C');
		alfabeto.add('D');
		alfabeto.add('E');
		alfabeto.add('F');
		alfabeto.add('G');
		alfabeto.add('H');
		alfabeto.add('I');
		alfabeto.add('J');
		alfabeto.add('K');
		alfabeto.add('L');
		alfabeto.add('M');
		alfabeto.add('N');
		alfabeto.add('Ñ');
		alfabeto.add('O');
		alfabeto.add('P');
		alfabeto.add('Q');
		alfabeto.add('R');
		alfabeto.add('S');
		alfabeto.add('T');
		alfabeto.add('U');
		alfabeto.add('V');
		alfabeto.add('W');
		alfabeto.add('X');
		alfabeto.add('Y');
		alfabeto.add('Z');
		alfabeto.add('0');
		alfabeto.add('1');
		alfabeto.add('2');
		alfabeto.add('3');
		alfabeto.add('4');
		alfabeto.add('5');
		alfabeto.add('6');
		alfabeto.add('7');
		alfabeto.add('8');
		alfabeto.add('9');
		alfabeto.add(' ');
		alfabeto.add('&');
		alfabeto.add('(');
		alfabeto.add(')');
		alfabeto.add('\\');		
		
	}
	
	public static void agregarOperadores(Set<Character> operadores) {
		operadores.add('*'); 
		operadores.add('+');
		operadores.add('-');//Concatenación
		operadores.add(',');//Unión
		
	}
}
