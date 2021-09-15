import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;

public class TP5Q03 {
    // Verifica se e ou nao o das entradas
    public static boolean isFim(String s) {
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

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
        Hash hash = new Hash(25);

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
            hash.inserirInicio(p);
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

class Hash {
    Lista tabela[];
    int tamanho;
    final Jogador NULO = new Jogador("0,a,0,0,a,0,a,a");

    public Hash() {
        this(7);
    }

    public Hash(int tamanho) {
        this.tamanho = tamanho;
        tabela = new Lista[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new Lista();
        }
    }

    public int h(int elemento) {
        return elemento % tamanho;
    }

    public boolean pesquisar(String elemento) {
        boolean resp = false;

        for (int i = 0; i < tamanho; i++) {
            if (tabela[i].pesquisar(elemento) == true) {
                resp = true;
            }
        }

        return resp;
    }

    public void inserirInicio(Jogador elemento) {
        int pos = h(elemento.getAltura());
        tabela[pos].inserirInicio(elemento);
    }
}

class Celula {
    public Jogador element; // Elemento inserido na celula
    public Celula prox; // Aponta celula prox

    /**
     * Construtor da classe
     */

    public Celula() {
        this(null);
    }

    /**
     * Construtor da classe
     * 
     * @param elemento Player inserido na celula
     */
    public Celula(Jogador p) {
        this.element = p;
        this.prox = null;
    }
}

class Lista {
    private Celula primeiro;
    private Celula ultimo;

    /**
     * Construtor da classe que cria uma lista sem elementos (somente no cabeça)
     */
    Lista() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    /**
     * Insere um elemento na primeira posicao da lista
     * 
     * @param Player p elemento a ser inserido
     */
    public void inserirInicio(Jogador plr) {
        Celula tmp = new Celula(plr);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        }

        tmp = null;
    }

    public void imprimeLista() {
        Celula j = primeiro.prox;
        for (int i = 0; j != null; j = j.prox) {
            MyIO.print("[" + i + "] ");
            j.element.imprimir();
            i++;
        }
    }

    /**
     * Procura um elemento e retorna se ele existe.
     * 
     * @param x Elemento a pesquisar.
     * @return <code>true</code> se o elemento existir, <code>false</code> em caso
     *         contrario.
     */
    public boolean pesquisar(String x) {
        boolean retorno = false;
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            if (i.element.getNome().equals(x)) {
                retorno = true;
                i = ultimo;
            }
        }
        return retorno;
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