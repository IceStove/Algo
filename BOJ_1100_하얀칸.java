package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1100_하얀칸 {
	private static char[][] arr;
	private static int Answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new char[8][8];
		Answer = 0;
		for(int i = 0; i<8; i++) {
			arr[i] = br.readLine().toCharArray();
			for(int j = 0; j<8; j++) {
				if(i % 2 == 0) {	//	0번 칸이 흰색인 경우
					if(arr[i][j] == 'F' && j % 2 == 0) {
						Answer += 1;
					}
				} else {	//	0번 칸이 검은색인 경우
					if(arr[i][j] == 'F' && j % 2 == 1) {
						Answer += 1;
					}
				}				
			}
		}
		System.out.println(Answer);
	}
}
