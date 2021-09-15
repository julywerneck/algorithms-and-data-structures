#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>

#define tam 1000
int comp = 0;
//Cria a struct do jogador
typedef struct
{
    int id;
    int altura;
    int peso;
    int anoNascimento;
    char universidade[300];
    char cidadeNascimento[300];
    char estadoNascimento[300];
    char nome[300];
} Player;
//Seta os atributos do mesmo verificando o tipo do dado no caso de inteiros
Player *p[500];

void setNome(char info[], Player *p)
{
    strcpy(p->nome, info);
}

void setId(char info[], Player *p)
{
    int id;
    id = atoi(info);
    p->id = id;
}

void setAltura(char info[], Player *p)
{
    int altura;
    altura = atoi(info);
    p->altura = altura;
}

void setPeso(char info[], Player *p)
{
    int peso;
    peso = atoi(info);
    p->peso = peso;
}

void setAnoNascimento(char info[], Player *p)
{
    int anoNascimento;
    anoNascimento = atoi(info);
    p->anoNascimento = anoNascimento;
}

void setUniversidade(char info[], Player *p)
{
    char universidade[100];

    if (strcmp("", info) == 0)
    {
        strcpy(p->universidade, "nao informado");
    }
    else
    {
        strcpy(p->universidade, info);
    }
}

void setCidadeNascimento(char info[], Player *p)
{
    char cidadeNascimento[100];

    if (strcmp("", info) == 0)
    {
        strcpy(p->cidadeNascimento, "nao informado");
    }
    else
    {
        strcpy(p->cidadeNascimento, info);
    }
}

void setEstadoNascimento(char info[], Player *p)
{
    char estadoNascimento[100];

    if (strcmp("", info) == 0)
    {
        strcpy(p->estadoNascimento, "nao informado");
    }
    else
    {
        strcpy(p->estadoNascimento, info);
    }
}
//Lê o nome da string recebida lida do arquivo e seta no jogador levando em consideração a virgula
void lerNome(char str[], Player *p)
{
    int virgula = 0;
    char nome[100] = {0};
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
//Lê o id da string recebida lida do arquivo e seta no jogador levando em consideração a virgula
void lerID(char str[], Player *p)
{
    int virgula = 0;
    int j = 0;
    char id[100] = {0};

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
//Lê o altura da string recebida lida do arquivo e seta no jogador levando em consideração a virgula
void lerAltura(char str[], Player *p)
{
    int virgula = 0;
    char altura[100] = {0};
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
//Lê o peso da string recebida lida do arquivo e seta no jogador levando em consideração a virgula
void lerPeso(char str[], Player *p)
{
    int virgula = 0;
    char peso[100] = {0};
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
//Lê a univerisade da string recebida lida do arquivo e seta no jogador levando em consideração a virgula
void lerUniversidade(char str[], Player *p)
{
    int virgula = 0;
    char universidade[100] = {0};
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
//Lê o ano de nascimento da string recebida lida do arquivo e seta no jogador levando em consideração a virgula
void lerAnoNascimento(char str[], Player *p)
{
    int virgula = 0;
    char anoNascimento[100] = {0};
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
//Lê a cidade de nascimento da string recebida lida do arquivo e seta no jogador levando em consideração a virgula
void lerCidadeNascimento(char str[], Player *p)
{
    int virgula = 0;
    char cidadeNascimento[100] = {0};
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
//Lê o estado de nascimento da string recebida lida do arquivo e seta no jogador levando em consideração a virgula
void lerEstadoNascimento(char str[], Player *p)
{
    int virgula = 0;
    char estadoNascimento[100] = {0};
    int j = 0;
    str[strlen(str) - 1] = '\0'; //retira \n do final da string

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
//Imprime os dados do jogador recebido como param
void imprimir(Player *p)
{
    printf("[%i ## %s ## %i ## %i ## %i ## %s ## %s ## %s]\n", p->id, p->nome, p->altura, p->peso, p->anoNascimento, p->universidade, p->cidadeNascimento, p->estadoNascimento);
}
//Verifica se o array de char recebido é FIM
bool isFim(char s[])
{
    return (s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

void swap(int i, int j)
{
    Player *temp = p[i];
    p[i] = p[j];
    p[j] = temp;
}

void construir(int tamHeap)
{
    for (int i = tamHeap; i > 1 && (p[i]->altura > p[i / 2]->altura || (p[i]->altura == p[i / 2]->altura && strcmp(p[i]->nome, p[i / 2]->nome) > 0)); i /= 2)
    {
        swap(i, i / 2); //atenção para swap no tmp ou no global
    }
}

int getMaiorFilho(int i, int tamHeap)
{
    int filho;
    if (2 * i == tamHeap || p[2 * i]->altura > p[2 * i + 1]->altura)
    {
        filho = 2 * i;
    }
    else if (p[2 * i]->altura == p[2 * i + 1]->altura && strcmp(p[2 * i]->nome, p[2 * i + 1]->nome) > 0)
    {
        filho = 2 * i;
    }
    else
    {
        filho = 2 * i + 1;
    }

    return filho;
}

void reconstruir(int tamHeap)
{
    int i = 1;
    while (i <= (tamHeap / 2))
    {
        int filho = getMaiorFilho(i, tamHeap);
        if (p[i]->altura < p[filho]->altura || (p[i]->altura == p[filho]->altura && strcmp(p[i]->nome, p[filho]->nome) < 0))
        {
            swap(i, filho); //atenção ao swap no tmp e no global
            i = filho;
        }
        else
        {
            i = tamHeap;
        }
    }
}

void heapSort(int n)
{
    //Alterar vetor ignorando a posição zero
    for (int i = n; i > 0; i--)
    {
        p[i] = p[i - 1];
    }

    //Construção do heap
    for (int tamHeap = 2; tamHeap <= 10; tamHeap++)
    {
        construir(tamHeap);
    }

    for (int i = 10 + 1; i <= n; i++)
    {
        if (p[i]->altura < p[1]->altura || p[i]->altura == p[1]->altura && strcmp(p[i]->nome, p[1]->nome) < 0)
        {
            swap(i, 1);
            reconstruir(10);
        }
    }
    //Ordenação propriamente dita
    int tamHeap = n;
    while (tamHeap > 1)
    {
        swap(1, tamHeap--);
        reconstruir(tamHeap);
    }

    //Alterar o vetor para voltar a posição zero
    for (int i = 0; i < n; i++)
    {
        p[i] = p[i + 1];
    }

    for (int u = 0; u < 10; u++)
    {
        imprimir(p[u]);
    }
}

int main()
{
    char entrada[tam][tam];
    int numEntrada = 0;
    FILE *pont_arq;
    FILE *pont_arq_log;
    char strPlayers[4000][1500];
    int i = 0;
    clock_t inicio, fim;

    do
    {
        scanf(" %[^\n]", entrada[numEntrada]);
    } while (isFim(entrada[numEntrada++]) == false); //Le todas as entradas até que seja lido o 'FIM'

    numEntrada--; //Diminui o fim lido

    pont_arq = fopen("/tmp/players.csv", "r"); //Abre o arquivo para leitura

    while (fgets(strPlayers[i], tam, pont_arq)) //Le do arquivo e coloca no array de char os dados
    {
        i++;
    }

    fclose(pont_arq); // Fecha o arquivo

    for (int j = 0; j < numEntrada; j++)
    {
        p[j] = (Player *)malloc(sizeof(Player));
        int num = atoi(entrada[j]) + 1;
        lerNome(strPlayers[num], p[j]);
        lerID(strPlayers[num], p[j]);
        lerAltura(strPlayers[num], p[j]);
        lerPeso(strPlayers[num], p[j]);
        lerUniversidade(strPlayers[num], p[j]);
        lerAnoNascimento(strPlayers[num], p[j]);
        lerCidadeNascimento(strPlayers[num], p[j]);
        lerEstadoNascimento(strPlayers[num], p[j]);
        //imprimir(player[j]);
    } // Lê todos o dado referente ao que é demandado na entrada + 1, desconsiderando a linha do cabeçalho e envia para as funções identificadoras
    //printf("ooooooooooooooooi\n");
    inicio = clock();

    heapSort(numEntrada);

    fim = clock();

    double tempo = ((fim - inicio) / (double)CLOCKS_PER_SEC);
    pont_arq_log = fopen("683595_quicksort.txt", "w");
    fprintf(pont_arq, "Matricula : 683595 \t Tempo de execução: %fs \t Numero de Comparações : %i", tempo, comp);
    fclose(pont_arq_log);
}