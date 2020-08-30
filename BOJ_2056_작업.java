package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2056_작업 {
	private static int N;
	private static int[] works;
	private static ArrayList[] preWorks;
	private static int[] times;
	private static int Answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		works = new int[N+1];	//	1번 인덱스부터 사용
		times = new int[N+1];	//	사전 작업 포함 걸리는 시간 계산용
		
		preWorks = new ArrayList[N+1];	//	사전 작업 리스트
		
		for(int i = 0; i<=N; i++) {	//	리스트 초기화, 종료 여부 초기화
			preWorks[i] = new ArrayList<Integer>();
		}
		
		for(int i = 1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			works[i] = Integer.parseInt(st.nextToken());
			
			int preNum = Integer.parseInt(st.nextToken());
			
			for(int k = 0; k<preNum; k++) {
				int temp = Integer.parseInt(st.nextToken());
				preWorks[i].add(temp);
			}
		}
		
//		System.out.println(Arrays.toString(works));
//		
//		for(int i = 1; i<=N; i++) {
//			System.out.println(preWorks[i].toString());
//		}
		boolean play = true;
		for(int i = 1; i<=N; i++) {
			if(preWorks[i].size() == 0) {
				times[i] = works[i];
			}
		}
		while(play) {
			for(int i = 1; i<=N; i++) {
				if(times[i] == 0) {
					int checkResult = checker(i);
					if(checkResult == -1) {
						// pass
					} else {
						times[i] = checkResult + works[i];
					}
				}
			}
			
			for(int i = 1; i<=N; i++) {
				play = false;
				if(times[i] == 0) {
					play = true;
					break;
				}
			}
		}
		
		Answer = 0;
		for(int i = 1; i<=N; i++) {
			if(Answer < times[i]) {
				Answer = times[i];
			}
		}
		System.out.println(Answer);
	}	//	end of main

	private static int checker(int nth) {	//	사전 작업 끝난 유무 체크
		int result = -1;
		for(int i = 0; i<preWorks[nth].size(); i++) {
			int curTimes = times[(int) preWorks[nth].get(i)];
			if(curTimes == 0) {
				result = -1;
				break;
			} else {
				if(result < curTimes) {
					result = curTimes;
				}
			}
		}
		
		return result;
	}	//	end of checker
	
}	//	end of class
