package toPosfix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class toPosfix {
  public static void main(String[] args) throws IOException {

	//Se abre el documento y se genera una variable para lectura del documento
  	FileReader f = new FileReader("Expresion.txt");
  	BufferedReader b = new BufferedReader(f);
  	
  	String toPostfix = b.readLine();
  	
    //Elimina espacios en blanco
    toPostfix = toPostfix.replaceAll("\\s+", "");
    toPostfix = "(" + toPostfix + ")";
    String simbols = "+*()";
    String temp = "";
    	 
    //Escribir espacios entre todos los operadores
    for (int i = 0; i < toPostfix.length(); i++) {
    	if (simbols.contains(toPostfix.charAt(i) + "")) {
    		temp += " " + toPostfix.charAt(i) + " ";
    	}else 
    		temp += toPostfix.charAt(i);
    	}
    
    temp.replaceAll("\\s+", " ").trim();
    String arrInfix[] = temp.split(" ");

    //Pila "entrada" utilizada para la entrada
    //Pila "operadores" para manipular los operadores
    //Pila "salida" definir la salida
    Stack<String> entrada = new Stack<>(); 
    Stack<String> operadores = new Stack<>(); 
    Stack<String> salida = new Stack<>(); 

    //Añadir la array a la Pila de entrada (E)
    for (int i = arrInfix.length - 1; i >= 0; i--) {
      entrada.push(arrInfix[i]);
    }

    try {
      //Se ejecuta mientras la pila de entrada no este vacia
      while (!entrada.isEmpty())
    	  
    	//Verifica si es un operador o simplemente cualquier operando
        switch (jerarquia(entrada.peek())){
        	//Operar con "("
          	case 1:
          		//en caso de ser un "(" se mete con los operadores
          		operadores.push(entrada.pop());
          		break;
          	//Operar con ")"
          	//En caso de encontrar un "(" se agrega a la salida y se saca de los operadores
          	case 2:
          		while(!operadores.peek().equals("(")) {
          			salida.push(operadores.pop());
          		}
          		operadores.pop();
          		entrada.pop();
          		break;
          	//Operar con "+"
          	//No se pone nada porque se le da el mismo trato que a "*"
            case 3:
            //Operar con "*"
            //Mientras la jerarquia del operando encontrado sea mayor en la pila "operadores",
            //se saca de la dalida
            case 4:
            	while(jerarquia(operadores.peek()) >= jerarquia(entrada.peek())) {
            		salida.push(operadores.pop());
            	}
            	operadores.push(entrada.pop());
            	break;  
            default:
            	salida.push(entrada.pop()); 
      }
      
    //Meter todo al documento
  	File file = new File("toPostfix.txt");
      
    // creates the file
    file.createNewFile();
    
    // creates a FileWriter Object
    FileWriter writer = new FileWriter(file); 
      

    
    //Insertar todo al documento
    String postfix = salida.toString().replaceAll("[\\]\\[,]", "");
    writer.append(postfix);
    
    writer.flush();
    writer.close();
      
    b.close();
    
    }catch(Exception e){ 
      e.printStackTrace();
    }
  }

  //Jerarquia de operadores
  private static int jerarquia(String op) {
    int operador = 0;
    
    //Se jerarquisan los operadores
    if(op.equals("*"))
    	operador = 4;
    else if(op.equals("+")) 
    	operador = 3;
    else if(op.equals(")")) 
    	operador = 2;
    else if(op.equals("("))
    	operador = 1;
    
    return operador;
  }
}
