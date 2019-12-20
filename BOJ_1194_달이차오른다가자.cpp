#include <iostream>
#include <cstdio>
#include <stack>
#include <queue>
#include <algorithm>
#include <vector>

#define MAX 53

using namespace std;

struct Man {
	// y, x 좌표 선언
	int y;
	int x;
	// key 유무체크용 && 체크맵 연산을 위한 char로 선언
	char a;		
	char b;
	char c;
	char d;
	char e;
	char f;
	int step;		//	이건 걸음수
};

char map[MAX][MAX];	//	일반 맵
char checkmap[2][2][2][2][2][2][MAX][MAX];	//	체크맵

int N;	//	1<= 가로 <= 50
int M;	//	1<= 세로 <= 50

// 시작 좌표
int sy;
int sx;

// 끝내는 플래그
bool stopPlay;

// 4방탐색용 상 하 좌 우 순서
int dy[] = { -1, +1, 0, 0 };
int dx[] = { 0, 0, -1, +1 };

int Answer;

void printMap() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			printf("%c ", map[i][j]);
		}
		printf("\n");
	}
}


void play(int y, int x) {
	queue<Man> q;
	Man sman = { y, x, 0, 0, 0, 0, 0, 0, 0 };
	checkmap[0][0][0][0][0][0][y][x] = 1;			//	키를 아무것도 가지지 않은 상태에서 현위치 체크맵 체크
	q.push(sman);

	while (!q.empty()) {
		if (stopPlay) {
			break;
		}

		Man temp = q.front();
		q.pop();

		int cy = temp.y;
		int cx = temp.x;
		char ca = temp.a;
		char cb = temp.b;
		char cc = temp.c;
		char cd = temp.d;
		char ce = temp.e;
		char cf = temp.f;
		int cstep = temp.step;
		
		for (int dir = 0; dir < 4; dir++) {
			int ny = cy + dy[dir];
			int nx = cx + dx[dir];

			if (ny < 0 || ny >= N || nx < 0 || nx >= M || map[ny][nx] == '#') {
				continue;
			}

			if (map[ny][nx] == '1') {
				Answer = cstep + 1;
				stopPlay = true;
				return;
			}
			else if (map[ny][nx] == 'A' && temp.a == 1 && checkmap[ca][cb][cc][cd][ce][cf][ny][nx] == 0) {	//	열쇠를 갖고 있으면서, 다음 위치로 갈 경우 체크
				checkmap[ca][cb][cc][cd][ce][cf][ny][nx] = 1;
				Man next = { ny, nx, ca, cb, cc, cd, ce, cf, cstep + 1 };
				q.push(next);
			}
			else if (map[ny][nx] == 'B' && temp.b == 1 && checkmap[ca][cb][cc][cd][ce][cf][ny][nx] == 0) {	//	열쇠를 갖고 있으면서, 다음 위치로 갈 경우 체크
				checkmap[ca][cb][cc][cd][ce][cf][ny][nx] = 1;
				Man next = { ny, nx, ca, cb, cc, cd, ce, cf, cstep + 1 };
				q.push(next);
			}
			else if (map[ny][nx] == 'C' && temp.c == 1 && checkmap[ca][cb][cc][cd][ce][cf][ny][nx] == 0) {	//	열쇠를 갖고 있으면서, 다음 위치로 갈 경우 체크
				checkmap[ca][cb][cc][cd][ce][cf][ny][nx] = 1;
				Man next = { ny, nx, ca, cb, cc, cd, ce, cf, cstep + 1 };
				q.push(next);
			}
			else if (map[ny][nx] == 'D' && temp.d == 1 && checkmap[ca][cb][cc][cd][ce][cf][ny][nx] == 0) {	//	열쇠를 갖고 있으면서, 다음 위치로 갈 경우 체크
				checkmap[ca][cb][cc][cd][ce][cf][ny][nx] = 1;
				Man next = { ny, nx, ca, cb, cc, cd, ce, cf, cstep + 1 };
				q.push(next);
			}
			else if (map[ny][nx] == 'E' && temp.e == 1 && checkmap[ca][cb][cc][cd][ce][cf][ny][nx] == 0) {	//	열쇠를 갖고 있으면서, 다음 위치로 갈 경우 체크
				checkmap[ca][cb][cc][cd][ce][cf][ny][nx] = 1;
				Man next = { ny, nx, ca, cb, cc, cd, ce, cf, cstep + 1 };
				q.push(next);
			}
			else if (map[ny][nx] == 'F' && temp.f == 1 && checkmap[ca][cb][cc][cd][ce][cf][ny][nx] == 0) {	//	열쇠를 갖고 있으면서, 다음 위치로 갈 경우 체크
				checkmap[ca][cb][cc][cd][ce][cf][ny][nx] = 1;
				Man next = { ny, nx, ca, cb, cc, cd, ce, 1, cstep + 1 };
				q.push(next);
			}
			else if (map[ny][nx] == '0' && checkmap[ca][cb][cc][cd][ce][cf][ny][nx] == 0) {
				checkmap[ca][cb][cc][cd][ce][cf][ny][nx] = 1;
				Man next = { ny, nx, ca, cb, cc, cd, ce, cf, cstep + 1 };
				q.push(next);
			}
			else if (map[ny][nx] == '.' && checkmap[ca][cb][cc][cd][ce][cf][ny][nx] == 0) {
				checkmap[ca][cb][cc][cd][ce][cf][ny][nx] = 1;
				Man next = { ny, nx, ca, cb, cc, cd, ce, cf, cstep + 1 };
				q.push(next);
			}
			else if (map[ny][nx] == 'a' && checkmap[ca][cb][cc][cd][ce][cf][ny][nx] == 0 && checkmap[1][cb][cc][cd][ce][cf][ny][nx] == 0) {
				checkmap[ca][cb][cc][cd][ce][cf][ny][nx] = 1;
				checkmap[1][cb][cc][cd][ce][cf][ny][nx] = 1;
				Man next{ ny, nx, 1, cb, cc, cd, ce, cf, cstep + 1 };
				q.push(next);
			}
			else if (map[ny][nx] == 'b' && checkmap[ca][cb][cc][cd][ce][cf][ny][nx] == 0 && checkmap[ca][1][cc][cd][ce][cf][ny][nx] == 0) {
				checkmap[ca][cb][cc][cd][ce][cf][ny][nx] = 1;
				checkmap[ca][1][cc][cd][ce][cf][ny][nx] = 1;
				Man next{ ny, nx, ca, 1, cc, cd, ce, cf, cstep + 1 };
				q.push(next);
			}
			else if (map[ny][nx] == 'c' && checkmap[ca][cb][cc][cd][ce][cf][ny][nx] == 0 && checkmap[ca][cb][1][cd][ce][cf][ny][nx] == 0) {
				checkmap[ca][cb][cc][cd][ce][cf][ny][nx] = 1;
				checkmap[ca][cb][1][cd][ce][cf][ny][nx] = 1;
				Man next{ ny, nx, ca, cb, 1, cd, ce, cf, cstep + 1 };
				q.push(next);
			}
			else if (map[ny][nx] == 'd' && checkmap[ca][cb][cc][cd][ce][cf][ny][nx] == 0 && checkmap[ca][cb][cc][1][ce][cf][ny][nx] == 0) {
				checkmap[ca][cb][cc][cd][ce][cf][ny][nx] = 1;
				checkmap[ca][cb][cc][1][ce][cf][ny][nx] = 1;
				Man next{ ny, nx, ca, cb, cc, 1, ce, cf, cstep + 1 };
				q.push(next);
			}
			else if (map[ny][nx] == 'e' && checkmap[ca][cb][cc][cd][ce][cf][ny][nx] == 0 && checkmap[ca][cb][cc][cd][1][cf][ny][nx] == 0) {
				checkmap[ca][cb][cc][cd][ce][cf][ny][nx] = 1;
				checkmap[ca][cb][cc][cd][1][cf][ny][nx] = 1;
				Man next{ ny, nx, ca, cb, cc, cd, 1, cf, cstep + 1 };
				q.push(next);
			}
			else if (map[ny][nx] == 'f' && checkmap[ca][cb][cc][cd][ce][cf][ny][nx] == 0 && checkmap[ca][cb][cc][cd][ce][1][ny][nx] == 0) {
				checkmap[ca][cb][cc][cd][ce][cf][ny][nx] = 1;
				checkmap[ca][cb][cc][cd][ce][1][ny][nx] = 1;
				Man next{ ny, nx, ca, cb, cc, cd, ce, 1, cstep + 1 };
				q.push(next);
			}
		}

	}
}

int main(void) {

	scanf("%d %d", &N, &M);

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			scanf("%c", &map[i][j]);

			if (map[i][j] == '\n') {
				j = j - 1;
			}
			else if (map[i][j] == '0') {
				sy = i;
				sx = j;
			}
		}
	}

	stopPlay = false;

	/*
	printf("\n");
	printMap();
	printf("\n");
	*/
	play(sy, sx);

	if (Answer == 0) {
		Answer = -1;
	}

	printf("%d \n", Answer);


	return 0;
}