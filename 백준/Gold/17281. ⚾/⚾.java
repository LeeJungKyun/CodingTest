/**
 * @author 이정균
 * 선수 9명, 1번 선수는 4번타자
 * 아웃: 0, 안타: 1, 2루타: 2, 3루타: 3, 홈런:4
 * 점수 계산은 4-bit 짜리 비트를 이용 (각각 left shift 횟수를 뜻함)
 * 0 0 0 0 -> 홈 1루 2루 3루 순
 * 각 선수의 결과를 알고 있을 때 가장 많은 득점을 하는 타순을 찾기 (완탐 9P8 = 약 360000)
 * 플레이어들의 순서를 정함(1번 선수 제외)
 * 
 * 1. 이닝 수 N 입력
 * 2. 이닝 수 만큼 각 타자들의 결과를 입력
 * 3. 선수들의 순서를 저장할 playerOrder의 3번 인덱스(4번타자) 에 0번인덱스(1번 선수) 고정
 * 4. 순열구하기
 * 	4-1. 순열을 다 구했다면 시뮬레이션
 * 		4-1-1. 시뮬레이션 시작시 변수 초기화 점수, 현재 타순
 * 		4-1-2. 각 이닝별로 outcount,base 초기화 4-bit
 * 		4-1-3. out 이면 outCount 증가
 * 		4-1-4. out이 아니라면 홈(0번째 비트)에 타자 추가
 * 		4-1-5. 득점 계산: 안타 수만큼 진루하면 사라질 비트
 * 		4-1-6.진루 처리
 * 		4-1-7. 4 비트만 남기기 위해서 0b1111과 AND 연산
 * 		
 * 	4-2. 4번타자는 넘기기
 * 	4-3. 1번부터 8까지 순서 정하기
 */

import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int N;
	static int[][] innings;
	static int[] playerOrder = new int[9];
	static boolean[] isPlayed = new boolean[9];
	static int maxScore = -1;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		// 1. 이닝 수 N 입력
		N = Integer.parseInt(br.readLine());

		// 2. 이닝 수 만큼 각 타자들의 결과를 입력
		innings = new int[N][9];
		for (int inning = 0; inning < N; inning++) {
			st = new StringTokenizer(br.readLine());
			for (int player = 0; player < 9; player++) {
				innings[inning][player] = Integer.parseInt(st.nextToken());
			}
		}

		// 3. 선수들의 순서를 저장할 playerOrder의 3번 인덱스(4번타자) 에 0번인덱스(1번 선수) 고정
		playerOrder[3] = 0;
		isPlayed[0] = true;

		//4. 순열 구하기
		getPermutation(0);
		
		System.out.println(maxScore);
	}

	static void getPermutation(int selectedIdx) {
		//4-1. 순열을 다 구했다면 시뮬레이션
		if (selectedIdx == 9) {
			maxScore = Math.max(maxScore, playBaseball());
			return;
		}

		//4-2. 4번타자는 넘기기
		if (selectedIdx == 3) {
			getPermutation(selectedIdx + 1);
			return;
		}

		//4-3. 1번부터 8까지 순서 정하기
		for (int i = 1; i < 9; i++) {
			if (!isPlayed[i]) {
				isPlayed[i] = true;
				playerOrder[selectedIdx] = i;
				getPermutation(selectedIdx + 1);
				isPlayed[i] = false;
			}
		}
	}

	static int playBaseball() {
		//4-1-1. 시뮬레이션 시작시 변수 초기화 점수, 현재 타순
		int score = 0;
		int batterIdx = 0;

		for (int inning = 0; inning < N; inning++) {
			//4-1-2. 각 이닝별로 outcount, base 초기화 4-bit
			int outCount = 0;
			int base = 0; // 4비트: 0000 ~ 1111

			while (outCount < 3) {
				int player = playerOrder[batterIdx];
				int result = innings[inning][player];

				//4-1-3. out 이면 outCount 증가
				if (result == 0) {
					outCount++;
				} else {
					//4-1-4. out이 아니라면 홈(0번째 비트)에 타자 추가
					base |= 1 << 0;

					//4-1-5. 득점 계산: 안타 수만큼 진루하면 사라질 비트
					int scoreBits = base >> (4 - result);
					score += Integer.bitCount(scoreBits);

					//4-1-6.진루 처리
					base <<= result;

					//4-1-7. 4 비트만 남기기 위해서 0b1111과 AND 연산
					base &= 0b1111;

				}
				batterIdx = (batterIdx + 1) % 9;
			}
		}

		return score;
	}
}
