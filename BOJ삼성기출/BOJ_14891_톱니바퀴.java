package boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14891_톱니바퀴 {
	private static int[][] wheel;
	private static int K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		wheel = new int[4][8];	//	N극:0, S극:1
		
		for(int i = 0; i<4; i++) {
			char[] temp = br.readLine().toCharArray();
			for(int j = 0; j<8; j++) {
				wheel[i][j] = (int)(temp[j] - '0');
			}
		}
		
		K = Integer.parseInt(br.readLine());	//	1 <= 회전 횟수 <= 100
		StringTokenizer st;
		for(int i = 0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			play(num - 1, dir);	//	입력해서 들어오는 인덱스는 1부터인데, 배열은 0부터이므로
		}
		System.out.println(score());
	}	//	end of main

	private static void play(int num, int dir) {
		Queue<int[]> q = new LinkedList<int[]>();
		Queue<int[]> mq = new LinkedList<int[]>();
		int[] check = new int[4];
		q.offer(new int[] {num, dir});
		check[num] = 1;
		while(!q.isEmpty()) {	//	돌려야할 톱니바퀴 체크
			int[] cur = q.poll();
			int cnum = cur[0];
			int cdir = cur[1];
			if(cnum > 0 && wheel[cnum][6] != wheel[cnum-1][2] && check[cnum-1] == 0) {
				check[cnum-1] = 1;
				q.offer(new int[] {cnum-1, cdir*(-1)});
			}
			if(cnum < 3 && wheel[cnum][2] != wheel[cnum+1][6] && check[cnum+1] == 0) {
				check[cnum+1] = 1;
				q.offer(new int[] {cnum+1, cdir*(-1)});
			}
			mq.offer(cur);	//	다시 저장
		}
		while(!mq.isEmpty()) {	//	톱니바퀴 돌리기
			int[] cur = mq.poll();
			move(cur[0], cur[1]);
		}
	}	//	end of play
	
	private static void move(int num, int dir) {
		if(dir == -1) {	//	반시계 방향
			int temp = wheel[num][0];
			for(int i = 0; i<7; i++) {
				wheel[num][i] = wheel[num][i+1];
			}
			wheel[num][7] = temp;			
		} else {	//	시계 방향
			int temp = wheel[num][7];
			for(int i = 7; i>0; i--) {
				wheel[num][i] = wheel[num][i-1];
			}
			wheel[num][0] = temp;
		}
	}	//	end of move
	
	private static int score() {
		int result = 0;
		for(int i = 0; i<4; i++) {
			int temp = wheel[i][0];
			for(int j = 0; j<i; j++) {
				temp = temp * 2;
			}
			result += temp;
		}
		return result;
	}	//	end of score
}	//	end of class