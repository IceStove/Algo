#include <iostream>
#include <cstdio>
#include <stack>
#include <queue>
#include <vector>
#include <algorithm>

#define MAX 1004

using namespace std;

char map[MAX][MAX];
int fmap[MAX][MAX];
int jmap[MAX][MAX];

int R, C;
int Answer = -1;


pair<int, int> J;
vector<pair<int, int>> F;

int dy[] = { -1, +1, 0, 0 };
int dx[] = { 0, 0, -1, +1 };

void printMap() {
	for (int i = 0; i < R + 2; i++) {
		for (int j = 0; j < C + 2; j++) {
			printf("%c ", map[i][j]);
		}
		printf("\n");
	}
}

void printJmap() {
	for (int i = 0; i < R + 2; i++) {
		for (int j = 0; j < C + 2; j++) {
			printf("%d ", jmap[i][j]);
		}
		printf("\n");
	}
}

void printFmap() {
	for (int i = 0; i < R + 2; i++) {
		for (int j = 0; j < C + 2; j++) {
			printf("%d ", fmap[i][j]);
		}
		printf("\n");
	}
}

void resetJmap() {
	for (int i = 0; i < R + 2; i++) {
		for (int j = 0; j < C + 2; j++) {
			jmap[i][j] = -1;
		}
	}
}

void resetFmap() {
	for (int i = 0; i < R + 2; i++) {
		for (int j = 0; j < C + 2; j++) {
			fmap[i][j] = -1;
		}
	}
}

void play() {
	queue<pair<int, int>> jq;	//	지훈 큐
	jq.push(J);
	jmap[J.first][J.second] = 0;

	queue<pair<int, int>> fq;	//	불 큐
	for (int i = 0; i < F.size(); i++) {
		fq.push(F[i]);
		fmap[F[i].first][F[i].second] = 1;
	}

	bool pass = false;

	while (!(jq.size() == 0 && fq.size() == 0)) {
		if (pass) break;

		int jT = 0;
		int jqSize = jq.size();
		while (jT < jqSize) {
			pair<int, int> temp = jq.front();
			jq.pop();
			jT++;

			if (map[temp.first][temp.second] == 'F') {
				continue;
			}

			if (map[temp.first][temp.second] == 'G') {
				Answer = jmap[temp.first][temp.second];
				pass = true;
				break;
			}

			for (int dir = 0; dir < 4; dir++) {
				int ny = temp.first + dy[dir];
				int nx = temp.second + dx[dir];

				if (ny < 0 || ny > R + 1 || nx < 0 || nx > C + 1) {
					continue;
				}
				if (jmap[ny][nx] == -1 && (map[ny][nx] == '.' || map[ny][nx] == 'G')) {
					jmap[ny][nx] = jmap[temp.first][temp.second] + 1;
					jq.push(make_pair(ny, nx));
				}
			}
		}

		int fT = 0;
		int fqSize = fq.size();
		while (fT < fqSize) {
			pair<int, int> temp = fq.front();
			fq.pop();
			fT++;

			for (int dir = 0; dir < 4; dir++) {
				int ny = temp.first + dy[dir];
				int nx = temp.second + dx[dir];

				if (ny < 1 || ny > R || nx < 1 || nx > C || fmap[ny][nx] == 1) {
					continue;
				}

				if (map[ny][nx] == '.') {
					map[ny][nx] = 'F';
					fmap[ny][nx] = 1;
					fq.push(make_pair(ny, nx));
				}
			}
		}
	}
}

int main(void) {

	scanf("%d %d", &R, &C);

	for (int i = 1; i <= R; i++) {
		for (int j = 1; j <= C; j++) {
			scanf("%c", &map[i][j]);
			if (map[i][j] == '\n') {
				j = j - 1;
			}
			else if (map[i][j] == 'J') {
				J = make_pair(i, j);
				map[i][j] = '.';
			}
			else if (map[i][j] == 'F') {
				F.push_back(make_pair(i, j));
			}
		}
	}

	for (int i = 0; i < R + 2; i++) {
		for (int j = 0; j < C + 2; j++) {
			if (i == 0 || i == R + 1 || j == 0 || j == C + 1) {
				map[i][j] = 'G';
			}
		}
	}

	/*
	printf("\n");
	printMap();
	printf("\n");
	*/

	resetJmap();
	resetFmap();
	play();

	/*
	printJmap();
	printf("\n");

	printFmap();
	printf("\n");
	*/

	if (Answer == -1) {
		printf("IMPOSSIBLE \n");
	}
	else {
		printf("%d \n", Answer);
	}




	return 0;
}