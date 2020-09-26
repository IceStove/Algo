package boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3190_뱀 {
	public static class Order {
		int time;	//	시간
		char direction;	//	방향
		public Order() {
			super();
		}
		public Order(int time, char direction) {
			this.time = time;
			this.direction = direction;
		}
		@Override
		public String toString() {
			return "Order [time=" + time + ", direction=" + direction + "]";
		}
	}
	private static int N;
	private static int[][] map;
	private static int K;
	private static int L;
	private static ArrayDeque<int[]> snake;
	private static int Answer;
	private static Queue<Order> orders;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N+2][N+2];	//	벽으로 두르고 1~N까지 사용하기 위함
		
		for(int i = 0; i<N+2; i++) {
			map[i][0] = 5;
			map[i][N+1] = 5;
			map[0][i] = 5;
			map[N+1][i] = 5;
		}
		
		K = Integer.parseInt(br.readLine());
		for(int i = 0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			map[y][x] = 1;
		}
		
		L = Integer.parseInt(br.readLine());
		orders = new LinkedList<Order>();
		
		for(int i = 0; i<L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			char direction = st.nextToken().charAt(0);
			orders.offer(new Order(time, direction));
		}
		
		snake = new ArrayDeque<int[]>();
		snake.addFirst(new int[]{1, 1, 1});
		map[1][1] = 5;
		play();
		System.out.println(Answer);
	}	//	end of main

	private static void play() {
		int curTime = 0;
		while(true) {
			int[] head = snake.peekFirst();
			
			int cy = head[0];
			int cx = head[1];
			int cdir = head[2];
			
			int ny = 0;
			int nx = 0;
			int ndir = cdir;
			
			if(!orders.isEmpty()) {
				if(orders.peek().time == curTime) {
					Order o = orders.poll();
					if(o.direction == 'L') {
						ndir = cdir - 1;
					} else {
						ndir = cdir + 1;
					}
					
					if(ndir == -1) {
						ndir = 3;
					} else if(ndir == 4) {
						ndir = 0;
					}
					
					int[] temp = snake.pollFirst();
					temp[2] = ndir;
					snake.addFirst(temp);
				}
			}
			
			if(ndir == 0) {	//	상
				ny = cy - 1;
				nx = cx;
			} else if(ndir == 1) {	//	우
				ny = cy;
				nx = cx + 1;
			} else if(ndir == 2) {	//	하
				ny = cy + 1;
				nx = cx;
			} else if(ndir == 3) {	//	좌
				ny = cy;
				nx = cx - 1;
			} else {	//	벽에 부딪치거나 자기 몸에 부딪쳐서 죽었을 때
//				Answer = curTime;
				break;
			}
			
			if(map[ny][nx] == 5) {	//	벽 or 자기몸
				ndir = -1;
				snake.addFirst(new int[] {ny, nx, ndir});
				Answer = curTime + 1;
				break;
			} else if(map[ny][nx] == 1)	{	//	사과
				map[ny][nx] = 5;
				snake.addFirst(new int[] {ny, nx, ndir});
			} else {	//	빈 칸
				int[] tail = snake.pollLast();
				map[tail[0]][tail[1]] = 0;
				map[ny][nx] = 5;
				snake.addFirst(new int[] {ny, nx, ndir});
			}
			
			curTime += 1;
		}
	}	//	end of play
}	//	end of class
