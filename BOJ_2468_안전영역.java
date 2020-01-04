package prac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2468_안전영역 {
	private static int N;
	private static int[][] map;
	private static int[][] checkmap;
	private static int[][] savemap;
	private static int minheight;
	private static int maxheight;
	private static int Answer;
	private static int tempAnswer;

	private static int[] dy = {-1, +1, 0, 0};
	private static int[] dx = {0, 0, -1, +1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		savemap = new int[N][N];
		map = new int[N][N];
		checkmap = new int[N][N];
		
		minheight = 101;
		maxheight = -1;
		
		Answer = 1;	//	일단 최소 영역은 1일수 밖에 없다.
		
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				savemap[i][j] = Integer.parseInt(st.nextToken());
				if(savemap[i][j] > maxheight) {
					maxheight = savemap[i][j];
				}
				if(savemap[i][j] < minheight) {
					minheight = savemap[i][j];
				}
			}
		}

//		for(int i = 0; i<N; i++) {
//			System.out.println(Arrays.toString(savemap[i]));
//		}
//		System.out.println();
		
		for(int h = minheight; h<=maxheight; h++) {
			// 맵 만들고, 체크맵 리셋한다.
			setMap(h);
			resetCheckmap();
			tempAnswer = 0;	//	단지번호 사용용도 && 답이랑 비교 용도
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					if(map[i][j] == 1 && checkmap[i][j] == 0) {
						tempAnswer += 1;
						BFS(i, j, tempAnswer);				
					}
				}
			}
			
//			for(int i = 0; i<N; i++) {
//				System.out.println(Arrays.toString(checkmap[i]));
//			}
//			System.out.println();
			
			
			if(Answer < tempAnswer) {
				Answer = tempAnswer;
			}
		}
		System.out.println(Answer);
	}
	
	private static void setMap(int height) {
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(savemap[i][j] > height) {
					map[i][j] = 1;
				}else {
					map[i][j] = 0;
				}
			}
		}
	}
	
	private static void resetCheckmap() {
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				checkmap[i][j] = 0;
			}
		}
	}
	
	private static void BFS(int y, int x, int danjinum) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {y, x});
		checkmap[y][x] = danjinum;
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			for(int dir = 0; dir<4; dir++) {
				int ny = temp[0] + dy[dir];
				int nx = temp[1] + dx[dir];
				if(ny < 0 || ny >= N || nx < 0 || nx >= N || checkmap[ny][nx] != 0 || map[ny][nx] == 0) {
					continue;
				}	
				if(map[ny][nx] == 1) {
					q.offer(new int[] {ny, nx});
					checkmap[ny][nx] = danjinum;
				}
			}
		}
	}
	
}
