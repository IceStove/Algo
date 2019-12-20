#include <iostream>
#include <cstdio>
#include <queue>
#include <vector>
#include <stack>
#include <algorithm>

#define MAX 105

using namespace std;

struct Hero {
	int y;
	int x;
	char gram;
	int step;
};

int map[MAX][MAX];		// 맵
char checkmap[2][MAX][MAX];	//	체크맵	--> 그람 유무
int N, M;			//	3<= N,M <=100
int T;				//	1<= T <=5000

int dy[] = { -1, +1, 0, 0 };
int dx[] = { 0, 0, -1, +1 };

int Answer;
bool endPlay;

void play(int y, int x) {
	Hero hero = { y, x, 0, 0 };
	queue<Hero> q;
	q.push(hero);
	checkmap[0][y][x] = 1;

	while (!q.empty()) {
		Hero temp = q.front();
		q.pop();

		int cy = temp.y;
		int cx = temp.x;
		char cgram = temp.gram;
		int cstep = temp.step;

		if (cstep > T) {
			break;
		}

		if (endPlay) {
			break;
		}

		for (int dir = 0; dir < 4; dir++) {
			int ny = cy + dy[dir];
			int nx = cx + dx[dir];

			if (ny <= 0 || ny > N || nx <= 0 || nx > M || checkmap[cgram][ny][nx] == 1) {	//	범위 초과, 갔던 곳
				continue;
			}

			if (ny == N && nx == M) {
				Answer = cstep + 1;
				endPlay = true;
				return;
			}

			if (map[ny][nx] == 0) {	//	빈공간은 그냥 패스
				Hero nhero = { ny, nx, cgram, cstep+1 };
				q.push(nhero);
				checkmap[cgram][ny][nx] = 1;
			}
			else if (map[ny][nx] == 1 && cgram == 1) {	//	마법의 벽
				Hero nhero = { ny, nx, cgram, cstep+1 };
				q.push(nhero);
				checkmap[cgram][ny][nx] = 1;
			}
			else if (map[ny][nx] == 2) {	//	그람이 놓여 있음
				Hero nhero = { ny, nx, 1, cstep+1 };
				q.push(nhero);
				checkmap[cgram][ny][nx] = 1;
				checkmap[1][ny][nx] = 1;
			}
		}
	}
}

int main(void) {

	scanf("%d %d %d", &N, &M, &T);

	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			scanf("%d", &map[i][j]);	//	0:빈 공간, 1:마법의 벽, 2:그람
		}
	}

	for (int i = 0; i <= N + 1; i++) {
		for (int j = 0; j <= M + 1; j++) {
			if (i == 0 || i == N + 1 || j == 0 || j == M + 1) {
				map[i][j] = 1;
			}
		}
	}

	endPlay = false;
	
	play(1, 1);

	if (Answer > T || Answer == 0) {
		printf("Fail \n");
	}
	else {
		printf("%d \n", Answer);
	}

	return 0;
}