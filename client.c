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
#define BUFFER_SIZE 256
#define N_ENTRIES  3

int main(int argc, char **argv)
{
	int sock, i;
	struct sockaddr_in serv_addr;
	char input[20];
	char buffer[BUFFER_SIZE];

	serv_addr.sin_family = AF_INET;
	serv_addr.sin_port = htons(PORT);
	inet_pton(AF_INET, argv[1], &serv_addr.sin_addr);

	for (;;) {
		sock = socket(AF_INET, SOCK_STREAM, 0);
		connect(sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr));

		for (i = 0, buffer[0] = 0; i < N_ENTRIES; i++) {
			scanf("%s", input);
			strcat(buffer, input);
			strcat(buffer, " ");
		}

		send(sock, buffer, BUFFER_SIZE, 0);
		read(sock, buffer, BUFFER_SIZE);
		printf("juros = %s\n", buffer);

		close(sock);
	}

	return 0;
}
