/*
 * Aplicacao servidora
 *
 * Recebe e imprime uma serie de mensagens de um cliente
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <unistd.h>
#include <sys/socket.h>
#include <arpa/inet.h>

#define PORT 8080

double juros(double q0, double qf, int t)
{
	return pow(q0/qf, 1.0/t) - 1.0;
}

int main(int argc, char **argv)
{
	int server_fd, new_socket;
	struct sockaddr_in address;
	int addrlen = sizeof(address);
	int len;
	char buffer[256];

	server_fd = socket(AF_INET, SOCK_STREAM, 0);

	address.sin_family = AF_INET;
	address.sin_addr.s_addr = INADDR_ANY;
	address.sin_port = htons(PORT);

	bind(server_fd, (struct sockaddr *)&address, sizeof(address));
	listen(server_fd, 3);

	for (;;) {
		new_socket = accept(server_fd, (struct sockaddr *)&address, (socklen_t*)&addrlen);
		len = read(new_socket, buffer, 256);
		buffer[len] = 0;
		printf("%s\n", buffer);

		close(new_socket);
	}

	close(server_fd);

	return 0;
}
