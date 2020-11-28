package boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20057_마법사상어와토네이도 {
	private static int N;
	private static int[][] map;
	private static int Answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N+2][N+2];
		for(int i = 0; i<N+2; i++) {
			map[i][0] = 8;
			map[i][N+1] = 8;
			map[0][i] = 8;
			map[N+1][i] = 8;
		}
		
		for(int i = 1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		Answer = 0;
		play();
		System.out.println(Answer);
	}	//	end of main
	
	private static void play() {
		int dir = 0;	//	0:좌, 1:하, 2:우, 3:상
		int interval = 1;	//	1로 시작
		
		int cy = N / 2 + 1;
		int cx = N / 2 + 1;
		
		for(int n = 1; n < N; n++) {
			for(int twice = 0; twice < 2; twice++) {
				for(int i = 0; i < interval; i++) {
					int ny = 0;
					int nx = 0;
					if(dir == 0) {
						ny = cy;
						nx = cx - 1;
						left(ny, nx);
					} else if(dir == 1) {
						ny = cy + 1;
						nx = cx;
						down(ny, nx);
					} else if(dir == 2) {
						ny = cy;
						nx = cx + 1;
						right(ny, nx);
					} else if(dir == 3) {
						ny = cy - 1;
						nx = cx;
						up(ny, nx);
					}
					cy = ny;
					cx = nx;
				}
				dir++;
				if(dir == 4) dir = 0;				
			}
			interval += 1;
		}
		
		interval -= 1;
		for(int i = 0; i<interval; i++) {
			int ny = 0;
			int nx = 0;
			if(dir == 0) {
				ny = cy;
				nx = cx - 1;
				left(ny, nx);
			} else if(dir == 1) {
				ny = cy + 1;
				nx = cx;
				down(ny, nx);
			} else if(dir == 2) {
				ny = cy;
				nx = cx + 1;
				right(ny, nx);
			} else if(dir == 3) {
				ny = cy - 1;
				nx = cx;
				up(ny, nx);
			}
			cy = ny;
			cx = nx;
		}

	}	//	end of play

	private static void up(int y, int x) {
		int p1 = (int)(map[y][x] * 0.01);		//	1%
		int p2 = (int)(map[y][x] * 0.02);		//	2%
		int p5 = (int)(map[y][x] * 0.05);		//	5%
		int p7 = (int)(map[y][x] * 0.07);		//	7%
		int p10 = (int)(map[y][x] * 0.1);		//	10%
		int alpha = map[y][x] - 2*p1 - 2*p2 - p5 - 2*p7 - 2*p10;
		
		moveSand(y, x-1, p7);
		moveSand(y, x+1, p7);
		moveSand(y, x-2, p2);
		moveSand(y, x+2, p2);
		moveSand(y+1, x-1, p1);
		moveSand(y+1, x+1, p1);
		moveSand(y-1, x-1, p10);
		moveSand(y-1, x+1, p10);
		moveSand(y-2, x, p5);
		moveSand(y-1, x, alpha);
		map[y][x] = 0;
//		System.out.println("현재 토네이도 위치 : ["+y+","+x+"] 방향 : Up");
	}	//	end of up

	private static void down(int y, int x) {
		int p1 = (int)(map[y][x] * 0.01);		//	1%
		int p2 = (int)(map[y][x] * 0.02);		//	2%
		int p5 = (int)(map[y][x] * 0.05);		//	5%
		int p7 = (int)(map[y][x] * 0.07);		//	7%
		int p10 = (int)(map[y][x] * 0.1);		//	10%
		int alpha = map[y][x] - 2*p1 - 2*p2 - p5 - 2*p7 - 2*p10;
		
		moveSand(y, x-1, p7);
		moveSand(y, x+1, p7);
		moveSand(y, x-2, p2);
		moveSand(y, x+2, p2);
		moveSand(y-1, x-1, p1);
		moveSand(y-1, x+1, p1);
		moveSand(y+1, x-1, p10);
		moveSand(y+1, x+1, p10);
		moveSand(y+2, x, p5);
		moveSand(y+1, x, alpha);
		map[y][x] = 0;
//		System.out.println("현재 토네이도 위치 : ["+y+","+x+"] 방향 : Down");
	}	//	end of down

	private static void left(int y, int x) {
		int p1 = (int)(map[y][x] * 0.01);		//	1%
		int p2 = (int)(map[y][x] * 0.02);		//	2%
		int p5 = (int)(map[y][x] * 0.05);		//	5%
		int p7 = (int)(map[y][x] * 0.07);		//	7%
		int p10 = (int)(map[y][x] * 0.1);		//	10%
		int alpha = map[y][x] - 2*p1 - 2*p2 - p5 - 2*p7 - 2*p10;
		
		moveSand(y-1, x, p7);
		moveSand(y+1, x, p7);
		moveSand(y-2, x, p2);
		moveSand(y+2, x, p2);
		moveSand(y-1, x+1, p1);
		moveSand(y+1, x+1, p1);
		moveSand(y-1, x-1, p10);
		moveSand(y+1, x-1, p10);
		moveSand(y, x-2, p5);
		moveSand(y, x-1, alpha);
		map[y][x] = 0;
//		System.out.println("현재 토네이도 위치 : ["+y+","+x+"] 방향 : Left");
	}	//	end of left
	
	private static void right(int y, int x) {
		int p1 = (int)(map[y][x] * 0.01);		//	1%
		int p2 = (int)(map[y][x] * 0.02);		//	2%
		int p5 = (int)(map[y][x] * 0.05);		//	5%
		int p7 = (int)(map[y][x] * 0.07);		//	7%
		int p10 = (int)(map[y][x] * 0.1);		//	10%
		int alpha = map[y][x] - 2*p1 - 2*p2 - p5 - 2*p7 - 2*p10;
		
		moveSand(y-1, x, p7);
		moveSand(y+1, x, p7);
		moveSand(y-2, x, p2);
		moveSand(y+2, x, p2);
		moveSand(y-1, x-1, p1);
		moveSand(y+1, x-1, p1);
		moveSand(y-1, x+1, p10);
		moveSand(y+1, x+1, p10);
		moveSand(y, x+2, p5);
		moveSand(y, x+1, alpha);
		map[y][x] = 0;
//		System.out.println("현재 토네이도 위치 : ["+y+","+x+"] 방향 : Right");
	}	//	end of right
	
	private static void moveSand(int ny, int nx, int amount) {
		if(ny >= 1 && ny <= N && nx >= 1 && nx <= N) {
			map[ny][nx] += amount;
		} else {
			Answer += amount;
		}
	}
}	//	end of class
