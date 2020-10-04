package boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15683_감시 {
	private static int N, M;
	private static int[][] map;
	private static int[][] savemap;
	private static int[] dy = {-1, +1, 0, 0};
	private static int[] dx = {0, 0, -1, +1};
	private static ArrayList<int[]> cam;
	private static int Answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+2][M+2];	//	1번 좌표부터 사용할 예정
		cam = new ArrayList<int[]>();	//	{종류, y좌표, x좌표}
		
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0 && map[i][j] != 6) {	//	0:빈칸, 1~5:카메라, 6:벽
					cam.add(new int[] {map[i][j], i, j});
				}
			}
		}
		
		// 주변을 벽으로 두르기
		for(int i = 0; i<N+2; i++) {
			map[i][0] = 6;
			map[i][M+1] = 6;
		}
		for(int j = 0; j<M+2; j++) {
			map[0][j] = 6;
			map[N+1][j] = 6;
		}
		
		savemap = new int[N+2][M+2];	//	저장할 지도
		for(int i = 0; i<N+2; i++) {
			savemap[i] = Arrays.copyOf(map[i], map[i].length);
		}
		int[] dirs = new int[cam.size()];
		Answer = Integer.MAX_VALUE;
		play(0, dirs);
		System.out.println(Answer);
	}	//	end of main

	private static void play(int index, int[] dirs) {
		if(index == cam.size()) {
			// 카메라 방향이 다 설정 되었으므로, 시뮬레이션 실행
			Loadmap();	//	기본 맵으로 설정
			for(int i = 0; i<index; i++) {
				int[] temp = cam.get(i);
				int dir = dirs[i];
				Monitor(temp[1], temp[2], dir);	//	카메라 감시 돌리기
			}
			Check();	//	사각지대 체크
			return;
		}
		
		int[] temp = cam.get(index);
		if(temp[0] == 1) {
			dirs[index] = 0;
			play(index + 1, dirs);
			dirs[index] = 1;
			play(index + 1, dirs);
			dirs[index] = 2;
			play(index + 1, dirs);
			dirs[index] = 3;
			play(index + 1, dirs);
		} else if(temp[0] == 2) {
			dirs[index] = 4;
			play(index + 1, dirs);
			dirs[index] = 5;
			play(index + 1, dirs);
		} else if(temp[0] == 3) {
			dirs[index] = 6;
			play(index + 1, dirs);
			dirs[index] = 7;
			play(index + 1, dirs);
			dirs[index] = 8;
			play(index + 1, dirs);
			dirs[index] = 9;
			play(index + 1, dirs);
		} else if(temp[0] == 4) {
			dirs[index] = 10;
			play(index + 1, dirs);
			dirs[index] = 11;
			play(index + 1, dirs);
			dirs[index] = 12;
			play(index + 1, dirs);
			dirs[index] = 13;
			play(index + 1, dirs);
		} else if(temp[0] == 5) {
			dirs[index] = 14;
			play(index + 1, dirs);
		} else {
			// 나올 수 없는 경우
		}
	}	//	end of play
	
	private static void Monitor(int y, int x, int dir) {
		if(dir == 0 || dir == 1 || dir == 2 || dir == 3) {
			Cam(y, x, dir);
		} else if(dir == 4) {	//	좌우
			Cam(y, x, 2);
			Cam(y, x, 3);
		} else if(dir == 5) {	//	상하
			Cam(y, x, 0);
			Cam(y, x, 1);
		} else if(dir == 6) {	//	상우
			Cam(y, x, 0);
			Cam(y, x, 3);
		} else if(dir == 7) {	//	하우
			Cam(y, x, 1);
			Cam(y, x, 3);
		} else if(dir == 8) {	//	하좌
			Cam(y, x, 1);
			Cam(y, x, 2);
		} else if(dir == 9) {	//	상좌
			Cam(y, x, 0);
			Cam(y, x, 2);			
		} else if(dir == 10) {	//	상좌우
			Cam(y, x, 0);
			Cam(y, x, 2);
			Cam(y, x, 3);
		} else if(dir == 11) {	//	상하우
			Cam(y, x, 0);
			Cam(y, x, 1);
			Cam(y, x, 3);
		} else if(dir == 12) {	//	하좌우
			Cam(y, x, 1);
			Cam(y, x, 2);
			Cam(y, x, 3);
		} else if(dir == 13) {	//	상하좌
			Cam(y, x, 0);
			Cam(y, x, 1);
			Cam(y, x, 2);
		} else if(dir == 14) {	//	상하좌우
			Cam(y, x, 0);
			Cam(y, x, 1);
			Cam(y, x, 2);
			Cam(y, x, 3);
		}
	}
	
	private static void Cam(int y, int x, int dir) {
		int ny = y + dy[dir];
		int nx = x + dx[dir];
		
		while(map[ny][nx] != 6) {
			if(map[ny][nx] == 0) {
				map[ny][nx] = 9;	//	감시됨을 9로 표시
			}
			ny += dy[dir];
			nx += dx[dir];
		}
	}	//	end of Cam
	
	private static void Check() {
		int sum = 0;
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=M; j++) {
				if(map[i][j] == 0) {
					sum += 1;
				}
			}
		}
		if(Answer > sum) {
			Answer = sum;
		}
	}
	
	private static void Loadmap() {
		for(int i = 0; i<N+2; i++) {
			map[i] = Arrays.copyOf(savemap[i], savemap[i].length);
		}
	}
}	//	end of class