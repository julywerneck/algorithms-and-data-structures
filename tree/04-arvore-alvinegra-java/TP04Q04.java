public class TP04Q04 {
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
        Alvinegra arvore = new Alvinegra();

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
    public boolean cor;
    public Jogador jogador; // Conteudo do no.
    public No esq, dir; // Filhos da esq e dir.

    /**
     * Construtor da classe.
     * 
     * @param p Conteudo do no.
     */
    public No(Jogador p, boolean cor) {
        this.jogador = p;
        this.cor = cor;
        this.dir = null;
        this.esq = null;
    }

    /**
     * Construtor da classe.
     * 
     * @param p Conteudo do no.
     */
    public No(Jogador p) {
        this.jogador = p;
        this.cor = false;
        this.dir = null;
        this.esq = null;
    }

    /**
     * Construtor da classe.
     * 
     * @param p   Conteudo do no.
     * @param esq No da esquerda.
     * @param dir No da direita.
     */
    public No(Jogador p, No esq, No dir, boolean cor) {
        this.jogador = p;
        this.esq = esq;
        this.dir = dir;
        this.cor = cor;
    }
}

class Alvinegra {

    private No raiz; // Raiz da arvore.

    /**
     * Construtor da classe.
     */
    public Alvinegra() {
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
     * Metodo publico iterativo para exibir elementos.
     */
    public void mostrar() {
        mostrar(raiz);
    }

    /**
     * Metodo privado recursivo para exibir elementos.
     * 
     * @param i No em analise.
     */
    private void mostrar(No i) {
        if (i != null) {
            mostrar(i.esq); // Elementos da esquerda.
            System.out.print(i.jogador.getNome() + ((i.cor) ? "(p) " : "(b) \n")); // Conteudo do no.
            mostrar(i.dir); // Elementos da direita.
        }
    }

    /**
     * Metodo publico iterativo para inserir elemento.
     * 
     * @param p Personagem a ser inserido.
     * @throws Exception Se o p existir.
     */
    public void inserir(Jogador p) throws Exception {

        // Se a arvore estiver vazia
        if (raiz == null) {
            raiz = new No(p, false);

            // Senao, se a arvore tiver um personagem
        } else if (raiz.esq == null && raiz.dir == null) {
            if (raiz.jogador.getNome().compareTo(p.getNome()) > 0) {
                raiz.esq = new No(p, true);

            } else {
                raiz.dir = new No(p, true);
            }

            // Senao, se a arvore tiver dois elementos (raiz e dir)
        } else if (raiz.esq == null) {

            if (raiz.jogador.getNome().compareTo(p.getNome()) > 0) {
                raiz.esq = new No(p);

            } else if (raiz.dir.jogador.getNome().compareTo(p.getNome()) > 0) {
                raiz.esq = new No(raiz.jogador);
                raiz.jogador = p;

            } else {
                raiz.esq = new No(raiz.jogador);
                raiz.jogador = raiz.dir.jogador;
                raiz.dir.jogador = p;
            }

            raiz.esq.cor = raiz.dir.cor = false;

            // Senao, se a arvore tiver dois elementos (raiz e esq)
        } else if (raiz.dir == null) {

            if (raiz.jogador.getNome().compareTo(p.getNome()) < 0) {
                raiz.dir = new No(p);

            } else if (raiz.esq.jogador.getNome().compareTo(p.getNome()) < 0) {
                raiz.dir = new No(raiz.jogador);
                raiz.jogador = p;

            } else {
                raiz.dir = new No(raiz.jogador);
                raiz.jogador = raiz.esq.jogador;
                raiz.esq.jogador = p;
            }

            raiz.esq.cor = raiz.dir.cor = false;

            // Senao, a arvore tem tres ou mais elementos
        } else {
            inserir(p, null, null, null, raiz);
        }

        raiz.cor = false;
    }

    /**
     * Metodo privado recursivo para inserir elemento.
     * 
     * @param p   Personagem a ser inserido.
     * @param avo No em analise.
     * @param pai No em analise.
     * @param i   No em analise.
     * @throws Exception Se o elemento existir.
     */
    private void inserir(Jogador p, No bisavo, No avo, No pai, No i) throws Exception {

        if (i == null) {
            if (p.getNome().compareTo(pai.jogador.getNome()) < 0) {
                i = pai.esq = new No(p, true);
            } else {
                i = pai.dir = new No(p, true);
            }
            if (pai.cor == true) {
                balancear(bisavo, avo, pai, i);
            }
        } else {
            // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
            if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                if (i.jogador.getNome().compareTo(raiz.jogador.getNome()) == 0) {
                    i.cor = false;
                } else if (pai.cor == true) {
                    balancear(bisavo, avo, pai, i);
                }
            }

            if (p.getNome().compareTo(i.jogador.getNome()) < 0) {
                inserir(p, avo, pai, i, i.esq);
            } else if (p.getNome().compareTo(i.jogador.getNome()) > 0) {
                inserir(p, avo, pai, i, i.dir);
            } else {
                throw new Exception("Erro inserir (elemento repetido)!");
            }
        }
    }

    private void balancear(No bisavo, No avo, No pai, No i) {

        // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
        if (pai.cor == true) {
            // 4 tipos de reequilibrios e acoplamento
            if (pai.jogador.getNome().compareTo(avo.jogador.getNome()) > 0) { // rotacao a esquerda ou direita-esquerda
                if (i.jogador.getNome().compareTo(pai.jogador.getNome()) > 0) {
                    avo = rotacaoEsq(avo);
                } else {
                    avo = rotacaoDirEsq(avo);
                }

            } else { // rotacao a direita ou esquerda-direita
                if (i.jogador.getNome().compareTo(pai.jogador.getNome()) < 0) {
                    avo = rotacaoDir(avo);
                } else {
                    avo = rotacaoEsqDir(avo);
                }
            }

            if (bisavo == null) {
                raiz = avo;
            } else {
                if (avo.jogador.getNome().compareTo(bisavo.jogador.getNome()) < 0) {
                    bisavo.esq = avo;
                } else {
                    bisavo.dir = avo;
                }
            }

            // reestabelecer as cores apos a rotacao
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;

        }
    }

    private No rotacaoDir(No no) {
        No noEsq = no.esq;
        No noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }

    private No rotacaoEsq(No no) {
        No noDir = no.dir;
        No noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }

    private No rotacaoDirEsq(No no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }

    private No rotacaoEsqDir(No no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
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