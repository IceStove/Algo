package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11726_2xn타일링 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int result = fibo(N);
		System.out.println(result);
	}	//	end of main
	private static int fibo(int n) {
		int[] f = new int[1001];
		f[1] = 1;
		f[2] = 2;
		
		if(n > 2) {
			for(int i = 3; i<=n; i++) {
				f[i] = f[i-1] + f[i-2];
				f[i] = f[i] % 10007;
			}
 		}
		
		return f[n];
	}	//	end of fibo
}	//	end of class
