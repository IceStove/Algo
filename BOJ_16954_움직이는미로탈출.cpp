#include <iostream>
#include <cstdio>
#include <queue>
#include <vector>
#include <stack>
#include <algorithm>

#define MAX 10

using namespace std;

char map[MAX][MAX];
int checkmap[MAX][MAX];

int Answer = 0;

int dy[] = { -1, +1, 0, 0, -1, -1, +1, +1, 0 };
int dx[] = { 0, 0, -1, +1, -1, +1, -1, +1, 0 };

queue<pair<int, int>> personQ;
queue<pair<int, int>> wallQ;

void printMap() {
	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			printf("%c ", map[i][j]);
		}
		printf("\n");
	}
}

void findWall() {
	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			if (map[i][j] == '#') {
				wallQ.push(make_pair(i, j));
			}
		}
	}
}

void findPerson() {
	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			if (map[i][j] == 'W') {
				personQ.push(make_pair(i, j));
			}
		}
	}
}

void play() {
	// 사람 퍼뜨리기
	while (1) {
		if (personQ.size() == 0 || Answer == 1) {
			break;
		}
		while (!personQ.empty()) {
			pair<int, int> temp = personQ.front();
			personQ.pop();
			for (int dir = 0; dir < 9; dir++) {
				int ny = temp.first + dy[dir];
				int nx = temp.second + dx[dir];
				if (ny < 0 || ny >= 8 || nx < 0 || nx >= 8 || map[ny][nx] != '.') {
					continue;
				}
				map[ny][nx] = 'W';
				if (ny == 0 && nx == 7) Answer = 1;
			}
			if (Answer == 1) break;
		}

		// 벽 내리기
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {
				if (map[i][j] == '#') {
					map[i][j] = '.';
					if (i + 1 < 8) map[i + 1][j] = '#';
				}
			}
		}

		/*
		printf("\n");
		printMap();
		printf("\n");
		*/
		// 다음 사람 찾기
		findPerson();
	}
}

int main(void) {

	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			scanf("%c", &map[i][j]);

			if (map[i][j] == '\n') {
				j = j - 1;
			}
			else if (map[i][j] == '#') {
				wallQ.push(make_pair(i, j));
			}
		}
	}
	/*
	printf("\n");
	printMap();
	printf("\n");
	*/
	map[7][0] = 'W';
	personQ.push(make_pair(7, 0));
	
	play();
	

	printf("%d \n", Answer);

	return 0;
}