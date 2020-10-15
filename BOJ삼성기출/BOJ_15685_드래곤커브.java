package boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15685_드래곤커브 {	
	private static int N;
	private static int[][] map;
	private static ArrayList<Integer> directionList;
	private static int[] dy = {0, -1, 0, +1};	//	우상좌하 순서
	private static int[] dx = {+1, 0, -1, 0};
	private static int[] endPoint;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());	//	드래곤 커브 개수
		map = new int[101][101];	
		
		for(int num = 0; num<N; num++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());	//	시작점 x좌표
			int y = Integer.parseInt(st.nextToken());	//	시작점 y좌표
			int d = Integer.parseInt(st.nextToken());	//	시작 방향 (0:x좌표+(우), 1:y좌표-(상), 2:x좌표-(좌), 3:y좌표+(하))
			int g = Integer.parseInt(st.nextToken());	//	세대
			directionList = new ArrayList<Integer>();
			map[y][x] = 1;
			endPoint = new int[2];
			endPoint[0] = y+dy[d];	//	다음 y좌표
			endPoint[1] = x+dx[d];	//	다음 x좌표
			map[endPoint[0]][endPoint[1]] = 1;
			directionList.add(d);
			goDragonCurve(g, d);
		}
		System.out.println(findSquare());
	}	//	end of main
	
	private static void goDragonCurve(int g, int startDirection) {
		for(int gen = 0; gen < g; gen++) {
			int directionListSize = directionList.size();
			for(int i = directionListSize-1; i>=0; i--) {
				int curD = directionList.get(i);
				int nextD = nextDirection(curD);
				directionList.add(nextD);
				// 0, 2로 시작한 경우에는 0, 2는 제대로 +/- 작용하고, 1, 3은 반대로 작용
				// 1, 3으로 시작한 경우에는 1, 3은 제대로 +/- 작용하고, 0, 2는 반대로 작용
				if(startDirection % 2 == 0) {	//	시작 방향이 0, 2
					if(nextD % 2 == 1) {
						nextD -= 2;
						if(nextD < 0) nextD += 4;
					}
				} else {	//	시작 방향이 1, 3
					if(nextD % 2 == 0) {
						nextD -= 2;
						if(nextD < 0) nextD += 4;
					}
				}
				endPoint[0] = endPoint[0] + dy[nextD];
				endPoint[1] = endPoint[1] + dx[nextD];
				map[endPoint[0]][endPoint[1]] = 1;
			}
		}
	}	//	end of goDragonCurve
	
	private static int nextDirection(int curDirection) {
		int next = curDirection - 1;
		if(next < 0) next += 4;
		return next;
	}	//	end of nextDirection
	
	private static int findSquare() {
		int result = 0;
		for(int i = 0; i<100; i++) {	//	(100, x), (y, 100) 이 좌표에선 검사를 못하므로 99까지만 하도록 체크
			for(int j = 0; j<100; j++) {
				if(map[i][j] == 1 && map[i+1][j] == 1 && map[i][j+1] == 1 && map[i+1][j+1] == 1) {
					result += 1;
				}
			}
		}
		return result;
	}	//	end of findSquare
}	//	end of class
