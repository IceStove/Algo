#include <iostream>
#include <cstdio>
#include <queue>
#include <vector>
#include <stack>
#include <algorithm>

#define MAX 52

using namespace std;

char map[MAX][MAX];

int N, M;

int Answer = 10000;

void printMap() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			printf("%c ", map[i][j]);
		}
		printf("\n");
	}
}

// 블랙이 시작인 보드
int makeBoardA(int sy, int sx) {
	int result = 0;
	for (int i = sy; i < sy + 8; i++) {
		for (int j = sx; j < sx + 8; j++) {
			if ((i - sy) % 2 == 0) {
				if ((j - sx) % 2 == 0) {
					if (map[i][j] == 'W') {
						result += 1;
					}
				}
				else {
					if (map[i][j] == 'B') {
						result += 1;
					}
				}
			}
			else {
				if ((j - sx) % 2 == 0) {
					if (map[i][j] == 'B') {
						result += 1;
					}
				}
				else {
					if (map[i][j] == 'W') {
						result += 1;
					}
				}
			}
		}
	}
	return result;
}

// 화이트가 시작인 보드
int makeBoardB(int sy, int sx) {
	int result = 0;
	for (int i = sy; i < sy + 8; i++) {
		for (int j = sx; j < sx + 8; j++) {
			if ((i - sy) % 2 == 0) {
				if ((j - sx) % 2 == 0) {
					if (map[i][j] == 'B') {
						result += 1;
					}
				}
				else {
					if (map[i][j] == 'W') {
						result += 1;
					}
				}
			}
			else {
				if ((j - sx) % 2 == 0) {
					if (map[i][j] == 'W') {
						result += 1;
					}
				}
				else {
					if (map[i][j] == 'B') {
						result += 1;
					}
				}
			}
		}
	}
	return result;
}

int main(void) {

	scanf("%d %d", &N, &M);

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			scanf("%c", &map[i][j]);

			if (map[i][j] == '\n') {
				j = j - 1;
			}
		}
	}
	/*
	printf("\n");
	printMap();
	printf("\n");
	*/
	for (int i = 0; i <= N - 8; i++) {
		if (Answer == 0) {
			break;
		}
		for (int j = 0; j <= M - 8; j++) {
			int tempA = makeBoardA(i, j);
			int tempB = makeBoardB(i, j);

			if (Answer > tempA) {
				Answer = tempA;
			}
			if (Answer > tempB) {
				Answer = tempB;
			}
			if (Answer == 0) {
				break;
			}
		}
	}

	printf("%d \n", Answer);

	return 0;
}