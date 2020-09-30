package boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14499_주사위굴리기 {
	private static int N, M, y, x, K;
	private static int[][] map;
	private static int[] order;
	private static int[] dice;
	// 동:1 서:2 북:3 남:4 순서
	private static int[] dy = {0, 0, 0, -1, +1};
	private static int[] dx = {0, +1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//	지도 세로 크기
		M = Integer.parseInt(st.nextToken());	//	지도 가로 크기
		y = Integer.parseInt(st.nextToken());	//	주사위 놓인 y좌표
		x = Integer.parseInt(st.nextToken());	//	주사위 놓인 x좌표
		K = Integer.parseInt(st.nextToken());	//	명령의 개수
		
		map = new int[N][M];
		order = new int[K];
		dice = new int[6];	//	{top, north, east, west, south, bottom}
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<K; i++) {
			order[i] = Integer.parseInt(st.nextToken());
			int result = Move(order[i]);
			if(result != -1) {
				System.out.println(result);
			}
		}		
	}	//	end of main

	private static int Move(int num) {
		int result = 0;
		int ny = y + dy[num];
		int nx = x + dx[num];
		if(ny < 0 || ny >= N || nx < 0 || nx >=M) {
			// 범위 이탈 --> -1 리턴해서 출력조차 안하게
			result = -1;
			return result;
		}
		y = ny;
		x = nx;
		if(num == 1) {
			int temp = dice[0];
			dice[0] = dice[3];
			dice[3] = dice[5];
			dice[5] = dice[2];
			dice[2] = temp;
		} else if(num == 2) {
			int temp = dice[0];
			dice[0] = dice[2];
			dice[2] = dice[5];
			dice[5] = dice[3];
			dice[3] = temp;
		} else if(num == 3) {
			int temp = dice[0];
			dice[0] = dice[4];
			dice[4] = dice[5];
			dice[5] = dice[1];
			dice[1] = temp;
		} else if(num == 4) {
			int temp = dice[0];
			dice[0] = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[4];
			dice[4] = temp;
		}
		if(map[ny][nx] == 0) {	//	지도의 칸이 0일 때, 지도에 주사위의 바닥면을 복사
			map[ny][nx] = dice[5];
		} else {				//	지도의 칸이 0이 아닐 때, 주사위의 바닥면에 지도의 칸을 복사하고, 지도의 칸을 0으로 변경
			dice[5] = map[ny][nx];
			map[ny][nx] = 0;
		}
		result = dice[0];	//	top 출력
		return result;
	}
}	//	end of class
