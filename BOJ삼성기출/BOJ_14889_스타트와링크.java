package boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889_스타트와링크 {
	private static int N;
	private static int[][] S;
	private static int Answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		S = new int[N][N];
		StringTokenizer st;
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		Answer = Integer.MAX_VALUE;
		boolean[] check = new boolean[N];
		comb(check, 0, 0);
		System.out.println(Answer);
	}	//	end of main

	private static void comb(boolean[] check, int index, int count) {
		if(count == N/2) {
			int sPoint = 0;	//	스타트 팀의 능력치
			int lPoint = 0;	//	링크 팀의 능력치
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					if(check[i]) {	//	check[i]가 true면 스타트팀
						if(check[i] == check[j]) {
							sPoint += S[i][j];
						}
					} else {		//	check[i]가 false면 링크팀
						if(check[i] == check[j]) {
							lPoint += S[i][j];
						}
					}
				}
			}
			int chai = Math.abs(sPoint - lPoint);
			if(Answer > chai) Answer = chai;
			return;
		}
		if(index == N) return;
		if(N/2 - count > N-index) return;
		if(index < N) {	
			check[index] = true;
			comb(check, index + 1, count + 1);
			check[index] = false;
			comb(check, index + 1, count);			
		}
	}

}	//	end of class
