#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>

#define MAX 16

using  namespace std;

int savemap[MAX][MAX];
int map[MAX][MAX];

int N, M;	//	3<= 행, 열 <=15
int D;		//	1<= 사거리 <=10

int Answer = 0;

pair<int, int> p[3];

void printSavemap() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			printf("%d ", savemap[i][j]);
		}
		printf("\n");
	}
}

void setMap() {
	for (int i = 0; i <= N; i++) {
		for (int j = 0; j < M; j++) {
			map[i][j] = savemap[i][j];
		}
	}
}

void printMap() {
	for (int i = 0; i <= N; i++) {
		for (int j = 0; j < M; j++) {
			printf("%d ", map[i][j]);
		}
		printf("\n");
	}
}

void comb() {
	vector<int> v;
	for (int i = 0; i < M; i++) {
		v.push_back(i);
	}
	sort(v.begin(), v.end());

	vector<int> ind;
	for (int i = 0; i < 3; i++) {
		ind.push_back(1);
	}
	for (int i = 3; i < M; i++) {
		ind.push_back(2);
	}
	sort(ind.begin(), ind.end());

	do {
		setMap();	//	지도 세팅
		/*
		for (int i = 0; i < M; i++) {	//	궁수 라인 비우기
			map[N][i] = 0;
		}
		*/
		int num = 0;
		for (int i = 0; i < v.size(); i++) {
			if (ind[i] == 1) {
				map[N][v[i]] = 9;	//	궁수 위치 지정
				p[num] = make_pair(N, v[i]);
				num++;
			}
		}
		// 여기서부터는 궁수들로 진행
		int die = 0;
		for (int pn = 0; pn < N; pn++) {	//	N줄이니까 N번 내려오게 해줌
			// play할 때 마다 죽이고
			for (int n = 0; n < 3; n++) {	//	n번째 궁수로 사냥 시작
				bool pass = false;
				for (int d = 1; d <= D; d++) {	//	사거리만큼 사냥 시작
					for (int i = 0, j = d; i <= d; i++, j--) {
						if (pass) break;
						int ny = p[n].first - i;
						int nx = p[n].second - j;
						if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
							continue;
						}
						if (map[ny][nx] != 9 && map[ny][nx] != 0) {
							map[ny][nx]++;
							pass = true;
						}
					}
					if (pass) break;
					for (int i = d, j = 0; i >= 0; i--, j++) {
						if (pass) break;
						int ny = p[n].first - i;
						int nx = p[n].second + j;
						if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
							continue;
						}
						if (map[ny][nx] != 9 && map[ny][nx] != 0) {
							map[ny][nx]++;
							pass = true;
						}
					}
					if (pass) break;
				}
			}

			// 죽은 애를 여기에서 체크
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] > 1) {
						die++;
						map[i][j] = 0;
					}
				}
			}

			// 그 다음 남은 애들 한칸씩 아래로 내려오기
			for (int j = 0; j < M; j++) {	//	맨 아랫줄 비워주기
				map[N - 1][j] = 0;
			}
			for (int i = N - 2; i >= 0; i--) {	//	나머지 한 줄씩 내려주기
				for (int j = 0; j < M; j++) {
					map[i + 1][j] = map[i][j];
				}
			}
			for (int j = 0; j < M; j++) {	//	맨 윗줄 비워주기
				map[0][j] = 0;
			}
		}

		if (Answer < die) {
			Answer = die;
		}

	} while (next_permutation(ind.begin(), ind.end()));
}


int main(void) {

	scanf("%d %d %d", &N, &M, &D);

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			scanf("%d", &savemap[i][j]);
		}
	}

	/*
	// 맵 출력
	printf("\n");
	printSavemap();
	printf("\n");
	*/
	comb();

	printf("%d \n", Answer);

	return 0;
}