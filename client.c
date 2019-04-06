#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <arpa/inet.h>

#define PORT 8080

int main(int argc, char **argv)
{
	struct sockaddr_in serv_addr;
	char msg[100];
	int sock = socket(AF_INET, SOCK_STREAM, 0);

	printf("Digite sua mensagem para %s: ", argv[1]);
	scanf("%s", msg);

	serv_addr.sin_family = AF_INET;
	serv_addr.sin_port = htons(PORT);
	inet_pton(AF_INET, argv[1], &serv_addr.sin_addr);

	connect(sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr));
	send(sock, msg, strlen(msg), 0);

	return 0;
}
