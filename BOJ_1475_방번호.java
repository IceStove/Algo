package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1475_방번호 {
	private static char[] arr;
	private static int[] nums;
	private static int Answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		arr = br.readLine().toCharArray();
		nums = new int[10];
		Answer = 0;
		for(int i = 0; i<arr.length; i++) {
			int n = arr[i] - '0';
			nums[n] += 1;
		}
		
		int temp = nums[6] + nums[9];
		if(temp % 2 == 0) {
			nums[6] = temp/2;
			nums[9] = temp/2;
		} else {
			nums[6] = temp/2;
			nums[9] = temp/2;
			nums[6] += 1;
		}
		
		for(int i = 0; i<10; i++) {
			if(Answer < nums[i]) {
				Answer = nums[i];
			}
		}
		
		System.out.println(Answer);
	}
}
