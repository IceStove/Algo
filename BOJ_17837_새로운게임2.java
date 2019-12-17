package prac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Mal{
	int y;
	int x;
	int dir;
	
	public Mal() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Mal(int y, int x, int dir) {
		super();
		this.y = y;
		this.x = x;
		this.dir = dir;
	}
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
	@Override
	public String toString() {
		return "Mal [y=" + y + ", x=" + x + ", dir=" + dir + "]";
	}
}

public class BOJ_17837_새로운게임2 {
	private static int N;
	private static int K;
	private static int[][] map;
	private static ArrayList<Integer>[][] listmap;
	private static Mal[] mal;
	private static int Answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N+2][N+2];
		listmap = new ArrayList[N+2][N+2];
		mal = new Mal[K];
		
		for(int i = 0; i<=N+1; i++) {
			for(int j = 0; j<=N+1; j++) {
				listmap[i][j] = new ArrayList<Integer>();
			}
		}
		
		for(int i = 1; i<=N; i++) {	//	체스판 색 입력
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 파란색으로 바깥 둘러쌓기
		for(int i = 0; i<=N+1; i++) {
			for(int j = 0; j<=N+1; j++) {
				if(i == 0 || i == N+1 || j == 0 || j == N+1) {
					map[i][j] = 2;
				}
			}
		}
		
		for(int i = 0; i<K; i++) {	//	말 입력
			st = new StringTokenizer(br.readLine());
			int cy = Integer.parseInt(st.nextToken());
			int cx = Integer.parseInt(st.nextToken());
			int cdir = Integer.parseInt(st.nextToken());
			mal[i] = new Mal(cy, cx, cdir);
			listmap[cy][cx].add(i);	//	리스트 맵에 말 등록
		}
		
		
		for(int pn = 1; pn <= 1000; pn++) {
			play(pn);
			if(Answer != 0) {
				break;
			}
		}
		
		if(Answer == 0) {
			Answer = -1;
		}
		
		System.out.println(Answer);
	}

	private static void play(int pn) {
		
		for(int num = 0; num<K; num++) {
			int cy = mal[num].y;
			int cx = mal[num].x;
			int cdir = mal[num].dir;
			
			int ny = cy;
			int nx = cx;
			int ndir = cdir;
			
			int temp_num = -1;
			for(int i = 0; i<listmap[cy][cx].size(); i++) {
				if(num == listmap[cy][cx].get(i)) {	//	해당 말의 위치
//					System.out.println(num + "번째 말의 listmap에서의 위치는 " + i + "이다.");
					temp_num = i;
					break;
				}
			}
			
			if(cdir == 1) {	//	우
				ny = cy;
				nx = cx + 1;
			}else if(cdir == 2) {	//	좌
				ny = cy;
				nx = cx - 1;
			}else if(cdir == 3) {	//	상
				ny = cy - 1;
				nx = cx;
			}else if(cdir == 4) {	//	 하
				ny = cy + 1;
				nx = cx;
			}else {
				// 이 경우는 나오면 안되는 방향
			}
			
			if(map[ny][nx] == 0) {	//	흰색
				ndir = cdir;
				mal[num].setDir(ndir);
				// temp_num부터 listmap[cy][cx].size()-1까지의 말들을 이동시켜야 한다.
				for(int i = temp_num; i<listmap[cy][cx].size(); i++) {
					int temp = listmap[cy][cx].get(i);
					listmap[ny][nx].add(temp);
					mal[temp].setY(ny);
					mal[temp].setX(nx);
				}
				for(int i = listmap[cy][cx].size() - 1; i>=temp_num; i--) {
					listmap[cy][cx].remove(i);	//	계속 temp_num만 지우면 알아서 이쪽으로 줄어드니까 되겠지? 만약 안되면 역순으로 삭제 하자.
				}
				if(listmap[ny][nx].size() >= 4) {	//	이동 후에 그 칸에 말의 개수가 4개 이상이면 pn을 저장해준다.
					Answer = pn;
				}
			}else if(map[ny][nx] == 1) {	//	빨간색
				ndir = cdir;
				mal[num].setDir(ndir);
				for(int i = listmap[cy][cx].size() - 1; i>=temp_num; i--) {
					int temp = listmap[cy][cx].get(i);
					listmap[ny][nx].add(temp);
					mal[temp].setY(ny);
					mal[temp].setX(nx);
				}
				for(int i = listmap[cy][cx].size() - 1; i>=temp_num; i--) {
					listmap[cy][cx].remove(i);	//	계속 temp_num만 지우면 알아서 이쪽으로 줄어드니까 되겠지? 만약 안되면 역순으로 삭제 하자.
				}
				if(listmap[ny][nx].size() >= 4) {	//	이동 후에 그 칸에 말의 개수가 4개 이상이면 pn을 저장해준다.
					Answer = pn;
				}
			}else if(map[ny][nx] == 2) {	//	파란색
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
					// 있으면 안되는 경우
				}
				
				if(map[nny][nnx] == 0) {	//	파랑색 반사 후 흰색
					for(int i = temp_num; i<listmap[cy][cx].size(); i++) {
						int temp = listmap[cy][cx].get(i);
						listmap[nny][nnx].add(temp);
						mal[temp].setY(nny);
						mal[temp].setX(nnx);
					}
					for(int i = listmap[cy][cx].size() - 1; i>=temp_num; i--) {
						listmap[cy][cx].remove(i);	//	계속 temp_num만 지우면 알아서 이쪽으로 줄어드니까 되겠지? 만약 안되면 역순으로 삭제 하자.
					}
				}else if(map[nny][nnx] == 1) {	//	파랑색 반사 후 빨간색
					for(int i = listmap[cy][cx].size()-1; i>=temp_num; i--) {
						int temp = listmap[cy][cx].get(i);
						listmap[nny][nnx].add(temp);
						mal[temp].setY(nny);
						mal[temp].setX(nnx);
					}
					for(int i = listmap[cy][cx].size() - 1; i>=temp_num; i--) {
						listmap[cy][cx].remove(i);	//	계속 temp_num만 지우면 알아서 이쪽으로 줄어드니까 되겠지? 만약 안되면 역순으로 삭제 하자.
					}
				}else if(map[nny][nnx] == 2) {	//	파랑색 반사 후 파랑색
					// 여긴 아무것도 하지 않는 것으로 알고 있다.
				}else {
					// 이건 절대 나올 수 없겠지??
				}
				// 반사 후 이동 한 다음에 그 칸에서 말 숫자 세본다.
				if(listmap[nny][nnx].size() >= 4) {
					Answer = pn;
				}
			}else {
				// 이 색깔은 없다.
			}
		}
	}
}
