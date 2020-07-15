package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Block {
	int num;
	int t;
	int y;
	int x;
	
	public Block() {
		// TODO Auto-generated constructor stub
	}

	public Block(int num, int t, int y, int x) {
		this.num = num;
		this.t = t;
		this.y = y;
		this.x = x;
	}
	

	@Override
	public String toString() {
		return num+"번째 블록 - [" + y + "," + x + "] 타입:" + t;
	}
}

class Block2 {
	int num;
	int t;
	int y;
	int x;
	int y2;
	int x2;
	
	public Block2() {
		this.y2 = -1;
		this.x2 = -1;
	}
}

public class BOJ_19235_모노미노도미노 {
	private static int N;
	private static ArrayList<Block> BlockList1;
	private static ArrayList<Block> BlockList2;
	private static int[][] map;
	private static int PungLineNum;
	private static int Answer;
	private static int Trash;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 블록 내릴 횟수 입력
		N = Integer.parseInt(br.readLine());
		
		BlockList1 = new ArrayList<Block>();	//	초록맵에 날릴 블록 리스트
		BlockList2 = new ArrayList<Block>();	//	파란맵에 날릴 블록 리스트
		
		map = new int[7][4];
		
		for(int i = 0; i<4; i++) {
			map[6][i] = -1;
		}
		
		for(int i = 0; i<N; i++) {	//	문제는 x(행), y(열)이라고 주어졌지만,헷갈려서 y(행), x(열)로 임의 수정한다.
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			Block temp = new Block(i+1, t, y, x);
			BlockList1.add(temp);
			
			Block temp2 = new Block();
			temp2.num = i+1;
			if(t == 1) {
				temp2.t = 1;
				temp2.y = x;
				temp2.x = 3-y;
			} else if(t==2) {
				temp2.t = 3;
				temp2.y = x;
				temp2.x = 3-y;
			} else if(t==3) {
				temp2.t = 2;
				temp2.y = x;
				temp2.x = 3-(y+1);
			}
			BlockList2.add(temp2);
		}
		
//		System.out.println(BlockList1.toString());
//		System.out.println(BlockList2.toString());
		
		play();
		
		System.out.println(Answer);
		System.out.println(Trash);
		
	}	//	end of main

	private static void play() {
		for(int i = 0; i<BlockList1.size(); i++) {
			// 1. 블록 내리기
			inputBlock(BlockList1.get(i));
			
			while(true) {
				int temp_score = checkLine();
				if(temp_score != 0) {	//	점수 먹고 나서 윗줄 블록들 땅바닥까지 끌어내리기
					Answer += temp_score;
					
//					System.out.println("라인 다운 전 출력");
//					printMap();
					lineDown(1);
//					System.out.println("라인 다운 후 출력");
//					printMap();
				}else {	//	점수를 먹을 수 있는 라인이 없을 때 while문 탈출
					break;
				}
			}
			checkTop();
			checkTop();
		
		}	//	end of all BlockList1
		getTrash();
		
//		System.out.println("초록맵 출력");
//		printMap();
		
		resetMap();
		
		for(int i = 0; i<BlockList2.size(); i++) {
			inputBlock(BlockList2.get(i));
			while(true) {
				int temp_score = checkLine();
				if(temp_score != 0) {
					Answer += temp_score;
					
					lineDown(2);
				} else {
					break;
				}
			}
			checkTop();
			checkTop();
		}	//	end of all BlockList2
		getTrash();
		
//		System.out.println("파란맵 출력");
//		printMap();
	}

	private static void getTrash() {
		for(int i = 0; i<6; i++) {
			for(int j = 0; j<4; j++) {
				if(map[i][j] != 0) {
					Trash += 1;
				}
			}
		}
	}

	private static void checkTop() {
		boolean down = false;
		for(int j = 0; j<4; j++) {
			if(map[1][j] != 0) {
				down = true;
				break;
			}
		}
		
		// 1줄 내리기
		if(down) {
			for(int i = 5; i>0; i--) {
				for(int j = 0; j<4; j++) {
					map[i][j] = map[i-1][j];
					map[i-1][j] = 0;
				}
			}
		}
	}

	private static void lineDown(int list_num) {
		// 시작은 지워진 라인 윗칸부터 시작해야 함.
		int sy = PungLineNum - 1;
		
		// 현재 지도에 있는 타일, 블록 정보를 들고 있어야 한다.
		ArrayList<Integer> curBlockNumList = new ArrayList<Integer>();
		ArrayList<Block2> curBlockList = new ArrayList<Block2>();
		
		for(int i = 5; i >= 0; i--) {
			for(int j = 0; j < 4; j++) {
				if(map[i][j] != 0) {
					if(curBlockNumList.contains(map[i][j]) == true) {
						for(int k = 0; k<curBlockNumList.size(); k++) {
							if(curBlockList.get(k).num == map[i][j]) {
								Block2 temp = curBlockList.get(k);
								temp.y2 = i;
								temp.x2 = j;
								curBlockList.set(k, temp);
								break;
							}
						}
					} else {
						curBlockNumList.add(map[i][j]);
						Block2 temp = new Block2();
						temp.num = map[i][j];
						if(list_num == 1) {
							temp.t = BlockList1.get(map[i][j] - 1).t;							
						}else if(list_num == 2) {
							temp.t = BlockList2.get(map[i][j] - 1).t;														
						}
						temp.y = i;
						temp.x = j;
						curBlockList.add(temp);
					}
				}
			}
		}
		
		for(int i = 0; i<curBlockList.size(); i++) {
			Block2 temp = curBlockList.get(i);
			if(temp.t != 1) {
				if(temp.y2 == -1 && temp.x2 == -1) {
					temp.t = 1;
					curBlockList.set(i, temp);
				}
			}
		}
		
		for(int n = 0; n<curBlockList.size(); n++) {
			Block2 temp = curBlockList.get(n);
			int chai = 0;
			int start_y = temp.y;
			if(temp.t == 1) {
				int x = temp.x;
				
				for(int i = temp.y; i<6; i++) {
					if(map[i+1][x] != 0) {
						chai = i - temp.y;
						break;
					}
				}
				
				if(chai != 0) {
					map[temp.y + chai][x] = map[temp.y][x];
					map[temp.y][x] = 0;					
				}
				
//				for(int k = start_y; k>=0; k--) {
//					map[k+chai][x] = map[k][x];
//					map[k][x] = 0;
//				}
				
			} else if(temp.t == 2) {
				for(int i = temp.y; i<6; i++) {
					if(map[i+1][temp.x] != 0 || map[i+1][temp.x2] != 0) {
						chai = i - temp.y;
						break;
					}
				}
				
				if(chai != 0) {
					map[temp.y + chai][temp.x] = map[temp.y][temp.x];
					map[temp.y][temp.x] = 0;
					
					map[temp.y + chai][temp.x2] = map[temp.y][temp.x2];
					map[temp.y][temp.x2] = 0;					
				}
				
			} else if(temp.t == 3) {
				for(int i = temp.y; i<6; i++) {
					if(map[i+1][temp.x] != 0) {
						chai = i - temp.y;
						break;
					}
				}
				
				if(chai != 0) {
					map[temp.y+chai][temp.x]= map[temp.y][temp.x];
					map[temp.y][temp.x] = 0;
					
					map[temp.y2+chai][temp.x2] = map[temp.y2][temp.x2];
					map[temp.y2][temp.x2] = 0;					
				}
				
			} else {
				// 여기가 나올 일은 없다.
			}
		}	//	현재의 모든 블록들을 내려봄
		
	}

	private static int checkLine() {
		int result = 0;
		
		for(int i = 0; i<6; i++) {
			// 한 줄이 타일로 가득차서 지워야 할 때
			if(map[i][0] != 0 && map[i][1] != 0 && map[i][2] != 0 && map[i][3] != 0) {
				result += 1;	//	라인 점수 획득
				// 해당 라인 지우기
				for(int j = 0; j<4; j++) {
					map[i][j] = 0;
				}
				PungLineNum = i;	//	터진 라인 번호
			}
		}
		
		return result;	//	0 출력하면 얻은 스코어가 없음, 1이상의 값을 출력하면 얻은 스코어가 있음
	}

	private static void inputBlock(Block b) {
		int num = b.num;
		int t = b.t;
		int y = b.y;
		int x = b.x;
		
		if(t == 1) {
			for(int i = 0; i<6; i++) {
				if(map[i+1][x] != 0) {
					map[i][x] = num;
					break;
				}
			}
		} else if(t == 2) {
			for(int i = 0; i<6; i++) {
				if(map[i+1][x] != 0 || map[i+1][x+1] != 0) {
					map[i][x] = num;
					map[i][x+1] = num;
					break;
				}
			}
		} else if(t == 3) {
			for(int i = 0; i<6; i++) {
				if(map[i+1][x] != 0) {
					map[i][x] = num;
					map[i-1][x] = num;
					break;
				}
			}
		} else {
			// 이 경우는 나올 수 없음
		}
		
	}

	private static void resetMap() {
		for(int i = 0; i<6; i++) {
			for(int j = 0; j<4; j++) {
				map[i][j] = 0;
			}
		}
	}

	private static void printMap() {
		for(int i = 0; i<6; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}
	
	
}
