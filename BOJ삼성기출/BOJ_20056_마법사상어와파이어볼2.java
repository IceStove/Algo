package boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20056_마법사상어와파이어볼2 {
	public static class FireBall {
		int y;	//	y좌표
		int x;	//	x좌표
		int m;	//	질량
		int s;	//	속력
		int d;	//	방향
		public FireBall() {

		}
		public FireBall(int y, int x, int m, int s, int d) {
			this.y = y;
			this.x = x;
			this.m = m;
			this.s = s;
			this.d = d;
		}
		@Override
		public String toString() {
			return "FireBall [y=" + y + ", x=" + x + ", m=" + m + ", s=" + s + ", d=" + d + "]";
		}
	}
	private static int N;
	private static int M;
	private static int K;
	private static int[] dy = {-1, -1, 0, +1, +1, +1, 0, -1};
	private static int[] dx = {0, +1, +1, +1, 0, -1, -1, -1};
	private static Queue<FireBall> FQ;
	private static Queue<FireBall>[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		FQ = new LinkedList<FireBall>();	//	불 저장
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			FireBall temp = new FireBall(y, x, m, s, d);
			FQ.add(temp);
		}
		
		play();
		
		int Answer = 0;
		while(!FQ.isEmpty()) {
			FireBall temp = FQ.poll();
			Answer += temp.m;
		}
		System.out.println(Answer);
	}	//	end of main

	private static void play() {
		int t = 0;
		
		map = new Queue[N+1][N+1];
		for(int i = 0; i<N+1; i++) {
			for(int j = 0; j<N+1; j++) {
				map[i][j] = new LinkedList<FireBall>();
			}
		}
		while(t < K) {
			move();
			pung();
			t++;
		}
	}	//	end of play
	
	private static void move() {
		while(!FQ.isEmpty()) {
			FireBall temp = FQ.poll();
			
			int cy = temp.y;
			int cx = temp.x;
			int m = temp.m;
			int s = temp.s;
			int d = temp.d;
			
			int ny = cy + dy[d] * s;
			int nx = cx + dx[d] * s;
			
			ny = ny % N;
			nx = nx % N;
			
			if(ny <= 0) ny += N;
			if(nx <= 0) nx += N;
			
			FireBall next = new FireBall(ny, nx, m, s, d);
			
			map[ny][nx].add(next);
		}
	}	//	end of move

	private static void pung() {
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				int mapSize = map[i][j].size();
				if(mapSize == 1) {
					FireBall temp = map[i][j].poll();
					FQ.add(temp);
				} else if(mapSize >= 2) {
					int sumM = 0;
					int sumS = 0;
					int nextD = -1;
					while(!map[i][j].isEmpty()) {
						FireBall temp = map[i][j].poll();
						sumM += temp.m;
						sumS += temp.s;
						if(nextD == -1) {
							nextD = temp.d % 2;
						} else if(nextD == 0 || nextD == 1) {
							int tempD = temp.d % 2;
							if(nextD != tempD) {
								nextD = 3;
							}
						} else {
							//	nextD == 3일 때, 더 이상 비교 의미없음
						}
					}
					int nextM = sumM / 5;
					int nextS = sumS / mapSize;
					if(nextM > 0) {
						for(int nth = 0; nth < 4; nth++) {
							if(nextD == 0 || nextD == 1) {
								FireBall next = new FireBall(i, j, nextM, nextS, 2*nth);
								FQ.add(next);
							} else {
								FireBall next = new FireBall(i, j, nextM, nextS, 2*nth + 1);
								FQ.add(next);
							}
						}
					}
				}
			}
		}
	}	//	end of pung

	
}	//	end of class
