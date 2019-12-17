package prac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Mal{
	int y;
	int x;
	int dir;	//	1:우 / 2:좌 / 3:상 / 4:하
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getDir() {
		return dir;
	}
	public void setDir(int dir) {
		this.dir = dir;
	}
	public Mal(int y, int x, int dir) {
		super();
		this.y = y;
		this.x = x;
		this.dir = dir;
	}
	public Mal() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Mal [y=" + y + ", x=" + x + ", dir=" + dir + "]";
	}
}

public class BOJ_17780_새로운게임 {
	private static int N;
	private static int K;
	private static int[][] map;
	private static ArrayList<Integer>[][] listmap;
	private static Mal[] mal;
	private static int Answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	//	4<=체스판의 크기<=12
		K = Integer.parseInt(st.nextToken());	//	4<=말의 개수<=10
		
		map = new int[N+2][N+2];
		listmap = new ArrayList[N+2][N+2];
		mal = new Mal[K];	//	말 초기화
		
		for(int i = 0; i<=N+1; i++) {
			for(int j = 0; j<=N+1; j++) {
				listmap[i][j] = new ArrayList<Integer>();
			}
		}
		
		// 체스판 입력
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 체스판 테두리 파랑색으로 둘러싸기
		for(int i = 0; i<=N+1; i++) {
			for(int j = 0; j<=N+1; j++) {
				if(i == 0 || i == N+1 || j == 0 || j == N+1) {
					map[i][j] = 2;
				}
			}
		}
		
		// 말 입력받는 부분 만들어야함
		for(int i = 0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int cy = Integer.parseInt(st.nextToken());	//	y좌표
			int cx = Integer.parseInt(st.nextToken());	//	x좌표
			int cdir = Integer.parseInt(st.nextToken());	//	진행 방향 : 1우 2좌 3상 4하 순서
			mal[i] = new Mal(cy, cx, cdir);
			listmap[cy][cx].add(i);
		}
		/*
		System.out.println("체스판 컬러 출력");
		for(int i = 0; i<=N+1; i++) {	//	체스판 출력
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
		
		System.out.println("말 정보 출력");
		for(int i = 0; i<K; i++) {
			System.out.println(mal[i].toString());
		}
		System.out.println();
		
		System.out.println("현재 말 위치 보여주는 맵 출력");
		for(int i = 0; i<=N+1; i++) {
			for(int j = 0; j<=N+1; j++) {
				System.out.print(listmap[i][j].toString() + " ");
			}
			System.out.println();
		}
		*/
		
PLAY:	for(int n = 1; n<=1000; n++) {
			play();
			for(int i = 1; i<=N; i++) {
				for(int j = 1; j<=N; j++) {
					if(listmap[i][j].size() >= 4) {
						Answer = n;
						break PLAY;
					}
				}
			}
		}
		
		if(Answer == 0) {
			Answer = -1;
		}
		System.out.println(Answer);
		
	}

	private static void play() {
		for(int num = 0; num<K; num++) {
			int cy = mal[num].y;
			int cx = mal[num].x;
			int cdir = mal[num].dir;
			
			int ny = cy;
			int nx = cx;
			int ndir = cdir;
			
			if(listmap[cy][cx].get(0) == num) {
				if(cdir == 1) {
					ny = cy;
					nx = cx + 1;
				}else if(cdir == 2) {
					ny = cy;
					nx = cx - 1;
				}else if(cdir == 3) {
					ny = cy - 1;
					nx = cx;
				}else if(cdir == 4) {
					ny = cy + 1;
					nx = cx;
				}else {
					// 이외의 방향은 안나오는게 정상
				}
				
				if(map[ny][nx] == 0) {	//	흰색
					ndir = cdir;
					mal[num].setDir(ndir);
					for(int i = 0; i<listmap[cy][cx].size(); i++) {
						int temp = listmap[cy][cx].get(i);
						listmap[ny][nx].add(temp);
						mal[temp].setY(ny);
						mal[temp].setX(nx);
//						mal[temp].setDir(ndir);
					}
					listmap[cy][cx].clear();
				}else if(map[ny][nx] == 1) {	//	빨간색
					ndir = cdir;
					mal[num].setDir(ndir);
					for(int i = listmap[cy][cx].size() - 1; i>=0; i--) {
						int temp = listmap[cy][cx].get(i);
						listmap[ny][nx].add(temp);
						mal[temp].setY(ny);
						mal[temp].setX(nx);
//						mal[temp].setDir(ndir);
					}
					listmap[cy][cx].clear();
				}else if(map[ny][nx] == 2) {	//	 파란색
					if(cdir == 1) ndir = 2;
					else if(cdir == 2) ndir = 1;
					else if(cdir == 3) ndir = 4;
					else if(cdir == 4) ndir = 3;
					
					mal[num].setDir(ndir);
					
					int nny = ny;
					int nnx = nx;
					int nndir = ndir;
					
					if(ndir == 1) {
						nny = cy;
						nnx = cx + 1;
					}else if(ndir == 2) {
						nny = cy;
						nnx = cx - 1;
					}else if(ndir == 3) {
						nny = cy - 1;
						nnx = cx;
					}else if(ndir == 4) {
						nny = cy + 1;
						nnx = cx;
					}else {
						// 다른 방향은 안나올걸?
					}
					
					if(map[nny][nnx] == 0) {	//	파랑색 반사한 뒤 흰색
						for(int i = 0; i<listmap[cy][cx].size(); i++) {
							int temp = listmap[cy][cx].get(i);
							listmap[nny][nnx].add(temp);
							mal[temp].setY(nny);
							mal[temp].setX(nnx);
//							mal[temp].setDir(nndir);
						}
						listmap[cy][cx].clear();
					}else if(map[nny][nnx] == 1) {	//	파랑색 반사한 뒤 빨간색
						for(int i = listmap[cy][cx].size()-1; i>=0; i--) {
							int temp = listmap[cy][cx].get(i);
							listmap[nny][nnx].add(temp);
							mal[temp].setY(nny);
							mal[temp].setX(nnx);
//							mal[temp].setDir(nndir);
						}
						listmap[cy][cx].clear();
					}else if(map[nny][nnx] == 2) {	//	파랑색 반사한 뒤 파랑색
						
					}else {
						// 나오면 안되는 것
					}
					
				}else {
					// 흰색, 빨간색, 파란색을 제외한 색이 없는게 정상
				}
			}
			
		}
	}
}
