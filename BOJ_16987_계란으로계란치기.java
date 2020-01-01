package prac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_16987_계란으로계란치기 {
	private static int N;
	private static int[] S;
	private static int[] W;
	private static int Answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	//	1<= 계란수 <=8
		S = new int[N];		//	1<= 내구도 <=300
		W = new int[N];		//	1<= 무게 <=300
		
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			S[i] = Integer.parseInt(st.nextToken());
			W[i] = Integer.parseInt(st.nextToken());
		}
		
//		System.out.println(Arrays.toString(S));
//		System.out.println(Arrays.toString(W));
		
		Answer = 0;
		
		play(0);
		
		System.out.println(Answer);
	}

	private static void play(int num) {
		if(num == N) {	//	계란 다 손댔을 때 검사
			int temp = 0;
			for(int i = 0; i<N; i++) {
				if(S[i] <= 0) {
					temp += 1;
				}
			}
			if(Answer < temp) {
				Answer = temp;
			}
			return;
		}
		
		if(S[num] > 0 && num == N-1) {	//	마지막에 든 계란이 내구도가 0보다 큰데, 칠 계란이 없는 경우
			boolean allKill = true;
			
			for(int i = 0; i<N-1; i++) {
				if(S[i] > 0) {
					allKill = false;
					break;
				}
			}
			if(allKill) {
				play(num+1);
			}
		}
		
		if(S[num] > 0) {	//	손에 든 계란의 내구도가 0보다 클 때 --> 깨지지 않았을 때
			for(int i = 0; i<N; i++) {
				if(num == i) {	//	자기가 자기를 깰 수는 없지 패스패스
					continue;
				}
				if(S[i] > 0) {
					S[i] -= W[num];
					S[num] -= W[i];
					play(num + 1);
					S[i] += W[num];
					S[num] += W[i];
				}
				
			}			
		}else {	//	손에 든 계란이 깨진 계란일 때
			play(num + 1);
		}
	}
}
