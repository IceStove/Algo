package boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15684_사다리조작 {
	private static int N, M, H;
	private static int[][] map;
	private static int height;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//	2 <= N <= 10
		M = Integer.parseInt(st.nextToken());	//	0 <= M <= (N-1) * H
		H = Integer.parseInt(st.nextToken());	//	1 <= H <= 30
		
		map = new int[2*H + 1][2*N + 1];
		
		for(int i = 0; i<=2*H; i++) {
			for(int j = 1; j<=N; j++) {
				map[i][2*j-1] = 1;
			}
		}
		
		height = 2*H+1;	//	실제 맵 높이크기
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[2*a - 1][2*b] = 1;
		}
		
		for(int i = 0; i<map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		play();
	}	//	end of main

	private static boolean play() {
		boolean result = true;
		for(int num = 1; num <=N; num++) {
			int cy = 0;
			int cx = 2*num - 1;
			
			int[][] checkmap = new int[2*H+1][2*N + 1];
			checkmap[cy][cx] = 1;
			
			while(cy < height) {
				if(map[cy][cx-1] == 1 && checkmap[cy][cx-1] == 0) {
					cx = cx -1;
				} else if(map[cy][cx+1] == 1 && checkmap[cy][cx+1] == 0) {
					cx = cx + 1;
				} else if(map[cy + 1][cx] == 1 && checkmap[cy+1][cx] == 0) {
					cy = cy + 1;
				}
			}
			
			if(cx != 2*num - 1) {
				result = false;
				break;
			}
		}
		return result;
	}
}	//	end of class
