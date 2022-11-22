public class Prog_숫자_짝꿍 {
    public static void main(String[] args) {
        // 두 정수 X, Y 의 임의의 자리에서 나타나는 정수 k(0 <= k <= 9)들을 이용하여 만들 수 있는 가장 큰 정수를 두 수의 짝꿍
        String X = "5525";
        String Y = "1255";

        String result = solution(X, Y);
        System.out.println("result = " + result);
    }

    private static String solution(String X, String Y) {
        char[] xCharArray = X.toCharArray();
        char[] yCharArray = Y.toCharArray();

        int[] xNum = new int[10];
        int[] yNum = new int[10];

        for(int i = 0; i<xCharArray.length; i++) {
            int idx = (int)(xCharArray[i] - '0');
            xNum[idx] += 1;
        }

        for(int i = 0; i< yCharArray.length; i++) {
            int idx = (int)(yCharArray[i] - '0');
            yNum[idx] += 1;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 9; i >= 0; i--) {
            if(xNum[i] > 0 && yNum[i] > 0) {
                int nth = Math.min(xNum[i], yNum[i]);
                for(int j = 0; j<nth; j++) {
                    sb.append(i);
                }
            }
        }
        String answer = sb.toString();

        if(answer.startsWith("0")) {
            answer = "0";
        }

        if(answer.length() == 0) {
            answer = "-1";
        }

        return answer;
    }
}
