/*
 * Aplicacao cliente
 *
 * Le e envia uma serie de mensagens para um servidor
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/socket.h>
#include <arpa/inet.h>

#define PORT 8080

int main(int argc, char **argv)
{
	int sock;
	struct sockaddr_in serv_addr;
	char msg[20];

	for (;;) {
		sock = socket(AF_INET, SOCK_STREAM, 0);

		serv_addr.sin_family = AF_INET;
		serv_addr.sin_port = htons(PORT);
		inet_pton(AF_INET, argv[1], &serv_addr.sin_addr);

		connect(sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr));
		scanf("%s", msg);
		send(sock, msg, sizeof(msg), 0);

		close(sock);
	}

	return 0;
}
