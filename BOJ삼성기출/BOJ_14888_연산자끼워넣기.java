package boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14888_연산자끼워넣기 {
	private static int N;
	private static int[] A;
	private static int[] Oper;
	private static int Min;
	private static int Max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];	//	숫자
		Oper = new int[4];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<4; i++) {
			Oper[i] = Integer.parseInt(st.nextToken());
		}
		Max = Integer.MIN_VALUE;	//	최대
		Min = Integer.MAX_VALUE;	//	최소
		play(A[0], 1, Oper[0], Oper[1], Oper[2], Oper[3]);
		System.out.println(Max);
		System.out.println(Min);
	}	//	end of main

	private static void play(int cur, int index, int add, int sub, int mul, int div) {
		if(index == N) {
			if(cur > Max) Max = cur;
			if(cur < Min) Min = cur;
			return;
		}
		
		if(add > 0) {
			play(cur + A[index], index+1, add-1, sub, mul, div);
		}
		if(sub > 0) {
			play(cur - A[index], index+1, add, sub-1, mul, div);
		}
		if(mul > 0) {
			play(cur * A[index], index+1, add, sub, mul-1, div);
		}
		if(div > 0) {
			play(cur / A[index], index+1, add, sub, mul, div-1);
		}
	}
}	//	end of class
