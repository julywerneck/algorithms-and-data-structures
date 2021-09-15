#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

bool isFim(char str[]) //Recebe uma string e verifica se ela é FIM pelo tamanho e letras;
{
    bool isFim = false;
    if (strlen(str) >= 3 && str[0] == 'F' && str[1] == 'I' && str[2] == 'M')
    {
        isFim = true;
    }
    return (isFim);
}

bool isPalindromo(char str[])
{
    bool palindromo = true; 
    int j = strlen(str) - 1; 

    for (int i = 0; palindromo == true && i <= j; i++) { //Verifica se palindromo ainda é verdadeiro e se i ainda < j para continuar o loop
        if (str[i] != str[j]) { // Verifica se a letra na posicacao i(começou de 0) é diferente da j(comecou do fim) 
            palindromo = false; // Altera o valor de palindromo para falso
        }

        j--; 
    }

    return (palindromo);
}

int main() {
	char str[10000];
	//Le a entrada e verifica se é igual a FIM, se não verifica se é palindromo
	do {
		scanf(" %[^\n]s", str); 
		
		if(isFim(str) == false) {
			if(isPalindromo(str) == true) { 
				printf("SIM\n"); 
			} else {
				printf("NAO\n"); 
			}
		}

	} while(isFim(str) == false); //Condição de parada para do ... while, verifica se a entrda = FIM
}
