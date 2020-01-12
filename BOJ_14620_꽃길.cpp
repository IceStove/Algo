#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <queue>
#include <stack>

#define MAX 12

using namespace std;

int map[MAX][MAX];
int checkmap[MAX][MAX];

int N;
int Answer = 10000;

void printMap() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			printf("%d ", map[i][j]);
		}
		printf("\n");
	}
}

void printCheckmap() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			printf("%d ", checkmap[i][j]);
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

void comb() {
	vector<int> v;
	for (int i = 0; i < (N - 2)*(N - 2); i++) {
		v.push_back(i);
	}
	sort(v.begin(), v.end());

	vector<int> ind;
	for (int i = 0; i < 3; i++) {
		ind.push_back(1);
	}
	for (int i = 3; i < (N - 2)*(N - 2); i++) {
		ind.push_back(2);
	}
	sort(ind.begin(), ind.end());

	do {
		for (int i = 0; i < v.size(); i++) {
			if (ind[i] == 1) {
				//printf("%d ", v[i]);

				int y = i / (N - 2) + 1;
				int x = i % (N - 2) + 1;

				checkmap[y][x] += 1;
				checkmap[y-1][x] += 1;
				checkmap[y+1][x] += 1;
				checkmap[y][x-1] += 1;
				checkmap[y][x+1] += 1;
			}
		}
		//printf("\n");
		int temp = 0;
		bool pung = false;
		for (int i = 0; i < N; i++) {
			if (pung) break;
			for (int j = 0; j < N; j++) {
				if (checkmap[i][j] > 1) {
					pung = true;
					break;
				}
				else if (checkmap[i][j] == 1) {
					temp += map[i][j];
				}
				else {
					//0인 부분에 대해선 아무짓도 하지 않는다.
				}
			}
		}

		if (pung == false && Answer > temp) {
			Answer = temp;
		}

		resetCheckmap();

	} while (next_permutation(ind.begin(), ind.end()));
}

int main() {

	scanf("%d", &N);

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%d", &map[i][j]);
		}
	}

	/*
	printf("\n");
	printMap();
	printf("\n");
	*/
	comb();

	printf("%d \n", Answer);

	return 0;
}