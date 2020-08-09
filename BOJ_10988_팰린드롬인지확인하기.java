package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10988_팰린드롬인지확인하기 {
	private static char[] arr;
	private static int Answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		arr = br.readLine().toCharArray();
		Answer = 1;
		
		int arrLength = arr.length;
		for(int i = 0; i<arrLength / 2; i++) {
			if(arr[i] != arr[arrLength - 1 - i]) {
				Answer = 0;
				break;
			}
		}
		System.out.println(Answer);
	}
}
