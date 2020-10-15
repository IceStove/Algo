package boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15686_치킨배달 {
	private static int N;
	private static int M;
	private static int[][] map;
	private static ArrayList<int[]> home;
	private static ArrayList<int[]> store;
	private static int[] selected;
	private static int Answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//	2 <= N(맵 가로,세로) <= 50
		M = Integer.parseInt(st.nextToken());	//	1 <= M(남길 치킨집 수) <= 13
		map = new int[N][N];
		
		home = new ArrayList<int[]>();	//	가정집
		store = new ArrayList<int[]>();	//	치킨집
		selected = new int[M];			//	선택된 치킨집 번호
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					home.add(new int[] {i, j});
				} else if(map[i][j] == 2) {
					store.add(new int[] {i, j});
				}
			}
		}
		Answer = Integer.MAX_VALUE;
		Comb(0, 0, M);
		System.out.println(Answer);
	}	// end of main

	private static void Comb(int index, int count, int required) {
		if(count == required) {
			CalculateDistance();
			return;
		} else if(index == store.size()) {	//	인덱스 오버
			return;
		} else if(store.size() - index < required - count) {	//	필요한 양보다 남은 인덱스가 적은 경우
			return;
		} else {
			selected[count] = index;				//	선택된 번호 저장
			Comb(index + 1, count + 1, required);	//	선택하고 넘기기
			Comb(index + 1, count, required);		//	선택안하고 넘기기
		}
	}	//	end of Comb

	private static void CalculateDistance() {
		int result = 0;
		int[] distance = new int[home.size()];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		for(int i = 0; i<home.size(); i++) {
			int[] curHome = home.get(i);	//	현재 집 좌표
			for(int j = 0; j<selected.length; j++) {
				int[] curStore = store.get(selected[j]);
				int curDistance = Math.abs(curHome[0] - curStore[0]) + Math.abs(curHome[1] - curStore[1]);
				if(curDistance < distance[i]) distance[i] = curDistance;
			}
			result += distance[i];
		}
		if(result < Answer) Answer = result;
	}	//	end of CalculateDistance
}	//	end of class
