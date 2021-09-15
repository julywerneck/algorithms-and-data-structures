public class TP04Q02 {
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
        ArvoreArvore arvore = new ArvoreArvore();

        arvore.inserir1(7);
        arvore.inserir1(3);
        arvore.inserir1(11);
        arvore.inserir1(1);
        arvore.inserir1(7);
        arvore.inserir1(5);
        arvore.inserir1(9);
        arvore.inserir1(12);
        arvore.inserir1(0);
        arvore.inserir1(2);
        arvore.inserir1(4);
        arvore.inserir1(6);
        arvore.inserir1(8);
        arvore.inserir1(10);
        arvore.inserir1(13);
        arvore.inserir1(14);

        /* Lê todos os valores de entrada até que encontre 'FIM' */
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);

        numEntrada--;
        String[] allPlayers = readArq(); // Inicializa um array de Strings com os dados do arquivo players

        for (int i = 0; i < numEntrada; i++) {
            int id = Integer.parseInt(entrada[i]);
            Jogador p = new Jogador(allPlayers[id + 1]);
            arvore.inserir2(p);
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
    public int elemento; // Conteudo do no.
    public No esq, dir; // Filhos da esq e dir.
    public No2 outro;

    /**
     * Construtor da classe.
     * 
     * @param p Conteudo do no.
     */
    public No(int elemento) {
        this.elemento = elemento;
        this.esq = null;
        this.dir = null;
        this.outro = null;
    }
}

class No2 {
    public String nome; // Conteudo do no.
    public No2 esq; // No da esquerda.
    public No2 dir; // No da direita.

    /**
     * Construtor da classe.
     * 
     * @param elemento Conteudo do no.
     */
    No2(String nome) {
        this.nome = nome;
        this.esq = this.dir = null;
    }
}

class ArvoreArvore {

    private No raiz; // Raiz da arvore.

    /**
     * Construtor da classe.
     */
    public ArvoreArvore() {
        raiz = null;
    }

    /**
     * Metodo publico iterativo para pesquisar elemento.
     * 
     * @param elemento Elemento que sera procurado.
     * @return <code>true</code> se o elemento existir, <code>false</code> em caso
     *         contrario.
     */
    public boolean pesquisar(String p) {
        return pesquisar(raiz, p);
    }

    private boolean pesquisar(No no, String p) {
        boolean resp;

        if (no == null) {
            resp = false;

        } else {
            resp = pesquisarSegundaArvore(no.outro, p);

            if (!resp) {
                System.out.print(" esq");
                resp = pesquisar(no.esq, p);

                if (!resp) {
                    System.out.print(" dir");
                    resp = pesquisar(no.dir, p);
                }
            }
        }

        return resp;
    }

    private boolean pesquisarSegundaArvore(No2 no, String x) {
        boolean resp;

        if (no == null) {
            resp = false;

        } else {

            if (x.equals(no.nome)) {
                resp = true;

            } else {
                System.out.print(" ESQ");
                resp = pesquisarSegundaArvore(no.esq, x);

                if (!resp) {
                    System.out.print(" DIR");
                    resp = pesquisarSegundaArvore(no.dir, x);
                }
            }
        }

        return resp;
    }

    /**
     * 
     * Inserir elemento na primeira arvore
     * 
     * @param num int
     */
    public void inserir1(int num) {
        raiz = inserir1(num, raiz);
    }

    public No inserir1(int num, No i) {

        if (i == null) {
            i = new No(num);

        } else if (num < i.elemento) {
            i.esq = inserir1(num, i.esq);

        } else if (num > i.elemento) {
            i.dir = inserir1(num, i.dir);
        }

        return i;
    }

    public void inserir2(Jogador p) {
        raiz = inserir2(p, raiz);
    }

    public No inserir2(Jogador p, No i) {

        if ((p.getAltura() % 15) == i.elemento) {
            i.outro = inserir3(p.getNome(), i.outro);

        } else if ((p.getAltura() % 15) < i.elemento) {
            i.esq = inserir2(p, i.esq);

        } else if ((p.getAltura() % 15) > i.elemento) {
            i.dir = inserir2(p, i.dir);

        }

        return i;
    }

    private No2 inserir3(String nome, No2 i) {

        if (i == null) {
            i = new No2(nome);
        } else if (nome.compareTo(i.nome) < 0) {
            i.esq = inserir3(nome, i.esq);

        } else if (nome.compareTo(i.nome) > 0) {
            i.dir = inserir3(nome, i.dir);

        }

        return i;
    }

    /**
     * Metodo publico iterativo para exibir elementos.
     */
    public void mostrar() {
        System.out.print("[ ");
        mostrar(raiz);
        System.out.println("]");
    }

    /**
     * Metodo privado recursivo para exibir elementos.
     * 
     * @param i No em analise.
     */
    private void mostrar(No i) {
        if (i != null) {
            mostrar(i.esq); // Elementos da esquerda.
            mostrar(i.dir); // Elementos da direita.
            System.out.print(i.elemento + " "); // Conteudo do no.
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