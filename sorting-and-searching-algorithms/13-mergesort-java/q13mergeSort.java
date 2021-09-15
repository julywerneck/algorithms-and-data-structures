import java.util.Date;
import java.util.*;

class q13mergeSort {
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

    public static long now() {
        return new Date().getTime();
    }

    public static void main(String[] args) throws Exception {
        String[] entrada = new String[1000];
        int numEntrada = 0;
        Players[] arrayPlayer = new Players[500];
        int comparacoes = 0;
        /* Lê todos os valores de entrada até que encontre 'FIM' */
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);

        numEntrada--;
        String[] allPlayers = readArq(); // Inicializa um array de Strings com os dados do arquivo players

        for (int i = 0; i < numEntrada; i++) {
            int id = Integer.parseInt(entrada[i]);
            arrayPlayer[i] = new Players();
            arrayPlayer[i].iniciaJogador(allPlayers[id + 1]);
        }

        long inicio = now();
        mergeSort(arrayPlayer, 0, numEntrada);
        long fim = now();

        for (int u = 0; u < numEntrada; u++) {
            arrayPlayer[u].imprimir();
        }

        double tempo = (fim - inicio) / 1000.0;

        Arq.openWrite("683595_countingSort.txt", "UTF-8");
        Arq.print("Matricula : 683585\t");
        Arq.print("Tempo de execução : " + tempo + "s \t");
        Arq.print("Numero de comparações : " + comparacoes);
        Arq.close();
    }

    /* Função de faz o swap de elementos usando uma variável temporária */
    public static void swap(int menor, int i, Players[] player) {
        Players tmp = player[menor];
        player[menor] = player[i];
        player[i] = tmp;
    }

    /*
     * Algoritmo mergeSort - critério de comparação o nome do jogador e para
     * desempate o ano de nascimento
     */
    public static void mergeSort(Players[] p, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergeSort(p, esq, meio);
            mergeSort(p, meio + 1, dir);
            merge(p, esq, meio, dir);
        }
    }

    public static void merge(Players[] p, int e, int m, int d) {
        Players[] tmp = new Players[p.length];

        for (int i = 0; i < p.length; i++) {
            tmp[i] = new Players();
            tmp[i] = p[i];
        }

        int esq = e;
        int dir = m + 1;
        int pos = esq;

        while (esq <= m || dir <= d) {
            if (esq <= m && dir <= d) {
                if (tmp[esq].getUniversidade().compareTo(tmp[dir].getUniversidade()) < 0
                        || (tmp[esq].getUniversidade().compareTo(tmp[dir].getUniversidade()) == 0
                                && tmp[esq].getNome().compareTo(tmp[dir].getNome()) < 0)) {
                    p[pos] = tmp[esq];
                    esq++;
                } else {
                    p[pos] = tmp[dir];
                    dir++;
                }
            } else if (esq <= m) {
                p[pos] = tmp[esq];
                esq++;
            } else if (dir <= d) {
                p[pos] = tmp[dir];
                dir++;
            }
            pos++;
        }
    }
}

/* Classe jogador */
class Players {
    /* Atributos privados da classe jogador */
    private int id, altura, peso, anoNascimento;
    private String nome, universidade, cidadeNascimento, estadoNascimento;

    /* Construtor vazio */
    public Players() {
        this.id = this.altura = this.peso = this.anoNascimento = 0;
        this.nome = this.universidade = this.cidadeNascimento = this.estadoNascimento = "";
    }

    /* Construtor que recebe os param */
    public Players(int id, int altura, int peso, int anoNascimento, String nome, String universidade,
            String cidadeNascimento, String estadoNascimento) {
        this.id = id;
        this.altura = altura;
        this.peso = peso;
        this.anoNascimento = anoNascimento;
        this.nome = nome;
        this.universidade = universidade;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    /*
     * Gets e sets para cada atributo uma vez que os mesmos são privados
     */
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAltura() {
        return this.altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return this.peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getAnoNascimento() {
        return this.anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUniversidade() {
        return this.universidade;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public String getCidadeNascimento() {
        return this.cidadeNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public String getEstadoNascimento() {
        return this.estadoNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }

    /* Função para ler o nome da linha indicada e setar o nome no objeto */
    public void lerNome(String str) {
        int i = 0;
        String nome = "";
        int virgula = 0;

        do {
            if (str.charAt(i) == 44) {
                virgula++;
            } else if (virgula == 1) {
                nome += str.charAt(i);
            }
            i++;

        } while (virgula <= 1);

        if (nome == "") {
            nome = "nao informado";
        }

        this.nome = nome;
    }

    /* Função para ler o nome da linha indicada e setar id do objeto */
    public void lerID(String str) {
        int i = 0;
        String id = "";
        int virgula = 0;

        do {
            if (str.charAt(i) == 44) {
                virgula++;
            } else {
                id += str.charAt(i);
            }

            i++;
        } while (virgula == 0);

        this.id = Integer.parseInt(id);
    }

    /* Função para ler a altura da linha indicada e setar a altura do objeto */
    public void lerAltura(String str) {
        int i = 0;
        String altura = "";
        int virgula = 0;

        do {
            if (str.charAt(i) == 44) {
                virgula++;
            } else if (virgula == 2) {
                altura += str.charAt(i);
            }

            i++;

        } while (virgula <= 2);

        this.altura = Integer.parseInt(altura);
    }

    /* Função para ler o peso da linha indicada e setar o peso no objeto */
    public void lerPeso(String str) {
        String peso = "";
        int virgula = 0;

        for (int i = 0; virgula <= 3; i++) {

            if (str.charAt(i) == 44) {
                virgula++;
            } else if (virgula == 3) {
                peso += str.charAt(i);
            }
        }

        this.peso = Integer.parseInt(peso);
    }

    /*
     * Função para ler a universidade da linha indicada e setar a universidade do
     * jogador
     */
    public void lerUniversidade(String str) {
        String universidade = "";
        int virgula = 0;

        for (int i = 0; virgula <= 4; i++) {

            if (str.charAt(i) == 44) {
                virgula++;
            } else if (virgula == 4) {
                universidade += str.charAt(i);
            }
        }

        if (universidade == "") {
            universidade = "nao informado";
        }

        this.universidade = universidade;
    }

    /*
     * Função que lê o ano de nascimento do jogador da linha indicada e seta no
     * objeto
     */
    public void lerAnoNascimento(String str) {
        String anoNascimento = "";
        int virgula = 0;

        for (int i = 0; virgula <= 5; i++) {

            if (str.charAt(i) == 44) {
                virgula++;
            } else if (virgula == 5) {
                anoNascimento += str.charAt(i);
            }
        }

        this.anoNascimento = Integer.parseInt(anoNascimento);
    }

    /* Função para ler a cidade de nascimento do jogador e setar o dado no obj */
    public void lerCidadeNascimento(String str) {
        String cidadeNascimento = "";
        int virgula = 0;

        for (int i = 0; virgula <= 6; i++) {

            if (str.charAt(i) == 44) {
                virgula++;
            } else if (virgula == 6) {
                cidadeNascimento += str.charAt(i);
            }
        }

        if (cidadeNascimento == "") {
            cidadeNascimento = "nao informado";
        }

        this.cidadeNascimento = cidadeNascimento;
    }

    /* Função para ler o estado de nascimento do jogador e setar o mesmo no obj */
    public void lerEstadoNascimento(String str) {
        String estadoNascimento = "";
        int virgula = 0;

        for (int i = 0; virgula <= 7 && i < str.length(); i++) {

            if (str.charAt(i) == 44) {
                virgula++;
            } else if (virgula == 7) {
                estadoNascimento += str.charAt(i);
            }
        }

        if (estadoNascimento == "") {
            estadoNascimento = "nao informado";
        }

        this.estadoNascimento = estadoNascimento;
    }

    /* Clona personagem ja criado */
    protected Players Clone() {
        Players clone = new Players();
        clone.id = this.id;
        clone.nome = this.nome;
        clone.altura = this.altura;
        clone.peso = this.peso;
        clone.anoNascimento = this.anoNascimento;
        clone.universidade = this.universidade;
        clone.cidadeNascimento = this.cidadeNascimento;
        clone.estadoNascimento = this.estadoNascimento;
        return clone;
    }

    public void imprimir() {
        MyIO.println("[" + this.id + " ## " + this.nome + " ## " + this.altura + " ## " + this.peso + " ## "
                + this.anoNascimento + " ## " + this.universidade + " ## " + this.cidadeNascimento + " ## "
                + this.estadoNascimento + "]");
    }

    public void iniciaJogador(String str) {
        lerNome(str);
        lerID(str);
        lerAltura(str);
        lerPeso(str);
        lerAnoNascimento(str);
        lerUniversidade(str);
        lerCidadeNascimento(str);
        lerEstadoNascimento(str);
    }

}
