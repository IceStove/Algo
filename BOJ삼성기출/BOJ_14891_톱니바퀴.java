package boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14891_톱니바퀴 {
	private static int[][] wheel;
	private static int K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		wheel = new int[4][8];
		
		for(int i = 0; i<4; i++) {
			char[] temp = br.readLine().toCharArray();
			for(int j = 0; j<8; j++) {
				wheel[i][j] = (int)(temp[j] - '0');
			}
		}
		
		K = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i = 0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			play(num, dir);
		}
	}	//	end of main

	private static void play(int num, int dir) {
		// TODO Auto-generated method stub
		
	}
}	//	end of class
