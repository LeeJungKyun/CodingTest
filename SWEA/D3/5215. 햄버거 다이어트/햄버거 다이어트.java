/**
 * @author 이정균
 * 
 * 1. testCase 입력받고 반복문 실행
 * 2. N과 L 입력받기
 * 3. ArrayList<Ingredient>를 선언후 N만큼 반복하면서 재료 저장
 * 4. maxScore 를 최솟값으로 초기화
 * 5. 1부터 n까지 1의 개수를 바꿔가며 경우 생성
 * 6. 선택된 재료만큼 1로 표시
 * 7. do-while문 내에서 nextPermutation() 함수를 실행해서 모든 조합을 확인
 * 	7-1. 뒤에서부터 감소하는 지점(decreasingIndex - 1)을 찾음
 * 	7-2. 다음 순열이 없으면 false return
 *  7-3. decreasingIndex - 1보다 큰 값을 뒤에서부터 찾음
 *  7-4. decreasingIndex - 1과 rightIndex값 스왑
 *  7-5. decreasingIndex 부터 끝까지 오름차순 정렬
 */

import java.io.*;
import java.util.*;

public class Solution {
    static class Ingredient {
        int score, calories;
        public Ingredient(int score, int calories) {
            this.score = score;
            this.calories = calories;
        }
    }

    static int testCase, n, limit;
    static int maxScore;
    static ArrayList<Ingredient> ingredientList;
    static int[] pickedIngredient;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //1. testCase 입력받고 반복문 실행
        testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            //2. N과 L 입력받기
            
            n = Integer.parseInt(st.nextToken());
            limit = Integer.parseInt(st.nextToken());

            //3. ArrayList<Ingredient>를 선언후 N만큼 반복하면서 재료 저장
            ingredientList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int score = Integer.parseInt(st.nextToken());
                int calories = Integer.parseInt(st.nextToken());
                ingredientList.add(new Ingredient(score, calories));
            }

            //4. maxScore 를 최솟값으로 초기화
            maxScore = 0;

            //5. 1부터 n까지 1의 개수를 바꿔가며 경우 생성
            for (int ingredientCount = 1; ingredientCount <= n; ingredientCount++) {
                pickedIngredient = new int[n];
                //6. 선택된 재료만큼 1로 표시
                for (int j = n - ingredientCount; j < n; j++) {
                    pickedIngredient[j] = 1;
                }
                //7. do-while문 내에서 nextPermutation() 함수를 실행해서 모든 조합을 확인 후 계산
                do {
                    calculate();
                } while (nextPermutation());
            }
            System.out.println("#" + tc + " " + maxScore);
        }
    }

    public static void calculate() {
        int totalScore = 0;
        int totalCalories = 0;
        for (int i = 0; i < n; i++) {
            if (pickedIngredient[i] == 1) {
                totalScore += ingredientList.get(i).score;
                totalCalories += ingredientList.get(i).calories;
            }
        }

        if (totalCalories <= limit) {
            maxScore = Math.max(maxScore, totalScore);
        }
    }

    public static boolean nextPermutation() {
        int decreasingIndex = n - 1;

        // 7-1. 뒤에서부터 감소하는 지점(decreasingIndex - 1)을 찾음
        while (decreasingIndex > 0 && pickedIngredient[decreasingIndex - 1] >= pickedIngredient[decreasingIndex]) decreasingIndex--;

        // 7-2. 다음 순열이 없으면 false return
        if (decreasingIndex == 0) return false;

        // 7-3. decreasingIndex - 1보다 큰 값을 뒤에서부터 찾음
        int rightIndex = n - 1;
        while (pickedIngredient[decreasingIndex - 1] >= pickedIngredient[rightIndex])
        	rightIndex--;

        // 7-4. decreasingIndex - 1과 rightIndex값 스왑
        int temp = pickedIngredient[decreasingIndex - 1];
        pickedIngredient[decreasingIndex - 1] = pickedIngredient[rightIndex];
        pickedIngredient[rightIndex] = temp;

        // 7-5. decreasingIndex 부터 끝까지 오름차순 정렬
        Arrays.sort(pickedIngredient, decreasingIndex, n);
        return true;
    }

}
