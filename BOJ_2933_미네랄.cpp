#include <iostream>
#include <cstdio>
#include <queue>
#include <stack>
#include <vector>
#include <algorithm>

#define MAX 105

using namespace std;

int	R, C;	//	R:��, C:ĭ
int N;		//	N:��� ��
int num;	//	������ȣ ���� �뵵

char map[MAX][MAX];	//	����
char check_map[MAX][MAX];	//	üũ���� ��
int new_map[MAX][MAX];	//	���� ��ȣ ���� ��
int order[MAX];		//	���

const int dy[] = { -1, +1, 0, 0 };
const int dx[] = { 0, 0, -1, +1 };


void printMap() {	//	����� 1�ε�������
	for (int i = R; i >= 1; i--) {
		for (int j = 1; j <= C; j++) {
			printf("%c", map[i][j]);
		}
		printf("\n");
	}
}

void copyMap() {	//	ī�Ǵ� 0�ε�������
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

void printCheckMap() {	//	üũ�� ����� 0�ε�������
	for (int i = R; i >= 0; i--) {
		for (int j = 1; j <= C; j++) {
			printf("%d ", check_map[i][j]);
		}
		printf("\n");
	}
}

void resetCheckMap() {	//	������ 0�ε�������
	for (int i = R; i >= 0; i--) {
		for (int j = 1; j <= C; j++) {
			check_map[i][j] = 0;
		}
	}
}

void printNewMap() {	//	���� ����� 0�ε�������
	for (int i = R; i >= 0; i--) {
		for (int j = 1; j <= C; j++) {
			printf("%d ", new_map[i][j]);
		}
		printf("\n");
	}
}

void resetNewMap() {	//	������ 0�ε�������
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
						// ���
					}
					else if (new_map[k][j] == 1) {
						temp_count = i - k - 1;
						if (count > temp_count) count = temp_count;

						break;
					}
					else {	//	1�� �ƴ� �ٸ� ������ȣ
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
				new_map[i - downcount][j] = 1;	//	�ش� ���� ��ȣ�� �ȶ���߸���, �ٷ� �׳� 1�� ���� �ٿ�����
				new_map[i][j] = 0;				//	������ �ִ� ĭ�� �������
			}
		}
	}
}

int main(void) {
	// ���� �Է�
	scanf("%d %d", &R, &C);
	for (int i = R; i >= 1; i--) {
		for (int j = 1; j <= C; j++) {
			scanf("%c", &map[i][j]);
			if (map[i][j] == '\n') {
				j = j - 1;
			}
		}
	}
	for (int j = 1; j <= C; j++) {	//	�Ʒ��� ũ����Ż�� ä��������
		map[0][j] = 'x';
	}
	// ��� �Է�
	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		scanf("%d", &order[i]);
	}


	/*
	// ���� �Է� �� �Ǿ����� Ȯ�ο�
	printf("\n");
	printMap();
	printf("\n");
	*/

	/*
	// ����üũ ���ϴ��� Ȯ�ο�
	CheckDanji();
	printf("\n");
	printNewMap();
	printf("\n");
	*/
	for (int k = 0; k < N; k++) {
		int i = order[k];
		if (k % 2 == 0) {	//	���ʿ��� �߻�
			int j;
			for (j = 1; j <= C; j++) {
				if (map[i][j] == 'x') {	//	ũ����Ż ����
					map[i][j] = '.';
					break;
				}
			}
			if (j == C + 1) continue;
			CheckDanji();
			if (num > 1) {	//	������ȣ�� �ٿ��µ� 1�� �Ѿ�??? �߷����� ����߷�
				// �켱 �Ʒ�ĭ�� �߸� ������ �ִٸ�?
				if (i - 1 >= 1 && new_map[i - 1][j] != 0 && new_map[i - 1][j] != 1) {
					int dc = findDownCount(new_map[i - 1][j]);
					MoveDown(new_map[i - 1][j], dc);
				}
				// ���� ��ĭ�� �߸� ������ �ִٸ�??
				if (i + 1 <= R && new_map[i + 1][j] != 0 && new_map[i + 1][j] != 1) {
					int dc = findDownCount(new_map[i + 1][j]);
					MoveDown(new_map[i + 1][j], dc);
				}
				// �� ���� �����ʿ� �߸� ������ �ִٸ�??
				if (j + 1 <= C && new_map[i][j + 1] != 0 && new_map[i][j + 1] != 1) {
					int dc = findDownCount(new_map[i][j + 1]);
					MoveDown(new_map[i][j + 1], dc);
				}
				
			}
		}
		else {	//	�����ʿ��� �߻�
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
				// �켱 �Ʒ�ĭ�� �߸� ������ �ִٸ�?
				if (i - 1 >= 1 && new_map[i - 1][j] != 0 && new_map[i - 1][j] != 1) {
					int dc = findDownCount(new_map[i - 1][j]);
					MoveDown(new_map[i - 1][j], dc);
				}
				// ���� ��ĭ�� �߸� ������ �ִٸ�??
				if (i + 1 <= R && new_map[i + 1][j] != 0 && new_map[i + 1][j] != 1) {
					int dc = findDownCount(new_map[i + 1][j]);
					MoveDown(new_map[i + 1][j], dc);
				}
				// �� ���� ���ʿ� �߸� ������ �ִٸ�?
				if (j - 1 >= 1 && new_map[i][j - 1] != 0 && new_map[i][j - 1] != 1) {
					int dc = findDownCount(new_map[i][j - 1]);
					MoveDown(new_map[i][j - 1], dc);
				}
			}
		}

		copyMap();
		/*
		printf("order[%d] ���� �� ���� \n", k);
		printNewMap();
		printf("\n");
		printMap();
		printf("\n");
		*/
		resetCheckMap();
		resetNewMap();
	}

	// ��� Ȯ��
	//printf("\n");
	printMap();
	//printf("\n");

	return 0;
}