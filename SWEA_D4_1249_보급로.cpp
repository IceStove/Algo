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

int T;	//	�׽�Ʈ���̽� ��
int N;	//	������ ũ��

// �� �� �� ��
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

			// ��谪 �Ѿ
			if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
				continue;
			}

			// savemap�� ������ ���� ������, ���� savemap[ny][nx]�� ���� ���� ���� ���Ϸ��� ������ �� ������ ����� ��� �н�
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
		scanf("%d", &N);	//	���� ũ�� �Է�
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				scanf("%1d", &map[i][j]);
			}
		}

		/*
		// ���� �Է� �� �Ǿ����� ���
		printf("\n");
		printMap();
		printf("\n");
		*/
		setSavemap();	//	���� ��� savemap�� -1�� �ʱ�ȭ
		BFS();
		printf("#%d %d \n", t, savemap[0][0]);
		resetSavemap();
	}


	return 0;
}