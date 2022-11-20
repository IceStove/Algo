public class Prog_기사단원의_무기 {
    public static void main(String[] args) {
        int number = 10;
        int limit = 3;
        int power = 2;

        int result = solution(number, limit, power);
        System.out.println("result = " + result);
    }

    private static int solution(int number, int limit, int power) {
        int answer = 0;
        for(int i = 1; i<=number; i++) {
            int tempResult = findDivisor(i);
            if(tempResult > limit) {
                tempResult = power;
            }
            answer += tempResult;
        }
        return answer;
    }

    private static int findDivisor(int num) {
        int result = 0;

        for(int i = 1; i * i <= num; i++) {
            if(num % i == 0) {
                result += 1;
                if(i * i < num) {
                    result += 1;
                }
            }
        }

        return result;
    }
}
