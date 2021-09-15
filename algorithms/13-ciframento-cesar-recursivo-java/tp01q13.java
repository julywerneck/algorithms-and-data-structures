public class tp01q13 {
    public static boolean isFim(String s) { // Verifica se string é igual a FIM
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void chamaCripPalavra(String s) {
        int tam = s.length();
        int i = 0;
        String crip = "";

        crip = criptografaPalavra(s, tam, i, crip);

        MyIO.println(crip);
    }

    public static String criptografaPalavra(String str, int tam, int i, String crip) {

        if (i < tam) {
            int letra = str.charAt(i) + 3;
            crip = crip + (char) letra;
            return criptografaPalavra(str, tam, i + 1, crip);
        } else
            return crip;

    }

    public static void main(String[] args) {
        String[] entrada = new String[3000];
        int numEntrada = 0;

        do {
            entrada[numEntrada] = MyIO.readLine(); // Entrada padrão
        } while (isFim(entrada[numEntrada++]) == false); // Verifica se não é a condição de parada

        numEntrada--; // Descresce em um para retirar o 'FIM'

        for (int i = 0; i < numEntrada; i++) {
            chamaCripPalavra(entrada[i]);
        }
    }
}