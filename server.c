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

#define BUFFER_SIZE 256

#define ERROR(m) do {				\
			printf(m);		\
			exit(EXIT_FAILURE);	\
		} while(0)

double juros(double q0, double qf, double t)
{
	return pow(qf/q0, 1.0/t) - 1.0;
}

int main(int argc, char **argv)
{
	struct sockaddr_in address;
	uint16_t port;
	int server_fd, new_socket, status;
	int addrlen = sizeof(address), len;
	double q0, qf, t;
	char buffer[BUFFER_SIZE];

	if (argc < 2)
		ERROR("USAGE: ./server <port>\n");

	port = (uint16_t) atoi(argv[1]);

	server_fd = socket(AF_INET, SOCK_STREAM, 0);
	if (server_fd < 0)
		ERROR("Socket failed\n");

	address.sin_family = AF_INET;
	address.sin_addr.s_addr = INADDR_ANY;
	address.sin_port = htons(port);
	status = bind(server_fd, (struct sockaddr *)&address, sizeof(address));
	if (status < 0)
		ERROR("Binding failed\n");

	listen(server_fd, 3);

	for (;;) {
		new_socket = accept(server_fd, (struct sockaddr *)&address, (socklen_t*)&addrlen);
		if (new_socket < 0)
			ERROR("Socket failed\n");

		len = read(new_socket, buffer, BUFFER_SIZE);
		if (len < 0)
			ERROR("Reading failed\n");

		buffer[len] = 0;
		sscanf(buffer, "%lf %lf %lf", &q0, &qf, &t);
		sprintf(buffer, "%lf", juros(q0, qf, t));

		status = send(new_socket, buffer, BUFFER_SIZE, 0);
		if (status < 0)
			ERROR("Sending failed\n");

		close(new_socket);
	}

	close(server_fd);

	return 0;
}
