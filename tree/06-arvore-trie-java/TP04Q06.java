public class TP04Q06 {
    /* Verifica se String recebida é igual a 'FIM' */

    public static boolean isFim(String str) {
        return (str.length() >= 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M');
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
        String[] entrada = new String[1000];
        int numEntrada = 0;
        Jogador[] player = new Jogador[300];
        Trie arvoreMerge = new Trie();
        Trie arvore1 = new Trie();

        /* Lê todos os valores de entrada até que encontre 'FIM' */
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);

        numEntrada--;
        String[] allPlayers = readArq(); // Inicializa um array de Strings com os dados do arquivo players

        for (int i = 0; i < numEntrada; i++) {
            int id = Integer.parseInt(entrada[i]);
            Jogador p = new Jogador(allPlayers[id + 1]);
            arvore1.inserir(p.getNome());
        }

        arvore1.mostrar();

        String[] entrada2 = new String[1000];
        numEntrada = 0;
        Trie arvore2 = new Trie();

        // Leitura da entrada padrao
        do {
            entrada2[numEntrada] = MyIO.readLine();
        } while (isFim(entrada2[numEntrada++]) == false);
        numEntrada--; // Desconsiderar ultima linha contendo a palavra FIM

        // Para cada linha de entrada, cria um personagem com o caminho lido e o insere
        // na lista
        for (int i = 0; i < numEntrada; i++) {
            int id = Integer.parseInt(entrada[i]);
            Jogador p = new Jogador(allPlayers[id + 1]);
            arvore2.inserir(p.getNome());
        }

        arvore2.mostrar();

        String[] pesquisa = new String[1000];
        int numPes = 0;

        // Leitura da entrada padrao
        do {
            pesquisa[numPes] = MyIO.readLine();
        } while (isFim(pesquisa[numPes++]) == false);
        numPes--; // Desconsiderar ultima linha contendo a palavra FIM

        for (int i = 0; i < numPes; i++) {
            boolean resp = false;

            resp = arvore1.pesquisar(pesquisa[i]);
            if (resp == false) {
                resp = arvore2.pesquisar(pesquisa[i]);
            }

            System.out.print(pesquisa[i] + " ");
            if (resp == true)
                MyIO.print("SIM\n");
            else
                MyIO.print("NAO\n");

        }

    }
}

class No {
    public char elemento;
    public int tamanho = 255;
    public No[] prox;
    public boolean folha;

    public No() {
        this(' ');
    }

    public No(char elemento) {
        this.elemento = elemento;

        prox = new No[tamanho];

        for (int i = 0; i < tamanho; i++) {
            prox[i] = null;
        }

        folha = false;
    }

    public static int hash(char x) {
        return (int) x;
    }
}

class Trie {

    private No raiz;

    public Trie() {
        raiz = new No();
    }

    public void inserir(String s) throws Exception {
        inserir(s, raiz, 0);
    }

    public void inserir(String s, No no, int i) throws Exception {

        if (no.prox[s.charAt(i)] == null) {
            no.prox[s.charAt(i)] = new No(s.charAt(i));

            if (i == s.length() - 1) {
                no.prox[s.charAt(i)].folha = true;

            } else {
                inserir(s, no.prox[s.charAt(i)], i + 1);
            }

        } else if (no.prox[s.charAt(i)].folha == false && i < s.length() - 1) {
            inserir(s, no.prox[s.charAt(i)], i + 1);

        } else {
            throw new Exception("Erro ao inserir!");
        }
    }

    public boolean pesquisar(String s) throws Exception {
        return pesquisar(s, raiz, 0);
    }

    public boolean pesquisar(String s, No no, int i) throws Exception {

        boolean resp;

        if (no.prox[s.charAt(i)] == null) {
            resp = false;
        } else if (i == s.length() - 1) {
            resp = (no.prox[s.charAt(i)].folha == true);
        } else if (i < s.length() - 1) {
            resp = pesquisar(s, no.prox[s.charAt(i)], i + 1);
        } else {
            throw new Exception("Erro ao pesquisar!");
        }

        return resp;
    }

    public void mostrar() {
        mostrar("", raiz);
    }

    public void mostrar(String s, No no) {
        if (no.folha == true) {
            System.out.println("Palavra: " + (s + no.elemento));
        } else {
            for (int i = 0; i < no.prox.length; i++) {
                if (no.prox[i] != null) {
                    mostrar(s + no.elemento, no.prox[i]);
                }
            }
        }
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