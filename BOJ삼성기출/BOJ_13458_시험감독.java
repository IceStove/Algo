package boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13458_시험감독 {
	private static int N;
	private static long[] map;
	private static long B;
	private static long C;
	private static long Answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i<N; i++) {
			map[i] = Long.parseLong(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		B = Long.parseLong(st.nextToken());	//	총 감독관이 커버칠 수 있는 학생 수
		C = Long.parseLong(st.nextToken());	//	부 감독관이 커버칠 수 있는 학생 수
		
		Answer = (long)N;	//	시작이 N인 이유는 어차피 각 클래스마다 총감독관은 무조건 있어야 함.
							//	자료형이 long인 이유는 최대값이 1조이기에 int를 넘어감
		
		for(int i = 0; i<N; i++) {
			map[i] -= B;
			if(map[i] > 0) {
				if(map[i] % C == 0) {
					Answer += (map[i] / C);
				} else {
					Answer += (map[i] / C + 1);
				}
			}
		}
		System.out.println(Answer);
	}	//	end of main
}	//	end of class
