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

#define BUFFER_SIZE 256
#define N_ENTRIES  3

#define ERROR(m) do {				\
			printf(m);		\
			exit(EXIT_FAILURE);	\
		} while(0)

int main(int argc, char **argv)
{
	struct sockaddr_in serv_addr;
	int sock, status, i;
	uint16_t port;
	char ip_addr[15];
	char input[20], buffer[BUFFER_SIZE];

	if (argc < 3)
		ERROR("USAGE: ./client <ip address> <port>\n");
	strcpy(ip_addr, argv[1]);
	port = (uint16_t) atoi(argv[2]);

	serv_addr.sin_family = AF_INET;
	serv_addr.sin_port = htons(port);
	status = inet_pton(AF_INET, ip_addr, &serv_addr.sin_addr);
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
