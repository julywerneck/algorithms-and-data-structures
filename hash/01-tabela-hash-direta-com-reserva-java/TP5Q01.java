import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;

public class TP5Q01 {
    // Verifica se e ou nao o das entradas
    public static boolean isFim(String s) {
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    /*
     * public static Jogador criaJogador(String s) { Jogador j = new Jogador(s);
     * 
     * return j; }
     */

    /*
     * Abre o arquivo .csv que contém os jogadores e faz leitura linha por linha
     * armazenando em um array de Strings
     */
    public static String[] readArq() {
        String[] arquivo = new String[4000];
        Arq.openRead("/tmp/players.csv");

        for (int i = 0; i < 3921; i++) {
            arquivo[i] = Arq.readLine();
        }

        Arq.close();

        return arquivo;
    }

    public static void main(String[] args) throws Exception {

        MyIO.setCharset("UTF-8");
        String[] entrada = new String[1000];
        int numEntrada = 0;
        Hash hash = new Hash(21, 9);

        // Leitura da entrada padrao
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--; // Desconsiderar ultima linha contendo a palavra FIM

        String[] todos = readArq(); // Inicializa um array de Strings com os dados do arquivo players

        // Para cada linha de entrada, cria um personagem com o caminho lido e o insere
        // no hash
        for (int i = 0; i < numEntrada; i++) {
            int id = Integer.parseInt(entrada[i]);
            Jogador p = new Jogador(todos[id + 1]);
            hash.inserir(p);
        }

        String[] pesquisa = new String[1000];
        int numpes = 0;

        // Leitura da entrada padrao
        do {
            pesquisa[numpes] = MyIO.readLine();
        } while (isFim(pesquisa[numpes++]) == false);
        numpes--; // Desconsiderar ultima linha contendo a palavra FIM

        for (int i = 0; i < numpes; i++) {

            MyIO.print(pesquisa[i]);
            boolean resp = hash.pesquisar(pesquisa[i]);

            if (resp == true)
                MyIO.print(" SIM\n");
            else
                MyIO.print(" NAO\n");
        }
    }
}

/**
 * Classe Hash
 */
class Hash {
    Jogador tabela[]; // tabela propriamente dita
    int m1, // area principal
            m2, // area reserva
            m, // area total
            reserva; // contador de reserva
    Jogador NULO = new Jogador("0,a,0,0,a,0,a,a");

    public Hash() {
        this(21, 9);
    }

    /**
     * Construtor da classe Hash
     * 
     * @param m1 Area principal
     * @param m2 Area reserva
     */
    public Hash(int m1, int m2) {
        this.m1 = m1;
        this.m2 = m2;
        this.m = m1 + m2;
        this.tabela = new Jogador[this.m];

        for (int i = 0; i < m; i++) {
            tabela[i] = NULO;
        }

        reserva = 0;
    }

    /**
     * Retorna a posição na tabela
     * 
     * @param altura altura do personagem
     * @return int posição na tabela
     */
    public int h(int altura) {
        return altura % m1;
    }

    /**
     * Mostra os elementos da tabela
     */
    public void mostrar() {

        for (int i = 0; i < m; i++) {
            tabela[i].imprimir();
        }
    }

    /**
     * Inserir um Personagem na tabela Hash
     * 
     * @param elemento Personagem a ser inserido
     * @return booleano confirmação de inserção
     */
    public boolean inserir(Jogador elemento) {
        boolean resp = false;

        if (elemento.getNome() != NULO.getNome()) {

            int pos = h(elemento.getAltura());

            if (tabela[pos] == NULO) {
                tabela[pos] = elemento;
                resp = true;

            } else if (reserva < m2) {
                tabela[m1 + reserva] = elemento;
                reserva++;
                resp = true;
            }
        }

        return resp;
    }

    /**
     * Pesquisar um nome na tabela Hash
     * 
     * @param nome Nome de pesquisa no Hash
     * @return resp booleano com resultado da busca
     */
    public boolean pesquisar(String nome) {

        boolean resp = false;

        for (int i = 0; i < m; i++) {
            if (tabela[i].getNome().equals(nome)) {
                resp = true;
            }
        }

        return resp;
    }
}

class Jogador {
    int id;
    String nome;
    int altura;
    int peso;
    String universidade;
    int anoNascimento;
    String cidadeNascimento;
    String estadoNascimento;

    public Jogador() {
    }

    public Jogador(String linha) {
        String campos[] = linha.split(",");
        this.id = Integer.parseInt(campos[0]);
        this.nome = campos[1];
        this.altura = Integer.parseInt(campos[2]);
        this.peso = Integer.parseInt(campos[3]);
        this.universidade = (campos[4].isEmpty()) ? "nao informado" : campos[4];
        this.anoNascimento = Integer.parseInt(campos[5]);
        if (campos.length > 6) {
            this.cidadeNascimento = (campos[6].isEmpty()) ? "nao informado" : campos[6];
            if (campos.length < 8) {
                this.estadoNascimento = "nao informado";
            } else {
                this.estadoNascimento = campos[7];
            }
        } else {
            this.cidadeNascimento = "nao informado";
            this.estadoNascimento = "nao informado";
        }
    }

    // id,Player,height,weight,collage,born,birth_city,birth_state
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public String getUniversidade() {
        return universidade;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public String getEstadoNascimento() {
        return estadoNascimento;
    }

    public void setEstadoNascimetno(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }

    public Jogador clone() {
        Jogador novo = new Jogador();
        novo.id = this.id;
        novo.nome = this.nome;
        novo.altura = this.altura;
        novo.peso = this.peso;
        novo.universidade = this.universidade;
        novo.cidadeNascimento = this.cidadeNascimento;
        novo.estadoNascimento = this.estadoNascimento;
        return novo;
    }

    public void imprimir() {
        System.out.println("## " + nome + " ## " + altura + " ## " + peso + " ## " + anoNascimento + " ## "
                + universidade + " ## " + cidadeNascimento + " ## " + estadoNascimento + " ##");
    }

    public String toString() {
        return "[" + id + " ## " + nome + " ## " + altura + " ## " + peso + " ## " + anoNascimento + " ## "
                + universidade + " ## " + cidadeNascimento + " ## " + estadoNascimento + "]";
    }
}