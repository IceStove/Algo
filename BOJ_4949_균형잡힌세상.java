package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_4949_균형잡힌세상 {
	private static int Answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			String string = br.readLine();
			if(string.equals(".")) {	//	종료 조건
				break;
			}
			
			char[] stringArr = string.toCharArray();
			
			Stack<Character> roundStack = new Stack<Character>();
			Stack<Character> squareStack = new Stack<Character>();
//			1:roundStack 먼저 검사, 2:squareStack 먼저 검사
			Stack<Integer> cursorStack = new Stack<Integer>();
			Answer = 0;
			for(int i = 0; i<stringArr.length; i++) {
				if(stringArr[i] == '(') {
					roundStack.push(stringArr[i]);
					cursorStack.push(1);
				} else if(stringArr[i] == '[') {
					squareStack.push(stringArr[i]);
					cursorStack.push(2);
				} else if(stringArr[i] == ')') {
					if(roundStack.size() > 0 && cursorStack.size() > 0) {
						int cursor = cursorStack.pop();
						if(cursor == 1) {	//	소괄호 열림상태이면서 소괄호 닫힘이 들어올 때
							roundStack.pop();	//	정상
						} else {	//	대괄호 열림상태인데 소괄호 닫힘이 들어올 때
							Answer = -1;	//	비정상
							break;
						}
					} else {			//	지울 소괄호가 없음
						Answer = -1;	//	비정상
						break;
					}
				} else if(stringArr[i] == ']') {
					if(squareStack.size() > 0 && cursorStack.size() > 0) {
						int cursor = cursorStack.pop();
						if(cursor == 2) {	//	대괄호 열림상태인데 대괄호 닫힘이 들어올 때
							squareStack.pop();	//	정상
						} else {	//	소괄호 열림상태인데 대괄호 닫힘이 들어올 때
							Answer = -1;	//	비정상
							break;
						}
					} else {			//	지울 대괄호가 없음
						Answer = -1;	//	비정상
						break;
					}
				} else {	//	일반 문자열들은 체크 안함
					
				}
			}
			
			if(cursorStack.size() > 0) Answer = -1;
			
			if(Answer == 0) {
				sb.append("yes").append("\n");
			} else {
				sb.append("no").append("\n");
			}
		}	//	end of allTestcase
		
		System.out.println(sb.toString());
	}	//	end of main
}	//	end of class
