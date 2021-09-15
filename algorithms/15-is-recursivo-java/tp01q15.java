public class tp01q15{
        public static boolean isFim(String s){ //Verifica se string é igual a FIM
            return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
         }
    
         public static boolean isVogal(String str, int i, boolean vogal){
    
                //Verifica se o char na posição i é diferente de qualquer vogal
                if(str.charAt(i) != 'a' && str.charAt(i) != 'e' && str.charAt(i) != 'i' && str.charAt(i) != 'o' && str.charAt(i) != 'u' &&  str.charAt(i) != 'A' && str.charAt(i) != 'E' && str.charAt(i) != 'I' && str.charAt(i) != 'O' && str.charAt(i) != 'U'){
                    vogal = false; //Caso seja diferente de vogal atribui falso para o valor booleano de vogal
                }else if((i < str.length() - 1) && vogal == true){
                    i++; 
                    vogal = isVogal(str, i, vogal);
                } return vogal; //Retorna o valor bool de vogal

         }
    
         public static boolean isConso(String str, int i, boolean conso){
    
                int verifica = (int)str.charAt(i); // Transforma o char em um valor inteiro
    
                // Verifica se o valor verifica esta entre as letras                          Verifica se o char em i é igual a uma vogal
                if(((verifica < 65 || verifica > 90) && (verifica < 97 || verifica > 122)) || (str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o' || str.charAt(i) == 'u' || str.charAt(i) == 'A' || str.charAt(i) == 'E' || str.charAt(i) == 'I' || str.charAt(i) == 'O' || str.charAt(i) == 'U')){
                    conso = false; // Caso qualquer das verificações seja verdadeira atribuir falso para o valor bool conso
                }else if(conso == true && i < str.length()-1){
                    i++; 
                    conso = isConso(str, i, conso);
                } return conso; // Retorna o valor bool de conso
        }
    
        public static boolean isInteger(String str, int i, boolean inteiro){

                int verifica = (int)str.charAt(i); // Transforma o char em i em um número inteiro de acordo com o ASCII
    
                if(verifica < 48 || verifica > 57){ // Verifica se os valores de verifica estão entre os números
                    inteiro = false; // Caso não atribui falso ao bool inteiro
                }else if(inteiro == true && i < str.length()-1){
                   i++; 
                   inteiro = isInteger(str, i, inteiro);
               } return inteiro; // Retorna valor bool de inteiro
        }
    
        public static boolean isFloat(String str, int i, boolean real, int j){

                int verifica = (int)str.charAt(i);  // Transforma o char em i em um número inteiro de acordo com o ASCII
    
                if(verifica != 46 || verifica != 44 || verifica < 48 || verifica > 57){ // Verifica se char em i é diferente de "." ou "," ou número
                    real = false; // Caso seja diferente, atribui falso ao valor bool
                } else if(verifica == 46 || verifica == 44){ // Verifica caso char for "." ou "," 
                    j++; // Soma 1 a variável que controla a ocorrência de "." e ","
                } 
            
                if(j > 1){ // Verifica se tem mais de um "." ou "," na string
                    real = false; // Se sim, atribuir falso ao valor bool
                }
    
                if(real == true && i < str.length()-1){
                    i++; 
                    real = isFloat(str, i, real, j);
                }
                
                return real;
        }
    
         public static void main(String[] args){
            String[] entrada = new String[3000]; 
            int numEntrada = 0;
            int j = 0;
            boolean verifica = true; 
            int g = 0; 
    
            do{
                entrada[numEntrada] = MyIO.readLine(); // Entada padrão
            } while (isFim(entrada[numEntrada++]) == false); // Verifica se é a condição de parada
    
            numEntrada--; 
    
            for(int i = 0; i < numEntrada; i++){
                if(isVogal(entrada[i], j, verifica) == true){ // Chama função que verifica se todos são vogais
                    MyIO.println("SIM NAO NAO NAO");
                } else if(isConso(entrada[i], j, verifica) == true){ //Chama função que verifica se todos são consoantes
                    MyIO.println("NAO SIM NAO NAO");
                } else if(isInteger(entrada[i], j, verifica) == true){ //Chama função que verifica se é um número inteiro
                    MyIO.println("NAO NAO SIM SIM");
                } else if(isFloat(entrada[i], j, verifica, g) == true){ //Chama função que verifica se é um número real
                    MyIO.println("NAO NAO NAO SIM");
                } else MyIO.println("NAO NAO NAO NAO"); //Caso todas sejam falsas, printa todas as verificações como NAO
            }
        }
}