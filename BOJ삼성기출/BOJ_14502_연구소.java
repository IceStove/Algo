package boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502_연구소 {
	private static int N, M;
	private static int[][] map;
	private static int[][] savemap;
	private static ArrayList<int[]> vList;
	private static ArrayList<int[]> bList;
	private static int Answer;
	private static int[] dy = {-1, +1, 0, 0};
	private static int[] dx = {0, 0, -1, +1};	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//	3 <= 세로 <= 8
		M = Integer.parseInt(st.nextToken());	//	3 <= 가로 <= 8
		
		map = new int[N][M];
		savemap = new int[N][M];
		
		vList = new ArrayList<int[]>();	//	바이러스 좌표 저장용 리스트
		bList = new ArrayList<int[]>();	//	빈 칸 좌표 저장용 리스트
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) {
					bList.add(new int[] {i, j});
				} else if(map[i][j] == 2) {
					vList.add(new int[] {i, j});
				}
			}
		}
		
		for(int i = 0; i<N; i++) {
			savemap[i] = Arrays.copyOf(map[i], map[i].length);
		}
		Answer = 0;
		int[] select = new int[3];
		comb(select, 0, 0);
		System.out.println(Answer);
	}	//	end of main

	private static void comb(int[] select, int index, int count) {
		if(count == 3) {
//			System.out.println(Arrays.toString(select));
			resetMap();
			
			//	벽 3개 세우기
			for(int i = 0; i<3; i++) {
				int[] temp = bList.get(select[i]);
				map[temp[0]][temp[1]] = 1;
			}
			
			BFS();	//	바이러스 퍼트리기
			Check();	//	안전지역 확인
			return;
		}
		
		if(index == bList.size()) return;
		
		if(index < bList.size()) {
			select[count] = index;
			comb(select, index+1, count+1);
			select[count] = 0;
			comb(select, index+1, count);
		}
	}	//	end of comb
	
	private static void Check() {
		int safe = 0;
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(map[i][j] == 0) 
					safe += 1; 
			}
		}
		if(Answer < safe) {
			Answer = safe;
		}
	}	//	end of Check

	private static void BFS() {
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][] visit = new boolean[N][M];
		for(int i = 0; i<vList.size(); i++) {
			int[] temp = vList.get(i);
			visit[temp[0]][temp[1]] = true;
			q.add(temp);
		}
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			
			for(int dir = 0; dir<4; dir++) {
				int ny = temp[0] + dy[dir];
				int nx = temp[1] + dx[dir];
				
				if(ny < 0 || ny >= N || nx < 0 || nx >= M || visit[ny][nx] == true || map[ny][nx] != 0) {
					// 범위 벗어나거나, 이미 방문한 곳이거나, 빈 칸이 아닐 때 패스
					continue;
				}
				
				map[ny][nx] = 2;
				visit[ny][nx] = true;
				q.add(new int[] {ny, nx});
			}
		}
	}	//	end of BFS

	private static void resetMap() {
		for(int i = 0; i<N; i++) {
			map[i] = Arrays.copyOf(savemap[i], savemap[i].length);
		}
	}	//	end of resetMap
}	//	end of class
