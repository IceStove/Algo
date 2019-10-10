#include <iostream>
#include <cstdio>
#include <queue>
#include <stack>
#include <vector>
#include <algorithm>

#define MAX 105

using namespace std;

int	R, C;	//	R:줄, C:칸
int N;		//	N:명령 수
int num;	//	단지번호 붙일 용도

char map[MAX][MAX];	//	지도
char check_map[MAX][MAX];	//	체크전용 맵
int new_map[MAX][MAX];	//	단지 번호 붙일 맵
int order[MAX];		//	명령

const int dy[] = { -1, +1, 0, 0 };
const int dx[] = { 0, 0, -1, +1 };


void printMap() {	//	출력은 1인덱스까지
	for (int i = R; i >= 1; i--) {
		for (int j = 1; j <= C; j++) {
			printf("%c", map[i][j]);
		}
		printf("\n");
	}
}

void copyMap() {	//	카피는 0인덱스까지
	for (int i = 0; i <= R; i++) {
		for (int j = 1; j <= C; j++) {
			if (new_map[i][j] == 1 || new_map[i][j] == 2 || new_map[i][j] == 3) {
				map[i][j] = 'x';
			}
			else {
				map[i][j] = '.';
			}
		}
	}
}

void printCheckMap() {	//	체크맵 출력은 0인덱스까지
	for (int i = R; i >= 0; i--) {
		for (int j = 1; j <= C; j++) {
			printf("%d ", check_map[i][j]);
		}
		printf("\n");
	}
}

void resetCheckMap() {	//	리셋은 0인덱스까지
	for (int i = R; i >= 0; i--) {
		for (int j = 1; j <= C; j++) {
			check_map[i][j] = 0;
		}
	}
}

void printNewMap() {	//	뉴맵 출력은 0인덱스까지
	for (int i = R; i >= 0; i--) {
		for (int j = 1; j <= C; j++) {
			printf("%d ", new_map[i][j]);
		}
		printf("\n");
	}
}

void resetNewMap() {	//	리셋은 0인덱스까지
	for (int i = R; i >= 0; i--) {
		for (int j = 1; j <= C; j++) {
			new_map[i][j] = 0;
		}
	}
}

void BFS(int y, int x, int danji_num) {
	queue<pair<int, int> > q;
	q.push(make_pair(y, x));
	check_map[y][x] = 1;
	new_map[y][x] = danji_num;

	while (!q.empty()) {
		pair<int, int> temp;
		temp = q.front();
		q.pop();

		for (int dir = 0; dir < 4; dir++) {
			int ny = temp.first + dy[dir];
			int nx = temp.second + dx[dir];

			if (ny < 0 || ny > R || nx < 0 || nx > C || check_map[ny][nx] == 1) {
				continue;
			}

			if (map[ny][nx] == 'x') {
				q.push(make_pair(ny, nx));
				check_map[ny][nx] = 1;
				new_map[ny][nx] = danji_num;
			}
		}
	}
}

void CheckDanji(void) {
	num = 0;
	for (int i = 0; i <= R; i++) {
		for (int j = 1; j <= C; j++) {
			if (map[i][j] == 'x' && check_map[i][j] == 0) {
				num += 1;
				BFS(i, j, num);
			}
		}
	}
}

int findDownCount(int danji_num) {
	int count = 10000;
	int temp_count = 0;

	for (int i = 1; i <= R; i++) {
		for (int j = 1; j <= C; j++) {
			if (new_map[i][j] == danji_num) {
				for (int k = i-1; k >= 0; k--) {
					if (new_map[k][j] == 0) {
						// 계속
					}
					else if (new_map[k][j] == 1) {
						temp_count = i - k - 1;
						if (count > temp_count) count = temp_count;

						break;
					}
					else {	//	1이 아닌 다른 단지번호
						break;
					}
				}
			}
		}
	}
	return count;
}

void MoveDown(int danji_num, int downcount) {
	for (int i = 0; i <= R; i++) {
		for (int j = 1; j <= C; j++) {
			if (new_map[i][j] == danji_num) {
				new_map[i - downcount][j] = 1;	//	해당 단지 번호로 안떨어뜨리고, 바로 그냥 1로 만들어서 붙여버림
				new_map[i][j] = 0;				//	기존에 있던 칸은 비워버림
			}
		}
	}
}

int main(void) {
	// 지도 입력
	scanf("%d %d", &R, &C);
	for (int i = R; i >= 1; i--) {
		for (int j = 1; j <= C; j++) {
			scanf("%c", &map[i][j]);
			if (map[i][j] == '\n') {
				j = j - 1;
			}
		}
	}
	for (int j = 1; j <= C; j++) {	//	아랫줄 크리스탈로 채워버리기
		map[0][j] = 'x';
	}
	// 명령 입력
	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		scanf("%d", &order[i]);
	}


	/*
	// 지도 입력 잘 되었는지 확인용
	printf("\n");
	printMap();
	printf("\n");
	*/

	/*
	// 단지체크 잘하는지 확인용
	CheckDanji();
	printf("\n");
	printNewMap();
	printf("\n");
	*/
	for (int k = 0; k < N; k++) {
		int i = order[k];
		if (k % 2 == 0) {	//	왼쪽에서 발사
			int j;
			for (j = 1; j <= C; j++) {
				if (map[i][j] == 'x') {	//	크리스탈 만남
					map[i][j] = '.';
					break;
				}
			}
			if (j == C + 1) continue;
			CheckDanji();
			if (num > 1) {	//	단지번호를 붙였는데 1이 넘어??? 중력으로 떨어뜨려
				// 우선 아래칸에 잘린 조각이 있다면?
				if (i - 1 >= 1 && new_map[i - 1][j] != 0 && new_map[i - 1][j] != 1) {
					int dc = findDownCount(new_map[i - 1][j]);
					MoveDown(new_map[i - 1][j], dc);
				}
				// 다음 윗칸에 잘린 조각이 있다면??
				if (i + 1 <= R && new_map[i + 1][j] != 0 && new_map[i + 1][j] != 1) {
					int dc = findDownCount(new_map[i + 1][j]);
					MoveDown(new_map[i + 1][j], dc);
				}
				// 그 다음 오른쪽에 잘린 조각이 있다면??
				if (j + 1 <= C && new_map[i][j + 1] != 0 && new_map[i][j + 1] != 1) {
					int dc = findDownCount(new_map[i][j + 1]);
					MoveDown(new_map[i][j + 1], dc);
				}
				
			}
		}
		else {	//	오른쪽에서 발사
			int j;
			for (j = C; j >= 1; j--) {
				if (map[i][j] == 'x') {
					map[i][j] = '.';
					break;
				}
			}
			if (j == 0) continue;
			CheckDanji();
			if (num > 1) {
				// 우선 아래칸에 잘린 조각이 있다면?
				if (i - 1 >= 1 && new_map[i - 1][j] != 0 && new_map[i - 1][j] != 1) {
					int dc = findDownCount(new_map[i - 1][j]);
					MoveDown(new_map[i - 1][j], dc);
				}
				// 다음 윗칸에 잘린 조각이 있다면??
				if (i + 1 <= R && new_map[i + 1][j] != 0 && new_map[i + 1][j] != 1) {
					int dc = findDownCount(new_map[i + 1][j]);
					MoveDown(new_map[i + 1][j], dc);
				}
				// 그 다음 왼쪽에 잘린 조각이 있다면?
				if (j - 1 >= 1 && new_map[i][j - 1] != 0 && new_map[i][j - 1] != 1) {
					int dc = findDownCount(new_map[i][j - 1]);
					MoveDown(new_map[i][j - 1], dc);
				}
			}
		}

		copyMap();
		/*
		printf("order[%d] 수행 후 지도 \n", k);
		printNewMap();
		printf("\n");
		printMap();
		printf("\n");
		*/
		resetCheckMap();
		resetNewMap();
	}

	// 결과 확인
	//printf("\n");
	printMap();
	//printf("\n");

	return 0;
}