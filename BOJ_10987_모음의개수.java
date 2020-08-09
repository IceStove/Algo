package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10987_모음의개수 {
	private static char[] arr;
	private static int Answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		arr = br.readLine().toCharArray();
		Answer = 0;
		for(int i = 0; i<arr.length; i++) {
			if(arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') {
				Answer += 1;
			}
		}
		System.out.println(Answer);
	}
}
