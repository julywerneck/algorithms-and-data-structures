public class tp01q06 {
	public static boolean isFim(String str) {
		return str.length() >= 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M';
	} // Verificar se é 'FIM' 

	public static boolean isVogal(String str) {
		boolean vogal = true; 
		
		for(int	i = 0; vogal == true && i < str.length(); i++) {	
		       if(str.charAt(i) != 'a' && str.charAt(i) != 'A' && str.charAt(i) != 'e' && str.charAt(i) != 'E' && str.charAt(i) != 'i' && str.charAt(i) != 'I' && str.charAt(i) != 'o' && str.charAt(i) != 'O' && str.charAt(i) != 'u' && str.charAt(i) != 'U') {
	       			       vogal = false; 
		       }
	 	}

		return vogal; 		
	} // Verifica se é uma string formada apenas de vogais - varre toda a str e caso encontre algo diferente das vogais seta valor como false e sai do for

	public static boolean isConso(String str) {
		boolean consoante = true; 

		for(int i = 0; consoante == true && i < str.length(); i++) {
			if((str.charAt(i) < 'A' ||  str.charAt(i) > 'Z') && (str.charAt(i) < 'a' || str.charAt(i) > 'z')){ 
				consoante = false;
			} else if(str.charAt(i) == 'a' || str.charAt(i) == 'A' || str.charAt(i) == 'e' || str.charAt(i) == 'E' || str.charAt(i) == 'I' || str.charAt(i) == 'i' || str.charAt(i) == 'o' || str.charAt(i) == 'O' || str.charAt(i) == 'U' || str.charAt(i) == 'u') {
				       consoante = false;
				}
			}

		return consoante; 

	} // Verifica se a string é formada apenas por consoante - verifica se é letra depois verifica se é apenas vogal e seta o valor como false nesses casos

	public static boolean isInt(String str) {
		boolean inteiro = true; 

		for(int i = 0; inteiro == true && i < str.length(); i++) {
			int check = (int)str.charAt(i);

			if(check < 48 || check > 57) {
				inteiro = false; 
			}
		}

		return inteiro; 
	} // Verifica se a string é um inteiro - varre a str verificando se é formada apenas por numeros

	public static boolean isReal(String str) {
		boolean real = true; 

		for(int i = 0; real == true && i < str.length(); i++) {
			int check = (int)str.charAt(i); 

			if(check < 48 || check > 57 || check != 46 || check != 44) {
					real = false;  
			}
		}
	
		return real; 
	}

	public static void main(String[] args) {
		String str[] = new String[10000]; 
		int numEntrada = 0; 

		do {
			str[numEntrada] = MyIO.readLine();
		} while(isFim(str[numEntrada++]) == false); 
		// Leitura da entrada enquanto diferente de FIM
		numEntrada--;

		for(int i = 0; i < numEntrada; i++) {
			if(isVogal(str[i]) == true) {
				MyIO.println("SIM NAO NAO NAO"); 
			} else if(isConso(str[i]) == true) {
				MyIO.println("NAO SIM NAO NAO");
			} else if(isInt(str[i]) == true) {
				MyIO.println("NAO NAO SIM SIM");
			} else if(isReal(str[i]) == true) {
				MyIO.println("NAO NAO NAO SIM");
			} else {
				MyIO.println("NAO NAO NAO NAO");
			}
		}
	}
}
