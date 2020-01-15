#include <iostream>
#include <cstdio>
#include <queue>
#include <vector>
#include <stack>
#include <algorithm>

#define MAX 204

using namespace std;

char map[MAX][MAX];	//	초기 & 1초후 -> 0번, 2초후 -> 1번, 3초후 -> 2번, 4초후 -> 3번
int checkmap[MAX][MAX];
int timemap[MAX][MAX];

int R, C;
int N;

int dy[] = { -1, +1, 0, 0, 0 };
int dx[] = { 0, 0, -1, +1, 0 };

void printMap() {
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			printf("%c", map[i][j]);
		}
		printf("\n");
	}
}

void printTimemap() {
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			printf("%d ", timemap[i][j]);
		}
		printf("\n");
	}
}

void printCheckmap() {
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			printf("%d ", checkmap[i][j]);
		}
		printf("\n");
	}
}

void resetCheckmap() {
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			checkmap[i][j] = 0;
		}
	}
}

void resetTimemap() {
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			timemap[i][j] = -1;
		}
	}
}

void timeGo() {
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			if (timemap[i][j] >= 0) {
				timemap[i][j] += 1;
			}
		}
	}
}

void Pung() {
	for (int i = 0; i < R; i++) {	//	3초 된 폭탄들 터트리기
		for (int j = 0; j < C; j++) {
			if (timemap[i][j] == 3) {
				for (int dir = 0; dir < 5; dir++) {
					int ny = i + dy[dir];
					int nx = j + dx[dir];

					if (ny < 0 || ny >= R || nx < 0 || nx >= C || checkmap[ny][nx] == 1) {
						continue;
					}

					checkmap[ny][nx] = 1;
					map[ny][nx] = '.';
				}
			}
		}
	}

	for (int i = 0; i < R; i++) {	//	터진 곳 시간맵을 -1로 바꿔줌
		for (int j = 0; j < C; j++) {
			if (checkmap[i][j] == 1) {
				timemap[i][j] = -1;
				checkmap[i][j] = 0;
			}
		}
	}
}

void PlantBomb() {
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			if (map[i][j] == '.') {
				map[i][j] = 'O';
				timemap[i][j] = 0;
			}
		}
	}
}

int main(void) {

	scanf("%d %d %d", &R, &C, &N);
	resetCheckmap();
	resetTimemap();
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			scanf("%c", &map[i][j]);

			if (map[i][j] == '\n') {
				j = j - 1;
			}
			else if (map[i][j] == 'O') {
				timemap[i][j] = 0;
			}
		}
	}

	// 1초까지는 timeGo
	timeGo();

	int t = 1;

	while (t < N) {
		timeGo();	//	폭탄 1살씩 먹고
		PlantBomb();	//	그 와중에 설치하고
		Pung();		//	폭탄 터트리기
		t++;
	}
	
	//printf("\n");
	printMap();
	//printf("\n");
	
	return 0;
}