public class Prog_푸드_파이트_대회 {
    public static void main(String[] args) {
        int[] food = {1,7,1,2};
        System.out.println(solution(food));
    }

    private static String solution(int[] food) {
        StringBuilder sb = new StringBuilder();
        sb.append(0);

        for(int i = food.length-1; i>0; i--) {
            if(food[i] >= 2) {
                for(int j = 0; j < food[i]/2; j++) {
                    sb.insert(0, i);
                    sb.append(i);
                }
            }
        }

        String answer = sb.toString();

        return answer;
    }
}
