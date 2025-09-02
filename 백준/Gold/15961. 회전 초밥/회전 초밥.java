import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 
 * 벨트의 임의의 한 위치부터 k개의 접시를 연속해서 먹으면 할인된 가격
 * 초밥의 종류가 써진 쿠폰이 있는데, 1번 행사에 참가할 경우 이 쿠폰으로 초밥 하나를 무료로 제공.
 * 없으면 새로 만들어서 줄테니깐 걱정 ㄴㄴ
 * 가능한 다양한 종류의 초밥
 * 
 * 회전 초밥 음식점의 벨트 상태, 메뉴에 있는 초밥의 가짓수, 연속해서 먹는 접시의 개수, 쿠폰 번호가 주어짐 얼마나 다양하게 먹을 수 있는지 구하세요
 * 
 * 1. n, d, k, c 입력
 * 2. 배열 입
 * 3. maxEatCount배열에다가 내꺼 포함 4개 해서 최대 몇개 먹을 수 있는지 확인해서 i번째 값 = i 번째에서 시작해서 최대 몇개 먹을 수 있는지 저장
 * 		-> 슬라이딩 윈도우로 계산
 *
 */
public class Main {
	// 접시의 수 n, 초밥 종류 d, 연속 접시 k, 쿠폰 번호 c
	static int n, d, k, c;
	static int[] arr, maxEatCount;
	static boolean[] sushi;
	static int max = Integer.MIN_VALUE;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		// 1. n, d, k, c 입력
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		// 2. 배열 입력
		arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(br.readLine());

		// 3. maxEatCount배열에다가 내꺼 포함 4개 해서 최대 몇개 먹을 수 있는지 확인해서 i번째 값 = i 번째에서 시작해서 최대 몇개 먹을 수 있는지 저장
		maxEatCount = new int[n];
		int[] count = new int[d + 1];
		int uniqueCount = 0;

		// 초기 윈도우 처리 (i = 0 ~ k-1)
		for (int i = 0; i < k; i++) {
			if (count[arr[i]] == 0)
				uniqueCount++;
			count[arr[i]]++;
		}

		// 쿠폰 처리
		if (count[c] == 0)
			maxEatCount[0] = uniqueCount + 1;
		else
			maxEatCount[0] = uniqueCount;

		// 초기값으로 max 설정
		max = maxEatCount[0];

		// 슬라이딩 윈도우
		for (int i = 1; i < n; i++) {
			int remove = arr[(i - 1) % n];
			int add = arr[(i + k - 1) % n];

			count[remove]--;
			if (count[remove] == 0)
				uniqueCount--;

			if (count[add] == 0)
				uniqueCount++;
			count[add]++;

			if (count[c] == 0)
				maxEatCount[i] = uniqueCount + 1;
			else
				maxEatCount[i] = uniqueCount;

			max = Math.max(max, maxEatCount[i]);
		}

		System.out.println(max);
	}
}
