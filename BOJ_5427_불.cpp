#include <iostream>
#include <cstdio>
#include <queue>
#include <vector>
#include <algorithm>
#include <stack>

#define MAX 1003

using namespace std;

char map[MAX][MAX];	//	.(빈공간) #(벽) @(상근이) *(불)
int checkmap[MAX][MAX];	//	상근이 체크


int w, h;	//	1 <= w(너비), h(높이) <= 1000
int T;		//	1<= T(테케) <=100
int Answer = 0;	//	정답

// 상하좌우 순서
int dy[] = { -1, +1, 0, 0 };
int dx[] = { 0, 0, -1, +1 };

pair<int, int> s;	//	상근이 시작 지점

queue<pair<int, int>> sq;	//	상근이 Queue
queue<pair<int, int>> fq;	//	불 Queue

void printMap() {
	for (int i = 0; i < h; i++) {
		for (int j = 0; j < w; j++) {
			printf("%c ", map[i][j]);
		}
		printf("\n");
	}
}

void resetMap() {
	for (int i = 0; i < h; i++) {
		for (int j = 0; j < w; j++) {
			map[i][j] = '.';
		}
	}
}

void resetCheckmap() {
	for (int i = 0; i < h; i++) {
		for (int j = 0; j < w; j++) {
			checkmap[i][j] = 0;
		}
	}
}

void BFS() {
	int cycle = 0;
	
	while (1) {
		int fq_size = fq.size();
		int sq_size = sq.size();

		if (sq_size == 0) {
			break;
		}

		//	불 1사이클 퍼뜨리기
		for (int i = 0; i < fq_size; i++) {
			pair<int, int> temp = fq.front();
			fq.pop();
			for (int dir = 0; dir < 4; dir++) {
				int ny = temp.first + dy[dir];
				int nx = temp.second + dx[dir];

				if (ny < 0 || ny >= h || nx < 0 || nx >= w || map[ny][nx] == '#' || map[ny][nx] == '*') {
					continue;
				}

				map[ny][nx] = '*';
				fq.push(make_pair(ny, nx));
			}
		}

		//	사람 1사이클 퍼뜨리기
		for (int i = 0; i < sq_size; i++) {
			pair<int, int> temp = sq.front();
			sq.pop();
			for (int dir = 0; dir < 4; dir++) {
				int ny = temp.first + dy[dir];
				int nx = temp.second + dx[dir];

				if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
					Answer = cycle + 1;
					return;
				}
				else if (map[ny][nx] == '#' || map[ny][nx] == '*' || checkmap[ny][nx] == 1) {
					continue;
				}
				else {
					sq.push(make_pair(ny, nx));
					checkmap[ny][nx] = 1;
				}
			}
		}
		cycle += 1;
	}
}

int main(void) {
	scanf("%d", &T);

	for (int t = 0; t < T; t++) {
		Answer = 0;
		scanf("%d %d", &w, &h);
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				scanf("%c", &map[i][j]);
				if (map[i][j] == '\n') {
					j = j - 1;
				}
				else if (map[i][j] == '@') {
					s = make_pair(i, j);
					map[i][j] = '.';
					checkmap[i][j] = 1;
					sq.push(s);
				}
				else if (map[i][j] == '*') {
					fq.push(make_pair(i, j));
				}
			}
		}
		/*
		printf("\n");
		printMap();
		printf("\n");
		*/

		BFS();
		if (Answer == 0) {
			printf("IMPOSSIBLE \n");
		}
		else {
			printf("%d \n", Answer);
		}
		resetMap();
		resetCheckmap();
		while (!sq.empty()) sq.pop();
		while (!fq.empty()) fq.pop();
	}
	
	return 0;
}