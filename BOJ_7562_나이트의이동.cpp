#include <iostream>
#include <cstdio>
#include <vector>
#include <queue>
#include <algorithm>
#include <stack>

#define MAX 302

using namespace std;

int map[MAX][MAX];	//	맵
int checkmap[MAX][MAX];	//	체크맵

int T;	//	테케 수
int N;	//	맵 한 변의 길이

pair<int, int> start;	//	시작 위치
pair<int, int> goal;	//	목적 위치

int dy[] = { -2, -2, -1, -1, +2, +2, +1, +1 };
int dx[] = { -1, +1, -2, +2, -1, +1, -2, +2 };

void resetMap() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			map[i][j] = -1;
		}
	}
}

void printMap() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			printf("%d ", map[i][j]);
		}
		printf("\n");
	}
}

void resetCheckmap() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			checkmap[i][j] = 0;
		}
	}
}

void BFS(int y, int x) {
	queue<pair<int, int> >q;
	map[y][x] = 0;
	checkmap[y][x] = 1;
	q.push(make_pair(y, x));

	while (!q.empty()) {
		pair<int, int> temp = q.front();
		q.pop();

		if (temp.first == goal.first && temp.second == goal.second) {
			break;
		}

		for (int dir = 0; dir < 8; dir++) {
			int ny = temp.first + dy[dir];
			int nx = temp.second + dx[dir];

			if (ny < 0 || ny >= N || nx < 0 || nx >= N || checkmap[ny][nx] != 0) {
				continue;
			}

			q.push(make_pair(ny, nx));
			map[ny][nx] = map[temp.first][temp.second] + 1;
			checkmap[ny][nx] = 1;
		}


	}
	
}


int main(void) {

	scanf("%d", &T);

	for (int t = 0; t < T; t++) {	//	테케 수 만큼 돌아가자
		scanf("%d", &N);	//	맵 크기 받기
		scanf("%d %d", &start.first, &start.second);
		scanf("%d %d", &goal.first, &goal.second);

		//printf("%d번째 테케 입력값 \n", t);
		//printf("%d %d \n", start.first, start.second);
		//printf("%d %d \n", goal.first, goal.second);

		// 수행하기 전에 맵, 체크맵 리셋
		resetMap();
		resetCheckmap();

		BFS(start.first, start.second);

		printf("%d \n", map[goal.first][goal.second]);


	}


	return 0;
}