import java.io.*;
import java.util.*;

public class Solution {
	static int testCase;
	static int k;
	static int score;
	static int[][] magnet;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		// 1. 테스트케이스 입력
		testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			// 2. 회전 횟수 K 입력
			k = Integer.parseInt(br.readLine());

			// 3. 자석 정보 입력
			magnet = new int[4][8];
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 4. 필요 변수 초기화
			score = 0;

			// 5. K개 줄에 회전 정보 입력
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken()) - 1;
				int dir = Integer.parseInt(st.nextToken());

				// 회전 방향을 저장할 배열 초기화 (1: 시계방향, -1: 반시계방향)
				int[] rotateDirs = new int[4];
				
				// 기준 자석의 회전 방향 설정
				rotateDirs[num] = dir == 1 ? 1 : -1;
				
				// 왼쪽으로 연쇄 회전 방향 계산
				// 자석의 2번 날(오른쪽)과 다음 자석의 6번 날(왼쪽) 비교
				for (int j = num - 1; j >= 0; j--) {
					if (magnet[j][2] != magnet[j + 1][6]) {
						rotateDirs[j] = -rotateDirs[j + 1];
					} else {
						break; // 극성이 같으면 연쇄 중단
					}
				}

				// 오른쪽으로 연쇄 회전 방향 계산
				// 자석의 2번 날(오른쪽)과 다음 자석의 6번 날(왼쪽) 비교
				for (int j = num + 1; j < 4; j++) {
					if (magnet[j][6] != magnet[j - 1][2]) {
						rotateDirs[j] = -rotateDirs[j - 1];
					} else {
						break; // 극성이 같으면 연쇄 중단
					}
				}

				// 모든 자석을 동시에 회전
				for (int j = 0; j < 4; j++) {
					if (rotateDirs[j] == 1) { // 시계방향
						int temp = magnet[j][7];
						for (int l = 7; l > 0; l--) {
							magnet[j][l] = magnet[j][l - 1];
						}
						magnet[j][0] = temp;
					} else if (rotateDirs[j] == -1) { // 반시계방향
						int temp = magnet[j][0];
						for (int l = 0; l < 7; l++) {
							magnet[j][l] = magnet[j][l + 1];
						}
						magnet[j][7] = temp;
					}
				}
			}

			// 모든 회전이 끝난 후 최종 점수 계산
			for (int i = 0; i < 4; i++) {
				if (magnet[i][0] == 1) {
					score += Math.pow(2, i);
				}
			}

			sb.append('#').append(tc).append(' ').append(score).append('\n');
		}
		System.out.println(sb);
	}
}