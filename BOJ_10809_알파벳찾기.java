package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10809_알파벳찾기 {
	private static char[] arr;
	private static int[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		arr = br.readLine().toCharArray();
		nums = new int[26];
		
		for(int i = 0; i<26; i++) {
			nums[i] = -1;
		}
		
		for(int i = 0; i<arr.length; i++) {
			int n = arr[i] - 'a';
			if(nums[n] == -1) {
				nums[n] = i;
			}
		}
		
		for(int i = 0; i<26; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}
}
