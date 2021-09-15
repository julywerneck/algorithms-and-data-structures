import java.util.Date;
import java.util.*;

class q09heapSort {
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

    public static Players[] vet = new Players[500];

    public static void main(String[] args) throws Exception {
        String[] entrada = new String[1000];
        int numEntrada = 0;
        int comparacoes = 0;
        /* Lê todos os valores de entrada até que encontre 'FIM' */
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);

        numEntrada--;
        String[] allPlayers = readArq(); // Inicializa um array de Strings com os dados do arquivo players

        for (int i = 0; i < numEntrada; i++) {
            int id = Integer.parseInt(entrada[i]);
            vet[i] = new Players();
            vet[i].iniciaJogador(allPlayers[id + 1]);
        }

        long inicio = now();
        heapSort(numEntrada);
        long fim = now();

        for (int j = 0; j < numEntrada; j++) {
            vet[j].imprimir();
        }

        double tempo = (fim - inicio) / 1000.0;

        Arq.openWrite("683595_heapSort.txt", "UTF-8");
        Arq.print("Matricula : 683585\t");
        Arq.print("Tempo de execução : " + tempo + "s \t");
        Arq.print("Numero de comparações : " + comparacoes);
        Arq.close();
    }

    /* Função de faz o swap de elementos usando uma variável temporária */
    public static void swap(int i, int menor) {
        Players tmp = vet[menor];
        vet[menor] = vet[i];
        vet[i] = tmp;
    }

    public static void heapSort(int n) {
        Players[] tmp = new Players[n + 1];
        for (int i = 0; i < n; i++) {
            tmp[i + 1] = vet[i];
        }

        vet = tmp;

        // Construção do heap
        for (int tamHeap = 2; tamHeap <= n; tamHeap++) {
            construir(tamHeap);
        }

        // Ordenação propriamente dita
        int tamHeap = n;
        while (tamHeap > 1) {
            swap(1, tamHeap--);
            reconstruir(tamHeap);
        }

        // Alterar o vetor para voltar a posição 0
        tmp = vet;
        vet = new Players[n];
        for (int i = 0; i < n; i++) {
            vet[i] = tmp[i + 1];
        }
    }

    public static void construir(int tamHeap) {
        for (int i = tamHeap; (i > 1)
                && (vet[i].getAltura() > vet[i / 2].getAltura() || vet[i].getAltura() == vet[i / 2].getAltura()
                        && vet[i].getNome().compareTo(vet[i / 2].getNome()) > 0); i /= 2) {
            swap(i, i / 2);
        }
    }

    public static void reconstruir(int tamHeap) {
        int i = 1;
        while (i <= (tamHeap / 2)) {
            int filho = getMaiorFilho(i, tamHeap);
            if (vet[i].getAltura() < vet[filho].getAltura() || (vet[i].getAltura() == vet[filho].getAltura()
                    && vet[i].getNome().compareTo(vet[filho].getNome()) < 0)) {
                swap(i, filho);
                i = filho;
            } else {
                i = tamHeap;
            }
        }
    }

    public static int getMaiorFilho(int i, int tamHeap) {
        int filho;
        if (2 * i == tamHeap || vet[2 * i].getAltura() > vet[2 * i + 1].getAltura()) {
            filho = 2 * i;
        } else if (vet[2 * i].getAltura() < vet[2 * i + 1].getAltura()) {
            filho = 2 * i + 1;
        } else if (vet[2 * i].getAltura() == vet[2 * i + 1].getAltura()
                && vet[2 * i].getNome().compareTo(vet[2 * i + 1].getNome()) > 0) {
            filho = 2 * i;
        } else {
            filho = 2 * i + 1;
        }

        return filho;
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
