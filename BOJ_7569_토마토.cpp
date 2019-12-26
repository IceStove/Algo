#include <iostream>
#include <cstdio>
#include <queue>
#include <vector>
#include <algorithm>

#define MAX 102

using namespace std;

struct point {
	int z;
	int y;
	int x;
};

int map[MAX][MAX][MAX];
int checkmap[MAX][MAX][MAX];
vector<point> v;

int Answer;

int H;	//	높이
int N;	//	세로
int M;	//	가로

int dz[] = { -1, +1, 0, 0, 0, 0 };
int dy[] = { 0, 0, -1, +1, 0, 0 };
int dx[] = { 0, 0, 0, 0, -1, +1 };

void BFS() {
	queue<point> q;
	for (int i = 0; i < v.size(); i++) {
		q.push(v[i]);
		checkmap[v[i].z][v[i].y][v[i].x] = 1;
	}

	while (!q.empty()) {
		point temp = q.front();
		q.pop();

		for (int dir = 0; dir < 6; dir++) {
			int nz = temp.z + dz[dir];
			int ny = temp.y + dy[dir];
			int nx = temp.x + dx[dir];

			if (nz < 0 || nz >= H || ny < 0 || ny >= N || nx < 0 || nx >= M || checkmap[nz][ny][nx] != 0) {
				continue;
			}

			if (map[nz][ny][nx] == 0) {
				point next = { nz, ny, nx };
				checkmap[nz][ny][nx] = checkmap[temp.z][temp.y][temp.x] + 1;
				map[nz][ny][nx] = 1;
				q.push(next);
			}
		}
	}
}

int main(void) {

	scanf("%d %d %d", &M, &N, &H);

	for (int i = 0; i < H; i++) {
		for (int j = 0; j < N; j++) {
			for (int k = 0; k < M; k++) {
				scanf("%d", &map[i][j][k]);
				if (map[i][j][k] == 1) {
					v.push_back({ i, j, k });
				}
			}
		}
	}

	/*
	for (int i = 0; i < v.size(); i++) {
		printf("v[%d] = {%d, %d, %d} \n", i, v[i].z, v[i].y, v[i].x);
	}
	*/

	int need = 0;
	int tempMax = -1;

	for (int i = 0; i < H; i++) {
		for (int j = 0; j < N; j++) {
			for (int k = 0; k < M; k++) {
				if (map[i][j][k] == 0) {
					need = 1;
				}
			}
		}
	}

	if (need == 0) {
		Answer = 0;
	}
	else {
		BFS();

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (tempMax < checkmap[i][j][k]) {	//	맥스값으로 계속 치환
						tempMax = checkmap[i][j][k];
					}
				}
			}
		}

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (map[i][j][k] == 0) {
						Answer = -1;
					}
				}
			}
		}

		if (Answer != -1) {
			Answer = tempMax - 1;
		}

	}

	printf("%d \n", Answer);

	
	return 0;
}