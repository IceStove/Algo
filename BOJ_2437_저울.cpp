#include <iostream>
#include <cstdio>
#include <algorithm>
#include <queue>
#include <vector>
#include <stack>

#define MAX 1002

using namespace std;

int chu[MAX];

int N;

int Answer = -1;

void printChu() {
	for (int i = 0; i < N; i++) {
		printf("%d ", chu[i]);
	}
	printf("\n");
}


int main(void) {

	scanf("%d", &N);

	for (int i = 0; i < N; i++) {
		scanf("%d", &chu[i]);
	}

	//	정렬
	//	첫번째 인자는 시작지점 = 배열의 포인터
	//	두번째 인자는 끝나는 지점 + 1 = chu(배열의 포인터) + 배열의 크기
	sort(chu, chu+N);
	
	/*
	printf("\n");
	printChu();
	printf("\n");
	*/
	int sum = chu[0];

	if (chu[0] != 1) {
		Answer = 1;
	}
	else {
		for (int i = 1; i < N; i++) {
			if (sum+1 >= chu[i]) {	//	가능한 것
				sum += chu[i];
			}
			else {
				Answer = sum + 1;
				break;
			}
		}
	}

	if (Answer == -1) {
		Answer = sum + 1;
	}

	printf("%d \n", Answer);


	return 0;
}