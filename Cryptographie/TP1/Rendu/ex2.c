#include <stdio.h>
#include <string.h>

int main(int argc, char **argv){
	
	if (argc < 9){
		printf("manque d'arguments \n");
		return 1;
	}

	char message[255];
	char key[5];
	char option[4];
	char mode[4];


	int i;
	for (i = 0; i < argc; i++){
		printf("l'argument %d est %s\n",i, argv[i]);

		if (strcmp(argv[i], "-msg") == 0)
			strcpy(message, argv[i+1]);

		else if (strcmp(argv[i], "-key") == 0)
			strcpy(key, argv[i+1]);

		else if (strcmp(argv[i], "-op") == 0)
			strcpy(option, argv[i+1]);	

		else if (strcmp(argv[i], "-mode") == 0)
			strcpy(mode, argv[i+1]);
			
		}
	}
	printf(%s\n,message);
	printf(%s\n,key);
	printf(%s\n,option);
	printf(%s\n,mode);


	return 0;



}