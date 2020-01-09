package boj0109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2749_피보나치수3 {
	private static long N;
	private static int P;
	private static int nth;
	private static long[] fibo;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Long.parseLong(br.readLine());
		
		P = 1500000;	//	15 * 10^(K-1) --> 여기에서 K가 6이기 때문에 반복되는 피사노 주기는 공식에 따라 150만이 된다
		
		nth = (int)(N % P);
		
		fibo = new long[P];
		fibo[0] = (long)0;
		fibo[1] = (long)1;
		
		for(int i = 2; i<=nth; i++) {
			fibo[i] = fibo[i-1] + fibo[i-2];
			fibo[i] %= 1000000;
		}
		
		System.out.println(fibo[nth]);
		
		
	}
}
