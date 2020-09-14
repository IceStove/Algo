package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class BOJ_1181_단어정렬 {
	private static int N;
	private static TreeSet<String>[] words;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		words = new TreeSet[51];
		
		for(int i = 1; i<=50; i++) {
			words[i] = new TreeSet<String>();
		}
		
		for(int i = 0; i<N; i++) {
			String temp = br.readLine();
			words[temp.length()].add(temp);
		}
		
		for(int i = 1; i<=50; i++) {
			int wordsSize = words[i].size();
			if(wordsSize > 0) {
				for(int j = 0; j<wordsSize; j++) {
					System.out.println(words[i].pollFirst());
				}
			}
		}
	}	//	end of main
}	//	end of class
