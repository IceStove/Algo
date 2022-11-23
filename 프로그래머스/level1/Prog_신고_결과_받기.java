import java.util.ArrayList;
import java.util.Arrays;

public class Prog_신고_결과_받기 {
    // 2022 KAKAO BLIND RECRUITMENT 문제
    public static void main(String[] args) {
//        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] id_list = {"con", "ryan"};
//        String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        String[] report = {"ryan con", "ryan con", "ryan con", "ryan con"};
        int k = 2;
        int[] result = solution(id_list, report, k);
        System.out.println("result = " + Arrays.toString(result));
    }

    private static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        int[] score = new int[id_list.length];
        ArrayList<String> idList = new ArrayList<>();
        ArrayList[] lists = new ArrayList[id_list.length];
        for(int i = 0; i < id_list.length; i++) {
            idList.add(id_list[i]);
            lists[i] = new ArrayList<String>();
        }

        for(int i = 0; i<report.length; i++) {
            String[] temp = report[i].split(" ");
            int idx = idList.indexOf(temp[0]);
            if(lists[idx].indexOf(temp[1]) == -1) {
                lists[idx].add(temp[1]);
                score[idList.indexOf(temp[1])] += 1;
            }
        }

        for(int i = 0; i<id_list.length; i++) {
            if(score[i] >= k) {
                for(int j = 0; j< id_list.length; j++) {
                    String killed = idList.get(i);
                    if(lists[j].indexOf(killed) != -1) {
                        answer[j] += 1;
                    }
                }
            }
        }

        return answer;
    }
}
