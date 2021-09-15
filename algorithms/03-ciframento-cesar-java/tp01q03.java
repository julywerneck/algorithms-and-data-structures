public class tp01q03 {
	public static boolean isFim(String str) {
		return(str.length() >= 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M'); 
	} // Verifica se a string recebida é FIM;

	public static String ciframentoCesar(String str) {
		String cifrado = ""; // Cria uma nova string para receber a troca de caracteres e fazer o ciframento;  

		for(int i = 0; i < str.length(); i++) {
			cifrado += (char)(str.charAt(i) + 3);  
		}

		return cifrado; 
	}


	public static void main(String[] args) {
		String entrada[] = new String [1000]; 
		int numEntrada = 0; 

		do {
			entrada[numEntrada] = MyIO.readLine();
		} while(isFim(entrada[numEntrada++]) == false); 

		numEntrada--; 

		for(int i = 0; i < numEntrada; i++) {
			MyIO.println(ciframentoCesar(entrada[i])); // printa a string 'cifrado' criada no metodo e que já trocou as letras
		}
	}
}
