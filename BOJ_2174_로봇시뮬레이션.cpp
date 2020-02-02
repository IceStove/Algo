#include <iostream>
#include <cstdio>
#include <vector>
#include <queue>
#include <stack>
#include <algorithm>

#define MAX 102

using namespace std;

struct robot {
	int y;		//	y좌표
	int x;		//	x좌표
	char dir;	//	E, W, S, N
	int life;	//	생존 여부
};


int map[MAX][MAX];
vector<robot> v;

int A, B;	//	1<= 가로, 세로 <=100
int N, M;	//	1<= 로봇수, 명령수 <=100

int pung;	//	충돌이 났는지 안났는지 여부

int r1, r2;	//	충돌 일으킨 로봇 r1, 충돌 당한 로봇 r2 (벽인경우는 0)

void printMap() {
	for (int i = B; i >=1 ; i--) {
		for (int j = 1; j <= A; j++) {
			printf("%d ", map[i][j]);
		}
		printf("\n");
	}
}

void turnLeft(int num) {
	if (v[num].dir == 'E') {
		v[num].dir = 'N';
	}
	else if (v[num].dir == 'W') {
		v[num].dir = 'S';
	}
	else if (v[num].dir == 'S') {
		v[num].dir = 'E';
	}
	else if (v[num].dir == 'N') {
		v[num].dir = 'W';
	}
}

void turnRight(int num) {
	if (v[num].dir == 'E') {
		v[num].dir = 'S';
	}
	else if (v[num].dir == 'W') {
		v[num].dir = 'N';
	}
	else if (v[num].dir == 'S') {
		v[num].dir = 'W';
	}
	else if (v[num].dir == 'N') {
		v[num].dir = 'E';
	}
}

void goFront(int num) {
	robot temp = v[num];

	if (temp.life == 0) {
		return;
	}

	int cy = temp.y;
	int cx = temp.x;
	char cdir = temp.dir;
	int clife = temp.life;

	int ny, nx;

	if (temp.dir == 'E') {	//	x++
		ny = cy;
		nx = cx + 1;
	}
	else if (temp.dir == 'W') {	//	x--
		ny = cy;
		nx = cx - 1;
	}
	else if (temp.dir == 'S') {	//	y++
		ny = cy - 1;
		nx = cx;
	}
	else if (temp.dir == 'N') {	//	y--
		ny = cy + 1;
		nx = cx;
	}

	robot next;
	next.y = ny;
	next.x = nx;
	next.dir = cdir;
	next.life = clife;

	if (ny <= 0 || ny > B || nx <= 0 || nx > A) {
		//	벽 충돌
		map[cy][cx] = 0;
		next.life = 0;
		v[num] = next;
		if (r1 == 0) {
			r1 = num;
			r2 = 0;
			pung = 1;
		}
	}
	else if (map[ny][nx] == 0) {
		map[cy][cx] = 0;
		map[ny][nx] = num;
		v[num] = next;
	}
	else if (map[ny][nx] != 0) {
		// 로봇 충돌
		map[cy][cx] = 0;
		next.life = 0;
		v[num] = next;
		if (r1 == 0) {
			r1 = num;
			r2 = map[ny][nx];
			pung = 1;
		}
	}

}

int main(void) {

	scanf("%d %d", &A, &B);
	scanf("%d %d", &N, &M);

	v.push_back({ 0,0,'O' });

	int cy, cx;
	char cdir;

	for (int i = 1; i <= N; i++) {
		scanf("%d %d %c", &cx, &cy, &cdir);
		robot temp;
		temp.y = cy;
		temp.x = cx;
		temp.dir = cdir;
		temp.life = 1;
		v.push_back(temp);
		map[cy][cx] = i;
	}

	int n, repeat;
	char order;
	for (int i = 1; i <= M; i++) {
		scanf("%d %c %d", &n, &order, &repeat);
		if (pung == 1) {
			continue;
		}
		if (order == 'L') {
			for (int k = 0; k < repeat; k++) {
				turnLeft(n);
			}
		}
		else if (order == 'R') {
			for (int k = 0; k < repeat; k++) {
				turnRight(n);
			}
		}
		else if (order == 'F') {
			for (int k = 0; k < repeat; k++) {
				goFront(n);
			}
		}
	}

	/*
	printf("\n");
	printMap();
	printf("\n");
	*/

	if (pung == 0) {
		printf("OK \n");
	}
	else {
		if (r2 == 0) {
			printf("Robot %d crashes into the wall \n", r1);
		}
		else {
			printf("Robot %d crashes into robot %d \n", r1, r2);
		}
	}


	return 0;
}