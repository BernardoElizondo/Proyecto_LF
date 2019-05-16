package fuckEpsilonTransitions;

import java.io.*;
import java.io.FileReader;

public class byeByeE {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
			//Se abre el documento y se genera una variable para lectura del documento
	    	FileReader f = new FileReader("noE.txt");
	    	BufferedReader b = new BufferedReader(f);
	    	
	    	//Se declara una variable para el tamaño del arreglo, donde posteriormente se guarden los estados (size)
	    	//Se declaran variables para almacenar el destino (cont1)
	    	//Se declara una variable para almacenar los renglones de estado-tabla (sizeReader)
	    	int size = 0, cont1 = 0;
	    	String sizeReader;
	    	
	    	//See almacenan el alfabeto, esto inicial y estados finales
	    	String alfabeto = b.readLine().replaceAll(";", "").toLowerCase().trim();
	    	String estadoIn = b.readLine().trim();
	    	String estadoFi = b.readLine().replaceAll(";", "").toLowerCase().trim();
	    	
	    	//Se genera una cadena que almacene los destinos de cada estado
	    	String strMat = "";
	    	while((sizeReader = b.readLine())!=null)
	    	{
	    		strMat += sizeReader.replaceAll(";", "").replaceAll(" ", "");
	    		size++;
	    	}
	    	
	    	//Los destinos de cada estado se guardan en un arreglo para simular una tabla
	    	String table[][] = new String[size][alfabeto.length()+1];
	    	
	    	for(int i = 0; i < size; i++)
	    		for(int j = 0; j < alfabeto.length()+1; j++)
	    		{
	    			table[i][j] = Character.toString(strMat.charAt(cont1));
	    			cont1++;
	    		}
	    	//Se guardan los estados alcanzables sin ningún símbolo consumido
	    	String eEach[]= new String[size];
	    	String eTrans;
	    	
	    	for(int i = 0; i < size; i++)
	    	{
	    		eTrans = Integer.toString(i);
	    		for(int k = i; k < size; k++)
	    			if(!table[k][alfabeto.length()].equals("n") && !eTrans.contains(table[k][alfabeto.length()]))
	    				eTrans += table[k][alfabeto.length()];
	    		eEach[i] = eTrans;
	    	}
	    		    	
	    	//Eliminar las e transiciones y sustituirlas por las nuevas
	    	String newETrans = "";
	    	String almostDone = "";
	    	String newRTransList[][] = new String[size][alfabeto.length()];
	    	String finaList[][] = new String[size][alfabeto.length()];
	    	
	    	for(int i = 0; i < alfabeto.length(); i++)
	    	{
	    		for(int j = 0; j < size; j++) {
	    			eTrans = eEach[j];
	    			for(int k = 0; k < eTrans.length(); k++)
	    				if(!table[Integer.parseInt(eTrans.substring(k, k+1))][i].equals("n"))
	    					newETrans += table[Integer.parseInt(eTrans.substring(k, k+1))][i];
	    			newRTransList[j][i] = newETrans;
	    			newETrans = "";
	    		}
	    	}
	    	
	    	for(int i = 0; i < size; i++) {
	    		for(int j = 0; j < alfabeto.length(); j++)
	    		{
	    			newETrans = newRTransList[i][j];
	    			for(int k = 0; k < newETrans.length(); k++)
	    				almostDone += eEach[Integer.parseInt(newETrans.substring(k, k+1))];
	    			almostDone = almostDone.replaceAll("(.)\\1", "$1");
	    			finaList[i][j] = almostDone;
	    			almostDone = "";
	    		}
	    	}
	    	
	    	//Dar formato de salida
	    	String formatVar = "";
	    	String fFormatList[][] = new String[size][alfabeto.length()];
	    	
	    	for(int i = 0; i < size; i++)
	    		for(int j = 0; j < alfabeto.length(); j++) {
	    			almostDone = finaList[i][j];
	    			if(almostDone.length() > 1) {
	    				String fList[] = new String[almostDone.length()];
	    				for(int k = 0; k < almostDone.length(); k++) 
	    				{
	    					fList[k] = almostDone.substring(k, k+1) + ",";
	    					formatVar += fList[k];
	    				}
	    				almostDone = "{" + formatVar.substring(0, formatVar.length()-1) + "}";
	    			}
	    			if(almostDone.equals(""))
	    				almostDone = "n";
	    			fFormatList[i][j] = almostDone;
	    		}
	    	
	    	//Meter todo al documento
	    	File file = new File("No_epsilon_transitions.txt");
	        
	        // creates the file
	        file.createNewFile();
	        
	        // creates a FileWriter Object
	        FileWriter writer = new FileWriter(file); 
	        
	        // Writes the content to the file
	        for(int i = 0; i < alfabeto.length(); i++)
	        	writer.write(alfabeto.charAt(i) + ";");
	        writer.write("\r\n" + estadoIn + "\r\n");
	        if(estadoFi.length() > 1)
	        	for(int i = 0; i < estadoFi.length(); i++)
	        		writer.write(estadoFi.charAt(i) + ";");
	        else
	        	writer.write(estadoFi);
	        writer.write("\r\n");
	        for(int i = 0; i < size; i++) {
	        	for(int j = 0; j < alfabeto.length(); j++)
	        		writer.write(fFormatList[i][j] + ";");
	        	writer.write("\r\n");
	        }
	        
	        writer.flush();
	        writer.close();
	        
	    	b.close();
		}catch(Exception e){
	    	e.printStackTrace();
	    }
	}
}
