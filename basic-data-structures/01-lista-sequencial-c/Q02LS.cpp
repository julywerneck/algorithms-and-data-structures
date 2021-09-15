#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>

#define tam 1000
/**
 * Cria struct do jador
*/
typedef struct
{
    int id;
    int altura;
    int peso;
    int anoNascimento;
    char universidade[tam];
    char cidadeNascimento[tam];
    char estadoNascimento[tam];
    char nome[tam];
} Player;

/**
 * Seta os atributos do mesmo verificando o tipo do dado no caso de inteiros
 * @param info string que contém o dado do nome
 * @param *p jogador a ser inicializado
*/
void setNome(char info[], Player *p)
{
    strcpy(p->nome, info);
}
/**
 * Seta os atributo verificando o tipo do dado no caso de inteiros
 * @param info string que contém o dado do id
 * @param *p jogador a ser inicializado
*/
void setId(char info[], Player *p)
{
    int id;
    id = atoi(info);
    p->id = id;
}
/**
 * Seta os atributo verificando o tipo do dado no caso de inteiros
 * @param info string que contém o dado da altura
 * @param *p jogador a ser inicializado
*/
void setAltura(char info[], Player *p)
{
    int altura;
    altura = atoi(info);
    p->altura = altura;
}
/**
 * Seta os atributo verificando o tipo do dado no caso de inteiros
 * @param info string que contém o dado da peso
 * @param *p jogador a ser inicializado
*/
void setPeso(char info[], Player *p)
{
    int peso;
    peso = atoi(info);
    p->peso = peso;
}
/**
 * Seta os atributo verificando o tipo do dado no caso de inteiros
 * @param info string que contém o dado do ano de nascimento
 * @param *p jogador a ser inicializado
*/
void setAnoNascimento(char info[], Player *p)
{
    int anoNascimento;
    anoNascimento = atoi(info);
    p->anoNascimento = anoNascimento;
}
/**
 * Seta os atributo de acordo com o valor recebido
 * @param info string que contém o dado da unviversidade
 * @param *p jogador a ser inicializado
*/
void setUniversidade(char info[], Player *p)
{
    char universidade[60];

    /**
     * verifica se dado recebido é diferente de nenhum e seta o valor padrão 
     * caso seja
    */
    if (strcmp("", info) == 0)
    {
        strcpy(p->universidade, "nao informado");
    }
    else
    {
        strcpy(p->universidade, info);
    }
}
/**
 * Seta os atributo de acordo com o valor recebido
 * @param info string que contém o dado da cidade de nascimento
 * @param *p jogador a ser inicializado
*/
void setCidadeNascimento(char info[], Player *p)
{
    char cidadeNascimento[50];

    /**
     * verifica se dado recebido é diferente de nenhum e seta o valor padrão 
     * caso seja
    */
    if (strcmp("", info) == 0)
    {
        strcpy(p->cidadeNascimento, "nao informado");
    }
    else
    {
        strcpy(p->cidadeNascimento, info);
    }
}
/**
 * Seta os atributo de acordo com o valor recebido
 * @param info string que contém o dado do estado de nascimento
 * @param *p jogador a ser inicializado
*/
void setEstadoNascimento(char info[], Player *p)
{
    char estadoNascimento[50];

    /**
     * verifica se dado recebido é diferente de nenhum e seta o valor padrão 
     * caso seja
    */
    if (strcmp("", info) == 0)
    {
        strcpy(p->estadoNascimento, "nao informado");
    }
    else
    {
        strcpy(p->estadoNascimento, info);
    }
}
/**
 * Procura o valor referente na string com todos os dados recebida
 * @param str string que contém todos os dados do jogador
 * @param *p jogador a ser inicializado
*/
void lerNome(char str[], Player *p)
{
    int virgula = 0;
    char nome[50] = {0};
    int j = 0;

    for (int i = 0; virgula <= 1; i++)
    {
        if (str[i] == 44)
        {
            virgula++;
        }
        else if (virgula == 1)
        {
            nome[j] = str[i];
            j++;
        }
    }

    setNome(nome, p);
}
/**
 * Procura o valor referente na string com todos os dados recebida
 * @param str string que contém todos os dados do jogador
 * @param *p jogador a ser inicializado
*/
void lerID(char str[], Player *p)
{
    int virgula = 0;
    int j = 0;
    char id[50] = {0};

    for (int i = 0; virgula == 0; i++)
    {
        if (str[i] == 44)
        {
            virgula++;
        }
        else
        {
            id[j] = str[i];
            j++;
        }
    }

    setId(id, p);
}
/**
 * Procura o valor referente na string com todos os dados recebida
 * @param str string que contém todos os dados do jogador
 * @param *p jogador a ser inicializado
*/
void lerAltura(char str[], Player *p)
{
    int virgula = 0;
    char altura[50] = {0};
    int j = 0;

    for (int i = 0; virgula <= 2; i++)
    {
        if (str[i] == 44)
        {
            virgula++;
        }
        else if (virgula == 2)
        {
            altura[j] = str[i];
            j++;
        }
    }

    setAltura(altura, p);
}
/**
 * Procura o valor referente na string com todos os dados recebida
 * @param str string que contém todos os dados do jogador
 * @param *p jogador a ser inicializado
*/
void lerPeso(char str[], Player *p)
{
    int virgula = 0;
    char peso[50] = {0};
    int j = 0;

    for (int i = 0; virgula <= 3; i++)
    {
        if (str[i] == 44)
        {
            virgula++;
        }
        else if (virgula == 3)
        {
            peso[j] = str[i];
            j++;
        }
    }

    setPeso(peso, p);
}
/**
 * Procura o valor referente na string com todos os dados recebida
 * @param str string que contém todos os dados do jogador
 * @param *p jogador a ser inicializado
*/
void lerUniversidade(char str[], Player *p)
{
    int virgula = 0;
    char universidade[50] = {0};
    int j = 0;

    for (int i = 0; virgula <= 4; i++)
    {
        if (str[i] == 44)
        {
            virgula++;
        }
        else if (virgula == 4)
        {
            universidade[j] = str[i];
            j++;
        }
    }

    setUniversidade(universidade, p);
}
/**
 * Procura o valor referente na string com todos os dados recebida
 * @param str string que contém todos os dados do jogador
 * @param *p jogador a ser inicializado
*/
void lerAnoNascimento(char str[], Player *p)
{
    int virgula = 0;
    char anoNascimento[50] = {0};
    int j = 0;

    for (int i = 0; virgula <= 5; i++)
    {
        if (str[i] == 44)
        {
            virgula++;
        }
        else if (virgula == 5)
        {
            anoNascimento[j] = str[i];
            j++;
        }
    }

    setAnoNascimento(anoNascimento, p);
}
/**
 * Procura o valor referente na string com todos os dados recebida
 * @param str string que contém todos os dados do jogador
 * @param *p jogador a ser inicializado
*/
void lerCidadeNascimento(char str[], Player *p)
{
    int virgula = 0;
    char cidadeNascimento[50] = {0};
    int j = 0;

    for (int i = 0; virgula <= 6; i++)
    {
        if (str[i] == 44)
        {
            virgula++;
        }
        else if (virgula == 6)
        {
            cidadeNascimento[j] = str[i];
            j++;
        }
    }

    setCidadeNascimento(cidadeNascimento, p);
}
/**
 * Procura o valor referente na string com todos os dados recebida
 * @param str string que contém todos os dados do jogador
 * @param *p jogador a ser inicializado
*/
void lerEstadoNascimento(char str[], Player *p)
{
    int virgula = 0;
    char estadoNascimento[50] = {0};
    int j = 0;
    str[strlen(str) - 2] = '\0'; //retira \n do final da string

    for (int i = 0; virgula <= 7 && i < strlen(str); i++)
    {
        if (str[i] == 44)
        {
            virgula++;
        }
        else if (virgula == 7)
        {
            estadoNascimento[j] = str[i];
            j++;
        }
    }

    setEstadoNascimento(estadoNascimento, p);
}
/**
 * Imprime os dados do jogador
 * @param *p jogador a ser imprimido
*/
void imprimir(Player *p)
{
    printf("## %s ## %i ## %i ## %i ## %s ## %s ## %s\n", p->nome, p->altura, p->peso, p->anoNascimento, p->universidade, p->cidadeNascimento, p->estadoNascimento);
}

/**
*  Chama as funções para leitura de dados e inicializações do jogador
*  @param string com jogador
*  @param *plr jogador
*/
void iniciaJogador(char str[], Player *plr)
{
    lerNome(str, plr);
    lerID(str, plr);
    lerAltura(str, plr);
    lerPeso(str, plr);
    lerUniversidade(str, plr);
    lerAnoNascimento(str, plr);
    lerCidadeNascimento(str, plr);
    lerEstadoNascimento(str, plr);
}

/**
 * Verifica se uma string é igual a 'FIM'
 * @param s string a ser verificada
 * @returns se string é igual a 'FIM' bool 
*/
bool isFim(char s[])
{
    return (s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

Player *lista[200]; //Lista
int n;              // Tamanho da lista

/**
*   Inicializador
*/
void start()
{
    n = 0;
}
/**
*  Insere um elemento na primeira posição da lista e move os demais
*  para o fim
*  @param *plr jogador a ser inserido
*/

void inserirInicio(Player *plr)
{
    //valida se há espaço para inserção no vetor
    if (n >= 300)
    {
        printf("Erro ao inserir");
        exit(1);
    }

    //leva jogadores para fim do vetor
    for (int i = n; i > 0; i--)
    {
        lista[i] = lista[i - 1];
    }

    lista[0] = plr;
    n++;
}

/**
*  Insere um jogador na última posição da lista
*  @param *plr jogador a ser inserido
*/
void inserirFim(Player *plr)
{
    //valida a inserção
    if (n >= 300)
    {
        printf("Erro ao inserir");
        exit(1);
    }

    lista[n] = plr;
    n++;
}

/**
*  Insere um jogador na posição especifica e move os demais para o fim 
*  da lista
*  @param *plr jogador a ser inserido
*  @param pos posição de inserção
*/
void inserir(Player *plr, int pos)
{
    //valida se há espaço para inserção
    if (n >= 300 || pos < 0 || pos > n)
    {
        printf("Erro ao inserir!");
        exit(1);
    }
    //leva elemento para o fim da lista até a pos indicada
    for (int i = n; i > pos; i--)
    {
        lista[i] = lista[i - 1];
    }

    lista[pos] = plr;
    n++;
}

/**
*  Remove um jogador da primeira posição da lista e movimento os demais 
*  para o inicio
*/
void removerInicio()
{
    //validar remoção
    if (n == 0)
    {
        printf("Erro ao remover");
        exit(1);
    }

    printf("(R) %s\n", lista[0]->nome);
    n--;

    for (int i = 0; i < n; i++)
    {
        lista[i] = lista[i + 1];
    }
}

/**
*  Remove um jogador da ultima posicao da lista
*/
void removerFim()
{
    //validar remoção
    if (n == 0)
    {
        printf("Erro ao remover");
        exit(1);
    }

    printf("(R) %s\n", lista[--n]->nome);
}

/**
*  Remove um jogador em uma posição especifica e movimenta os demais
*  de acordo com a pos
*  @param pos posição de remoção
*/
void remover(int pos)
{
    //validar remoção
    if (n == 0 || pos < 0 || pos >= n)
    {
        printf("Erro ao remover");
        exit(1);
    }

    printf("(R) %s\n", lista[pos]->nome);
    n--;
    //move os elementos
    for (int i = pos; i < n; i++)
    {
        lista[i] = lista[i + 1];
    }
}

/**
*  Mostra os jogadores, elementos da lista
*/
void mostrar()
{

    for (int i = 0; i < n; i++)
    {
        printf("[%i] ", i);
        imprimir(lista[i]);
    }
}

int getID(char s[])
{
    char *aux[3];
    int id = 0;

    aux[0] = strtok(s, " ");
    aux[1] = strtok(NULL, " ");
    aux[2] = strtok(NULL, " ");

    if (strcmp(aux[0], "I*") == 0)
    {
        id = atoi(aux[2]);
    }
    else
    {
        id = atoi(aux[1]);
    }

    return id;
}

int getPosicao(char s[])
{
    char *tmp[3];

    tmp[0] = strtok(s, " ");
    tmp[1] = strtok(NULL, " ");
    tmp[2] = strtok(NULL, " ");

    int pos = atoi(tmp[1]);

    return pos;
}

int main()
{
    char entrada[130][5];
    char mov[300][300];
    int numEntrada = 0;
    int numMov = 0;
    Player *player[130];
    Player *plrComando[100];
    FILE *pont_arq;
    char strPlayers[4000][250];
    int i = 0;

    do
    {
        scanf(" %[^\n]", entrada[numEntrada]);
    } while (isFim(entrada[numEntrada++]) == false); //Le todas as entradas até que seja lido o 'FIM'
    //printf("sai da leitura da entrada\n");
    numEntrada--; //Diminui o fim lido
    //printf("abri arquivo\n");
    pont_arq = fopen("/tmp/players.csv", "r"); //Abre o arquivo para leitura
    //printf("while do arq\n");
    while (fgets(strPlayers[i], tam, pont_arq)) //Le do arquivo e coloca no array de char os dados
    {
        i++;
    }
    //printf("sai do while do arq\n");
    fclose(pont_arq); // Fecha o arquivo
    //printf("fecha arq\n");

    for (int j = 0; j < numEntrada; j++)
    {
        player[j] = (Player *)malloc(sizeof(Player));
        int num = atoi(entrada[j]) + 1;
        iniciaJogador(strPlayers[num], player[j]);
        inserirFim(player[j]);
    } // Lê todos o dado referente ao que é demandado na entrada + 1, desconsiderando a linha do cabeçalho e envia para as funções identificadoras

    scanf("%i", &numMov);

    for (int k = 0; k < numMov; k++)
    {
        //printf("for da lista\n");

        scanf(" %[^\n]", mov[k]);
        plrComando[k] = (Player *)malloc(sizeof(Player));

        if (mov[k][0] == 'I')
        {
            char copy[10];
            strcpy(copy, mov[k]);
            int id = getID(mov[k]) + 1;
            iniciaJogador(strPlayers[id], plrComando[k]);

            if (mov[k][1] == '*')
            {
                int pos1 = getPosicao(copy);
                inserir(plrComando[k], pos1);
            }
            else if (mov[k][1] == 'I')
            {
                inserirInicio(plrComando[k]);
            }
            else if (mov[k][1] == 'F')
            {
                inserirFim(plrComando[k]);
            }
        }
        else if (mov[k][0] == 'R' && mov[k][1] == 'F')
        {
            removerFim();
        }
        else if (mov[k][0] == 'R' && mov[k][1] == 'I')
        {
            removerInicio();
        }
        else if (mov[k][0] == 'R' && mov[k][1] == '*')
        {
            int pos2 = getPosicao(mov[k]);
            remover(pos2);
        }
    }

    mostrar();
}
