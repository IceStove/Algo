package prac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17136_색종이붙이기 {
	private static int[][] map;
	private static int[] paper;
	private static int Answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[10][10];
		paper = new int[6];
		for(int i=0; i<10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j<10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 1; i<=5; i++) {
			paper[i] = 5;
		}
		
//		for(int i = 0; i<10; i++) {	//	잘 입력 되었는지 확인용
//			System.out.println(Arrays.toString(map[i]));
//		}
		Answer = 100;
		play(0);
		
		if(Answer == 100) {
			Answer = -1;
		}
		
		System.out.println(Answer);
		
	}

	private static void play(int key) {
		boolean pung = true;
		Checker:
		for(int i = 0; i<10; i++) {
			for(int j = 0; j<10; j++) {
				if(map[i][j] == 1) {
					pung = false;
					break Checker;
				}
			}
		}
		if(pung) {	//	조건
			int temp = 25;
			for(int i = 1; i<=5; i++) {
				temp -= paper[i];
			}
			if(Answer > temp) {
				Answer = temp;
			}
			return;
		}
		
		int y = key / 10;
		int x = key % 10;
		
		if(map[y][x] == 0) {	//	해당 위치에 붙일 수 없으면 다음으로 넘기기
			play(key + 1);	
		}else {	//	붙일 수 있는 곳이면, 붙이기
			for(int num = 1; num<=5; num++) {
				int go = paint(y, x, num);
				if(go == 1)	{	//	종이가 붙여지면 다음으로 보내고, 붙였던 종이를 다시 뗀다.
					play(key + num);
					erase(y, x, num);
				}
			}
		}
	}
	
	private static int paint(int y, int x, int num) {	//	num * num 종이 붙일 때
		int result = -1;
		if(paper[num] > 0 && y+num <= 10 && x+num <= 10) {
			boolean pass = true;
			Checker:
			for(int i = y; i<y+num; i++) {
				for(int j = x; j<x+num; j++) {
					if(map[i][j] == 0) {
						pass = false;
						break Checker;
					}
				}
			}
			if(pass) {	//	조건에 부합하면 붙이고, 해당 종이 갯수 -1;
				for(int i = y; i<y+num; i++) {
					for(int j = x; j<x+num; j++) {
						map[i][j] = 0;
					}
				}
				paper[num] -= 1;
				result = 1;
			}
		}
		return result;
	}
	
	private static void erase(int y, int x, int num) {	//	num * num 종이 뗄 때
		if(paper[num] < 5 && y+num <= 10 && x+num <= 10) {
			boolean pass = true;
			Checker:
			for(int i = y; i<y+num; i++) {
				for(int j = x; j<x+num; j++) {
					if(map[i][j] == 1) {
						pass = false;
						break Checker;
					}
				}
			}
			if(pass) {	//	조건에 부합하면 떼고, 해당 종이 갯수 +1;
				for(int i = y; i<y+num; i++) {
					for(int j = x; j<x+num; j++) {
						map[i][j] = 1;
					}
				}
				paper[num] += 1;
			}
		}
	}
	
	
}
