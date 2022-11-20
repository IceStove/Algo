import java.util.ArrayList;

public class Prog_햄버거_만들기 {
    public static void main(String[] args) {
//        int[] ingredient = {2,1,1,2,3,1,2,3,1};
        int[] ingredient = {1,3,2,1,2,1,3,1,2};

        int result = solution(ingredient);
        System.out.println("result = " + result);
    }

    private static int solution(int[] ingredient) {
        int answer = 0;

        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 0; i<ingredient.length; i++) {
            list.add(ingredient[i]);
            if(list.size() >= 4 && ingredient[i] == 1) {
                int bunTop = list.get(list.size() - 1);
                int meat = list.get(list.size() - 2);
                int vegetable = list.get(list.size() - 3);
                int bunBottom = list.get(list.size() - 4);

                if(bunTop == 1 && meat == 3 && vegetable == 2 && bunBottom == 1) {
                    answer += 1;
                    for(int j = 0; j < 4; j++) {
                        list.remove(list.size()-1);
                    }
                }
            }
        }

        return answer;
    }
}
