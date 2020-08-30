package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11047_동전0_DP {
	private static int N;
	private static int K;
	private static int[] coins;
	private static int[] values;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		coins = new int[N];
		values = new int[K+1];
		
		for(int i = 0; i<N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		for(int k = 1; k<=K; k++) {
			int MIN = Integer.MAX_VALUE;
			
			for(int i = 0; i<N; i++) {
				if(k == coins[i]) {
					MIN = 1;
					break;
				} else if(k > coins[i] && k - coins[i] > 0) {
					if(values[k - coins[i]] != 0) {
						if(values[k - coins[i]] + 1 < MIN) {
							MIN = values[k - coins[i]] + 1;
						} else {
							MIN = MIN;
						}
					}
				}
			}
			
			if(MIN == Integer.MAX_VALUE) {
				values[k] = 0;
			} else {
				values[k] = MIN;
			}
		}
		
		System.out.println(values[K]);
	}	//	end of main
}	//	end of class
