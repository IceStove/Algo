package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2864_5와6의차이 {
	private static char[] Arr;
	private static char[] Brr;
	private static int Amin;
	private static int Bmin;
	private static int Amax;
	private static int Bmax;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		Arr = st.nextToken().toCharArray();
		Brr = st.nextToken().toCharArray();
		
		Amin = 0;
		Bmin = 0;
		
		Amax = 0;
		Bmax = 0;
		
		for(int i = 0; i<Arr.length; i++) {
			if(Arr[i] == '5' || Arr[i] == '6') {
				Amin += (int) (5 * Math.pow(10, Arr.length - 1 - i));
				Amax += (int) (6 * Math.pow(10, Arr.length - 1 - i));
			} else {
				Amin += (int) ((Arr[i] - '0') * Math.pow(10, Arr.length - 1 - i));
				Amax += (int) ((Arr[i] - '0') * Math.pow(10, Arr.length - 1 - i));
			}
		}
		
		for(int i = 0; i<Brr.length; i++) {
			if(Brr[i] == '5' || Brr[i] == '6') {
				Bmin += (int) (5 * Math.pow(10, Brr.length - 1 - i));
				Bmax += (int) (6 * Math.pow(10, Brr.length - 1 - i));
			} else {
				Bmin += (int) ((Brr[i] - '0') * Math.pow(10, Brr.length - 1 - i));
				Bmax += (int) ((Brr[i] - '0') * Math.pow(10, Brr.length - 1 - i));
			}
		}
		
		System.out.println((Amin + Bmin) + " " + (Amax + Bmax));
	}
}
