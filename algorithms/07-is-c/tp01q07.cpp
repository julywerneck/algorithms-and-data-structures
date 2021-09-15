#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

bool isFim(char string[])
{
    bool isFim = false;
    if (strlen(string) >= 3 && string[0] == 'F' && string[1] == 'I' && string[2] == 'M')
    {
        isFim = true;
    }
    return (isFim);
}

bool isVogal(char str[])
{
    bool vogal = true;
    int tam = strlen(str) - 1;

    for (int i = 0; vogal == true && i < tam; i++)
    {
        if (str[i] != 'a' && str[i] != 'A' && str[i] != 'e' && str[i] != 'E' && str[i] != 'i' && str[i] != 'I' && str[i] != 'o' && str[i] != 'O' && str[i] != 'u' && str[i] != 'U')
        {
            vogal = false;
        }
    }

    return vogal;
}

bool isConso(char str[])
{
    int tam = strlen(str) - 1;
    bool conso = false;

    for (int i = 0; conso == true && i < tam; i++)
    {
        if ((str[i] < 'A' || str[i] > 'Z') && (str[i] < 'a' || str[i] > 'z'))
        {
            conso = false;
        }
        else if (isVogal(str) == true)
        {
            conso = false;
        }
    }

    return conso;
}

bool isInt(char str[])
{
    int tam = strlen(str) - 1;
    bool inteiro = false;

    for (int i = 0; inteiro == true && i < tam; i++)
    {
        int check = (int)str[i];

        if (check < 48 || check > 57)
        {
            inteiro = false;
        }
    }

    return inteiro;
}

bool isDouble(char str[])
{
    bool real = true;
    int tam = strlen(str) - 1;

    for (int i = 0; real == true && i < tam; i++)
    {
        int check = (int)str[i];

        if (check < 48 || check > 57 || check != 46 || check != 44)
        {
            real = false;
        }
    }

    return real;
}

int main()
{
    char str[3000];

    do
    {
        scanf(" %[^\n]s", str);

        if (isFim(str) == false && isVogal(str) == true)
        {
            printf("SIM NAO NAO NAO\n");
        }
        else if (isFim(str) == false && isConso(str) == true)
        {
            printf("NAO SIM NAO NAO\n");
        }
        else if (isFim(str) == false && isInt(str) == true)
        {
            printf("NAO NAO SIM SIM\n");
        }
        else if (isFim(str) == false && isDouble(str) == true)
        {
            printf("NAO NAO NAO SIM\n");
        }
        else if (isFim(str) == false)
        {
            printf("NAO NAO NAO NAO\n");
        }

    } while (isFim(str) == false);
}