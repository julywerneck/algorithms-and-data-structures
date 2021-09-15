import java.util.Random;

public class tp01q04 {
	public static boolean isFim(String str) {
		return str.length() >= 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M';
	}

	public static String randomChange(String str, Random gerador) {

		char replaced = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));
		char substitute = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));

		String newStr = "";

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == replaced) {
				newStr += substitute;
			} else {
				newStr += str.charAt(i);
			}
		}

		return newStr;
	}

	public static void main(String[] args) {
		String entrada[] = new String[1000];
		int numEntrada = 0;
		Random gerador = new Random();
		gerador.setSeed(4);

		do {
			entrada[numEntrada] = MyIO.readLine();
		} while (isFim(entrada[numEntrada++]) == false);

		numEntrada--;

		for (int i = 0; i < numEntrada; i++) {
			MyIO.println(randomChange(entrada[i], gerador));
		}
		;
	}
}
