import java.util.ArrayList;
import java.util.Arrays;

public class Prog_성격_유형_검사하기 {
//    2022 KAKAO TECH INTERNSHIP 문제
    public static void main(String[] args) {
//        String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
        String[] survey = {"TR", "RT", "TR"};

//        int[] choices = {5,3,2,7,5};
        int[] choices = {7,1,3};
        String result = solution(survey, choices);
        System.out.println("result = " + result);
    }

    private static String solution(String[] survey, int[] choices) {
        // choices 기준
        // 1(매우 비동의) / 2(비동의) / 3(약간 비동의) / 4(모르겠음) / 5(약간 동의) / 6(동의) / 7(매우 동의)
        String[] personality = {"R", "T", "C", "F", "J", "M", "A", "N"};
        ArrayList<String> surveyKind = new ArrayList<>(Arrays.asList(new String[]{"RT", "TR", "CF", "FC", "JM", "MJ", "AN", "NA"}));
        int[] score = new int[8];

        for(int i = 0; i<survey.length; i++) {
            int kind = surveyKind.indexOf(survey[i]);
            int tempScore = choices[i] - 4;
            switch (kind) {
                case 0:
                    if(tempScore < 0) {
                        score[0] += (tempScore * (-1));
                    } else {
                        score[1] += tempScore;
                    }
                    break;
                case 1:
                    if(tempScore < 0) {
                        score[1] += (tempScore * (-1));
                    } else {
                        score[0] += tempScore;
                    }
                    break;
                case 2:
                    if(tempScore < 0) {
                        score[2] += (tempScore * (-1));
                    } else {
                        score[3] += tempScore;
                    }
                    break;
                case 3:
                    if(tempScore < 0) {
                        score[3] += (tempScore * (-1));
                    } else {
                        score[2] += tempScore;
                    }
                    break;
                case 4:
                    if(tempScore < 0) {
                        score[4] += (tempScore * (-1));
                    } else {
                        score[5] += tempScore;
                    }
                    break;
                case 5:
                    if(tempScore < 0) {
                        score[5] += (tempScore * (-1));
                    } else {
                        score[4] += tempScore;
                    }
                    break;
                case 6:
                    if(tempScore < 0) {
                        score[6] += (tempScore * (-1));
                    } else {
                        score[7] += tempScore;
                    }
                    break;
                case 7:
                    if(tempScore < 0) {
                        score[7] += (tempScore * (-1));
                    } else {
                        score[6] += tempScore;
                    }
                    break;
                default:
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<4; i++) {
            if(score[2*i] >= score[2*i + 1]) {
                sb.append(personality[2*i]);
            } else {
                sb.append(personality[2*i+1]);
            }
        }

        String answer = sb.toString();
        return answer;
    }
}
