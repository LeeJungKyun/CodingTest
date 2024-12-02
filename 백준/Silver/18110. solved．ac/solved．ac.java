import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int suggestionCount = Integer.parseInt(br.readLine());
		int[] suggestions = new int[suggestionCount];
		
		for (int i = 0; i < suggestionCount; i++) {
			suggestions[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(suggestions);
		int cutAmount = (int) Math.round(suggestionCount * 0.15);
		
		int sum = 0;
		for (int i = 0; i < suggestionCount; i++) {
			if (i < cutAmount || i > suggestionCount - 1 - cutAmount) {
				continue;
			}
			sum += suggestions[i];
		}
		
		int result = (int) Math.round((double) sum / (suggestionCount - cutAmount * 2));
		System.out.println(result);
	}
}