#include <iostream>
#include <cstdio>
#include <queue>
#include <vector>
#include <algorithm>
#include <stack>

#define MAX	1502

using namespace std;

char map[MAX][MAX];	//	'.':물, 'X':빙판, 'L':백조
char meltmap[MAX][MAX];
int timemap[MAX][MAX];
int checkmap[MAX][MAX];

int R, C;		//	1<= R, C <=1500
int danjiNum;
int allMeltingDay = 0;
int Answer = 0;

int dy[] = { -1, +1, 0, 0 };
int dx[] = { 0, 0, -1, +1 };

vector<pair<int, int>> v;	//	백조 위치 저장
vector<pair<int, int>> m;	//	녹일 위치 저장
queue<pair<int, int>> Q;	//	처음엔 물 위치 저장하고, 나중에 얼마 후에 녹을지 알아내는 BFS에 쓰일 것

void printMap() {
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			printf("%c ", map[i][j]);
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

void setTimemap() {
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			timemap[i][j] = -1;
		}
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

void TimeBFS() {
	while (!Q.empty()) {
		pair<int, int> temp = Q.front();
		Q.pop();

		for (int dir = 0; dir < 4; dir++) {
			int ny = temp.first + dy[dir];
			int nx = temp.second + dx[dir];

			if (ny < 0 || ny >= R || nx < 0 || nx >= C || timemap[ny][nx] != -1 || map[ny][nx] == '.') {
				continue;
			}

			if (map[ny][nx] == 'X') {
				timemap[ny][nx] = timemap[temp.first][temp.second] + 1;
				Q.push(make_pair(ny, nx));
				allMeltingDay = timemap[ny][nx];
			}
		}
	}
}

void FindBFS(int y, int x, int num, int day) {
	queue<pair<int, int>> q;
	q.push(make_pair(y, x));
	checkmap[y][x] = num;

	while (!q.empty()) {
		pair<int, int> temp = q.front();
		q.pop();

		for (int dir = 0; dir < 4; dir++) {
			int ny = temp.first + dy[dir];
			int nx = temp.second + dx[dir];

			if (ny < 0 || ny >= R || nx < 0 || nx >= C || checkmap[ny][nx] != 0) {
				continue;
			}

			if (timemap[ny][nx] <= day) {	//	일정 기간 이후엔 바다가 될 땅들이기 때문에 입력된 날짜 수보다 적은 타임맵을 가진 곳은 바다라고 봐도 무방하므로 돌린다.
				checkmap[ny][nx] = num;
				q.push(make_pair(ny, nx));
			}
		}
	}
}

bool checkFind(int day) {
	bool result = false;

	resetCheckmap();
	FindBFS(v[0].first, v[0].second, 1, day);

	if (checkmap[v[1].first][v[1].second] == 1) {
		result = true;
	}

	return result;
}

int main(void) {

	scanf("%d %d", &R, &C);

	setTimemap();

	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			scanf("%c", &map[i][j]);
			if (map[i][j] == '\n') {
				j = j - 1;
			}
			else if (map[i][j] == 'L') {	//	백조 좌표 벡터에 저장
				v.push_back(make_pair(i, j));
				map[i][j] = '.';	//	백조 저장 후에 물로 변경해준다.
			}

			if (map[i][j] == '.') {	//	물 위치에 올 때마다 큐에 저장
				Q.push(make_pair(i, j));
				timemap[i][j] = 0;
			}
		}
	}

	/*
	printf("\n");
	printMap();
	printf("\n");
	*/

	TimeBFS();

	/*
	printf("\n");
	printTimemap();
	printf("\n");
	printf("%d \n", allMeltingDay);
	*/

	// 이분 탐색으로 최소한의 날을 찾는다
	int left = 0;
	int right = allMeltingDay;
	int mid;

	while (left <= right) {
		mid = left + (right - left) / 2;

		if (checkFind(mid)) {	//	이어져 있을 때 더 적은 날짜로도 되는지 확인하자
			Answer = mid;
			right = mid - 1;
		}
		else {	//	이어져 있지 않을 때 더 많은 날짜에서 이어지는지 확인하자
			left = mid + 1;
		}
	}

	printf("%d \n", Answer);


	return 0;
}