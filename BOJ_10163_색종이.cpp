#include <iostream>
#include <cstdio>
#include <queue>
#include <stack>
#include <vector>
#include <algorithm>

#define MAX	103

using namespace std;

int map[MAX][MAX];

int N;

int result[MAX];

int main(void) {

	scanf("%d", &N);

	int sx = -1;
	int sy = -1;
	int	xrange = 0;
	int yrange = 0;

	for (int i = 1; i <= N; i++) {
		scanf("%d %d %d %d", &sx, &sy, &xrange, &yrange);
		for (int y = sy; y < sy + yrange; y++) {
			for (int x = sx; x < sx + xrange; x++) {
				map[y][x] = i;
			}
		}
	}

	for (int y = 0; y < 102; y++) {
		for (int x = 0; x < 102; x++) {
			result[map[y][x]]++;
		}
	}

	for (int i = 1; i <= N; i++) {
		printf("%d \n", result[i]);
	}

	return 0;
}