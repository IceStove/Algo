package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_9012_괄호 {
	private static int T;
	private static int Answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for(int t = 0; t<T; t++) {
			Answer = 0;
			char[] stringArr = br.readLine().toCharArray();
			Stack<Character> stack = new Stack<Character>();
			for(int i = 0; i<stringArr.length; i++) {
				if(stringArr[i] == '(') {
					stack.push(stringArr[i]);
				} else {
					if(stack.size() > 0) {
						stack.pop();						
					} else {
						Answer = -1;
						break;
					}
				}
			}
			
			if(stack.size() > 0) Answer = -1;
			
			if(Answer == 0) {
//				System.out.println("YES");
				sb.append("YES").append("\n");
			} else {
//				System.out.println("NO");
				sb.append("NO").append("\n");
			}
		}
		System.out.print(sb.toString());
		
	}	//	end of main
}	//	end of class
