package boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20058_마법사상어와파이어스톰 {
	private static int N;
	private static int Q;
	private static int mapSize;
	private static int[][] map;
	private static Queue<Integer> orderQ;
	private static int[] dy = {-1, +1, 0, 0};
	private static int[] dx = {0, 0, -1, +1};
	private static int restIce;
	private static int largeBlock;
	private static int[][] checkBlock;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		mapSize = (int)Math.pow(2, N);
		map = new int[mapSize][mapSize];
		
		for(int i = 0; i<mapSize; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<mapSize; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		orderQ = new LinkedList<Integer>();
		for(int i = 0; i<Q; i++) {
			orderQ.add(Integer.parseInt(st.nextToken()));
		}
		
		while(!orderQ.isEmpty()) {
			int tempOrder = orderQ.poll();			
			fireStorm(tempOrder);
		}
		
		restIce = iceSum();
		largeBlock = 0;
		if(restIce != 0) {
			checkBlock();			
		}
		System.out.println(restIce);
		System.out.println(largeBlock);
	}	//	end of main

	private static void checkBlock() {
		checkBlock = new int[mapSize][mapSize];
		int blockNum = 1;
		for(int i = 0; i<mapSize; i++) {
			for(int j = 0; j<mapSize; j++) {
				if(map[i][j] > 0 && checkBlock[i][j] == 0) {
					findBlock(i, j, blockNum);
				}
			}
		}
	}

	private static void findBlock(int y, int x, int num) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {y, x});
		checkBlock[y][x] = num;
		int blockSize = 0;
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			blockSize += 1;
			for(int dir = 0; dir<4; dir++) {
				int ny = temp[0] + dy[dir];
				int nx = temp[1] + dx[dir];
				
				if(ny < 0 || ny >= mapSize || nx < 0 || nx >= mapSize || checkBlock[ny][nx] != 0) {
					continue;
				}
				
				if(map[ny][nx] > 0) {
					checkBlock[ny][nx] = num;
					q.add(new int[] {ny, nx});
				}
			}
		}
		//	현재 구한 덩어리 크기가 가진 최대크기보다 클 경우
		if(largeBlock < blockSize) {
			largeBlock = blockSize;
		}
	}

	private static int iceSum() {
		int result = 0;
		for(int i = 0; i<mapSize; i++) {
			for(int j = 0; j<mapSize; j++) {
				result += map[i][j];
			}
		}
		return result;
	}

	private static void fireStorm(int L) {
		int tempSize = (int)Math.pow(2, L);
		
		int loop = mapSize / tempSize;
		
		int sy = 0;
		int sx = 0;
		
		// 격자로 나눈 칸들 90도로 회전
		for(int i = 0; i<loop; i++) {
			for(int j = 0; j<loop; j++) {
				sy = i*tempSize;
				sx = j*tempSize;
				rotate(sy, sx, tempSize);
			}
		}
		
		int[][] checkmap = new int[mapSize][mapSize];
		
		// 얼음이 있는 칸 3개 또는 그 이상과 인접해있지 않은 칸은 얼음의 양이 1 줄어든다.
		for(int y = 0; y<mapSize; y++) {
			for(int x = 0; x<mapSize; x++) {
				if(map[y][x] > 0) {	//	현재 칸이 얼음이 있는 칸인 경우
					int withIce = 0;
					for(int dir = 0; dir<4; dir++) {
						int ny = y + dy[dir];
						int nx = x + dx[dir];
						if(ny < 0 || ny >= mapSize || nx < 0 || nx >= mapSize) {
							continue;
						}
						if(map[ny][nx] > 0) {
							withIce += 1;
						}
					}
					if(withIce < 3) {	//	얼음이 있는 칸과 인접한 칸의 개수가 3개 밑일 경우 체크
						checkmap[y][x] = 1;
					}
				}
			}
		}
		
		for(int i = 0; i<mapSize; i++) {	//	얼음이 있는 칸 3개 또는 그 이상과 인접하지 않은 칸들의 얼음의 양을 1씩 줄인다.
			for(int j = 0; j<mapSize; j++) {
				if(checkmap[i][j] == 1 && map[i][j] > 0) {
					map[i][j] -= 1;
				}
			}
		}
	}

	private static void rotate(int sy, int sx, int tempSize) {
		int[][] tempMap = new int[tempSize][tempSize];
		// 기존 맵을 tempMap에 저장
		for(int i = 0; i<tempSize; i++) {
			for(int j = 0; j<tempSize; j++) {
				tempMap[i][j] = map[sy+i][sx+j];
			}
		}
		// tempMap 90도 회전한 것을 기존 맵에 저장
		for(int i = 0; i<tempSize; i++) {
			for(int j = 0; j<tempSize; j++) {
				map[j + sy][tempSize-1 - i + sx] = tempMap[i][j];
			}
		}
	}

}	//	end of class
