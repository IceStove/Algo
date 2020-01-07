#include <iostream>
#include <cstdio>
#include <vector>
#include <queue>
#include <algorithm>
#include <stack>

#define MAX 300002

using namespace std;

int map[MAX];
int checkmap[MAX];

vector<int> v[MAX];

int N, M;	//	N:최대길이, M:주어질 텔레포트 수
int S, E;	//	S:도착점, E:시작점

void resetMap() {	//	이거 중요한게 인덱스 1부터 N까지 쓸겁니다.
	for (int i = 1; i <= N; i++) {
		map[i] = -1;
	}
}

void BFS(int cur) {
	queue<int> q;
	q.push(cur);
	map[cur] = 0;
	checkmap[cur] = 1;

	while (!q.empty()) {
		int temp = q.front();
		q.pop();

		if (temp == S) {
			break;
		}

		if (temp + 1 <= N && checkmap[temp + 1] == 0) {	//	뒤로 한 칸
			q.push(temp + 1);
			map[temp + 1] = map[temp] + 1;
			checkmap[temp + 1] = 1;
		}
		if (temp - 1 >= 1 && checkmap[temp - 1] == 0) {	//	앞으로 한 칸
			q.push(temp - 1);
			map[temp - 1] = map[temp] + 1;
			checkmap[temp - 1] = 1;
		}
		for (int i = 0; i < v[temp].size(); i++) {	//	텔레포트 가능한 지점으로 한 칸
			int next = v[temp][i];
			if (checkmap[next] == 0) {
				q.push(next);
				map[next] = map[temp] + 1;
				checkmap[next] = 1;
			}
		}
	}



}

int main(void) {
	scanf("%d %d", &N, &M);
	scanf("%d %d", &S, &E);

	for (int i = 0; i < M; i++) {	//	텔레포트 지점 서로 이어져 있음을 벡터로 표시
		int y, x;
		scanf("%d %d", &y, &x);
		v[y].push_back(x);
		v[x].push_back(y);
	}

	BFS(E);

	printf("%d \n", map[S]);

	return 0;
}