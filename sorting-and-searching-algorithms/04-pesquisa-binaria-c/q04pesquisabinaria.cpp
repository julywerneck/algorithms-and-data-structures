#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>

#define tam 1000
int comparacoes = 0;
//Cria a struct do jogador
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
//Seta os atributos do mesmo verificando o tipo do dado no caso de inteiros
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
    char universidade[60];

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
    char cidadeNascimento[50];

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
    char estadoNascimento[50];

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
//Lê o id da string recebida lida do arquivo e seta no jogador levando em consideração a virgula
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
//Lê o altura da string recebida lida do arquivo e seta no jogador levando em consideração a virgula
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
//Lê o peso da string recebida lida do arquivo e seta no jogador levando em consideração a virgula
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
//Lê a univerisade da string recebida lida do arquivo e seta no jogador levando em consideração a virgula
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
//Lê o ano de nascimento da string recebida lida do arquivo e seta no jogador levando em consideração a virgula
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
//Lê a cidade de nascimento da string recebida lida do arquivo e seta no jogador levando em consideração a virgula
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
//Lê o estado de nascimento da string recebida lida do arquivo e seta no jogador levando em consideração a virgula
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

void pesqBin(Player *vet[], char pesq[], int dir)
{
    bool find = false;
    int esq = 0;
    int meio;
    dir = dir - 1;

    while (esq <= dir)
    {
        //printf("\twhile---\n");
        meio = (esq + dir) / 2;

        comparacoes++;
        if (strcmp(pesq, vet[meio]->nome) == 0)
        {
            //printf("\tACHEIIII ---");
            find = true;
            printf("SIM\n");
            esq = dir + 1;
        }
        else if (strcmp(pesq, vet[meio]->nome) > 0)
        {
            comparacoes++;
            //printf("\tentrei no else if\n");
            esq = meio + 1;
        }
        else
        {
            comparacoes++;
            //printf("\tentrei no else\n");
            dir = meio - 1;
        }
    }

    if (find == false)
    {
        printf("NAO\n");
    }
}

int main()
{
    char entrada[400][400];
    char search[300][300];
    int numEntrada = 0;
    int numSearch = 0;
    Player *player[800];
    FILE *pont_arq;
    FILE *pont_arq_log;
    char strPlayers[4000][tam];
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
        player[j] = (Player *)malloc(sizeof(Player));
        int num = atoi(entrada[j]) + 1;
        lerNome(strPlayers[num], player[j]);
        lerID(strPlayers[num], player[j]);
        lerAltura(strPlayers[num], player[j]);
        lerPeso(strPlayers[num], player[j]);
        lerUniversidade(strPlayers[num], player[j]);
        lerAnoNascimento(strPlayers[num], player[j]);
        lerCidadeNascimento(strPlayers[num], player[j]);
        lerEstadoNascimento(strPlayers[num], player[j]);
        //imprimir(player[j]);
    } // Lê todos o dado referente ao que é demandado na entrada + 1, desconsiderando a linha do cabeçalho e envia para as funções identificadoras
    //printf("ooooooooooooooooi\n");
    for (int i = 0; i < (numEntrada - 1); i++)
    {
        int menor = i;

        for (int k = (i + 1); k < numEntrada; k++)
        {
            if (strcmp(player[menor]->nome, player[k]->nome) > 0)
            {
                menor = k;
            }
        }

        Player tmp = *player[menor];
        *player[menor] = *player[i];
        *player[i] = tmp;
    }

    /*printf("ORDENADO ----------\n");

    for (int u = 0; u < numEntrada; u++)
    {
        imprimir(player[u]);
    }*/
    //printf("antes de ler os nomes");
    do
    {
        scanf(" %[^\n]", search[numSearch]);
    } while (strcmp("FIM", search[numSearch++]) != 0); //Le todas as entradas até que seja lido o 'FIM'

    numSearch--;
    //printf("antes de entrar no for da pesquisa binaria\n");
    inicio = clock();

    for (int y = 0; y < numSearch; y++)
    {
        pesqBin(player, search[y], numEntrada);
    }

    fim = clock();
    double tempo = ((fim - inicio) / (double)CLOCKS_PER_SEC);
    pont_arq_log = fopen("683595_binaria.txt", "w");
    fprintf(pont_arq, "Matricula : 683595 \t Tempo de execução: %fs \t Numero de Comparações : %i", tempo, comparacoes);
    fclose(pont_arq_log);
}