package prac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Shark{
	int y;
	int x;
	int speed;
	int direction;
	int size;
	int live;
	
	public Shark() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Shark(int y, int x, int speed, int direction, int size, int live) {
		super();
		this.y = y;
		this.x = x;
		this.speed = speed;
		this.direction = direction;
		this.size = size;
		this.live = live;
	}

	@Override
	public String toString() {
		return "Shark [y=" + y + ", x=" + x + ", speed=" + speed + ", direction=" + direction + ", size=" + size
				+ ", live=" + live + "]";
	}
	
}

public class BOJ_17143_낚시왕 {
	private static int R;
	private static int C;
	private static int M;
	private static Shark[] fish;
	private static int bag;
	private static PriorityQueue<Shark>[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
//		fish = new Shark[M];
		
		map = new PriorityQueue[R+1][C+1];
		
		bag = 0;
		
		for(int i = 0; i<R+1; i++) {
			for(int j = 0; j<C+1; j++) {
				map[i][j] = new PriorityQueue<Shark>(new Comparator<Shark>() {
					@Override
					public int compare(Shark s1, Shark s2) {
						return s2.size - s1.size;
					}
				});
			}
		}
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int cy = Integer.parseInt(st.nextToken());
			int cx = Integer.parseInt(st.nextToken());
			int cspeed = Integer.parseInt(st.nextToken());
			int cdirection = Integer.parseInt(st.nextToken());
			int csize = Integer.parseInt(st.nextToken());
//			fish[i] = new Shark(cy, cx, cspeed, cdirection, csize, 1);	//	상어 추가 마지막 1은 살아있는지 죽었는지 여부 표시
			Shark temp = new Shark(cy, cx, cspeed, cdirection, csize, 1);	//	상어 추가 마지막 1은 살아있는지 죽었는지 여부 표시
			map[cy][cx].offer(temp);	//	지도에 상어 표시
		}
		
		// 입력이 잘 되었는지 확인용
//		for(int i = 0; i<M; i++) {
//			System.out.println(fish[i]);
//		}
		
		// 상어 지도에 잘 입력되었는지 확인용
//		for(int i = 0; i<R+1; i++) {
//			for(int j = 0; j<C+1; j++) {
//				System.out.print(map[i][j].toString() + ' ');
//			}
//			System.out.println();
//		}
		
		Queue<Shark> q = new LinkedList<Shark>();
		for(int j = 1; j<C+1; j++) {	//	한 칸 오른쪽으로 이동
			for(int i = 1; i<R+1; i++) {	//	밑으로 한칸 씩 체크
				if(map[i][j].size() != 0) {
					Shark temp = map[i][j].poll();
					bag += temp.size;
					map[i][j].clear();
					break;
				}
			}
			// 여기에서 상어들 이동시키기
			for(int cy = 1; cy<R+1; cy++) {	//	지도에 있는 모든 상어 담기
				for(int cx = 1; cx<C+1; cx++) {
					if(map[cy][cx].size() != 0) {
						q.offer(map[cy][cx].poll());
						map[cy][cx].clear();
					}
				}
			}
			
			while(!q.isEmpty()) {
				Shark temp = q.poll();
				
				int cy = temp.y;
				int cx = temp.x;
				int dir = temp.direction;
				int ny = cy;
				int nx = cx;
				
				int num = temp.speed;
				if(dir == 1 || dir == 2) {
					num = temp.speed % ((R-1)*2);
				}
				else if(dir == 3 || dir == 4) {
					num = temp.speed % ((C-1)*2);
				}
				
				for(int i = 0; i<num; i++) {
					if(dir == 1) {	//	상
						ny = cy - 1;
					}else if(dir == 2) {	//	하
						ny = cy + 1;
					}else if(dir == 3) {	//	우
						nx = cx + 1;
					}else if(dir == 4) {	//	좌
						nx = cx - 1;
					}
					
					if(ny == 0) {
						dir = 2;
						ny += 2;
					}else if(ny == R+1) {
						dir = 1;
						ny -= 2;
					}else if(nx == 0) {
						dir = 3;
						nx += 2;
					}else if(nx == C+1) {
						dir = 4;
						nx -= 2;
					}
					
					cy = ny;
					cx = nx;
				}
				Shark next = new Shark(cy, cx, temp.speed, dir, temp.size, temp.live);
				map[cy][cx].offer(next);
			}
			
			
			
			
		}	//	end of 낚시
		
		System.out.println(bag);
		
	}	//	end of main
}	//	end of class
