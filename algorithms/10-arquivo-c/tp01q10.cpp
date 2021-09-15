#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

int main()
{
    double str[300];
    int tam = 0;
    double j = 0;
    FILE *arq;
    arq = fopen("exemplo.txt", "w");

    scanf(" %[^\n]i", tam);

    for (int i = 0; i < tam; i++)
    {
        fprintf(arq, "%d", j);
    }

    fclose(arq);

    FILE *arq2;
    arq2 = fopen("exemplo.txt", "r");

    for (int i = tam; i > 0; i--)
    {
        double x = fscanf("%d", arq2);
        printf("%d", x);
    }
}