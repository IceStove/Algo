package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10828_스택 {
	public static class Stack {
		int[] contents = new int[10000];
		int index;
		
		public Stack() {
			super();
			this.index = -1;
		}
		
		public void push(int num) {
			this.index = this.index + 1;
			this.contents[this.index] = num;
		}
		
		public int pop() {
			int result;
			if(this.index == -1) {
				result = -1;
			} else {
				result = this.contents[this.index];	//	top에 있던 값 빼고
				this.contents[this.index] = 0;
				this.index = this.index - 1;
			}
			return result;
		}
		
		public int size() {
			return (this.index + 1);
		}
		
		public int empty() {
			if(this.index == -1) {
				return 1;
			} else {
				return 0;
			}
		}

		public int top() {
			int result;
			if(this.index == -1) {
				result = -1;
			} else {
				result = this.contents[this.index];
			}
			return result;
		}
	}	//	end of Stack
	
	
	private static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		Stack stack = new Stack();
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			String order = st.nextToken().toString();
			
			if(order.equals("push")) {
				int num = Integer.parseInt(st.nextToken());
				stack.push(num);
			} else if(order.equals("pop")) {
//				System.out.println(stack.pop());
				sb.append(stack.pop()).append("\n");
			} else if(order.equals("size")) {
//				System.out.println(stack.size());
				sb.append(stack.size()).append("\n");
			} else if(order.equals("empty")) {
//				System.out.println(stack.empty());
				sb.append(stack.empty()).append("\n");
			} else if(order.equals("top")) {
//				System.out.println(stack.top());
				sb.append(stack.top()).append("\n");
			} else {
				// 이건 에러
			}
		}
		
		System.out.print(sb.toString());
		
	}	//	end of main
}	//	end of class
