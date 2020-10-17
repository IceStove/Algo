package boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17779_게리맨더링2 {
	private static int N;
	private static int[][] map;
	private static int[][] checkmap;
	private static int d1, d2, x, y;
	private static int Answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());	//	5 <= N <= 20
		
		map = new int[N + 1][N + 1];	//	1번 인덱스부터 사용하기		
		checkmap = new int[N+1][N+1];	//	선거구를 입력할 맵
		
		
		for(int i = 1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());	//	1 <= map[i][j] <= 100
			}
		}
		
		d1 = d2 = 0;
		x = 0;	//	세로
		y = 0;	//	가로
		
		Answer = Integer.MAX_VALUE;	//	최솟값을 구하는 문제이므로 초기값을 최댓값으로 잡는다.
		
		OUTER:
		for(d1 = 1; d1 <= N; d1++) {
			for(d2 = 1; d2 <= N; d2++) {
				for(x = 1; x <= N; x++) {
					for(y = 1; y<= N; y++) {
						if(x + d1 + d2 <= N && 1<=y-d1 && y+d2 <= N) {
							resetCheckmap();
							play();
							peopleCheck();
							if(Answer == 0) {	//	이것보다 차이가 적을 순 없다.
								break OUTER;
							}
						}
					}
				}
			}
		}
		
		System.out.println(Answer);
		
	}	//	end of main

	private static void peopleCheck() {
		int[] peopleNum = new int[6];	//	1번인덱스부터 5번인덱스까지 사용할 예정
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				peopleNum[checkmap[i][j]] += map[i][j];
			}
		}
		
		for(int i = 1; i<=5; i++) {
			min = Math.min(min, peopleNum[i]);
			max = Math.max(max, peopleNum[i]);
		}

		int chai = max - min;
		if(chai < Answer) Answer = chai;
	}

	private static void resetCheckmap() {
		for(int i = 0; i<N+1; i++) {
			Arrays.fill(checkmap[i], 0);
		}
	}	//	end of resetCheckmap

	private static void play() {
		// 경계선 만들기
		for(int i = 0; i<=d1; i++) {
			checkmap[x+i][y-i] = 5;			// 1. (x,y), (x+1,y-1), ... , (x+d1, y-d1)
			checkmap[x+d2+i][y+d2-i] = 5;	// 4. (x+d2,y+d2),(x+d2+1,y+d2-1), ... , (x+d2+d1, y+d2-d1)
		}
		for(int i = 0; i<=d2; i++) {
			checkmap[x+i][y+i] = 5;			// 2. (x,y), (x+1,y+1), ... , (x+d2, y+d2)
			checkmap[x+d1+i][y-d1+i] = 5;	// 3. (x+d1,y-d1), (x+d1+1,y-d1+1), ... , (x+d1+d2,y-d1+d2)
		}
		
		// 경계선 안의 값을 5로 채우기
		for(int r = x+1; r<x+d1+d2; r++) {
			int sc = -1;
			int ec = -1;
			for(int c = 1; c<=N; c++) {
				if(checkmap[r][c] == 5) {
					if(sc == -1) {
						sc = c;
					} else {
						ec = c;
					}
				}
			}
			for(int c = sc; c<=ec; c++) {
				checkmap[r][c] = 5;
			}
		}
		
		// 1번 선거구
		for(int r = 1; r<x+d1; r++) {
			for(int c = 1; c<=y; c++) {
				if(checkmap[r][c] == 0) checkmap[r][c] = 1;
			}
		}
		
		// 2번 선거구
		for(int r = 1; r<=x+d2; r++) {
			for(int c = y+1; c<=N; c++) {
				if(checkmap[r][c] == 0) checkmap[r][c] = 2;
			}
		}
		
		// 3번 선거구
		for(int r = x+d1; r<=N; r++) {
			for(int c = 1; c<y-d1+d2; c++) {
				if(checkmap[r][c] == 0) checkmap[r][c] = 3;
			}
		}
		
		// 4번 선거구
		for(int r = x+d2+1; r<=N; r++) {
			for(int c = y-d1+d2; c<=N; c++) {
				if(checkmap[r][c] == 0) checkmap[r][c] = 4;
			}
		}
	}	//	end of play
}	//	end of class
