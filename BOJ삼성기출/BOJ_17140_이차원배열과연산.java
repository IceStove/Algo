package boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_17140_이차원배열과연산 {
	private static int r;
	private static int c;
	private static int k;
	private static int[][] map;
	private static int Answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[3][3];
		
		for(int i = 0; i<3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		r = r-1;	//	문제가 1인덱스부터 시작하기 때문에 0인덱스부터 구현한 배열에 맞추기 위해서 재정의
		c = c-1;
		Answer = -1;
		int time = 0;
		while(time <= 100) {
			// 배열 연산 과정 중에 배열의 크기가 r, c보다 작아질 수 있다. 그때 발생하는 문제를 잡기 위해 배열 크기 비교부터한다.
			if(map.length > r && map[0].length > c && map[r][c] == k) {
				Answer = time;
				break;
			}
			Play();
			time += 1;
		}
		System.out.println(Answer);
	}	//	end of main

	private static void Play() {
		if(map.length >= map[0].length) {
			PlayR();
		} else {
			PlayC();
		}
	}	//	end of Play
	private static void PlayR() {	//	R 연산
		PriorityQueue<int[]>[] Rpq = new PriorityQueue[map.length];
		for(int i = 0; i<map.length; i++) {
			Rpq[i] = new PriorityQueue<int[]>(new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if(o1[1] > o2[1]) {	//	숫자 등장 횟수를 기준으로
						return 1;
					} else if(o1[1] < o2[1]) {
						return -1;
					} else {	//	숫자 등장 횟수가 같은 경우
						if(o1[0] > o2[0]) {	//	숫자 자체를 기준으로
							return 1;
						} else {
							return -1;
						}
					}
				}
			});
		}
		int max = Integer.MIN_VALUE;
		for(int i = 0; i<map.length; i++) {
			int[] nums = new int[101];		//	어차피 최대 숫자가 1~100이고, 
			for(int j = 0; j<map[i].length; j++) {	//	한 행에 대해서 숫자 세기
				nums[map[i][j]] += 1;		//	왜 이렇게? 어차피 0에 +가 되어도 1부터 세면 그만
			}
			for(int n = 1; n<nums.length; n++) {
				if(nums[n] > 0) {
					int[] temp = new int[2];
					temp[0] = n;
					temp[1] = nums[n];
					Rpq[i].add(temp);
				}
			}
			if(Rpq[i].size() > max) max = Rpq[i].size();
		}
		if(max > 50) max = 50;	//	2칸짜리 배열이 50개가 넘어가면 배열 최대 크기인 100을 맞추기 위해 50으로 지정
		
		// 모든 Rpq에 값이 채워졌을 때, 가장 큰 길이로 배열을 새로 정의한다.
		int rlength = map.length;	//	행 개수
		int clength = max * 2;	//	열 개수
		map = new int[rlength][clength];
		for(int i = 0; i<rlength; i++) {
			int jlength = Math.min(max, Rpq[i].size());	//	여기에서 배열 최대 크기를 넘기지 않도록 j 크기 제한을 걸음
			for(int j = 0; j<jlength; j++) {
//				if(j == 50) break;	//	배열 최대 크기가 100이므로 0~49번까지의 데이터만 받아 넣을 수 있다.
				int[] temp = Rpq[i].poll();
				map[i][2*j] = temp[0];
				map[i][2*j + 1] = temp[1];
			}
		}
	}	//	end of PlayR

	private static void PlayC() {
		PriorityQueue<int[]>[] Cpq = new PriorityQueue[map[0].length];
		for(int j = 0; j<map[0].length; j++) {
			Cpq[j] = new PriorityQueue<int[]>(new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if(o1[1] > o2[1]) {	//	숫자 등장 횟수를 기준으로
						return 1;
					} else if(o1[1] < o2[1]) {
						return -1;
					} else {	//	숫자 등장 횟수가 같으면 숫자 자체를 기준으로
						if(o1[0] > o2[0]) 
							return 1;
						else 
							return -1;
					}
				}
			});
		}
		
		int max = Integer.MIN_VALUE;
		for(int j = 0; j<map[0].length; j++) {
			int[] nums = new int[101];	//	1~100까지
			for(int i = 0; i<map.length; i++) {
				nums[map[i][j]] += 1;
			}
			for(int n = 1; n<nums.length; n++) {
				if(nums[n] > 0) {
					int[] temp = new int[2];
					temp[0] = n;
					temp[1] = nums[n];
					Cpq[j].add(temp);
				}
			}
			if(Cpq[j].size() > max) max = Cpq[j].size();
		}
		if(max > 50) max = 50;	//	2칸짜리 배열이 50개가 넘으면 배열 최대 크기인 100을 맞추기 위해 50으로 저장
		
		// 모든 Cpq에 값이 채워졌을 때, 가장 큰 길이로 배열을 새로 정의한다.
		int rlength = max * 2;	//	행개수
		int clength = map[0].length;	//	열개수
		map = new int[rlength][clength];
		for(int j = 0; j<clength; j++) {
			int ilength = Math.min(max, Cpq[j].size());	//	배열 최대 크기를 넘지 않도록 하기 위해
			for(int i = 0; i<ilength; i++) {
				int[] temp = Cpq[j].poll();
				map[2*i][j] = temp[0];
				map[2*i + 1][j] = temp[1];
			}
		}
	}	//	end of PlayC

}	//	end of class
