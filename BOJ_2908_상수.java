package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2908_상수 {
	private static char[] num_arr1;
	private static char[] num_arr2;
	private static int num1;
	private static int num2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		num_arr1 = st.nextToken().toCharArray();
		num_arr2 = st.nextToken().toCharArray();
		
		num1 = (num_arr1[0] - '0') * 1 + (num_arr1[1] - '0') * 10 + (num_arr1[2] - '0') * 100;
		num2 = (num_arr2[0] - '0') * 1 + (num_arr2[1] - '0') * 10 + (num_arr2[2] - '0') * 100;
		
		if(num1 > num2) {
			System.out.println(num1);
		} else {	//	두 숫자가 같은 경우는 없다고 하였다.
			System.out.println(num2);
		}
	}
}
