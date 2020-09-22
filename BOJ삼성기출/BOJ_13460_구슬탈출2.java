package boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13460_구슬탈출2_move개선 {
	public static class Beads {
		int num;
		int dir;
		int ry;
		int rx;
		int by;
		int bx;
		public Beads() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Beads(int num, int dir, int ry, int rx, int by, int bx) {
			super();
			this.num = num;
			this.dir = dir;
			this.ry = ry;
			this.rx = rx;
			this.by = by;
			this.bx = bx;
		}
		@Override
		public String toString() {
			return "Beads [num=" + num + ", dir=" + dir + ", ry=" + ry + ", rx=" + rx + ", by=" + by + ", bx=" + bx
					+ "]";
		}
	}
	
	private static int N;
	private static int M;
	private static char[][] map;
	private static Beads beads;
	private static int Answer;
	private static Queue<Beads> q;
	private static int[] dy = {-1, +1, 0, 0};
	private static int[] dx = {0, 0, -1, +1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		map = new char[N][M];
		
		beads = new Beads();
		
		for(int i = 0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j = 0; j<M; j++) {
				if(map[i][j] == 'R') {
					beads.ry = i;
					beads.rx = j;
				} else if(map[i][j] == 'B') {
					beads.by = i;
					beads.bx = j;
				}
			}
		}
		beads.num = 0;
		beads.dir = -1;
		
		// 입력 잘 받았는지 확인
//		for(int i = 0; i<N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
//		System.out.println(beads);
		
		map[beads.ry][beads.rx] = '.'; 
		map[beads.by][beads.bx] = '.'; 
		
		Answer = 0;
		play();
		if(Answer == 0) Answer = -1;
		System.out.println(Answer);
		
	}	//	end of main

	private static void play() {
		q = new LinkedList<Beads>();
		for(int dir = 0; dir<4; dir++) {
			Beads temp = new Beads(1, dir, beads.ry, beads.rx, beads.by, beads.bx);
			q.offer(temp);
		}
		
		while(!q.isEmpty()) {
			Beads temp = q.poll();
			
			if(temp.num > 10) {
				break;
			}
			
			int rcy = temp.ry;
			int rcx = temp.rx;
			int bcy = temp.by;
			int bcx = temp.bx;
			int cdir = temp.dir;
			
			int[] nextR = new int[2];	//	y, x 좌표순
			int[] nextB = new int[2];	//	y, x 좌표순
			
			map[rcy][rcx] = 'R';
			map[bcy][bcx] = 'B';
			
			if(cdir == 0) {
				if(rcy < bcy) {
					nextR = move(rcy, rcx, cdir, 'R', 'B');
					nextB = move(bcy, bcx, cdir, 'B', 'R');
				} else {
					nextB = move(bcy, bcx, cdir, 'B', 'R');
					nextR = move(rcy, rcx, cdir, 'R', 'B');
				}
			} else if(cdir == 1) {
				if(rcy > bcy) {
					nextR = move(rcy, rcx, cdir, 'R', 'B');
					nextB = move(bcy, bcx, cdir, 'B', 'R');
				} else {
					nextB = move(bcy, bcx, cdir, 'B', 'R');
					nextR = move(rcy, rcx, cdir, 'R', 'B');
				}
			} else if(cdir == 2) {
				if(rcx < bcx) {
					nextR = move(rcy, rcx, cdir, 'R', 'B');
					nextB = move(bcy, bcx, cdir, 'B', 'R');
				} else {
					nextB = move(bcy, bcx, cdir, 'B', 'R');
					nextR = move(rcy, rcx, cdir, 'R', 'B');
				}
			} else if(cdir == 3) {
				if(rcx > bcx) {
					nextR = move(rcy, rcx, cdir, 'R', 'B');
					nextB = move(bcy, bcx, cdir, 'B', 'R');
				} else {
					nextB = move(bcy, bcx, cdir, 'B', 'R');
					nextR = move(rcy, rcx, cdir, 'R', 'B');
				}
			}
			
			if(nextR[0] == -1 && nextB[0] == -1) {	//	빨간, 파란 구슬 구멍에 빠짐	--> 실패
				// 지도에 지울 구슬 없음
				// 실패
			} else if(nextR[0] == -1 && nextB[0] != -1) {	//	빨간 구슬만 구멍에 빠짐	--> 성공
				// 파란 구슬만 지우고
				map[nextB[0]][nextB[1]] = '.';
				// 성공
				Answer = temp.num;
				break;
			} else if(nextR[0] != -1 && nextB[0] == -1) {	//	파란 구슬만 구멍에 빠짐	--> 실패
				// 빨간 구슬만 지우고
				map[nextR[0]][nextR[1]] = '.';
				// 실패
			} else if(nextR[0] != -1 && nextB[0] != -1) {	//	둘 다 구멍에 안빠짐	--> 다음
				// 빨간, 파란 구슬 지우고
				map[nextR[0]][nextR[1]] = '.';
				map[nextB[0]][nextB[1]] = '.';
				// 다음 --> 큐에 넣기
				for(int dir = 0; dir<4; dir++) {
					Beads next = new Beads(temp.num + 1, dir, nextR[0], nextR[1], nextB[0], nextB[1]);
					if(temp.dir != next.dir) {
						q.offer(next);
					}
				}
			} else {
				// 이런 일은 없음
			}
		}
	}	//	end of play

	private static int[] move(int cy, int cx, int dir, char one, char other) {
		int[] result = new int[2];
		int ny = cy;
		int nx = cx;
		while(true) {
			if(map[ny+dy[dir]][nx+dx[dir]] == '.') {
				ny = ny + dy[dir];
				nx = nx + dx[dir];
			} else if(map[ny+dy[dir]][nx+dx[dir]] == '#' || map[ny+dy[dir]][nx+dx[dir]] == other) {
				break;
			} else if(map[ny+dy[dir]][nx+dx[dir]] == 'O') {
				ny = -1;
				nx = -1;
				break;
			}
		}
		
		map[cy][cx] = '.';
		if(ny != -1 && nx != -1) {
			map[ny][nx] = one;
		}
		result[0] = ny;
		result[1] = nx;
		return result;
	}	//	end of move
}	//	end of class
