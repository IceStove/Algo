package prac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15486_퇴사2 {
	private static int N;
	private static int n;
	private static int[] T;
	private static int[] P;
	private static int[] D;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		T = new int[N+1];
		P = new int[N+1];
		D = new int[N+1];
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i<N; i++) {
			if(D[i+1] < D[i]) {
				D[i+1] = D[i];
			}
			if(i+T[i] < N+1 && D[i+T[i]] < D[i] + P[i]) {
				D[i+T[i]] = D[i] + P[i];
			}
		}
//		System.out.println(Arrays.toString(T));
//		System.out.println(Arrays.toString(P));
//		System.out.println(Arrays.toString(D));
		
		System.out.println(D[N]);
	}
}
