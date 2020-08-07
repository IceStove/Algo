#include <iostream>
#include <cstdio>
#include <queue>
#include <vector>
#include <stack>
#include <algorithm>

#define MAX 51

using namespace std;

int map[MAX][MAX];
int checkmap[MAX][MAX];
char inputMap[MAX][MAX];

int M, N;	//	M:세로, N:가로 <= 50

// 상 하 좌 우 순서
int dy[] = { -1, +1, 0, 0 };
int dx[] = { 0, 0, -1, +1 };

int Answer = 0;

void printMap() {
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			printf("%d ", map[i][j]);
		}
		printf("\n");
	}
}

void printCheckmap() {
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			printf("%d ", checkmap[i][j]);
		}
		printf("\n");
	}
}

void resetCheckmap() {
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			checkmap[i][j] = -1;
		}
	}
}

void BFS(int y, int x) {
	pair<int, int> sp = make_pair(y, x);
	checkmap[y][x] = 0;
	
	queue<pair<int, int>> q;
	q.push(sp);

	while (!q.empty()) {
		pair<int, int> temp;
		temp = q.front();
		q.pop();

		for (int dir = 0; dir < 4; dir++) {
			int ny = temp.first + dy[dir];
			int nx = temp.second + dx[dir];

			if (ny < 0 || ny >= M || nx < 0 || nx >= N || checkmap[ny][nx] != -1 || map[ny][nx] != 1) {
				continue;
			}

			checkmap[ny][nx] = checkmap[temp.first][temp.second] + 1;
			if (checkmap[ny][nx] > Answer) Answer = checkmap[ny][nx];
			pair<int, int> next = make_pair(ny, nx);
			q.push(next);
		}
	}
}

int main(void) {

	scanf("%d %d", &M, &N);

	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%c", &inputMap[i][j]);
			if (inputMap[i][j] == '\n') {
				j = j - 1;
			}
		}
	}

	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			if (inputMap[i][j] == 'W') {
				map[i][j] = 0;
			}
			else {	//	inputMap[i][j] == 'L';
				map[i][j] = 1;
			}
		}
	}

	//printf("\n");
	//printMap();
	//printf("\n");

	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			if (map[i][j] == 1) {
				resetCheckmap();
				BFS(i, j);
			}
		}
	}

	printf("%d \n", Answer);

	return 0;
}