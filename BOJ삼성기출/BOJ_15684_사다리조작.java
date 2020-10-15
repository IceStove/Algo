package boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15684_사다리조작 {
	private static int N, M, H;
	private static int[][] map;
	private static int[][] savemap;
	private static int[][] flag;
	private static int[] data;
	private static int dataNum;
	private static int[] selected;
	private static int Answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//	2 <= 세로선의 개수 <= 10
		M = Integer.parseInt(st.nextToken());	//	0 <= 가로선의 개수 <= (N-1)*H
		H = Integer.parseInt(st.nextToken());	//	1 <= 가로선을 놓을 수 있는 위치의 개수 <= 30
		
		map = new int[H+1][N+1];
		savemap = new int[H+1][N+1];
		flag = new int[H+1][N+1];	//	사용 불가능 한 곳을 저장하기
		data = new int[310];	//	사다리 놓을 수 있는 위치 저장용
		dataNum = 0;			//	사다리 놓을 수 있는 위치의 총 개수 저장용

		selected = new int[3];	//	선택된 자리
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int tempH = Integer.parseInt(st.nextToken());
			int tempN = Integer.parseInt(st.nextToken());
			map[tempH][tempN] = 1;
			savemap[tempH][tempN] = 1;
		}
		
		FlagReset();
		DataReset();
		Answer = -1;
		for(int i = 0; i<=3; i++) {
			Comb(0, 0, i);
			if(Answer != -1) {
				break;
			}
		}
		System.out.println(Answer);
	}	//	end of main

	
	private static void Comb(int index, int count, int required) {
		if(count == required) {
			play(required);
			return;
		}
		else if(index == dataNum) {	//	다함
			return;
		}
		else if(required - count > dataNum - index) {	//	요구되는 숫자보다 남은 숫자가 적으면 펑
			return;
		} else {
			selected[count] = data[index];
			Comb(index + 1, count + 1, required);	//	선택하고 넘기기
			Comb(index + 1, count, required);		//	선택안하고 넘기기
		}
	}
	
	private static void play(int required) {
		mapReset();
		for(int i = 0; i<required; i++) {
			int tempH = selected[i] / 10;
			int tempN = selected[i] % 10;
			map[tempH][tempN] = 1;
		}
		if(Check()) {
			Answer = required;
		} 
	}
	
	private static void DataReset() {
		int num = 0;
		for(int i = 0; i<=H; i++) {
			for(int j = 0; j<N; j++) {
				if(flag[i][j] == 0) {
					data[num] = i*10 + j;	//	높이가 앞에, 줄 위치가 1의자리
					num += 1;
				}
			}
		}
		dataNum = num;		//	데이터 총 개수는 0~num-1까지 num일땐 터트릴 용도로 저장
	}
	
	private static void FlagReset() {
		for(int i = 0; i<=H; i++) {
			for(int j = 0; j<N; j++) {
				if(i == 0) {
					flag[i][j] = -1;
				} else if(j == 0) {
					flag[i][j] = -1;
				} else if(savemap[i][j] == 1) {
					flag[i][j] = -1;	//	이미 가로선이 존재
					flag[i][j-1] = -1;	//	2개의 가로선이 연속할 수 없으니 좌우에 있는 가로선도 못 놓음
					flag[i][j+1] = -1;	//	2개의 가로선이 연속할 수 없으니 좌우에 있는 가로선도 못 놓음
				}
			}
		}
	}

	private static boolean Check() {
		boolean result = true;
		for(int i = 1; i<=N; i++) {
			if(i == Adventure(i)) {
				result = true;
			} else {
				result = false;
				break;
			}
		}
		return result;
	}

	private static int Adventure(int num) {
		for(int i = 1; i<=H; i++) {
			if(map[i][num] == 1) {
				num = num + 1;
			} else if(map[i][num-1] == 1) {
				num = num - 1;
			}
		}
		return num;
	}

	private static void mapReset() {	//	지도 리셋
		for(int i = 0; i<=H; i++) {
			map[i] = Arrays.copyOf(savemap[i], savemap[i].length);
		}
	}
	
}	//	end of class
