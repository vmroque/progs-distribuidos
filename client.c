/*
 * Aplicacao cliente
 *
 * Faz a leitura de tres variaveis (q0, qf, t)
 * e envia para o servidor
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/socket.h>
#include <arpa/inet.h>

#define PORT 8080
#define ENT_MAXLEN 20
#define PKG_MAXLEN 256
#define N_ENTRIES  3

void pack_msg(char **entries, char *pkg, int n)
{
	int i;

	for (i = 0; i < n; ++i) {
		strcat(pkg, entries[i]);
		strcat(pkg," "); 
	}
}

int main(int argc, char **argv)
{
	int sock, i;
	struct sockaddr_in serv_addr;
	char *val, *pkg;
	char **ent;
	char buffer[PKG_MAXLEN];

	val = (char *) malloc(sizeof(char) * ENT_MAXLEN);
	pkg = (char *) malloc(sizeof(char) * PKG_MAXLEN);
	ent = (char **) malloc(sizeof(char*) * N_ENTRIES);

	for (i = 0; i < N_ENTRIES; ++i) {
		ent[i] = (char*) malloc(sizeof(char) * ENT_MAXLEN);
		ent[i][0] = 0;
	}

	serv_addr.sin_family = AF_INET;
	serv_addr.sin_port = htons(PORT);
	inet_pton(AF_INET, argv[1], &serv_addr.sin_addr);

	for (;;) {
		sock = socket(AF_INET, SOCK_STREAM, 0);
		connect(sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr));

		for (i = 0; i < 3; i++) {
			scanf("%s", val);
			strcpy(ent[i], val);
		}

		pkg[0] = 0;
		pack_msg(ent, pkg, N_ENTRIES); 
		send(sock, pkg, PKG_MAXLEN, 0);
		read(sock, buffer, PKG_MAXLEN);
		printf("juros = %s\n", buffer);

		close(sock);
	}

	return 0;
}
