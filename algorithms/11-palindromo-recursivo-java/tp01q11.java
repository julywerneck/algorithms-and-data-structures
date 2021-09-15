public class tp01q11{
    public static boolean isFim(String s){ //Verifica se string é igual a FIM
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
     }

    public static boolean callPalindromo(String str){
        int tam = (str.length() - 1);
        int i = 0;

        boolean isPalindromo = verificaPalindromo(str, tam, i);
        
        return isPalindromo;
    }

    public static boolean verificaPalindromo(String str, int tam, int i){
        boolean palindromo = true;
    
                if(str.charAt(i) != str.charAt(tam)){
                    palindromo = false;
                } else if(i < tam){
                    return verificaPalindromo(str, tam-1, i+1);
                }

            return palindromo; 
    }
    
        public static void main(String[] args){
            String[] entrada = new String[3000];
            int numEntrada = 0; 

            do {
                entrada[numEntrada] = MyIO.readLine(); // Entrada padrão 
            } while (isFim(entrada[numEntrada++]) == false); // Verifica se não é a condição de parada

            numEntrada--; // Descresce em um para retirar o 'FIM'

            for(int i = 0; i < numEntrada; i++){
                if(callPalindromo(entrada[i]) == true){ // Se o valor retornado pela função for verdade printa SIM
                    MyIO.println("SIM");
                } else MyIO.println("NAO"); // Caso contrário printa NÃO
            }
        }
}
