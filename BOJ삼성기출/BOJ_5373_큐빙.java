package boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_5373_큐빙 {
	private static int T;
	private static int N;
	private static ArrayList<char[]> orderList;
	private static int orderLength;
	private static char[][] U, D, F, B, L, R;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());	//	테스트케이스 수 <= 100
		for(int t = 1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());	//	큐브 돌릴 횟수
			//	명령이 공백을 기준으로 잘라서 들어오기 때문에 이렇게 받는다.
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			orderList = new ArrayList<char[]>();	//	명령을 저장할 리스트
			orderLength = st.countTokens();			//	명령 수 --> 사실 N이랑 같다.
			for(int i = 0; i<orderLength; i++) {
				char[] tempOrder = st.nextToken().toCharArray();
				orderList.add(tempOrder);
			}
			
			resetCube();
			
			for(int i = 0; i<orderLength; i++) {
				char[] tempOrder = orderList.get(i);
				play(tempOrder[0], tempOrder[1]);
			}
			printU();
		}	//	end of allTestCase
		
	}	//	end of main

	private static void printU() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				sb.append(U[i][j]);
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}	//	end of printU

	private static void play(char face, char direction) {
		if(face == 'U') {
			rotateU(direction);
		} else if(face == 'D') {
			rotateD(direction);
		} else if(face == 'F') {
			rotateF(direction);
		} else if(face == 'B') {
			rotateB(direction);
		} else if(face == 'L') {
			rotateL(direction);
		} else if(face == 'R') {
			rotateR(direction);
		} else {
			// 에러
		}
	}	//	end of play

	private static void rotateR(char direction) {
		if(direction == '+') {
			// R면 시계방향 회전
			char temp;
			for(int i = 0; i<2; i++) {
				temp = R[0][i];
				R[0][i] = R[2-i][0];
				R[2-i][0] = R[2][2-i];
				R[2][2-i] = R[i][2];
				R[i][2] = temp;				
			}
			// R인접면 시계방향 회전
			for(int i = 0; i<3; i++) {	//	B<-U<-F<-D<-B
				temp = B[i][0];
				B[i][0] = U[2-i][2];
				U[2-i][2] = F[2-i][2];
				F[2-i][2] = D[i][0];
				D[i][0] = temp;
			}
		} else if(direction == '-') {
			// R면 반시계방향 회전
			char temp;
			for(int i = 0; i<2; i++) {
				temp = R[0][i];
				R[0][i] = R[i][2];
				R[i][2] = R[2][2-i];
				R[2][2-i] = R[2-i][0];
				R[2-i][0] = temp;
			}
			// R인접면 반시계방향 회전
			for(int i = 0; i<3; i++) {	//	B<-D<-F<-U<-B
				temp = B[i][0];
				B[i][0] = D[i][0];
				D[i][0] = F[2-i][2];
				F[2-i][2] = U[2-i][2];
				U[2-i][2] = temp;
			}
		} else {
			// 에러
			System.out.println("rotateR중 에러 발생!!");
		}
	}	//	end of rotateR

	private static void rotateL(char direction) {
		if(direction == '+') {
			// L면 시계방향 회전
			char temp;
			for(int i = 0; i<2; i++) {
				temp = L[0][i];
				L[0][i] = L[2-i][0];
				L[2-i][0] = L[2][2-i];
				L[2][2-i] = L[i][2];
				L[i][2] = temp;				
			}
			// L인접면 시계방향 회전
			for(int i = 0; i<3; i++) {	//	F<-U<-B<-D<-F
				temp = F[i][0];
				F[i][0] = U[i][0];
				U[i][0] = B[2-i][2];
				B[2-i][2] = D[2-i][2];
				D[2-i][2] = temp;
			}
		} else if(direction == '-') {
			// L면 반시계방향 회전
			char temp;
			for(int i = 0; i<2; i++) {
				temp = L[0][i];
				L[0][i] = L[i][2];
				L[i][2] = L[2][2-i];
				L[2][2-i] = L[2-i][0];
				L[2-i][0] = temp;
			}
			// L인접면 반시계방향 회전
			for(int i = 0; i<3; i++) {	//	F<-D<-B<-U<-F
				temp = F[i][0];
				F[i][0] = D[2-i][2];
				D[2-i][2] = B[2-i][2];
				B[2-i][2] = U[i][0];
				U[i][0] = temp;
			}
		} else {
			// 에러
			System.out.println("rotateL중 에러 발생!!");
		}
	}	//	end of rotateL

	private static void rotateB(char direction) {
		if(direction == '+') {
			// B면 시계방향 회전
			char temp;
			for(int i = 0; i<2; i++) {
				temp = B[0][i];
				B[0][i] = B[2-i][0];
				B[2-i][0] = B[2][2-i];
				B[2][2-i] = B[i][2];
				B[i][2] = temp;				
			}
			// B인접면 시계방향 회전
			for(int i = 0; i<3; i++) {	//	L<-U<-R<-D<-L
				temp = L[i][0];
				L[i][0] = U[0][2-i];
				U[0][2-i] = R[2-i][2];
				R[2-i][2] = D[0][2-i];
				D[0][2-i] = temp;
			}
		} else if(direction == '-') {
			// B면 반시계방향 회전
			char temp;
			for(int i = 0; i<2; i++) {
				temp = B[0][i];
				B[0][i] = B[i][2];
				B[i][2] = B[2][2-i];
				B[2][2-i] = B[2-i][0];
				B[2-i][0] = temp;
			}
			// B인접면 반시계방향 회전
			for(int i = 0; i<3; i++) {	//	L<-D<-R<-U<-L
				temp = L[i][0];
				L[i][0] = D[0][2-i];
				D[0][2-i] = R[2-i][2];
				R[2-i][2] = U[0][2-i];
				U[0][2-i] = temp;
			}
		} else {
			// 에러
			System.out.println("rotateB중 에러 발생!!");
		}
	}	//	end of rotateB

	private static void rotateF(char direction) {
		if(direction == '+') {
			// F면 시계방향 회전
			char temp;
			for(int i = 0; i<2; i++) {
				temp = F[0][i];
				F[0][i] = F[2-i][0];
				F[2-i][0] = F[2][2-i];
				F[2][2-i] = F[i][2];
				F[i][2] = temp;				
			}
			// F인접면 시계방향 회전
			for(int i = 0; i<3; i++) {	//	R<-U<-L<-D<-R
				temp = R[i][0];
				R[i][0] = U[2][i];
				U[2][i] = L[2-i][2];
				L[2-i][2] = D[2][i];
				D[2][i] = temp;
			}
		} else if(direction == '-') {
			// F면 반시계방향 회전
			char temp;
			for(int i = 0; i<2; i++) {
				temp = F[0][i];
				F[0][i] = F[i][2];
				F[i][2] = F[2][2-i];
				F[2][2-i] = F[2-i][0];
				F[2-i][0] = temp;
			}
			// F인접면 반시계방향 회전
			for(int i = 0; i<3; i++) {	//	R<-D<-L<-U<-R
				temp = R[i][0];
				R[i][0] = D[2][i];
				D[2][i] = L[2-i][2];
				L[2-i][2] = U[2][i];
				U[2][i] = temp;
			}
		} else {
			// 에러
			System.out.println("rotateF중 에러 발생!!");
		}
	}	//	end of rotateF

	private static void rotateD(char direction) {
		if(direction == '+') {
			// D면 시계방향 회전
			char temp;
			for(int i = 0; i<2; i++) {
				temp = D[0][i];
				D[0][i] = D[2-i][0];
				D[2-i][0] = D[2][2-i];
				D[2][2-i] = D[i][2];
				D[i][2] = temp;				
			}
			// D인접면 시계방향 회전
			for(int i = 0; i<3; i++) {	//	F->R->B->L->F
				temp = F[2][i];
				F[2][i] = L[2][i];
				L[2][i] = B[2][i];
				B[2][i] = R[2][i];
				R[2][i] = temp;
			}
		} else if(direction == '-') {
			// D면 반시계방향 회전
			char temp;
			for(int i = 0; i<2; i++) {
				temp = D[0][i];
				D[0][i] = D[i][2];
				D[i][2] = D[2][2-i];
				D[2][2-i] = D[2-i][0];
				D[2-i][0] = temp;
			}
			// D인접면 반시계방향 회전
			for(int i = 0; i<3; i++) {	//	F<-R<-B<-L<-F
				temp = F[2][i];
				F[2][i] = R[2][i];
				R[2][i] = B[2][i];
				B[2][i] = L[2][i];
				L[2][i] = temp;
			}
		} else {
			// 에러
			System.out.println("rotateD중 에러 발생!!");
		}
	}	//	end of rotateD

	private static void rotateU(char direction) {
		if(direction == '+') {	//	시계 방향
			// U면 시계방향 회전
			char temp;
			for(int i = 0; i<2; i++) {
				temp = U[0][i];
				U[0][i] = U[2-i][0];
				U[2-i][0] = U[2][2-i];
				U[2][2-i] = U[i][2];
				U[i][2] = temp;				
			}
			// U인접면 시계방향 회전
			for(int i = 0; i<3; i++) {	//	F->L->B->R->F
				temp = R[0][i];
				R[0][i] = B[0][i];
				B[0][i] = L[0][i];
				L[0][i] = F[0][i];
				F[0][i] = temp;
			}
		} else if(direction == '-') {	//	반시계 방향
			// U면 반시계방향 회전
			char temp;
			for(int i = 0; i<2; i++) {
				temp = U[0][i];
				U[0][i] = U[i][2];
				U[i][2] = U[2][2-i];
				U[2][2-i] = U[2-i][0];
				U[2-i][0] = temp;
			}
			// U인접면 반시계방향 회전
			for(int i = 0; i<3; i++) {	//	F<-L<-B<-R<-F
				temp = F[0][i];
				F[0][i] = L[0][i];
				L[0][i] = B[0][i];
				B[0][i] = R[0][i];
				R[0][i] = temp;
			}
		} else {
			// 에러
			System.out.println("rotateU중 에러 발생!!");
		}
	}	//	end of rotateU

	private static void resetCube() {
		U = new char[3][3];		//	윗면(U): 흰색
		D = new char[3][3];		//	아랫면(D): 노란색
		F = new char[3][3];		//	앞면(F): 빨간색
		B = new char[3][3];		//	뒷면(B): 오렌지색
		L = new char[3][3];		//	왼쪽면(L): 초록색
		R = new char[3][3];		//	오른쪽면(R): 파란색
		
		for(int i = 0; i<3; i++) {
			Arrays.fill(U[i], 'w');
			Arrays.fill(D[i], 'y');
			Arrays.fill(F[i], 'r');
			Arrays.fill(B[i], 'o');
			Arrays.fill(L[i], 'g');
			Arrays.fill(R[i], 'b');
		}
	}	//	end of resetCube
}	//	end of class
