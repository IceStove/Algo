#include <iostream>
#include <cstdio>
#include <vector>
#include <queue>
#include <stack>
#include <algorithm>

#define MAX 52

using namespace std;

char smap[MAX][MAX];	//	BFS 돌릴 때 마다 맵이 손상되므로 저장하기 위한 용도
char map[MAX][MAX];
int checkmap[MAX][MAX];
int checkitem[6];

int N, M;	//	3<= N(가로),M(세로) <=50	

int Answer = 100000;

vector<pair<int, int>> item_v;
pair<int, int> sp;		//	start point
pair<int, int> ep;		//	end point

int dy[] = { -1, +1, 0, 0 };
int dx[] = { 0, 0, -1, +1 };

void setMap() {
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			map[i][j] = smap[i][j];
		}
	}
}

void printMap() {
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			printf("%c ", map[i][j]);
		}
		printf("\n");
	}
}

void resetCheckmap() {
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			checkmap[i][j] = 0;
		}
	}
}

void printCheckamp() {
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			printf("%d ", checkmap[i][j]);
		}
		printf("\n");
	}
}

void resetCheckitem() {
	for (int i = 0; i < 5; i++) {
		checkitem[i] = 0;
	}
}

void BFS(int y, int x, int goal) {
	queue<pair<int, int>> q;
	q.push(make_pair(y, x));
	checkmap[y][x] = 1;

	while (!q.empty()) {
		pair<int, int> temp = q.front();
		q.pop();

		if (temp.first == item_v[goal].first && temp.second == item_v[goal].second) {
			//map[temp.first][temp.second] = '.';
			checkitem[goal] = 1;
			break;
		}

		for (int dir = 0; dir < 4; dir++) {
			int ny = temp.first + dy[dir];
			int nx = temp.second + dx[dir];

			if (ny < 0 || ny >= M || nx < 0 || nx >= N || checkmap[ny][nx] != 0 || map[ny][nx] == '#') {
				continue;
			}

			q.push(make_pair(ny, nx));
			checkmap[ny][nx] = checkmap[temp.first][temp.second] + 1;
		}
	}
}


void comb() {
	vector<int> v;
	for (int i = 0; i < item_v.size() - 1; i++) {
		v.push_back(i);
	}
	sort(v.begin(), v.end());
	
	do {
		pair<int, int> cp = sp;	//	현재 위치는 시작 위치
		int tempAnswer = 0;
		resetCheckitem();
		for (int i = 0; i < v.size(); i++) {
			//printf("%d ", v[i]);
			BFS(cp.first, cp.second, v[i]);
			tempAnswer += (checkmap[item_v[v[i]].first][item_v[v[i]].second] - 1);
			cp = item_v[v[i]];
			resetCheckmap();
		}
		//printf("\n");
		BFS(cp.first, cp.second, item_v.size() - 1);
		tempAnswer += (checkmap[ep.first][ep.second] - 1);
		cp = ep;
		if (Answer > tempAnswer) {
			Answer = tempAnswer;
			/*
			printf("최적의 값일 때 출력 \n");
			printCheckamp();
			printf("\n");
			*/
		}
		resetCheckmap();
	} while (next_permutation(v.begin(), v.end()));
}

int main(void) {

	scanf("%d %d", &N, &M);

	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%c", &smap[i][j]);

			if (smap[i][j] == '\n') {
				j = j - 1;
			}
			else if (smap[i][j] == 'X') {
				item_v.push_back(make_pair(i, j));
			}
			else if (smap[i][j] == 'S') {
				sp = make_pair(i, j);
			}
			else if (smap[i][j] == 'E') {
				ep = make_pair(i, j);
			}
		}
	}
	
	item_v.push_back(ep);

	setMap();
	/*
	printf("\n");
	printMap();
	printf("\n");
	*/
	comb();

	printf("%d \n", Answer);

	return 0;
}