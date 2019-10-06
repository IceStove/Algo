#include <iostream>
#include <cstdio>
#include <queue>
#include <vector>
#include <stack>
#include <algorithm>

#define MAX 101

using namespace std;

int map[MAX][MAX];
int savemap[MAX][MAX];
int checkmap[MAX][MAX];

int T;	//	테스트케이스 수
int N;	//	지도의 크기

// 상 하 좌 우
const int dy[] = { -1, +1, 0, 0 };
const int dx[] = { 0, 0, -1, +1 };

void printMap(void) {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			printf("%d ", map[i][j]);
		}
		printf("\n");
	}
}

void resetMap(void) {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			map[i][j] = 0;
		}
	}
}

void printSavemap(void) {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			printf("%d ", savemap[i][j]);
		}
		printf("\n");
	}
}

void resetSavemap(void) {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			savemap[i][j] = -1;
		}
	}
}

void setSavemap(void) {
	for (int i = 0; i < MAX; i++) {
		for (int j = 0; j < MAX; j++) {
			savemap[i][j] = -1;
		}
	}
}

void printCheckmap(void) {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			printf("%d ", checkmap[i][j]);
		}
		printf("\n");
	}
}

void resetCheckmap(void) {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			checkmap[i][j] = 0;
		}
	}
}

void BFS() {
	queue<pair<int, int>> q;
	q.push(make_pair(N-1, N-1));
	//checkmap[N-1][N-1] = 1;
	savemap[N - 1][N - 1] = map[N - 1][N - 1];
	while (!q.empty()) {
		pair<int, int> temp;
		temp = q.front();
		q.pop();

		for (int dir = 0; dir < 4; dir++) {
			int ny = temp.first + dy[dir];
			int nx = temp.second + dx[dir];

			// 경계값 넘어감
			if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
				continue;
			}

			// savemap에 접근한 적이 있으며, 현재 savemap[ny][nx]에 구한 값이 지금 구하려는 값보다 더 최적의 경로인 경우 패스
			if (savemap[ny][nx] != -1 && savemap[temp.first][temp.second] + map[ny][nx] >= savemap[ny][nx]) {
				continue;
			}
	
			q.push(make_pair(ny, nx));
			savemap[ny][nx] = savemap[temp.first][temp.second] + map[ny][nx];
		}
	}
}

int main(void) {

	scanf("%d", &T);
	for (int t = 1; t <= T; t++) {
		scanf("%d", &N);	//	지도 크기 입력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				scanf("%1d", &map[i][j]);
			}
		}

		/*
		// 지도 입력 잘 되었는지 출력
		printf("\n");
		printMap();
		printf("\n");
		*/
		setSavemap();	//	최초 모든 savemap을 -1로 초기화
		BFS();
		printf("#%d %d \n", t, savemap[0][0]);
		resetSavemap();
	}


	return 0;
}