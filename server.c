/*
 * Aplicacao servidora
 *
 * Recebe tres variaveis do cliente
 * e executa a funcao 'juros' sobre elas 
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <unistd.h>
#include <sys/socket.h>
#include <arpa/inet.h>

#define PORT 8080
#define BUFFER_SIZE 256

double juros(double q0, double qf, double t)
{
	return pow(qf/q0, 1.0/t) - 1.0;
}

int main(int argc, char **argv)
{
	int server_fd, new_socket;
	struct sockaddr_in address;
	int addrlen = sizeof(address);
	int len;
	double q0, qf, t;
	char buffer[BUFFER_SIZE];

	server_fd = socket(AF_INET, SOCK_STREAM, 0);

	address.sin_family = AF_INET;
	address.sin_addr.s_addr = INADDR_ANY;
	address.sin_port = htons(PORT);

	bind(server_fd, (struct sockaddr *)&address, sizeof(address));
	listen(server_fd, 3);

	for (;;) {
		new_socket = accept(server_fd, (struct sockaddr *)&address, (socklen_t*)&addrlen);
		len = read(new_socket, buffer, BUFFER_SIZE);
		buffer[len] = 0;
		sscanf(buffer, "%lf %lf %lf", &q0, &qf, &t);
		sprintf(buffer, "%lf", juros(q0, qf, t));
		send(new_socket, buffer, BUFFER_SIZE, 0);
		close(new_socket);
	}

	close(server_fd);

	return 0;
}
