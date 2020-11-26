package boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20055_컨베이어벨트위의로봇 {
	private static int N;
	private static int K;
	private static int[] A;
	private static int[] S;
	private static int Pung;
	private static int Time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		A = new int[2*N + 1];	//	내구도
		S = new int[2*N + 1];	//	로봇이 있는지 없는지 유무 파악용
		st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i<=2*N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		Pung = 0;	//	고장난 칸의 개수
		Time = 0;	//	시간
		play();
		System.out.println(Time);
	}	//	end of main

	private static void play() {
		while(true) {
			Time += 1;
			// 1. 벨트 한 칸 이동
			moveBelt();
			// 2. 앞에 로봇 없으면 한 칸 걸어가기
			moveRobot();
			// 3. 로봇 하나 올리기
			addRobot();
			// 4. 내구도 0인 칸의 개수가 K개 이상이면 STOP
			if(Pung >= K) {
				break;
			}
		}
	}	//	end of play

	private static void addRobot() {
		if(A[1] > 0 && S[1] == 0) {
			A[1] -= 1;	//	내구도 -1
			S[1] = 1;	//	로봇 올라감
			if(A[1] == 0) Pung += 1;
		}
	}	//	end of addRobot

	private static void moveRobot() {
		for(int i = N; i>1; i--) {
			if(S[i] == 0 && S[i-1] != 0 && A[i] > 0) {
				S[i] = S[i-1];	//	뒷 칸에 있는 로봇을 앞 칸으로 이동
				S[i-1] = 0;		//	뒷 칸 비우기
				A[i] -= 1;		//	앞 칸 내구도 -1
				if(A[i] == 0) Pung += 1;
			}
		}
		// 과정 이후 N번칸에 로봇 있으면 바로 내리기
		if(S[N] != 0) {
			S[N] = 0;
		}
	}	//	end of moveRobot

	private static void moveBelt() {
		int tempA = A[2*N];
		int tempS = S[2*N];
		for(int i = 2*N; i>1; i--) {
			A[i] = A[i-1];
			S[i] = S[i-1];
		}
		A[1] = tempA;
		S[1] = tempS;
		// 과정 이후 N번칸에 로봇 있으면 바로 내리기
		if(S[N] != 0) {
			S[N] = 0;
		}
	}	//	end of move Belt
	
}	//	end of class
