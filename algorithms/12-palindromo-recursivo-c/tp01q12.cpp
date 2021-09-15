#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

bool isFim(char string[]){
    bool isFim = false;
    if(strlen(string) >= 3 && string[0] == 'F' && string[1] == 'I' && string[2] == 'M'){
        isFim = true;
    } return(isFim);
}

bool isPalindromo(char string[], int tam, int i){

        bool palindromo = true;

        if(string[i] != string[tam])
        {
            palindromo = false;
        } else if(i < tam){
            return isPalindromo(string, tam-1, i+1);
        }
    return palindromo;
}

bool callPalindromo(char string[]){
    int tam = strlen(string) - 1;
    int i = 0;

    return isPalindromo(string, tam, i);
}

int main()
{
    char string[3000];

    do{
        scanf(" %[^\n]s", string);

        if(isFim(string) == false && callPalindromo(string) == true){
            printf("SIM\n");
        } else if(isFim(string) == false && callPalindromo(string) == false){
            printf("NAO\n");
        }

    } while(isFim(string) == false);

}
