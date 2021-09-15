public class TP04Q01 {
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
        ArvoreBinaria arvore = new ArvoreBinaria();

        /* Lê todos os valores de entrada até que encontre 'FIM' */
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);

        numEntrada--;
        String[] allPlayers = readArq(); // Inicializa um array de Strings com os dados do arquivo players

        for (int i = 0; i < numEntrada; i++) {
            int id = Integer.parseInt(entrada[i]);
            Jogador p = new Jogador(allPlayers[id + 1]);
            arvore.inserir(p);
        }

        String[] pesquisa = new String[1000];
        int numPes = 0;

        // Leitura da entrada padrao
        do {
            pesquisa[numPes] = MyIO.readLine();
        } while (isFim(pesquisa[numPes++]) == false);
        numPes--; // Desconsiderar ultima linha contendo a palavra FIM

        for (int i = 0; i < numPes; i++) {

            boolean resp = arvore.pesquisar(pesquisa[i]);

            if (resp == true)
                MyIO.print("SIM\n");
            else
                MyIO.print("NAO\n");

        }

    }
}

/**
 * No da arvore binaria
 * 
 * @author Max do Val Machado
 */
class No {
    public Jogador jogador; // Conteudo do no.
    public No esq, dir; // Filhos da esq e dir.

    /**
     * Construtor da classe.
     * 
     * @param p Conteudo do no.
     */
    public No(Jogador j) {
        this(j.clone(), null, null);
    }

    /**
     * Construtor da classe.
     * 
     * @param p   Conteudo do no.
     * @param esq No da esquerda.
     * @param dir No da direita.
     */
    public No(Jogador j, No esq, No dir) {
        this.jogador = j.clone();
        this.esq = esq;
        this.dir = dir;
    }
}

class ArvoreBinaria {

    private No raiz; // Raiz da arvore.

    /**
     * Construtor da classe.
     */
    public ArvoreBinaria() {
        raiz = null;
    }

    /**
     * Metodo publico iterativo para pesquisar elemento.
     * 
     * @param nome String nome que sera procurado.
     * @return true se o elemento existir, false em caso contrario.
     */
    public boolean pesquisar(String nome) {
        MyIO.print(nome + " raiz ");
        return pesquisar(nome, raiz);
    }

    /**
     * Metodo privado recursivo para pesquisar nome.
     * 
     * @param nome String nome que sera procurado.
     * @param i    No em analise.
     * @return true se o elemento existir, false em caso contrario.
     */
    private boolean pesquisar(String nome, No i) {

        boolean resp;

        if (i == null) {
            resp = false;

        } else if (nome.compareTo(i.jogador.getNome()) == 0) {
            resp = true;

        } else if (nome.compareTo(i.jogador.getNome()) < 0) {
            MyIO.print("esq ");
            resp = pesquisar(nome, i.esq);

        } else {
            MyIO.print("dir ");
            resp = pesquisar(nome, i.dir);
        }
        return resp;
    }

    /**
     * Metodo publico iterativo para inserir elemento.
     * 
     * @param p Personagem a ser inserido.
     * @throws Exception Se o elemento existir.
     */
    public void inserir(Jogador p) throws Exception {
        raiz = inserir(p, raiz);
    }

    /**
     * Metodo privado recursivo para inserir elemento.
     * 
     * @param p Personagem a ser inserido.
     * @param i No em analise.
     * @return No em analise, alterado ou nao.
     * @throws Exception Se o elemento existir.
     */
    private No inserir(Jogador p, No i) throws Exception {

        if (i == null) {
            i = new No(p);

        } else if (p.getNome().compareTo(i.jogador.getNome()) < 0) {
            i.esq = inserir(p, i.esq);

        } else if (p.getNome().compareTo(i.jogador.getNome()) > 0) {
            i.dir = inserir(p, i.dir);

        } else {
            throw new Exception("Erro ao inserir!");
        }

        return i;
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