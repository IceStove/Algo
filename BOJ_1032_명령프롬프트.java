package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1032_명령프롬프트 {
	private static int N;
	private static char[] arr;
	private static char[] temp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = br.readLine().toCharArray();
		
		for(int t = 1; t<N; t++) {
			temp = br.readLine().toCharArray();
			for(int i = 0; i<arr.length; i++) {
				if(arr[i] != temp[i]) {
					arr[i] = '?';
				}
			}
		}
		
		System.out.println(arr);
	}
}
