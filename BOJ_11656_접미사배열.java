package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_11656_접미사배열 {
	private static char[] arr;
	private static ArrayList<String> strList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		arr = br.readLine().toCharArray();
		strList = new ArrayList<String>();
		String temp = "";
		for(int i = arr.length - 1; i >= 0; i--) {
			StringBuilder sb = new StringBuilder();
			sb.append(arr[i]);
			sb.append(temp);
			temp = sb.toString();
			strList.add(temp);
		}
		
		Collections.sort(strList);
		
		for(int i = 0; i<strList.size(); i++) {
			System.out.println(strList.get(i));
		}
	}
}
