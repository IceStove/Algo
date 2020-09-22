package boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_12100_2048Easy_테두리and재귀 {
	private static int N;
	private static int[][] map;
	private static int Answer;
	private static int[][][] savemap;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		//	map에 들어갈 값은 2보다 크거나 같고, 1024보다 작거나 같은 2의 제곱꼴
		//	그리고 N+2로 하는 이유는 테두리를 두르겠다.
		map = new int[N+2][N+2];	
		savemap = new int[5][N+2][N+2];
		
		for(int i = 1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i<N+2; i++) {	//	들어갈 값 중에 1이 없기 때문에, 1을 테두리의 경계로 사용
			map[0][i] = 1;
			map[N+1][i] = 1;
			map[i][0] = 1;
			map[i][N+1] = 1;
		}
		
		// 입력이 잘 되었는지 확인용
//		for(int i = 0; i<N+2; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		Answer = 0;
		play(0);
		System.out.println(Answer);
	}	//	end of main

	private static void play(int num) {
		if(num == 5) {	//	5번을 넘겼다면 최대값 체크
			findMax();
			return;
		}
		
		for(int i = 0; i<N+2; i++) {	//	맵 저장
			savemap[num][i] = Arrays.copyOf(map[i], map[i].length);
		}
		
		for(int dir = 0; dir<4; dir++) {
			for(int i = 0; i<N+2; i++) {	//	맵 로드
				map[i] = Arrays.copyOf(savemap[num][i], savemap[num][i].length);
			}
			Move(dir);
			play(num + 1);
		}
	}	//	end of play
	
	private static void findMax() {
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				if(Answer < map[i][j]) {
					Answer = map[i][j];					
				}
			}
		}
	}	//	end of findMax

	private static void Move(int dir) {
		if(dir == 0) {	//	상
			Up();
		} else if(dir == 1) {	//	하
			Down();
		} else if(dir == 2) {	//	좌
			Left();
		} else if(dir == 3) {	//	우
			Right();
		} else {
			// 없는 경우
		}
	}	//	end of Move

	private static void Up() {
		//	1. 블록 합치기
		for(int j = 1; j<=N; j++) {
			for(int i = 1; i<=N-1; i++) {
				if(map[i][j] != 0) {
					for(int k = i+1; k<=N; k++) {
						if(map[k][j] != 0) {
							if(map[k][j] == map[i][j]) {
								map[i][j] += map[k][j];
								map[k][j] = 0;
							}
							break;
						}
					}
				}
			}
		}
		//	2. 블록 몰아넣기		--> 테두리를 둘렀기 때문에 경계값이 빈 칸인 경우를 따로 신경쓰지 않아도 된다.
		for(int j = 1; j<=N; j++) {
			for(int i = 1; i<=N; i++) {
				if(map[i][j] != 0) {
					for(int k = i-1; k>=0; k--) {
						if(map[k][j] != 0) {
							if(map[k+1][j] == 0) {
								map[k+1][j] = map[i][j];
								map[i][j] = 0;
							}
							break;
						}
					}
				}
			}
		}
	}	// end of Up
	
	private static void Down() {
		//	1. 블록 합치기
		for(int j = 1; j<=N; j++) {
			for(int i = N; i>=2; i--) {
				if(map[i][j] != 0) {
					for(int k = i-1; k>=1; k--) {
						if(map[k][j] != 0) {
							if(map[k][j] == map[i][j]) {
								map[i][j] += map[k][j];
								map[k][j] = 0;
							}
							break;
						}
					}
				}
			}
		}
		//	2. 블록 몰아넣기
		for(int j = 1; j<=N; j++) {
			for(int i = N; i>=1; i--) {
				if(map[i][j] != 0) {
					for(int k = i+1; k<=N+1; k++) {
						if(map[k][j] != 0) {
							if(map[k-1][j] == 0) { 
								map[k-1][j] = map[i][j];
								map[i][j] = 0;
							}
							break;
						}
					}
				}
			}
		}		
	}	//	end of Down
	
	private static void Left() {
		//	1. 블록 합치기
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N-1; j++) {
				if(map[i][j] != 0) {
					for(int k = j+1; k<=N; k++) {
						if(map[i][k] != 0) {
							if(map[i][k] == map[i][j]) {
								map[i][j] += map[i][k];
								map[i][k] = 0;
							}
							break;
						}
					}
				}
			}
		}
		//	2. 블록 몰아넣기
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				if(map[i][j] != 0) {
					for(int k = j-1; k>=0; k--) {
						if(map[i][k] != 0) {
							if(map[i][k+1] == 0) {
								map[i][k+1] = map[i][j];
								map[i][j] = 0;
							}
							break;
						}
					}
				}
			}
		}
	}	//	end of Left
	
	private static void Right() {
		//	1. 블록 합치기
		for(int i = 1; i<=N; i++) {
			for(int j = N; j>=2; j--) {
				if(map[i][j] != 0) {
					for(int k = j-1; k>=1; k--) {
						if(map[i][k] != 0) {
							if(map[i][k] == map[i][j]) {
								map[i][j] += map[i][k];
								map[i][k] = 0;
							}
							break;
						}
					}
				}
			}
		}
		//	2. 블록 몰아넣기
		for(int i = 1; i<=N; i++) {
			for(int j = N; j>=1; j--) {
				if(map[i][j] != 0) {
					for(int k = j+1; k<=N+1; k++) {
						if(map[i][k] != 0) {
							if(map[i][k-1] == 0) {
								map[i][k-1] = map[i][j];
								map[i][j] = 0;
							}
							break;
						}
					}
				}
			}
		}
	}	//	end of Right
}	//	end of class
