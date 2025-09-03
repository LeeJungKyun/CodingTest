import java.io.*;
import java.util.*;

/**
 * @author 이정균
 *
 */

public class Solution {
	static class Ingredient{
		int score, calories;

		public Ingredient(int score, int calories) {
			this.score = score;
			this.calories = calories;
		}
	}
	static int testCase;
	static int n, l;
	static int[] dp;
	static ArrayList<Ingredient> ingredientList;
	
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			
			ingredientList = new ArrayList<>();
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int score = Integer.parseInt(st.nextToken());
				int calories = Integer.parseInt(st.nextToken());
				
				ingredientList.add(new Ingredient(score, calories));
			}
			
			dp = new int[l + 1];
			
			for(Ingredient food : ingredientList) {
				for (int cal = l; cal >= food.calories; cal--) {
			        dp[cal] = Math.max(dp[cal], dp[cal - food.calories] + food.score);
			    }
			}
			
			int maxScore = 0;
			for (int i = 0; i <= l; i++) {
			    maxScore = Math.max(maxScore, dp[i]);
			}
			
			sb.append('#').append(tc).append(' ').append(maxScore).append('\n');
		}
		System.out.println(sb);
	}
}