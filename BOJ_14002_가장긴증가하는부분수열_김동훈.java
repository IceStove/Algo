package prac;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ_14002_가장긴증가하는부분수열_김동훈 {
	private static int N;
	private static int[] arr;
	private static int[] D;
	private static int Answer;
	private static int cur;
	private static int num;
	private static ArrayList<Integer> list;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		arr = new int[N];
		D = new int[N];
		Answer = Integer.MIN_VALUE;
		cur = 0;	//	i 위치 이용하려고 씀
		num = 0;	//	모르겠음 얘는
		list = new ArrayList<Integer>();
		for(int i = 0; i<N; i++) {
			arr[i] = sc.nextInt();
			D[i] = 1;
		}
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<i; j++) {
				if(arr[i]>arr[j] && D[i] < D[j] + 1) {
					D[i] = D[j] + 1;
				}
			}
		}
		for(int i = 0; i<N; i++) {
			if(Answer < D[i]) {
				Answer = D[i];
				cur = i;			//	D[i]가 최대일 때의 위치 저장
				num = D[i];
			}
		}
		System.out.println(Answer);
		list.add(arr[cur]);	//	정답 위치부터 추가하고 시작
		while(num != 1) {
			for(int i = cur; i >= 0; i--) {
				if(D[i] == num-1 && arr[i] < arr[cur]) {
					list.add(arr[i]);
					cur = i;
					num -= 1;
					break;
				}
			}
		}
		
		Collections.sort(list);
		
		for(int i = 0; i<list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println();
	}
}
