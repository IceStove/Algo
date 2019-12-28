package prac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14501_퇴사 {
	private static int N;
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
		
//		System.out.println(Arrays.toString(T));
//		System.out.println(Arrays.toString(P));
		
		play(0, 0);
		
//		System.out.println(Arrays.toString(D));
		System.out.println(D[N]);
	}

	private static void play(int day, int point) {
		if(day > N) {
			return;
		}
		if(point > D[day]) {
			D[day] = point;
		}
		if(day < N) {
			play(day + T[day], D[day] + P[day]);
			play(day + 1, D[day]);			
		}
	}
}
