package boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16234_인구이동 {
	private static int N, L, R;
	private static int[][] map;
	private static int[][] checkmap;
	private static int allianceNum;
	private static int[] dy = {-1, +1, 0, 0};	//	상하좌우 순서
	private static int[] dx = {0, 0, -1, +1};
	private static ArrayList<Integer> allianceManList;
	private static int moveNum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	//	1 <= N <= 50
		L = Integer.parseInt(st.nextToken());	//	1 <= L <= R <= 100
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];			//	지도
		checkmap = new int[N][N];		//	각 국가의 번호표
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		moveNum = 0;
		while(moveNum <= 2000) {	//	문제의 조건에서 인구 이동 발생 횟수는 최대 2000번인 입력만 주어진다 하였음.
			resetCheckmap();
			
			allianceNum = 0;	//	연합국 번호는 0번부터
			allianceManList = new ArrayList<Integer>();	//	해당 번호의 연합국이 가질 평균 사람 수
			
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					if(checkmap[i][j] == -1) {
						BFS(i, j, allianceNum);
						allianceNum += 1;	//	이 과정이 끝나면 0~allianceNum-1까지의 번호를 부여받은 연합국이 존재한다.
					}
				}
			}
			
			if(allianceNum == N*N) {
				break;
			}
			
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					map[i][j] = allianceManList.get(checkmap[i][j]);
				}
			}
			moveNum += 1;
		}
		
		System.out.println(moveNum);
	}	//	end of main

	private static void BFS(int y, int x, int num) {
		Queue<int[]> q = new LinkedList<int[]>();
		int sum = 0;	// 연합국 인구수의 합
		int memberNum = 0;	//	연합국에 포함된 국가 수
		checkmap[y][x] = num;
		q.add(new int[] {y, x});
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int cy = temp[0];
			int cx = temp[1];
			sum += map[cy][cx];
			memberNum += 1;
			for(int dir = 0; dir < 4; dir++) {
				int ny = cy + dy[dir];
				int nx = cx + dx[dir];
				
				if(ny < 0 || ny >= N || nx < 0 || nx >= N || checkmap[ny][nx] != -1) {
					continue;
				}
				
				int chai = Math.abs(map[cy][cx] - map[ny][nx]);
				
				if(chai >= L && chai <= R) {
					checkmap[ny][nx] = num;
					q.add(new int[] {ny, nx});
				}
			}
		}
		if(memberNum != 0) {
			int average = sum / memberNum;
			allianceManList.add(average);	//	연합국의 평균 사람 수를 넣어줌
		}
	}	//	end of BFS

	private static void resetCheckmap() {
		for(int i = 0; i<N; i++) {
			Arrays.fill(checkmap[i], -1);
		}
	}	//	end of resetCheckmap

}	//	end of class
