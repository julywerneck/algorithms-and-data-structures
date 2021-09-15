public class tp01q01 {
	public static boolean isFim(String str) {
		return (str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M'); 
	}

	public static boolean isPalindromo(String str) {
		boolean palindromo = true; 
		int j = str.length()-1;
	        	

		for(int i = 0; palindromo == true && i <= j ; i++) {
		      if(str.charAt(i) != str.charAt(j)) { 
			     palindromo = false; 
		      } 

		     j--;
		}

		return palindromo; 
	}

	public static void main(String[] args) {
		String[] entrada = new String[1000]; 
		int numEntrada = 0; 	

		do{
			entrada[numEntrada] = MyIO.readLine(); 
		} while (isFim(entrada[numEntrada++]) == false); 

		numEntrada--;

		for(int i = 0; i < numEntrada; i++) {
			if(isPalindromo(entrada[i]) == true) {
				MyIO.println("SIM");
			} else { 
				MyIO.println("NAO");
			}
		}
	}
}
	
