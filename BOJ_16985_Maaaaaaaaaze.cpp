#include <iostream>
#include <cstdio>
#include <queue>
#include <vector>
#include <stack>
#include <algorithm>

using namespace std;

int playmap[5][5][5];
int checkmap[5][5][5];

int savemap[4][5][5][5];

int ch[5];

int Answer = 10000;

struct point {
	int z;
	int y;
	int x;
};

int dz[] = { 0, 0, 0, 0, -1, +1 };
int dy[] = { -1, +1, 0, 0, 0, 0 };
int dx[] = { 0, 0, -1, +1, 0, 0 };

void printMap(int num) {
	for (int i = 0; i < 5; i++) {
		printf("%d층 \n", i);
		for (int j = 0; j < 5; j++) {
			for (int k = 0; k < 5; k++) {
				printf("%d ", savemap[num][i][j][k]);
			}
			printf("\n");
		}
		printf("\n");
	}
}

void makeMap() {
	for (int s = 0; s < 3; s++) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < 5; k++) {
					savemap[s + 1][i][k][4-j] = savemap[s][i][j][k];
				}
			}
		}
	}
}


void resetCheckmap() {
	for (int i = 0; i < 5; i++) {
		for (int j = 0; j < 5; j++) {
			for (int k = 0; k < 5; k++) {
				checkmap[i][j][k] = -1;
			}
		}
	}
}

void BFS() {
	queue<point> q;
	q.push({ 0,0,0 });
	checkmap[0][0][0] = 0;

	while (!q.empty()) {
		point temp = q.front();
		q.pop();

		if (temp.z == 4 && temp.y == 4 && temp.x == 4) {
			break;
		}

		point next;
		for (int dir = 0; dir < 6; dir++) {
			next.z = temp.z + dz[dir];
			next.y = temp.y + dy[dir];
			next.x = temp.x + dx[dir];

			if (next.z < 0 || next.z >= 5 || next.y < 0 || next.y >= 5 || next.x < 0 || next.x >= 5 || checkmap[next.z][next.y][next.x] != -1 || playmap[next.z][next.y][next.x] == 0) {
				continue;
			}

			checkmap[next.z][next.y][next.x] = checkmap[temp.z][temp.y][temp.x] + 1;
			q.push(next);
		}
	}
}


void comp() {
	vector<int> v;
	for(int i = 0; i<5; i++){
		v.push_back(i);
	}
	sort(v.begin(), v.end());
	
	do {
		for (int num = 0; num < 5; num++) {
			//printf("%d ", v[num]);
			ch[num] = v[num];
		}
		for (int a = 0; a < 4; a++){
			for (int b = 0; b < 4; b++) {
				for (int c = 0; c < 4; c++) {
					for (int d = 0; d < 4; d++) {
						for (int e = 0; e < 4; e++) {
							for (int y = 0; y < 5; y++) {
								for (int x = 0; x < 5; x++) {
									playmap[ch[0]][y][x] = savemap[a][0][y][x];
									playmap[ch[1]][y][x] = savemap[b][1][y][x];
									playmap[ch[2]][y][x] = savemap[c][2][y][x];
									playmap[ch[3]][y][x] = savemap[d][3][y][x];
									playmap[ch[4]][y][x] = savemap[e][4][y][x];

								}
							}	//	end of input 1평면

							if (playmap[0][0][0] == 1 && playmap[4][4][4] == 1 && Answer > 12) {
								resetCheckmap();
								BFS();
								if (checkmap[4][4][4] != -1 && checkmap[4][4][4] < Answer) {
									Answer = checkmap[4][4][4];
									//printf("임시 Answer = %d \n", Answer);
								}
							}
						}
					}
				}
			}
		}
		//printf("\n");
	} while (next_permutation(v.begin(), v.end()));
}

int main(void) {
	
	for (int i = 0; i < 5; i++) {
		for (int j = 0; j < 5; j++) {
			for (int k = 0; k < 5; k++) {
				scanf("%d", &savemap[0][i][j][k]);
			}
		}
	}

	makeMap();

	/*
	printf("\n");
	printMap(0);
	printf("\n");

	printf("\n");
	printMap(1);
	printf("\n");
	
	printf("\n");
	printMap(2);
	printf("\n");
	
	printf("\n");
	printMap(3);
	printf("\n");
	*/

	comp();

	if (Answer == 10000) {
		Answer = -1;
	}

	printf("%d \n", Answer);

	return 0;
}