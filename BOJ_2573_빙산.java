package boj0109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573_빙산 {
	private static int N;
	private static int M;
	private static int[][] map;
	private static int num;
	private static int[][] checkmap;

	private static int[] dy = { -1, +1, 0, 0 };
	private static int[] dx = { 0, 0, -1, +1 };
	private static int Answer;
	private static int[][] copymap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Answer = 0;

		map = new int[N][M];	//	빙산 맵
		copymap = new int[N][M];	//	빙산 녹을 때 쓰려고 만든 복사 맵
		checkmap = new int[N][M];	//	단지 확인용 맵

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		for (int i = 0; i < N; i++) {	//	빙산 입력 잘 되었는지 확인하는 함수
//			System.out.println(Arrays.toString(map[i]));
//		}

		
		
		while(true) {
			checkPung();	//	단지 분리되었는지 확인하는 함수
//			System.out.println();
//			for(int i = 0; i<N; i++) {	//	단지 확인용 프린트
//				System.out.println(Arrays.toString(checkmap[i]));
//			}
			if(num == 0) {	//	다 녹을때까지 빙산이 분리가 되지 않으면, 0 출력
				System.out.println("0");
				break;
			}else if(num > 1) {	//	빙산이 분리되었으면 걸린 시간 출력
				System.out.println(Answer);
				break;
			}
			//	아직 다 녹지도 않았지만 빙산이 하나로 합쳐져 있는 경우 다음 액션 ㄱㄱ
			// 다시 수행
			resetCheckmap();
			setCopymap();	//	현재 맵의 빙산 유무를 수행하면서 잊지 않기 위해 저장해둠
			Melting();		//	맵의 빙산들을 녹임'
			Answer++;
		}
	}
	
	private static void Melting() {	//	테두리는 0으로 채워진다하여 따로 검사 안함
		int melt = 0;
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(map[i][j] != 0) {
					melt = 0;
					for(int dir = 0; dir<4; dir++) {
						int ny = i + dy[dir];
						int nx = j + dx[dir];
						
						
						
						if(copymap[ny][nx] == 0) {
							melt++;		//	주변에 바다가 있으면 녹는 개수 증가
						}
					}
					
					map[i][j] -= melt;
					if(map[i][j] < 0) map[i][j] = 0;	//	뺏는데 0보다 작으면 0으로 세팅					
				}
			}
		}
	}

	private static void resetCheckmap() {
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				checkmap[i][j] = 0;
			}
		}
	}

	private static void setCopymap() {
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				copymap[i][j] = map[i][j];
			}
		}
	}
	
	private static void checkPung() {
		num = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0 && checkmap[i][j] == 0) {
					num += 1;
					findDanji(i, j, num);	//	단지 번호 찾기 위한 BFS					
				}
			}
		}
	}

	private static void findDanji(int y, int x, int danjinum) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { y, x });
		checkmap[y][x] = danjinum;

		while (!q.isEmpty()) {
			int[] temp = q.poll();

			for (int dir = 0; dir < 4; dir++) {
				int ny = temp[0] + dy[dir];
				int nx = temp[1] + dx[dir];

				if (ny < 0 || ny >= N || nx < 0 || nx >= M || checkmap[ny][nx] != 0 || map[ny][nx] == 0) {
					continue;
				}

				checkmap[ny][nx] = danjinum;
				q.offer(new int[] { ny, nx });
			}
		}
	}
}
