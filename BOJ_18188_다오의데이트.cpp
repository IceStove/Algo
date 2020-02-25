#include <iostream>
#include <cstdio>
#include <queue>
#include <vector>
#include <stack>
#include <algorithm>

#define MAX 22

using namespace std;

char map[MAX][MAX];		//	지도
vector<char> v[MAX];	//	명령 모음

int H, W;	//	2 <= H:세로, W:가로 <= 20
int N;		//	1 <= N:명령 <= 20

pair<int, int> D;	//	다오
pair<int, int> Z;	//	디지니

bool answer = false;

void printMap() {
	for (int i = 1; i <= H; i++) {
		for (int j = 1; j <= W; j++) {
			printf("%c ", map[i][j]);
		}
		printf("\n");
	}
}

void printOrder() {
	for (int i = 1; i <= N; i++) {
		printf("%d번째 order \n", i);
		for (int j = 0; j < 2; j++) {
			printf("%c ", v[i][j]);
		}
		printf("\n");
	}
}

void play(int num, int cy, int cx, char orders[]) {
	if (answer) {
		// 이미 답이 나왔을 경우 리턴
		return;
	}

	if (cy == Z.first && cx == Z.second) {	//	현 위치에 디지니가 있을 경우 --> 정답
		printf("YES \n");
		for (int i = 0; i < num - 1; i++) {
			printf("%c", orders[i]);
		}
		printf("\n");
		answer = true;
		return;
	}

	if (num == N + 1) {	//	움직임 횟수를 모두 소모한 경우
		return;
	}

	for (int i = 0; i < v[num].size(); i++) {
		char nextOrder = v[num][i];
		orders[num - 1] = nextOrder;

		int ny = cy;
		int nx = cx;

		if (nextOrder == 'W') {			//	상
			ny = cy - 1;
		}
		else if (nextOrder == 'S') {	//	하
			ny = cy + 1;
		}
		else if (nextOrder == 'A') {	//	좌
			nx = cx - 1;
		}
		else if (nextOrder == 'D') {	//	우
			nx = cx + 1;
		}

		if (ny <= 0 || ny > H || nx <= 0 || nx > W || map[ny][nx] == '@') {	//	다음 방향으로 이동 불가
			continue;
		}

		play(num + 1, ny, nx, orders);
	}
}

int main(void) {

	// 지도 데이터 입력
	scanf("%d %d", &H, &W);
	for (int i = 1; i <= H; i++) {
		for (int j = 1; j <= W; j++) {
			scanf("%c", &map[i][j]);

			if (map[i][j] == '\n') {
				j = j - 1;
			}
			else if (map[i][j] == 'D') {
				D = make_pair(i, j);
			}
			else if (map[i][j] == 'Z') {
				Z = make_pair(i, j);
			}
		}
	}

	// 명령 데이터 입력
	scanf("%d", &N);
	for (int i = 1; i <= N; i++) {
		for (int j = 0; j < 2; j++) {
			char order;
			// cin >> order;
			scanf("%c", &order);
			if (order == ' ' || order == '\n') {
				j = j - 1;
			}
			else {
				v[i].push_back(order);
			}
		}
	}

	/*
	// 지도 출력
	printf("\n");
	printMap();
	printf("\n");
	
	// 명령 출력
	printf("\n");
	printOrder();
	printf("\n");
	*/

	char o[MAX];

	for (int i = 0; i < MAX; i++) {
		o[i] = NULL;
	}

	play(1, D.first, D.second, o);

	if (answer == false) {
		printf("NO \n");
	}

	return 0;
}