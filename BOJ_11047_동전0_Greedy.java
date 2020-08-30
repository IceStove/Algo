package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11047_동전0_Greedy {
	private static int N;
	private static int K;
	private static int[] coins;
	private static int Answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		coins = new int[N];
		
		for(int i = 0; i<N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		Answer = 0;
		
		for(int i = N-1; i>=0; i--) {
			Answer += K/coins[i];
			
			K = K % coins[i];
		}
		
		System.out.println(Answer);
	}	//	end of main
}	//	end of class
