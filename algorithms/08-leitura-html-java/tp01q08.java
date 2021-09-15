import java.io.*;
import java.net.*;

public class tp01q08 {

    public static boolean isFim(String s) { // Verifica se string é igual a FIM
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static String getHtml(String endereco) {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String resp = "", line;

        try {
            url = new URL(endereco);
            is = url.openStream(); // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                resp += line + "\n";
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            is.close();
        } catch (IOException ioe) {
            // nothing to see here

        }

        return resp;
    }

    public static void verificaVogais(String html) {
        int tam = html.length();
        int a = 0;
        int e = 0;
        int i = 0;
        int o = 0;
        int u = 0;

        for (int j = 0; j < tam; j++) {
            if (html.charAt(j) == 'a') { // Verifica ocorrencia de vogais
                a++;
            } else if (html.charAt(j) == 'e') {
                e++;
            } else if (html.charAt(j) == 'i') {
                i++;
            } else if (html.charAt(j) == 'o') {
                o++;
            } else if (html.charAt(j) == 'u') {
                u++;
            }
        }

        a = a - verificaTable(html); // Diminui um a para cada <table>
        e = e - verificaTable(html); // Diminui um e para cada <table>

        MyIO.print("a(" + a + ") e(" + e + ") i(" + i + ") o(" + o + ") u(" + u + ") ");

    }

    public static void verificaVogaisAcent(String html) {
        int tam = html.length();
        int a1 = 0, a2 = 0, a3 = 0, a4 = 0;
        int e1 = 0, e2 = 0, e3 = 0;
        int i1 = 0, i2 = 0, i3 = 0;
        int o1 = 0, o2 = 0, o3 = 0, o4 = 0;
        int u1 = 0, u2 = 0, u3 = 0;

        for (int j = 0; j < tam; j++) { // Verifica ocorrencia de vogais com acentos
            if (html.charAt(j) == '\u00E0') { // à
                a1++;
            } else if (html.charAt(j) == '\u00E1') { // á
                a2++;
            } else if (html.charAt(j) == '\u00E2') { // â
                a3++;
            } else if (html.charAt(j) == '\u00E3') { // ã
                a4++;
            } else if (html.charAt(j) == '\u00E8') { // è
                e1++;
            } else if (html.charAt(j) == '\u00E9') { // é
                e2++;
            } else if (html.charAt(j) == '\u00EA') { // ê
                e3++;
            } else if (html.charAt(j) == '\u00EC') { // ì
                i1++;
            } else if (html.charAt(j) == '\u00ED') { // í
                i2++;
            } else if (html.charAt(j) == '\u00EE') { // î
                i3++;
            } else if (html.charAt(j) == '\u00F2') { // ò
                o1++;
            } else if (html.charAt(j) == '\u00F3') { // ó
                o2++;
            } else if (html.charAt(j) == '\u00F4') { // ô
                o3++;
            } else if (html.charAt(j) == '\u00F5') {// õ
                o4++;
            } else if (html.charAt(j) == '\u00F9') { // ù
                u1++;
            } else if (html.charAt(j) == '\u00FA') { // ú
                u2++;
            } else if (html.charAt(j) == '\u00FB') { // û
                u3++;
            }
        }

        MyIO.print("á(" + a2 + ") é(" + e2 + ") í(" + i2 + ") ó(" + o2 + ") ú(" + u2 + ") ");
        MyIO.print("à(" + a1 + ") è(" + e1 + ") ì(" + i1 + ") ò(" + o1 + ") ù(" + u1 + ") ");
        MyIO.print("ã(" + a4 + ") õ(" + o4 + ") ");
        MyIO.print("â(" + a3 + ") ê(" + e3 + ") î(" + i3 + ") ô(" + o3 + ") û(" + u3 + ") ");

    }

    public static void verificaConsoante(String html) {
        int tam = html.length();
        int conso = 0;

        for (int i = 0; i < tam; i++) {
            // Verifica ocorrencia de consoantes minusculas
            if ((html.charAt(i) > 'a' && html.charAt(i) <= 'z') && (html.charAt(i) != 'e' && html.charAt(i) != 'i'
                    && html.charAt(i) != 'o' && html.charAt(i) != 'u')) {
                conso++;
            }
        }

        int a = verificaBr(html);
        int b = verificaTable(html);

        if (a > 0) {
            conso = conso - a * (2); // Diminui a quantidade de consoantes em 2 para cada br
        }

        if (b > 0) {
            conso = conso - b * (3); // Diminui a quantidade de consoantes em 3 para cada table
        }

        MyIO.print("consoante(" + conso + ") ");
    }

    public static int verificaBr(String html) {
        int tam = html.length();
        int br = 0;

        for (int i = 0; i < tam; i++) { // Verifica ocorrencia de <br>
            if (html.charAt(i) == 60 && html.charAt(i + 1) == 98 && html.charAt(i + 2) == 114
                    && html.charAt(i + 2) == 62) {
                br++;
            }
        }

        return br;
    }

    public static int verificaTable(String html) {
        int tam = html.length();
        int table = 0;

        for (int i = 0; i < tam; i++) { // Verifica a ocorrencia de <table>
            if (html.charAt(i) == 60 && html.charAt(i + 1) == 116 && html.charAt(i + 2) == 97
                    && html.charAt(i + 3) == 98 && html.charAt(i + 4) == 108 && html.charAt(i + 5) == 101
                    && html.charAt(i + 6) == 62) {
                table++;
            }
        }

        return table;
    }

    public static void main(String[] args) {
        String html;
        String[] entrada = new String[3000];
        int numEntrada = 0;
        String nome;

        do {
            entrada[numEntrada] = MyIO.readLine(); // Entada padrão
        } while (isFim(entrada[numEntrada++]) == false); // Verifica se é a condição de parada

        numEntrada--;

        for (int i = 0; i < numEntrada; i++) {
            nome = entrada[i];
            i++;
            html = getHtml(entrada[i]);
            verificaVogais(html);
            verificaVogaisAcent(html);
            verificaConsoante(html);
            int br = verificaBr(html);
            int table = verificaTable(html);
            MyIO.print("<br>(" + br + ") ");
            MyIO.print("<table>(" + table + ") ");
            MyIO.print(nome);
            MyIO.println("");
        }
    }
}
