package boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500_테트로미노 {
	private static int N, M;
	private static int[][] map;
	private static int Answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//	4 <= 세로 <= 500
		M = Integer.parseInt(st.nextToken());	//	4 <= 가로 <= 500
		
		map = new int[N][M];
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Answer = 0;	//	지도에 들어가는 값이 1000을 넘지 않는 자연수이므로 0으로 시작
		
		int ey = 0;
		int ex = 0;
		int sum = 0;
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				// 1. 가로 스틱
				ey = i;
				ex = j+3;
				if(ey < N && ex < M) {
					sum = map[i][j] + map[i][j+1] + map[i][j+2] + map[i][j+3];
					if(sum > Answer) Answer = sum;
				}
				
				// 2. 세로 스틱
				ey = i+3;
				ex = j;
				if(ey < N && ex < M) {
					sum = map[i][j] + map[i+1][j] + map[i+2][j] + map[i+3][j];
					if(sum > Answer) Answer = sum;
				}
				
				// 3. 정사각형
				ey = i+1;
				ex = j+1;
				if(ey < N && ex < M) {
					sum = map[i][j] + map[i][j+1] + map[i+1][j] + map[i+1][j+1];
					if(sum > Answer) Answer = sum;
				}
				
				// 4. 가로 6칸
				ey = i+1;
				ex = j+2;
				if(ey < N && ex < M) {
					sum = map[i][j] + map[i][j+1] + map[i][j+2] + map[i+1][j];
					if(sum > Answer) Answer = sum;
					sum = map[i][j+2] + map[i+1][j] + map[i+1][j+1] + map[i+1][j+2];
					if(sum > Answer) Answer = sum;
					sum = map[i][j] + map[i+1][j] + map[i+1][j+1] + map[i+1][j+2];
					if(sum > Answer) Answer = sum;
					sum = map[i][j] + map[i][j+1] + map[i][j+2] + map[i+1][j+2];
					if(sum > Answer) Answer = sum;
					sum = map[i][j+1] + map[i][j+2] + map[i+1][j] + map[i+1][j+1];
					if(sum > Answer) Answer = sum;
					sum = map[i][j] + map[i][j+1] + map[i+1][j+1] + map[i+1][j+2];
					if(sum > Answer) Answer = sum;
					sum = map[i][j+1] + map[i+1][j] + map[i+1][j+1] + map[i+1][j+2];
					if(sum > Answer) Answer = sum;
					sum = map[i][j] + map[i][j+1] + map[i][j+2] + map[i+1][j+1];
					if(sum > Answer) Answer = sum;
				}
				
				// 5. 세로 6칸
				ey = i+2;
				ex = j+1;
				if(ey < N && ex < M) {
					sum = map[i][j] + map[i+1][j] + map[i+2][j] + map[i+2][j+1];
					if(sum > Answer) Answer = sum;
					sum = map[i][j] + map[i][j+1] + map[i+1][j+1] + map[i+2][j+1];
					if(sum > Answer) Answer = sum;
					sum = map[i][j+1] + map[i+1][j+1] + map[i+2][j] + map[i+2][j+1];
					if(sum > Answer) Answer = sum;
					sum = map[i][j] + map[i][j+1] + map[i+1][j] + map[i+2][j];
					if(sum > Answer) Answer = sum;
					sum = map[i][j] + map[i+1][j] + map[i+1][j+1] + map[i+2][j+1];
					if(sum > Answer) Answer = sum;
					sum = map[i][j+1] + map[i+1][j] + map[i+1][j+1] + map[i+2][j];
					if(sum > Answer) Answer = sum;
					sum = map[i][j] + map[i+1][j] + map[i+1][j+1] + map[i+2][j];
					if(sum > Answer) Answer = sum;
					sum = map[i][j+1] + map[i+1][j] + map[i+1][j+1] + map[i+2][j+1];
					if(sum > Answer) Answer = sum;
				}
			}
		}
		
		System.out.println(Answer);
	}	//	end of main
}	//	end of class
