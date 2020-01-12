#include <iostream>
#include <cstdio>
#include <queue>
#include <vector>
#include <algorithm>
#include <stack>

#define MAX 54

using namespace std;

struct LOG {
	int y[3];
	int x[3];
	int state;	//	0은 가로 1은 세로
};

struct Center {
	int y;
	int x;
	int state;
	int order;
};

char map[MAX][MAX];	//	B:움직일 나무, E:나무 도착지, 0:빈공간, 1:장애물
char checkmap[MAX][MAX][2];	//	0:가로상태, 1:세로상태

int N;
int Answer = 0;


LOG B;
LOG E;

int dy[] = { -1, +1, 0, 0 };
int dx[] = { 0, 0, -1, +1 };

void printB() {
	printf("B의 상태 표시 \n");
	for (int i = 0; i < 3; i++) {
		printf("y[%d], x[%d] = %d, %d \n", i, i, B.y[i], B.x[i]);
	}
	printf("state = %d \n", B.state);
}

void printE() {
	printf("E의 상태 표시 \n");
	for (int i = 0; i < 3; i++) {
		printf("y[%d], x[%d] = %d, %d \n", i, i, E.y[i], E.x[i]);
	}
	printf("state = %d \n", E.state);
}

void printMap() {
	for (int i = 0; i < N + 2; i++) {
		for (int j = 0; j < N + 2; j++) {
			printf("%c ", map[i][j]);
		}
		printf("\n");
	}
}

void BFS(int cy, int cx, int cs) {
	Center C;
	C.y = cy;
	C.x = cx;
	C.state = cs;
	C.order = 0;

	queue<Center> q;
	q.push(C);
	checkmap[cy][cx][cs] = 1;
	
	while (!q.empty()) {
		Center temp = q.front();
		q.pop();
		if (temp.y == E.y[1] && temp.x == E.x[1] && temp.state == E.state) {
			Answer = temp.order;
			//printf("%d \n", temp.order);
			break;
		}

		// 경계를 '1'로 둘러놨기 때문에 따로 경계값 체크를 할 필요는 없다.

		if (temp.state == 0) {	//	가로 상태일 때
			// 상
			if (checkmap[temp.y - 1][temp.x][temp.state] == 0 && map[temp.y - 1][temp.x - 1] == '0' && map[temp.y - 1][temp.x] == '0' && map[temp.y - 1][temp.x + 1] == '0') {
				Center next;
				next.y = temp.y - 1;
				next.x = temp.x;
				next.state = temp.state;
				next.order = temp.order + 1;
				checkmap[next.y][next.x][next.state] = 1;
				q.push(next);
			}
			// 하
			if (checkmap[temp.y + 1][temp.x][temp.state] == 0 && map[temp.y + 1][temp.x - 1] == '0' && map[temp.y + 1][temp.x] == '0' && map[temp.y + 1][temp.x + 1] == '0') {
				Center next;
				next.y = temp.y + 1;
				next.x = temp.x;
				next.state = temp.state;
				next.order = temp.order + 1;
				checkmap[next.y][next.x][next.state] = 1;
				q.push(next);
			}
			// 좌
			if (checkmap[temp.y][temp.x - 1][temp.state] == 0 && map[temp.y][temp.x - 2] == '0') {
				Center next;
				next.y = temp.y;
				next.x = temp.x - 1;
				next.state = temp.state;
				next.order = temp.order + 1;
				checkmap[next.y][next.x][next.state] = 1;
				q.push(next);
			}
			// 우
			if (checkmap[temp.y][temp.x + 1][temp.state] == 0 && map[temp.y][temp.x + 2] == '0') {
				Center next;
				next.y = temp.y;
				next.x = temp.x + 1;
				next.state = temp.state;
				next.order = temp.order + 1;
				checkmap[next.y][next.x][next.state] = 1;
				q.push(next);
			}
			// 턴
			if (checkmap[temp.y][temp.x][1] == 0 && map[temp.y - 1][temp.x - 1] == '0' && map[temp.y - 1][temp.x] == '0' && map[temp.y - 1][temp.x + 1] == '0'
												&& map[temp.y + 1][temp.x - 1] == '0' && map[temp.y + 1][temp.x] == '0' && map[temp.y + 1][temp.x + 1] == '0') {
				Center next;
				next.y = temp.y;
				next.x = temp.x;
				next.state = 1;	//	가로 -> 세로
				next.order = temp.order + 1;
				checkmap[next.y][next.x][next.state] = 1;
				q.push(next);
			}
		}
		else {	//	세로 상태일 때
			// 상
			if (checkmap[temp.y - 1][temp.x][temp.state] == 0 && map[temp.y - 2][temp.x] == '0') {
				Center next;
				next.y = temp.y - 1;
				next.x = temp.x;
				next.state = temp.state;
				next.order = temp.order + 1;
				checkmap[next.y][next.x][next.state] = 1;
				q.push(next);
			}
			// 하
			if (checkmap[temp.y + 1][temp.x][temp.state] == 0 && map[temp.y + 2][temp.x] == '0') {
				Center next;
				next.y = temp.y + 1;
				next.x = temp.x;
				next.state = temp.state;
				next.order = temp.order + 1;
				checkmap[next.y][next.x][next.state] = 1;
				q.push(next);
			}
			// 좌
			if (checkmap[temp.y][temp.x - 1][temp.state] == 0 && map[temp.y - 1][temp.x - 1] == '0'&& map[temp.y][temp.x - 1] == '0'&& map[temp.y + 1][temp.x - 1] == '0') {
				Center next;
				next.y = temp.y;
				next.x = temp.x - 1;
				next.state = temp.state;
				next.order = temp.order + 1;
				checkmap[next.y][next.x][next.state] = 1;
				q.push(next);
			}
			// 우
			if (checkmap[temp.y][temp.x + 1][temp.state] == 0 && map[temp.y - 1][temp.x + 1] == '0'&& map[temp.y][temp.x + 1] == '0'&& map[temp.y + 1][temp.x + 1] == '0') {
				Center next;
				next.y = temp.y;
				next.x = temp.x + 1;
				next.state = temp.state;
				next.order = temp.order + 1;
				checkmap[next.y][next.x][next.state] = 1;
				q.push(next);
			}
			// 턴
			if (checkmap[temp.y][temp.x][0] == 0 && map[temp.y - 1][temp.x - 1] == '0'&& map[temp.y][temp.x - 1] == '0'&& map[temp.y + 1][temp.x - 1] == '0'
				&& map[temp.y - 1][temp.x + 1] == '0'&& map[temp.y][temp.x + 1] == '0'&& map[temp.y + 1][temp.x + 1] == '0') {
				Center next;
				next.y = temp.y;
				next.x = temp.x;
				next.state = 0;	//	세로 -> 가로
				next.order = temp.order + 1;
				checkmap[next.y][next.x][next.state] = 1;
				q.push(next);
			}
		}

	}

}

int main(void) {

	scanf("%d", &N);
	
	int Bnum = 0;
	int Enum = 0;

	for (int i = 0; i <= N + 1; i++) {
		for (int j = 0; j <= N + 1; j++) {
			if (i == 0 || j == 0 || i == N + 1 || j == N + 1) {
				map[i][j] = '1';
			}
		}
	}


	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			scanf("%c", &map[i][j]);
			if (map[i][j] == '\n') j = j - 1;
			else if (map[i][j] == 'B') {
				B.y[Bnum] = i;
				B.x[Bnum] = j;
				Bnum++;
			}
			else if (map[i][j] == 'E') {
				E.y[Enum] = i;
				E.x[Enum] = j;
				Enum++;
			}
		}
	}

	if (B.y[0] == B.y[1]) {	// y좌표가 같으면 통나무는 가로 상태
		B.state = 0;
	}
	else {	//	y좌표가 다르면 통나무는 세로 상태
		B.state = 1;
	}

	if (E.y[0] == E.y[1]) {	// y좌표가 같으면 통나무는 가로 상태
		E.state = 0;
	}
	else {	//	y좌표가 다르면 통나무는 세로 상태
		E.state = 1;
	}

	/*
	printB();
	printf("\n");
	printE();
	*/

	/*
	printf("\n");
	printMap();
	printf("\n");
	*/

	for (int i = 0; i < 3; i++) {
		map[B.y[i]][B.x[i]] = '0';
		map[E.y[i]][E.x[i]] = '0';
	}

	BFS(B.y[1], B.x[1], B.state);

	printf("%d \n", Answer);

	return 0;
}