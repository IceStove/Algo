package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_19238_스타트택시 {
	private static int N;
	private static int M;
	private static int F;
	private static int[][] map;
	private static Taxi taxi;
	private static Passenger[] ps;
	private static int[][] smap;
	private static int[][] emap;
	
	// 상하좌우 순서
	private static int[] dy = {-1, +1, 0, 0};
	private static int[] dx = {0, 0, -1, +1};
	private static int Answer;

	public static class Taxi {
		int num;	//	타고 있는 손님 번호
		int y;		//	택시 y 좌표
		int x;		//	택시 x 좌표
		int fuel;	//	연료량
		
		public Taxi() {
			super();
		}
		
		public Taxi(int num, int y, int x, int fuel) {
			super();
			this.num = num;
			this.y = y;
			this.x = x;
			this.fuel = fuel;
		}
		
		@Override
		public String toString() {
			return "Taxi [num=" + num + ", y=" + y + ", x=" + x + ", fuel=" + fuel + "]";
		}
	}
	
	public static class Passenger {
		int sy;	//	시작 y 좌표
		int sx;	//	시작 x 좌표
		int ey;	//	도착 y 좌표
		int ex;	//	도착 x 좌표
		boolean arrived;	//	도착 여부
		public Passenger() {
			super();
		}
		public Passenger(int sy, int sx, int ey, int ex, boolean arrived) {
			super();
			this.sy = sy;
			this.sx = sx;
			this.ey = ey;
			this.ex = ex;
			this.arrived = arrived;
		}
		@Override
		public String toString() {
			return "Passenger [sy=" + sy + ", sx=" + sx + ", ey=" + ey + ", ex=" + ex + ", arrived=" + arrived + "]";
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = Integer.parseInt(st.nextToken());	//	맵 한 변의 길이
		M = Integer.parseInt(st.nextToken());	//	승객 수
		F = Integer.parseInt(st.nextToken());	//	초기 연료량
		
		map = new int[N+2][N+2];	//	1~N 좌표를 이용할 예정
		smap = new int[N+2][N+2];	//	승객 시작점만 있는 맵
		emap = new int[N+2][N+2];	//	승객 도착점 놓고 돌릴 맵
		Answer = 0;
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N+2; i++) {	//	벽 두르기
			map[i][0] = 1;
			map[i][N+1] = 1;
			map[0][i] = 1;
			map[N+1][i] = 1;
		}
		
		for(int i = 0; i<N+2; i++) {	//	기본 맵을 시작맵, 도착맵에 복사
			smap[i] = Arrays.copyOf(map[i], map[i].length);
			emap[i] = Arrays.copyOf(map[i], map[i].length);
		}
		
		st = new StringTokenizer(br.readLine());
		int ty = Integer.parseInt(st.nextToken());
		int tx = Integer.parseInt(st.nextToken());
		
		taxi = new Taxi(0, ty, tx, F);	//	초기 택시 정보 입력 완료
		
		ps = new Passenger[M+2];	//	승객 2번부터 써야지 ㅇㅅㅇ --> 맵이 0, 1로 구성되어 있으므로
		
		for(int i = 2; i<M+2; i++) {	//	승객 2번부터 입력받기
			st = new StringTokenizer(br.readLine());
			int sy = Integer.parseInt(st.nextToken());
			int sx = Integer.parseInt(st.nextToken());
			int ey = Integer.parseInt(st.nextToken());
			int ex = Integer.parseInt(st.nextToken());
			
			ps[i] = new Passenger(sy, sx, ey, ex, false);	//	승객 정보 초기화
			smap[sy][sx] = i;	//	시작점 맵에 표시
		}
		
		while(true) {
			if(noPassenger()) break;	//	모든 승객이 도착하여 더 이상 태울 승객이 없음
			
			int nextPassenger = findPassenger();
			if(nextPassenger == 0) {	//	승객 못찾음
				Answer = -1;
			} else {	//	승객 찾음
				int reach = findDestination();
				if(reach == 0) {	//	도착 못함
					Answer = -1;
				} else {	//	도착함
					
				}
			}
			if(Answer == -1) {	//	승객 찾기, 도착지에 도달하기 미션 중에 실패한 경우
				break;
			}
		}
		
		if(Answer == 0) Answer = taxi.fuel;
		System.out.println(Answer);
	}	//	end of main

	private static boolean noPassenger() {	//	모든 승객이 도착했으면 true, 아니면 false
		boolean result = true;
		
		for(int i = 2; i<M+2; i++) {
			if(ps[i].arrived == false) {
				result = false;
				break;
			}
		}
		return result;
	}

	private static int findDestination() {
		int result = 0;
		
		//	택시 정보 입력
		int cy = taxi.y;
		int cx = taxi.x;
		int num = taxi.num;
		
		//	도착지 정보 입력
		int ey = ps[num].ey;
		int ex = ps[num].ex;
		emap[ey][ex] = num;
		
		if(emap[cy][cx] == num) {	//	현재 택시 위치가 도착지일 경우
			taxi.num = 0;	//	승객 내림
			ps[num].arrived = true;	//	승객 도착여부 입력
			result = 1;	//	도착을 하였으므로 1 입력
			emap[cy][cx] = 0;
		} else {
			Queue<int[]> q = new LinkedList<int[]>();
			int[][] checkmap = new int[N+2][N+2];
			q.add(new int[] {cy, cx});
			checkmap[cy][cx] = 1;
			
			int f = 0;
			while(!q.isEmpty()) {
				int qSize = q.size();
				f += 1;	//	연료 1 사용
				
				if(taxi.fuel < f) {	//	택시가 갖고 있는 연료량보다 소모한 연료량이 더 크게 될 경우
					result = 0;	//	도착지 도달 못하고
					break;	//	펑
				}
				
				for(int t = 0; t<qSize; t++) {
					int[] temp = q.poll();
					for(int dir = 0; dir<4; dir++) {
						int ny = temp[0] + dy[dir];
						int nx = temp[1] + dx[dir];
						
						if(ny < 1 || ny > N || nx < 1 || nx > N || emap[ny][nx] == 1 || checkmap[ny][nx] == 1) {
							continue;
						}
						
						if(emap[ny][nx] == num) {	//	다음 위치가 도착지일 경우
							taxi.num = 0;
							ps[num].arrived = true;
							taxi.y = ny;
							taxi.x = nx;
							result = 1;
							checkmap[ny][nx] = 1;
							
							taxi.fuel = taxi.fuel + f;
							emap[ny][nx] = 0;
							return result;
						} else if(emap[ny][nx] == 0) {
							q.add(new int[] {ny, nx});
							checkmap[ny][nx] = 1;				
						}		
					}
				}
				
//				if(result == 1) {	//	손님이 도착지에 도착했을 경우
//					taxi.fuel = taxi.fuel + f;	//	소모한 연료량의 2배를 더 준다는 말은 --> 그냥 소모한 연료량 만큼 더해주면 됨
//					emap[taxi.y][taxi.x] = 0;	//	도착맵에서 삭제해야함
//					break;
//				}
			}
		}
		return result;
	}

	private static int findPassenger() {
		int result = 0;
		
		int cy = taxi.y;
		int cx = taxi.x;
		
		if(smap[cy][cx] != 0 && smap[cy][cx] != 1) {	// 현재 택시 위치에 태울 승객이 존재할 경우
			result = smap[cy][cx];
//			얘넨 변화가 없으므로 입력하지 않는다.
//			taxi.y = cy;
//			taxi.x = cx;
//			taxi.fuel = taxi.fuel;
			taxi.num = result;	//	태운 승객 표시
			smap[cy][cx] = 0;
		} else {	//	현재 택시 위치에 승객이 존재하지 않을 경우 -> BFS 돌면서 찾아야지
			int[][] checkmap = new int[N+2][N+2];
			Queue<int[]> q = new LinkedList<int[]>();
			PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
				// int[] o => [승객 번호, 승객 y좌표, 승객 x좌표]
				@Override
				public int compare(int[] o1, int[] o2) {
					if(o1[1] > o2[1]) {
						return 1;
					} else if(o1[1] < o2[1]) {
						return -1;
					} else {	//	둘이 같은 경우
						if(o1[2] > o2[2]) return 1;
						else return -1;
					}
				}
			});
			q.add(new int[] {cy, cx});
			checkmap[cy][cx] = 1;
			int f = 0;
			while(!q.isEmpty()) {
				int qSize = q.size();
				f += 1;	//	연료 1 사용
				
				if(taxi.fuel < f) {	//	택시가 갖고 있는 연료량보다 소모한 연료량이 더 클 경우
					result = 0;
					break;
				}
				
				for(int t = 0; t<qSize; t++) {	//	연료 1 사용만큼의 퍼뜨리기
					int[] temp = q.poll();
					for(int dir = 0; dir<4; dir++) {
						int ny = temp[0] + dy[dir];
						int nx = temp[1] + dx[dir];
						
						if(ny < 1 || ny > N || nx < 1 || nx > N || smap[ny][nx] == 1 || checkmap[ny][nx] == 1) {
							continue;
						}
						
						if(smap[ny][nx] == 0) {
							q.add(new int[] {ny, nx});
							checkmap[ny][nx] = 1;
						} else if(smap[ny][nx] > 1) {	//	2부터 승객임
							pq.add(new int[] {smap[ny][nx], ny, nx});	//	승객 리스트에 추가
							q.add(new int[] {ny, nx});
							checkmap[ny][nx] = 1;
						}
					}
				}
				if(pq.size() > 0) {
					int[] np = pq.poll();
					result = np[0];
					taxi.num = np[0];
					taxi.y = np[1];
					taxi.x = np[2];
					taxi.fuel = taxi.fuel - f;
					
					smap[np[1]][np[2]] = 0;	//	해당 위치의 승객이 탑승했으니 지도에서 삭제한다.
					break;
				}
			}
		}
		return result;
	}	//	end of findPassenger
}	//	end of class
