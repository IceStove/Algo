package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11727_2xn타일링2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		System.out.println(fibo(N));
	}	//	end of main

	private static int fibo(int n) {
		int[] f = new int[1001];	//	1 <= n <= 1000
		f[1] = 1;
		f[2] = 3;
		if(n > 2) {
			for(int i = 3; i<=n; i++) {
				f[i] = f[i-1] + 2*f[i-2];
				f[i] = f[i] % 10007;
			}
		}
		return f[n];
	}	//	end of fibo
}	//	end of class
