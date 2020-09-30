package boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14501_퇴사 {
	private static int N;
	private static int[] T;
	private static int[] P;
	private static int[] D;	//	메모이제이션으로 품
//	퇴사2는 무조건 DP, 퇴사1은 재귀와 DP 둘 다 가능 --> Git에 재귀로 푼 것도 있음

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		T = new int[N+1];
		P = new int[N+1];
		D = new int[N+1];	//	기록
		StringTokenizer st;
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		
		for(int i = 1; i<=N; i++) {	
			if(D[i-1] > D[i]) D[i] = D[i-1];
			if(i + T[i] - 1 <= N) {
				if(D[i+T[i]-1] < D[i-1] + P[i]) {
					D[i+T[i]-1] = D[i-1] + P[i];
				}
			}
		}
		
//		System.out.println(Arrays.toString(T));
//		System.out.println(Arrays.toString(P));
//		System.out.println(Arrays.toString(D));
		
		System.out.println(D[N]);
	}	//	end of main
}	//	end of class
