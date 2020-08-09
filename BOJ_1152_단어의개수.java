package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1152_단어의개수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String temp = br.readLine().trim();
		StringTokenizer st = new StringTokenizer(temp, " ");
		
		System.out.println(st.countTokens());
	}
}
