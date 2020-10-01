package boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503_로봇청소기 {
	private static int N, M;
	private static int[][] map;
	private static int[] dy = {-1, 0, +1, 0};	//	북, 동, 남, 서 순서
	private static int[] dx = {0, +1, 0, -1};
	private static int Answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	//	3 <= 세로 <= 50
		M = Integer.parseInt(st.nextToken());	//	3 <= 가로 <= 50
		
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		int cy = Integer.parseInt(st.nextToken());	//	로봇 현재 y좌표
		int cx = Integer.parseInt(st.nextToken());	//	로봇 현재 x좌표
		int cdir = Integer.parseInt(st.nextToken());	//	로봇 현재 방향
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		boolean end = false;
		while(!end) {
			// 1. 현재 위치 청소
			if(map[cy][cx] == 0) {
				map[cy][cx] = 5;	//	청소했음을 표시
			}
			
			// 2. 현재 위치에서 현재 방향을 기준으로 왼쪽방향대로 차례대로 탐색
			boolean next = false;
			for(int n = 1; n<=4; n++) {	//	방향이 4 방향이니까 4면을 확인하기 위해서는 이게 답
				int ndir = cdir - n;
				if(ndir < 0) ndir += 4;
				if(map[cy+dy[ndir]][cx+dx[ndir]] == 0) {	// a. 왼쪽 방향에 아직 청소 안한 공간 존재
					cdir = ndir;	//	그 방향으로 회전
					cy = cy+dy[ndir];	//	1칸 전진 이후 1번부터 반복		
					cx = cx+dx[ndir];
					next = true;		//	주변에 청소를 하러 이동하였음을 표시
					break;
				}
				// b. 왼쪽 방향에 청소할 공간이 없으면 2번 다시
			}
			
			if(!next) {	//	c. 4 방향이 모두 청소 or 벽인 경우
				int ndir = cdir + 2;
				if(ndir >= 4) ndir -= 4;
				if(map[cy+dy[ndir]][cx+dx[ndir]] == 1) {	//	d. 뒤 쪽이 벽인 경우 --> 끝내기
					end = true;
				} else {	//	뒤쪽이 벽이 아닌 경우 --> 현재 방향 유지, 1칸 후진 --> 2번 다시
					cy = cy + dy[ndir];
					cx = cx + dx[ndir];
				}
			}
		}
		Answer = 0;	//	청소한 칸의 개수 세기
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(map[i][j] == 5) {
					Answer += 1;
				}
			}
		}
		System.out.println(Answer);
	}	//	end of main
}	//	end of class
