package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Fish {
	int y;
	int x;
	int dir;
	int live;
	@Override
	public String toString() {
		return "["+y+","+x+"] " + "dir:"+dir + " live:"+live;
	}
}
class Shark {
	int y;
	int x;
	int dir;
	int score;
	@Override
	public String toString() {
		return "["+y+","+x+"] " + "dir:"+dir + " score:"+score;
	}
}

public class BOJ_19236_청소년상어 {
	private static int[][] map;
	private static int[][] dirmap;

//	제자리, 상, 상좌, 좌, 하좌, 하, 하우, 우, 상우
	private static int[] dy = {0, -1, -1, 0, +1, +1, +1, 0, -1};
	private static int[] dx = {0, 0, -1, -1, -1, 0, +1, +1, +1};
	private static Fish[] fish;
	private static Shark shark;
	private static int Answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Answer = 0;
		map = new int[4][4];
		dirmap = new int[4][4];
		fish = new Fish[17];	//	물고기들 1~16
		for(int i = 0; i<17; i++) {
			fish[i] = new Fish();
		}
		shark = new Shark();
//		상어 기본값 지정
		shark.y = -1;
		shark.x = -1;
		shark.dir = 0;
		shark.score = 0;
		
		for(int i = 0; i<4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j<8; j++) {
				if(j % 2 == 0) {
					int fish_num = Integer.parseInt(st.nextToken()); 
					map[i][j/2] = fish_num;
					fish[fish_num].y = i;
					fish[fish_num].x = j/2;
					fish[fish_num].live = 1;
				} else {
					int fish_dir = Integer.parseInt(st.nextToken());
					dirmap[i][j/2] = fish_dir;
					fish[map[i][j/2]].dir = fish_dir;
				}
			}
		}
		// 입력 잘 되었는지 확인용
//		for(int i = 0; i<map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
//		System.out.println();
//		for(int i = 0; i<dirmap.length; i++) {
//			System.out.println(Arrays.toString(dirmap[i]));
//		}
		
		// 상어 시작 (0,0)에 위치한 물고기를 잡아먹고, 방향성 & 점수 획득 후 시작
		int start_num = map[0][0];
		shark.y = fish[start_num].y;
		shark.x = fish[start_num].x;
		shark.dir = fish[start_num].dir;
		shark.score += start_num;
		map[0][0] = 17;	//	상어는 17번
		fish[start_num].live = 0;	//	상어가 잡아먹었으니 사망처리
		
		move();	//	물고기 이동
		// 이동 잘 되는지 확인용
//		for(int i = 0; i<map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		play(shark);
		System.out.println(Answer);
	}

	private static void play(Shark cur_shark) {
		if(endpoint(cur_shark) == true) {	//	상어가 더 이상 움직일 수 없을 때
			if(cur_shark.score > Answer) {
				Answer = cur_shark.score;
			}
			return;
		}
		// 현재의 값(상어, 물고기, 지도)을 모두 저장
		Shark temp_shark = new Shark();
		temp_shark.y = cur_shark.y;
		temp_shark.x = cur_shark.x;
		temp_shark.dir = cur_shark.dir;
		temp_shark.score = cur_shark.score;
		
		Fish[] temp_fish = new Fish[17];
		for(int i = 0; i<17; i++) {
			temp_fish[i] = new Fish();
			temp_fish[i].y = fish[i].y;
			temp_fish[i].x = fish[i].x;
			temp_fish[i].dir = fish[i].dir;
			temp_fish[i].live = fish[i].live;
		}
		int[][] temp_map = new int[4][4];
		for(int i = 0; i<4; i++) {
			for(int j = 0; j<4; j++) {
				temp_map[i][j] = map[i][j];
			}
		}
	
		int cy = cur_shark.y;
		int cx = cur_shark.x;
		int cdir = cur_shark.dir;
		// 상어 사냥 시작
		for(int i = 1; i<4; i++) {
			int ny = cy + i*dy[cdir];
			int nx = cx + i*dx[cdir];
			
			if(ny < 0 || ny >= 4 || nx < 0 || nx >= 4 || map[ny][nx] == 0 || map[ny][nx] == 17) {
				continue;
			}
			
			if(map[ny][nx] > 0 && map[ny][nx] < 17) {
				// 잡아 먹고
				int next = map[ny][nx];
				fish[next].live = 0;
				shark.y = fish[next].y;
				shark.x = fish[next].x;
				shark.dir = fish[next].dir;
				shark.score += next;
				map[ny][nx] = 17;
				map[cy][cx] = 0; 
				// 물고기 이동
				move();
				play(shark);
				
				// 되돌리기를 시전
				shark.y = temp_shark.y;
				shark.x = temp_shark.x;
				shark.dir = temp_shark.dir;
				shark.score = temp_shark.score;
				
				for(int num = 0; num<17; num++) {
					fish[num].y = temp_fish[num].y;
					fish[num].x = temp_fish[num].x;
					fish[num].dir = temp_fish[num].dir;
					fish[num].live = temp_fish[num].live;
				}
				for(int m = 0; m<4; m++) {
					for(int n = 0; n < 4; n++) {
						map[m][n] = temp_map[m][n];
					}
				}
			}
		}
		
	}

	private static boolean endpoint(Shark cur_shark) {
		boolean result = true;
		
		int cy = cur_shark.y;
		int cx = cur_shark.x;
		int cdir = cur_shark.dir;
		
		for(int i = 1; i<4; i++) {
			int ny = cy + i*dy[cdir];
			int nx = cx + i*dx[cdir];
			
			// 경계값 밖이거나 빈칸 혹은 자기 자신일 때는 의미 없음
			if(ny < 0 || ny >= 4 || nx < 0 || nx >= 4 || map[ny][nx] == 0 || map[ny][nx] == 17) {
				continue;
			}
			
			// 경계값 밖도 아니고, 이동가능한 위치에 물고기가 있을 때
			if(map[ny][nx] > 0 && map[ny][nx] < 17) {
				result = false;
				break;
			}
		}
		return result;
	}

	private static void move() {
		for(int num=1; num<fish.length; num++) {
			if(fish[num].live == 1) {
				// 산 물고기
				int cy = fish[num].y;
				int cx = fish[num].x;
				int cdir = fish[num].dir;
				int ndir = cdir;
				for(int i = 0; i<8; i++) {
					int ny = cy + dy[ndir];
					int nx = cx + dx[ndir];
					
					// 이동할 수 없는 칸인 경우 반시계 방향으로 45도 회전
					if(ny < 0 || ny >= 4 || nx < 0 || nx >=4 || map[ny][nx] == 17) {
						ndir = ndir + 1;
						if(ndir == 9) ndir = 1;
						continue;
					}
					
					// 빈 칸인 경우 --> 해당 위치로 이동하고 for문 탈출
					if(map[ny][nx] == 0) {
						fish[num].y = ny;
						fish[num].x = nx;
						fish[num].dir = ndir;
						map[ny][nx] = num;
						map[cy][cx] = 0;
						break;
					}
					
					// 다른 물고기가 있는 경우 --> 해당 위치의 물고기와 swap하고 for문 탈출
					if(map[ny][nx] > 0 && map[ny][nx] < 17) {
						int temp = map[ny][nx];
						// 해당 위치로 현재 위치의 물고기 이동
						fish[num].y = ny;
						fish[num].x = nx;
						fish[num].dir = ndir;
						map[ny][nx] = num;
						
						// 해당 위치에 있던 물고기를 현재 위치로 이동
						fish[temp].y = cy;
						fish[temp].x = cx;
						map[cy][cx] = temp;
						break;
					}	
				}
			} else {
				// 죽은 물고기
			}
			
		}
	}
}
