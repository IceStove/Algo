import java.util.Arrays;

public class Prog_옹알이2 {
    private static boolean[] check;
    private static String[] can;

    public static void main(String[] args) {

        String[] babbling = {"aya", "yee", "u", "maa"};
        String[] babbling2 = {"ayaye", "uuu", "yeye", "yemawoo", "ayaayaa"};

        int result = solution(babbling);
        int result2 = solution(babbling2);
        System.out.println("result = " + result);
        System.out.println("result2 = " + result2);
    }

    private static int solution(String[] babbling) {
        can = new String[]{"aya", "ye", "woo", "ma"};
        int answer = 0;
        check = new boolean[babbling.length];
        Arrays.fill(check, false);
        int prev = -1;

        for(int idx = 0; idx < babbling.length; idx++) {
            tryBabbling(babbling[idx], prev, idx);
        }

        for(int i = 0; i< check.length; i++) {
            if(check[i] == true) answer += 1;
        }

        return answer;
    }

    private static void tryBabbling(String s, int prev, int idx) {
        if(check[idx] == true) {
            return;
        }
        if(s.length() == 0) {   //  모든 글자 일치여부 확인 끝
            check[idx] = true;
            return;
        }
        for(int i = 0; i<can.length; i++) {
            if(prev == i) {
                // 패스
            } else {
                if(s.indexOf(can[i]) == 0) {    // 앞글자부터 비교하고 같은지 여부 판단하고, s의 길이도 봐야함
                    // 같으면, tryBabbling(나머지s, i, idx);
                    char[] sCharArray = s.toCharArray();
                    StringBuilder sb = new StringBuilder();
                    for(int j = can[i].length(); j<sCharArray.length; j++) {
                        sb.append(sCharArray[j]);
                    }
                    tryBabbling(sb.toString(), i, idx);
                }
                // 다르면 패스
            }
        }

    }
}
