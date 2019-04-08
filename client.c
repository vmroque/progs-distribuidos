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
#define ERROR(m) do {				\
			printf(m);		\
			exit(EXIT_FAILURE);	\
		} while(0)

int main(int argc, char **argv)
{
	int sock, status, i;
	struct sockaddr_in serv_addr;
	char input[20];
	char buffer[BUFFER_SIZE];

	serv_addr.sin_family = AF_INET;
	serv_addr.sin_port = htons(PORT);
	status = inet_pton(AF_INET, argv[1], &serv_addr.sin_addr);

	if (status <= 0)
		ERROR("Invalid address\n");

	for (;;) {
		sock = socket(AF_INET, SOCK_STREAM, 0);
		if (sock < 0)
			ERROR("Socket failed\n");

		status = connect(sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr));
		if (status < 0)
			ERROR("Connection failed\n");

		for (i = 0, buffer[0] = 0; i < N_ENTRIES; i++) {
			scanf("%s", input);
			strcat(buffer, input);
			strcat(buffer, " ");
		}

		status = send(sock, buffer, BUFFER_SIZE, 0);
		if (status < 0)
			ERROR("Sending failed\n");

		status = read(sock, buffer, BUFFER_SIZE);
		if (status < 0)
			ERROR("Reading failure\n");

		printf("juros = %s\n", buffer);
		close(sock);
	}

	return 0;
}
