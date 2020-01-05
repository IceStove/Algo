package prac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_12764_싸지방에간준하 {
	private static int N;
	private static PriorityQueue<int[]> pq;
	private static int[] ua;
	private static int[] pa;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]) {
					return o1[1] - o2[1];
				}else {
					
					return o1[0] - o2[0];
				}
			}
		});
		
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int[] temp = new int[2];
			temp[0] = Integer.parseInt(st.nextToken());
			temp[1] = Integer.parseInt(st.nextToken());
			
			pq.offer(temp);
		}
		
//		for(int i = 0; i<N; i++) {	//	잘 입력되었는지 확인
//			int[] temp = pq.poll();
//			System.out.println(Arrays.toString(temp));
//		}
		
		ua = new int[N];
		pa = new int[N];
		
		int[] temp = pq.poll();
		
		ua[0] = temp[1];
		pa[0] = 1;
		int coms = 1;
		
		for(int n = 0; n<N-1; n++) {
			temp = pq.poll();
			for(int i = 0; i<ua.length; i++) {
				if(ua[i] < temp[0]) {
					if(ua[i] == 0) coms++;
					ua[i] = temp[1];
					pa[i] += 1;
					break;
				}
			}
		}
		
		System.out.println(coms);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i<coms; i++) {
//			System.out.print(pa[i] + " ");
			sb.append(pa[i]).append(' ');
		}
		System.out.println(sb);
	}
}
