package prac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1946_신입사원2 {
	private static int T;
	private static int N;
	private static int Answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			Answer = N;
			PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[0] - o2[0];
				}
			});
			
			for(int i = 0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				
				pq.offer(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
			}
			
			int[] fm = pq.poll();
			for(int i = 1; i<N; i++) {
				int[] temp = pq.poll();
				
				if(fm[1] > temp[1]) {
					fm = temp;
				}else {
					Answer -= 1;
				}
			}
			
			System.out.println(Answer);
			
		}	//	end of allTestcase
	}	//	end of main
}	//	end of class
