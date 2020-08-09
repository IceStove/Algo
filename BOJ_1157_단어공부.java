package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1157_단어공부 {
	private static int[] nums;
	private static char[] arr;
	private static char Answer;
	private static int max_num;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String temp = br.readLine();	//	문자열 입력
		temp = temp.toUpperCase();		//	대문자로 변환
		
		arr = temp.toCharArray();		//	문자열을 문자 배열로 변환
		nums = new int[26];
		max_num = 0;
		Answer = '?';
		
		for(int i = 0; i<arr.length; i++) {
			int n = arr[i] - 'A';
			nums[n] += 1;
			if(max_num < nums[n]) {
				max_num = nums[n];
			}
		}
		
		int t = 0;
		
		for(int i = 0; i<26; i++) {
			if(max_num == nums[i]) {
				Answer = (char) ('A' + i);
				t += 1;
				if(t == 2) {
					break;
				}
			}
		}
		
		if(t == 2) { 
			System.out.println("?");
		} else {
			System.out.println(Answer);
		}
	}
}
