package logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Map;

import javax.swing.JTextArea;

public class Traductor {
	
	//3 Hasmap los cuales se encargaran de reemplazar las sentencias en java por sentencias en python

	Map<String, String> variables = Map.of(";", "" , "int", "" , "String", "", "double",
	 "" , "float", "", "boolean", "", "char", "", "public", "" , "void" , "");
	Map<String, String> structure = Map.of("{", ":", "}" , "" ,"if" , "if" , "while" ,
	 "while" , "ifelse" , "elif" , "else" , "else" , "&&" , "and" , "||" , "or");
	Map<String, String> coments = Map.of("//" , "#" ,"/*" , "\"\"\"" , "*" , "" , "*/", "\"\"\"");
	
	// this method replaces the java syntax to python sintax.

	public File translate(File javaInputCode, JTextArea textArea) {
		
		//string auxiliar para los switch que se encuentren en el codigo entregado
		String cont = "";
		File output;
		
		// catch para controlar un posible fallo al crear el archivo o durante la ejecucion del codigo
		try {
			
			//creacion del archivo donde estara la traduccion del codigo en java
			output = new File("D:\\output.txt");
			
			BufferedReader brr = new BufferedReader(new FileReader(javaInputCode));

			FileWriter fw = new FileWriter(output);

			String line = brr.readLine();

			while (line != null) {
				
				//creacion de un arreglo de string donde se dividira por cada espacio que se encuentre en una linea
				String[] splitedLine = line.split(" ");
				
				
				//ciclo for que servira para revisar todo el arreglo de strings
				for (int i = 0; i < splitedLine.length; i++) {
					
					//revisa si existe una palabra en el arreglo que coincida con una palabra del HashMap
					//con el fin de reemplazarla por su equivalente en python
					if (getVariables().containsKey(splitedLine[i])) {
						splitedLine[i] = getVariables().get(splitedLine[i]);
					}
					if (getStructure().containsKey(splitedLine[i])) {
						splitedLine[i] = getStructure().get(splitedLine[i]);
					}
					
					//es un caso especial en el cual si encuentra en el string una palabra igual realizara 
					//el cambio que se observa en el if
					if ("extends".equals(splitedLine[i])) {

						splitedLine[i] = "(" + splitedLine[i + 1] + ")";
						splitedLine[i + 1] = "";
					}
					
					//caso especial para las funciones, en este revisara si la posicion i es un parentecis
					//y si es un parentecis revisara si el termino que lo acompa�a no es un statement en java
					if ("()".equals(splitedLine[i]) || "(".equals(splitedLine[i])
							&& getStructure().containsKey(splitedLine[i - 1]) == false) {

						splitedLine[i - 1] = " def " + splitedLine[i - 1];
					}
					
					//caso especial para los switch, si encuentra un switch tomara la variable del switch que esta
					//entre parentecis y la almacelara en un string para despues usarla en los case como variable
					if ("switch".equals(splitedLine[i])) {

						splitedLine[i] = "";
						splitedLine[i + 1] = "";

						cont = splitedLine[i + 2];

						splitedLine[i + 2] = "";

						splitedLine[i + 3] = "";

						splitedLine[i + 4] = "";

					}
					
					// si encuentra un case lo mas seguro es que antes se haya encontrado con un switch
					// asi que tomara la variable que le quito a switch para crear una serie de if que es como se
					// hace un switch en python
					if ("case".equals(splitedLine[i])) {

						splitedLine[i] = "if ( " + cont + " == " + splitedLine[i + 1] + " ) : ";

						splitedLine[i + 1] = "";

						splitedLine[i + 2] = "";

						splitedLine[i + 3] = "";

					}
					
					//si encuentra un private o un protected los eliminara y reemplazara con __ o _ respectivamente
					//y lo juntara a el nombre de la variable
					if ("private".equals(splitedLine[i])) {

						splitedLine[i] = "";

						splitedLine[i + 1] = "__" + splitedLine[i + 2];
					}
					if ("protected".equals(splitedLine[i])) {

						splitedLine[i] = "";

						splitedLine[i + 1] = "_" + splitedLine[i + 2];
					}

				}
				
				
				//escribe el codigo traduciodo en el archivo output
				fw.write(String.join(" ", splitedLine));

				System.out.println(String.join(" ", splitedLine));

				line = brr.readLine();
			}
			brr.close();
			fw.close();
			return output;
		} catch (Exception z) {
			// TODO: handle exception
			System.out.print(false);
			return new File("D:\\errorLog.txt");
		}
		
		
	}
	
	
	//getters y setters de los HashMaps
	public Map<String, String> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, String> variables) {
		this.variables = variables;
	}

	public Map<String, String> getStructure() {
		return structure;
	}

	public void setStructure(Map<String, String> structure) {
		this.structure = structure;
	}

	public Map<String, String> getComents() {
		return coments;
	}

	public void setComents(Map<String, String> coments) {
		this.coments = coments;
	}
	
	
}