public class Prog_삼총사 {
    private static int tempAnswer;

    public static void main(String[] args) {
        int[] number = {-2, 3, 0, 2, -5};
        int[] number2 = {-3, -2, -1, 0, 1, 2, 3};
        int[] number3 = {-1, 1, -1, 1};

        int result = solution(number);
        System.out.println("result = " + result);
        int result2 = solution(number2);
        System.out.println("result2 = " + result2);
        int result3 = solution(number3);
        System.out.println("result3 = " + result3);
    }

    private static int solution(int[] number) {
        tempAnswer = 0;
        int answer = 0;

        int[] index = new int[number.length];
        boolean[] visited = new boolean[number.length];

        for(int i = 0; i<index.length; i++) {
            index[i] = i;
            visited[i] = false;
        }

        comb(number, index, visited, 0, number.length, 3);

        answer = tempAnswer;

        return answer;
    }

    private static void comb(int[] number, int[] index, boolean[] visited, int start, int n, int r) {
        if(r == 0) {
            int temp = 0;
            for(int i = 0; i<visited.length; i++) {
                if(visited[i]) {
                    temp += number[i];
                }
            }
            if(temp == 0) {
                tempAnswer += 1;
            }
            return;
        } else {
            for(int i = start; i<n; i++) {
                visited[i] = true;
                comb(number, index, visited, i+1, n, r-1);
                visited[i] = false;
            }
        }
    }
}
