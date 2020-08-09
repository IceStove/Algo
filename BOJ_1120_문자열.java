package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1120_문자열 {
	private static char[] arr1;
	private static char[] arr2;
	private static int Answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		arr1 = st.nextToken().toCharArray();
		arr2 = st.nextToken().toCharArray();
		Answer = 100;
		
		int chai = arr2.length - arr1.length;
		for(int c = 0; c <= chai; c++) {
			int temp = 0;
			for(int i = 0; i<arr1.length; i++) {
				if(arr1[i] != arr2[i+c]) {
					temp += 1;
				}
			}
			if(Answer > temp) {
				Answer = temp;
			}
		}
		System.out.println(Answer);
	}
}
