#include <iostream>
#include <cstdio>
#include <vector>
#include <queue>
#include <algorithm>
#include <stack>

#define MAX 502

using namespace std;

struct turtle {
	int y;
	int x;
	char dir;	//	E, W, S, N
};

int T, N;

vector<pair<int, int>> v;
turtle tur;

int ymax;
int ymin;
int xmax;
int xmin;

int Answer;

void turnLeft() {
	if (tur.dir == 'E') {
		tur.dir = 'N';
	}
	else if (tur.dir == 'W') {
		tur.dir = 'S';
	}
	else if (tur.dir == 'S') {
		tur.dir = 'E';
	}
	else if (tur.dir == 'N') {
		tur.dir = 'W';
	}
}

void turnRight() {
	if (tur.dir == 'E') {
		tur.dir = 'S';
	}
	else if (tur.dir == 'W') {
		tur.dir = 'N';
	}
	else if (tur.dir == 'S') {
		tur.dir = 'W';
	}
	else if (tur.dir == 'N') {
		tur.dir = 'E';
	}
}

void goFront() {
	if (tur.dir == 'E') {
		tur.x += 1;
		v.push_back(make_pair(tur.y, tur.x));
	}
	else if (tur.dir == 'W') {
		tur.x -= 1;
		v.push_back(make_pair(tur.y, tur.x));
	}
	else if (tur.dir == 'S') {
		tur.y -= 1;
		v.push_back(make_pair(tur.y, tur.x));
	}
	else if (tur.dir == 'N') {
		tur.y += 1;
		v.push_back(make_pair(tur.y, tur.x));
	}
}

void goBack() {
	if (tur.dir == 'E') {
		tur.x -= 1;
		v.push_back(make_pair(tur.y, tur.x));
	}
	else if (tur.dir == 'W') {
		tur.x += 1;
		v.push_back(make_pair(tur.y, tur.x));
	}
	else if (tur.dir == 'S') {
		tur.y += 1;
		v.push_back(make_pair(tur.y, tur.x));
	}
	else if (tur.dir == 'N') {
		tur.y -= 1;
		v.push_back(make_pair(tur.y, tur.x));
	}
}


int main(void) {

	scanf("%d", &T);

	for (int t = 1; t <= T; t++) {
		v.clear();
		tur.y = 0;
		tur.x = 0;
		tur.dir = 'N';
		v.push_back(make_pair(0, 0));

		ymax = 0;
		ymin = 0;
		xmax = 0;
		xmin = 0;
		char order[MAX];
		scanf("%s", order);

		for (int i = 0; i < MAX; i++) {
			if (order[i] == '\n') {
				N = i;
				break;
			}
			if (order[i] == 'L') {
				turnLeft();
			}
			else if (order[i] == 'R') {
				turnRight();
			}
			else if (order[i] == 'F') {
				goFront();
			}
			else if (order[i] == 'B') {
				goBack();
			}
			else {
				break;
			}
		}

		for (int i = 0; i < v.size(); i++) {
			if (v[i].first > ymax) {
				ymax = v[i].first;
			}
			if (v[i].first < ymin) {
				ymin = v[i].first;
			}
			if (v[i].second > xmax) {
				xmax = v[i].second;
			}
			if (v[i].second < xmin) {
				xmin = v[i].second;
			}
		}

		Answer = (ymax - ymin) * (xmax - xmin);
		printf("%d \n", Answer);
	}


	return 0;
}