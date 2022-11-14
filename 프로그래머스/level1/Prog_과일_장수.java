import java.util.Comparator;
import java.util.PriorityQueue;

public class Prog_과일_장수 {
    public static void main(String[] args) {
        int k = 4;
        int m = 3;
        int[] score = {4,1,2,2,4,4,4,4,1,2,4,2};

        System.out.println("최대 점수 : " + solution(k, m, score));
    }

    private static int solution(int k, int m, int[] score) {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1 > o2) {
                    return -1;
                } else if(o1 < o2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        for(int i = 0; i<score.length; i++) {
            pq.add(score[i]);
        }

        while(!pq.isEmpty()) {
            if(pq.size() < m) {
                break;
            }
            int min = k;
            for(int i = 0; i<m; i++) {
                int temp = pq.poll();
                if(min > temp)
                    min = temp;
            }
            answer += (min * m);
        }

        return answer;
    }
}
