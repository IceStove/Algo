package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10808_알파벳개수 {
	private static char[] arr;
	private static int[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = br.readLine().toCharArray();
		nums = new int[26];
		
		for(int i = 0; i<arr.length; i++) {
			int n = arr[i] - 'a';
			nums[n] += 1;
		}
		
		for(int i = 0; i<26; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}
}
