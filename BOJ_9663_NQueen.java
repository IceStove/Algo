package prac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9663_NQueen {
	private static int N;
	private static int[][] map;
	private static int Answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		Answer = 0;
		
		play(0, 0);
		System.out.println(Answer);
	}

	private static void play(int y, int num) {
		if(num == N) {
			Answer += 1;
			return;
		}
		
		for(int x = 0; x<N; x++) {
			boolean check = true;
			for(int k = 1; k<=num; k++) {
				if(x-k >= 0 && map[y-k][x-k] != 0) {
					check = false;
				}
				if(map[y-k][x] != 0) {
					check = false;
				}
				if(x+k < N && map[y-k][x+k] != 0) {
					check = false;
				}
				if(check == false) {
					break;
				}
			}
			if(check) {
				map[y][x] = 1;
				play(y+1, num + 1);
				map[y][x] = 0;
			}
		}
	}
}
