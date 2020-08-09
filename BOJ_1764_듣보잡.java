package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_1764_듣보잡 {
	private static int N;
	private static int M;
	private static String temp;
	private static ArrayList<String> AnswerList;
	private static HashSet<String> hearSet;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	//	듣도 못한 사람의 수
		M = Integer.parseInt(st.nextToken());	//	보도 못한 사람의 수
		
		hearSet = new HashSet<String>();
		AnswerList = new ArrayList<String>();
		
		for(int i = 0; i<N; i++) {
			hearSet.add(br.readLine());
		}
		
		for(int i = 0; i<M; i++) {
			temp = br.readLine();
			if(hearSet.contains(temp)) {	//	HashSet의 contains가 ArrayList의 contains보다 훨씬 빠르다.
				AnswerList.add(temp);
			}
		}
		Collections.sort(AnswerList);
		System.out.println(AnswerList.size());
		for(int i = 0; i<AnswerList.size(); i++) {
			System.out.println(AnswerList.get(i));
		}
	}
}
