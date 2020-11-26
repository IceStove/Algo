package boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20056_마법사상어와파이어볼 {
	private static int N;
	private static int M;
	private static int K;
	private static ArrayList<int[]>[][] map;
	private static int[] dy = {-1, -1, 0, +1, +1, +1, 0, -1};
	private static int[] dx = {0, +1, +1, +1, 0, -1, -1, -1};
	private static Queue<int[]>[][] playmap;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//	4 <= 맵크기 <= 50
		M = Integer.parseInt(st.nextToken());	//	0 <= 파이어볼 수 <= N^2
		K = Integer.parseInt(st.nextToken());	//	1 <= 명령 횟수(사실상 시간) <= 1000
		
		map = new ArrayList[N+1][N+1];
		
		for(int i = 0; i<N+1; i++) {
			for(int j = 0; j<N+1; j++) {
				map[i][j] = new ArrayList<int[]>();	//	m(질량),s(속력),d(방향) 순으로 저장 
			}
		}
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());	//	y좌표
			int x = Integer.parseInt(st.nextToken());	//	x좌표
			int m = Integer.parseInt(st.nextToken());	//	1 <= 질량 <= 1000
			int s = Integer.parseInt(st.nextToken());	//	1 <= 속력 <= 1000
			int d = Integer.parseInt(st.nextToken());	//	0 <= 방향 <= 7
			map[y][x].add(new int[] {m, s, d});
		}
		
		play();
		
		// K번 명령한 후 , 남아있는 파이어볼의 질량의 합을 구하자.
		int Answer = 0;
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				for(int nth = 0; nth<map[i][j].size(); nth++) {
					int[] temp = map[i][j].get(nth);
					Answer += temp[0];
				}
			}
		}
		System.out.println(Answer);
	}	//	end of main

	private static void play() {
		int t = 0;
		//	0. 새로운 맵 생성
		playmap = new Queue[N+1][N+1];
		for(int i = 0; i<N+1; i++) {
			for(int j = 0; j<N+1; j++) {
				playmap[i][j] = new LinkedList<int[]>();
			}
		}
		while(t < K) {
			//	1. 모든 파이어볼은 자신의 방향(d)로 속력(s)만큼 이동	--> 이동하는 중에는 같은 칸에 여러 개의 파이어볼이 있을 수도 있다.
			move();
			//	2. 이동이 끝난 뒤
			pung();
			t++;
		}
		
	}	//	end of play

	private static void pung() {
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				int qSize = playmap[i][j].size();
				if(qSize == 1) {			//	1개인 경우 --> 그냥 바로 맵에 넣어준다.
					int[] temp = playmap[i][j].poll();
					map[i][j].add(temp);
				} else if(qSize >= 2) {	//	2개 이상의 파이어볼이 있는 칸에서는 다음과 같은 일이 일어난다.
					int sumM = 0;	//	질량의 합
					int sumS = 0;	//	속력의 합
					int nextD = -1;	//	다음 방향 결정용도 0 or 1이면 0,2,4,6 / 3이면 1,3,5,7
					
					//	1) 같은 칸에 있는 파이어볼은 모두 "하나"로 합쳐진다.
					while(!playmap[i][j].isEmpty()) {
						int[] temp = playmap[i][j].poll();
						sumM += temp[0];
						sumS += temp[1];
						if(nextD == -1) {	//	아직 초기일 때
							nextD = temp[2] % 2;	//	이제 여기에서 0이면 짝수, 1이면 홀수로 결정남
						} else if(nextD == 0 || nextD == 1) {
							int tempD = temp[2] % 2;
							if(nextD != tempD) {
								nextD = 3;
							}
						} else {	//	nextD == 3 일 때, 더 이상 의미 없다. --> 1,3,5,7로 다음방향 결정
							
						}
					}
					
					//	2) 파이어볼은 "4개"의 파이어볼로 나누어진다.
					//	3) 나누어진 파이어볼의 m(질량), s(속력), d(방향)은 다음과 같다.
					//		(1) m(질량) = 합쳐진 파이어볼의 질량의 합 / 5
					//		(2) s(속력) = 합쳐진 파이어볼의 속력의 합 / 합쳐진 파이어볼의 개수
					//		(3) d(방향) = 합쳐진 파이어볼의 방향이 모두 홀수 or 짝수면 0,2,4,6 아니면 1,3,5,7
					int nextM = sumM / 5;
					int nextS = sumS / qSize;
					
					//	4) 질량이 0인 파이어볼은 소멸되어 없어진다.	--> 나머지는 날린다.
					if(nextM > 0) {
						for(int nth = 0; nth < 4; nth++) {
							if(nextD == 0 || nextD == 1) {	//	모두 홀수 or 짝수
								map[i][j].add(new int[] {nextM, nextS, 2*nth});							
							} else {	//	홀수와 짝수가 섞임
								map[i][j].add(new int[] {nextM, nextS, 2*nth + 1});
							}
						}						
					}
				}
			}
		}
	}	//	end of pung

	private static void move() {
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				int nth = 0;
				int mapSize = map[i][j].size();
				while(nth < mapSize) {
					int[] temp = map[i][j].get(nth);
					int cy = i;			//	현재 y 좌표
					int cx = j;			//	현재 x 좌표
					int m = temp[0];	//	질량
					int s = temp[1];	//	속력
					int d = temp[2];	//	방향
					
					int ny = cy + dy[d] * s; 
					int nx = cx + dx[d] * s;
					
					ny = ny % N;
					nx = nx % N;
					
					if(ny <= 0) {
						ny += N;
					}
					if(nx <= 0) {
						nx += N;
					}
					
					playmap[ny][nx].add(new int[] {m, s, d});
					nth++;
				}
				map[i][j].clear();
			}
		}
	}	//	end of move
	
	
}	//	end of class
