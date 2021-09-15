public class Q01LS {

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
        Players[] player = new Players[300];
        String[] comandos = new String[300];
        Players[] plrComando = new Players[300];
        int tamComandos = 0;

        /* Lê todos os valores de entrada até que encontre 'FIM' */
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);

        numEntrada--;
        String[] allPlayers = readArq(); // Inicializa um array de Strings com os dados do arquivo players

        Lista lista = new Lista(300); // Cria lista

        for (int i = 0; i < numEntrada; i++) { 
            player[i] = new Players();
            int id = Integer.parseInt(entrada[i]);
            player[i].iniciaPlayer(allPlayers, id);
            lista.inserirFim(player[i]);
        }

        tamComandos = MyIO.readInt(); 

        for(int j = 0; j < tamComandos; j++) {
            comandos[j] = MyIO.readLine(); 
            plrComando[j] = new Players(); 

            if(comandos[j].charAt(0) == 'I') {
                int id = getID(comandos[j]);
                plrComando[j].iniciaPlayer(allPlayers, id);

                if(comandos[j].charAt(1) == '*') {
                    int pos1 = getPosicao(comandos[j]); 
                    lista.inserir(plrComando[j], pos1);
                } else if(comandos[j].charAt(1) == 'I') {
                    lista.inserirInicio(plrComando[j]);
                } else if(comandos[j].charAt(1) == 'F') {
                    lista.inserirFim(plrComando[j]);
                }
            } else if(comandos[j].charAt(0) == 'R' && comandos[j].charAt(1) == '*') {
                int pos = getPosicao(comandos[j]);
                lista.remover(pos);
            } else if(comandos[j].charAt(0) == 'R' && comandos[j].charAt(1) == 'F') {
                lista.removerFim(); 
            } else if(comandos[j].charAt(0) == 'R' && comandos[j].charAt(1) == 'I') {
                lista.removerInicio();
            }
        }

        lista.imprimeLista(); 
    }

    public static int getPosicao(String str){
        String pos = ""; 

        for(int i = 3; i < 5; i++) {
            pos += str.charAt(i);
        }

        int resp = Integer.parseInt(pos);

        return resp;
    }

    public static int getID(String str) {
        String[] divide = new String[2]; 

        divide = str.split(" ");

        int resp = Integer.parseInt(divide[1]); 

        return resp; 
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
        MyIO.println("## " + this.nome + " ## " + this.altura + " ## " + this.peso + " ## "
                + this.anoNascimento + " ## " + this.universidade + " ## " + this.cidadeNascimento + " ## "
                + this.estadoNascimento + " ##");
    }

    public void iniciaPlayer(String[] allPlayers, int id) {
        lerNome(allPlayers[id + 1]);
        lerID(allPlayers[id + 1]);
        lerAltura(allPlayers[id + 1]);
        lerPeso(allPlayers[id + 1]);
        lerUniversidade(allPlayers[id + 1]);
        lerAnoNascimento(allPlayers[id + 1]);
        lerCidadeNascimento(allPlayers[id + 1]);
        lerEstadoNascimento(allPlayers[id + 1]);
    }
}

class Lista {
    private Players[] array; 
    private int n; 

    Lista(int tam) {
        this.array = new Players[tam];
        this.n = 0;
    } 

    Lista() {
        this.array = new Players[300];
        this.n = 0; 
    }

    public void inserirFim(Players plr) throws Exception {
        if(n >= array.length) {
            throw new Exception("Erro ao inserir");
        }

        array[this.n] = plr;
        n++;
    } 

    public void inserirInicio(Players plr) throws Exception {
        if(n >= array.length) {
            throw new Exception("Erro ao inserir");
        } 

        for(int i = this.n; i > 0; i--) {
            this.array[i] = this.array[i-1];
        }

        this.array[0] = plr; 
        this.n++;
    } 

    public void inserir(Players plr, int pos) throws Exception {
        if(n >= array.length || pos < 0 || pos > this.n) {
            throw new Exception("Erro ao inserir");
        } 

        for(int i = this.n; i > pos; i--) {
            this.array[i] = this.array[i-1]; 
        } 

        this.array[pos] = plr; 
        this.n++;
    }

    public void removerInicio() throws Exception {
        if(n == 0) {
            throw new Exception("Erro ao remover!"); 
        } 

        Players resp = this.array[0]; 
        this.n--; 

        for(int i = 0; i < this.n; i++) {
            this.array[i] = this.array[i+1];
        } 

        MyIO.println("(R) " + resp.getNome()); 
    } 

    public void removerFim() throws Exception {
        if(n == 0) {
            throw new Exception("Erro ao remover!");
        }

        MyIO.println("(R) " + this.array[--this.n].getNome()); 
    } 

    public void remover(int pos) throws Exception { 
        if(pos == 0 || pos < 0 || pos >= n) {
            throw new Exception("Erro ao remover!");
        } 

        Players resp = this.array[pos]; 
        this.n--; 

        for(int i = pos; i < this.n; i++) {
            this.array[i] = this.array[i+1]; 
        } 

        MyIO.println("(R) " + resp.getNome());
    } 

    public void imprimeLista() {
        for(int i = 0; i < this.n; i++) {
            MyIO.print("[" + i + "] ");
            this.array[i].imprimir();
        }
    }
}
